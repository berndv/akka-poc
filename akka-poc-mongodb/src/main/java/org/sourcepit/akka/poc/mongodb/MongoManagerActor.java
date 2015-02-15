/*
 * Copyright 2015 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.akka.poc.mongodb;

import akka.actor.Props;
import akka.actor.UntypedActor;

import com.mongodb.DBObject;

public class MongoManagerActor extends UntypedActor {
   @Override
   public void onReceive(Object msg) throws Exception {
      System.out.println(msg);

      // { "users": { "find": [ { "age": { "$gt": 18 } }, { "name": 1, "address": 1 } ] } }
      // vs.
      // { "collection": "users", "method": "find", "criteria": { "age": { "$gt": 18 } }, "projection": { "name": 1,
      // "address": 1 } }

      if (msg instanceof DBObject) {
         final DBObject clientDef = (DBObject) msg;
         getContext().system().actorOf(Props.create(MongoClientActor.class, clientDef));
      }
   }

}