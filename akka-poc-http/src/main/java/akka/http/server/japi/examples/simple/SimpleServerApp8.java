// CSOFF
/*
 * Copyright (C) 2009-2014 Typesafe Inc. <http://www.typesafe.com>
 */
// CSON

package akka.http.server.japi.examples.simple;

import java.io.IOException;

import akka.actor.ActorSystem;
import akka.http.server.japi.Handler;
import akka.http.server.japi.Handler2;
import akka.http.server.japi.HttpApp;
import akka.http.server.japi.Parameter;
import akka.http.server.japi.Parameters;
import akka.http.server.japi.PathMatcher;
import akka.http.server.japi.PathMatchers;
import akka.http.server.japi.RequestContext;
import akka.http.server.japi.Route;
import akka.http.server.japi.RouteResult;

public class SimpleServerApp8 extends HttpApp {
   private static Parameter<Integer> x = Parameters.integer("x");
   private static Parameter<Integer> y = Parameters.integer("y");

   private static PathMatcher<Integer> xSegment = PathMatchers.integerNumber();
   private static PathMatcher<Integer> ySegment = PathMatchers.integerNumber();

   public static RouteResult multiply(RequestContext ctx, int x, int y) {
      int result = x * y;
      return ctx.complete(String.format("%d * %d = %d", x, y, result));
   }

   /**
    */
   private static class Test {
      private final int constant;

      Test(int constant) {
         this.constant = constant;
      }

      private RouteResult constantPlusMultiply(RequestContext ctx, int x, int y) {
         int result = x * y + constant;
         return ctx.complete(String.format("%d * %d + %d = %d", x, y, constant, result));
      }
   }

   public void test() {
      handleWith(xSegment, ySegment, SimpleServerApp8::multiply);
   }

   @Override
   public Route createRoute() {
      Handler addHandler = new Handler() {
         @Override
         public RouteResult handle(RequestContext ctx) {
            int xVal = x.get(ctx);
            int yVal = y.get(ctx);
            int result = xVal + yVal;
            return ctx.complete(String.format("%d + %d = %d", xVal, yVal, result));
         }
      };
      Handler2<Integer, Integer> subtractHandler = new Handler2<Integer, Integer>() {
         public RouteResult handle(RequestContext ctx, Integer xVal, Integer yVal) {
            int result = xVal - yVal;
            return ctx.complete(String.format("%d - %d = %d", xVal, yVal, result));
         }
      };

      return route(
         // matches the empty path
         pathSingleSlash().route(getFromResource("web/calculator.html")),
         // matches paths like this: /add?x=42&y=23
         path("add").route(handleWith(addHandler, x, y)),
         path("subtract").route(handleWith(x, y, subtractHandler)),
         path("divide").route(handleWith(x, y, (ctx, x, y) -> ctx.complete(String.format("%d / %d = %d", x, y, x / y)))),
         // matches paths like this: /multiply/{x}/{y}
         path("multiply", xSegment, ySegment).route(
         // bind handler by reflection
            handleWith(xSegment, ySegment, SimpleServerApp8::multiply)),
         path("multiply-methodref", xSegment, ySegment).route(
         // bind handler by reflection
            handleWith(xSegment, ySegment, new Test(123)::constantPlusMultiply)));
   }

   public static void main(String[] args) throws IOException {
      ActorSystem system = ActorSystem.create();
      new SimpleServerApp8().bindRoute("localhost", 8080, system);
      System.out.println("Type RETURN to exit");
      System.in.read();
      system.shutdown();
   }
}
