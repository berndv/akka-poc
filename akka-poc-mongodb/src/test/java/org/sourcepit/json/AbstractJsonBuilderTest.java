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

package org.sourcepit.json;

import static java.lang.Character.isWhitespace;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;
import org.sourcepit.json.JsonBuilder;

public abstract class AbstractJsonBuilderTest<JsonObject, JsonArray>
{
   protected abstract JsonBuilder<JsonObject, JsonArray> newJsonBuilder();

   @Test
   public void testEmptyObject()
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject().endObject();
      assertEquals("{}", normalize(object.toString()));
   }

   @Test
   public void testBigIntegerField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("big")
         .setTo(BigInteger.valueOf(42))
         .withField("null")
         .setTo((BigInteger) null)
         .endObject();
      assertEquals("{'big':42,'null':null}", normalize(object.toString()));
   }

   @Test
   public void testBigDecimalField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("big")
         .setTo(BigDecimal.valueOf(42))
         .withField("null")
         .setTo((BigDecimal) null)
         .endObject();
      assertEquals("{'big':42,'null':null}", normalize(object.toString()));
   }

   @Test
   public void testDoubleField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("big")
         .setTo(Double.valueOf(Double.MAX_VALUE))
         .withField("little")
         .setTo(Double.MAX_VALUE)
         .withField("null")
         .setTo((Double) null)
         .endObject();
      assertEquals("{'big':1.7976931348623157E308,'little':1.7976931348623157E308,'null':null}",
         normalize(object.toString()));
   }

   @Test
   public void testFloatField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("big")
         .setTo(Float.valueOf(Float.MAX_VALUE))
         .withField("little")
         .setTo(Float.MAX_VALUE)
         .withField("null")
         .setTo((Float) null)
         .endObject();
      assertEquals("{'big':3.4028235E38,'little':3.4028234663852886E38,'null':null}", normalize(object.toString()));
   }

   @Test
   public void testIntegerField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("big")
         .setTo(Integer.valueOf(Integer.MAX_VALUE))
         .withField("little")
         .setTo(Integer.MAX_VALUE)
         .withField("null")
         .setTo((Integer) null)
         .endObject();
      assertEquals("{'big':2147483647,'little':2147483647,'null':null}", normalize(object.toString()));
   }

   @Test
   public void testLongField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("big")
         .setTo(Long.valueOf(Long.MAX_VALUE))
         .withField("little")
         .setTo(Long.MAX_VALUE)
         .withField("null")
         .setTo((Long) null)
         .endObject();
      assertEquals("{'big':9223372036854775807,'little':9.223372036854776E18,'null':null}",
         normalize(object.toString()));
   }


   @Test
   public void testShortField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("big")
         .setTo(Short.valueOf(Short.MAX_VALUE))
         .withField("little")
         .setTo(Short.MAX_VALUE)
         .withField("null")
         .setTo((Short) null)
         .endObject();
      assertEquals("{'big':32767,'little':32767.0,'null':null}", normalize(object.toString()));
   }

   @Test
   public void testStringField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("big")
         .setTo("string")
         .withField("null")
         .setTo((String) null)
         .endObject();
      assertEquals("{'big':'string','null':null}", normalize(object.toString()));
   }

   @Test
   public void testComplexField() throws Exception
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.beginObject()
         .withField("foo")
         .setToObject()
         .withField("bar")
         .setTo(74)
         .endObject()
         .endObject();
      assertEquals("{'foo':{'bar':74}}", normalize(object.toString()));
   }

   @Test
   public void testEmptyArray()
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray().endArray();
      assertEquals("[]", normalize(array.toString()));
   }

   @Test
   public void testSimpleArray()
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray().add(1).add(2).add(3).endArray();
      assertEquals("[1,2,3]", normalize(array.toString()));
   }

   @Test
   public void testComplexArray()
   {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.beginArray()
         .addObject()
         .withField("foo")
         .setTo(1)
         .endObject()
         .addObject()
         .withField("bar")
         .setTo(2)
         .endObject()
         .endArray();
      assertEquals("[{'foo':1},{'bar':2}]", normalize(array.toString()));
   }

   private static String normalize(String str)
   {
      final StringBuilder sb = new StringBuilder();
      for (char ch : str.toCharArray())
      {
         if (!isWhitespace(ch))
         {
            sb.append(ch == '\"' ? '\'' : ch);
         }
      }
      return sb.toString();
   }

}
