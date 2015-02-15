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

public abstract class AbstractJsonBuilderTest<JsonObject, JsonArray> {
   protected abstract JsonBuilder<JsonObject, JsonArray> newJsonBuilder();

   protected abstract String arrayToString(JsonArray array);

   protected abstract String objectToString(JsonObject object);

   @Test
   public void testEmptyObject() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject().closeObject();
      assertEquals("{}", normalize(objectToString(object)));
   }

   @Test
   public void testNullField() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject().setField("foo").toNull().closeObject();
      assertEquals("{'foo':null}", normalize(objectToString(object)));
   }

   @Test
   public void testBigIntegerField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("big")
         .to(BigInteger.valueOf(42))
         .setField("null")
         .to((BigInteger) null)
         .closeObject();
      assertEquals("{'big':42,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testBigDecimalField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("big")
         .to(BigDecimal.valueOf(42))
         .setField("null")
         .to((BigDecimal) null)
         .closeObject();
      assertEquals("{'big':42,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testDoubleField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("big")
         .to(Double.valueOf(Double.MAX_VALUE))
         .setField("little")
         .to(Double.MAX_VALUE)
         .setField("null")
         .to((Double) null)
         .closeObject();
      assertEquals("{'big':1.7976931348623157E308,'little':1.7976931348623157E308,'null':null}",
         normalize(objectToString(object)));
   }

   @Test
   public void testFloatField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("big")
         .to(Float.valueOf(Float.MAX_VALUE))
         .setField("little")
         .to(Float.MAX_VALUE)
         .setField("null")
         .to((Float) null)
         .closeObject();
      assertEquals("{'big':3.4028235E38,'little':3.4028234663852886E38,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testIntegerField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("big")
         .to(Integer.valueOf(Integer.MAX_VALUE))
         .setField("little")
         .to(Integer.MAX_VALUE)
         .setField("null")
         .to((Integer) null)
         .closeObject();
      assertEquals("{'big':2147483647,'little':2147483647,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testLongField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("big")
         .to(Long.valueOf(Long.MAX_VALUE))
         .setField("little")
         .to(Long.MAX_VALUE)
         .setField("null")
         .to((Long) null)
         .closeObject();
      assertEquals("{'big':9223372036854775807,'little':9.223372036854776E18,'null':null}",
         normalize(objectToString(object)));
   }


   @Test
   public void testShortField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("big")
         .to(Short.valueOf(Short.MAX_VALUE))
         .setField("little")
         .to(Short.MAX_VALUE)
         .setField("null")
         .to((Short) null)
         .closeObject();
      assertEquals("{'big':32767,'little':32767.0,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testBooleanField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("big")
         .to(Boolean.TRUE)
         .setField("little")
         .to(false)
         .setField("null")
         .to((Boolean) null)
         .closeObject();
      assertEquals("{'big':true,'little':false,'null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testStringField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject().setField("big").to("string").setField("null").to((String) null).closeObject();
      assertEquals("{'big':'string','null':null}", normalize(objectToString(object)));
   }

   @Test
   public void testComplexField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("foo")
         .toOpenObject()
         .setField("bar")
         .to(74)
         .closeObject()
         .setField("bar")
         .toObject(jb.openObject().setField("foo").to(47).closeObject())
         .closeObject();
      assertEquals("{'foo':{'bar':74},'bar':{'foo':47}}", normalize(objectToString(object)));
   }

   @Test
   public void testArrayField() throws Exception {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonObject object = jb.openObject()
         .setField("foo")
         .toOpenArray()
         .add(1)
         .add(2)
         .closeArray()
         .setField("bar")
         .toArray(jb.openArray().add(2).add(1).closeArray())
         .closeObject();
      assertEquals("{'foo':[1,2],'bar':[2,1]}", normalize(objectToString(object)));
   }

   @Test
   public void testEmptyArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray().closeArray();
      assertEquals("[]", normalize(arrayToString(array)));
   }

   @Test
   public void testNullArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray().addNull().addNull().closeArray();
      assertEquals("[null,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testStringArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray().add("foo").add((String) null).closeArray();
      assertEquals("['foo',null]", normalize(arrayToString(array)));
   }

   @Test
   public void testBigDecimalArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray().add(BigDecimal.valueOf(1)).add((BigDecimal) null).closeArray();
      assertEquals("[1,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testBigIntegerArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray().add(BigInteger.valueOf(1)).add((BigInteger) null).closeArray();
      assertEquals("[1,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testDoubleArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray()
         .add(Double.valueOf(Double.MAX_VALUE))
         .add(Double.MAX_VALUE)
         .add((Double) null)
         .closeArray();
      assertEquals("[1.7976931348623157E308,1.7976931348623157E308,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testFloatArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray()
         .add(Float.valueOf(Float.MAX_VALUE))
         .add(Float.MAX_VALUE)
         .add((Float) null)
         .closeArray();
      assertEquals("[3.4028235E38,3.4028235E38,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testLongArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray()
         .add(Long.valueOf(Long.MAX_VALUE))
         .add(Long.MAX_VALUE)
         .add((Long) null)
         .closeArray();
      assertEquals("[9223372036854775807,9223372036854775807,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testIntegerArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray()
         .add(Integer.valueOf(Integer.MAX_VALUE))
         .add(Integer.MAX_VALUE)
         .add((Integer) null)
         .closeArray();
      assertEquals("[2147483647,2147483647,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testShortArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray()
         .add(Short.valueOf(Short.MAX_VALUE))
         .add(Short.MAX_VALUE)
         .add((Short) null)
         .closeArray();
      assertEquals("[32767,32767,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testBooleanArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray().add(Boolean.TRUE).add(false).add((Boolean) null).closeArray();
      assertEquals("[true,false,null]", normalize(arrayToString(array)));
   }

   @Test
   public void testComplexArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray()
         .addOpenObject()
         .setField("foo")
         .to(1)
         .closeObject()
         .addObject(jb.openObject().setField("bar").to(2).closeObject())
         .closeArray();
      assertEquals("[{'foo':1},{'bar':2}]", normalize(arrayToString(array)));
   }

   @Test
   public void testArrayArray() {
      JsonBuilder<JsonObject, JsonArray> jb = newJsonBuilder();
      JsonArray array = jb.openArray()
         .addOpenArray()
         .add(1)
         .add(2)
         .closeArray()
         .addArray(jb.openArray().add(2).add(1).closeArray())
         .closeArray();
      assertEquals("[[1,2],[2,1]]", normalize(arrayToString(array)));
   }

   private static String normalize(String str) {
      final StringBuilder sb = new StringBuilder();
      for (char ch : str.toCharArray()) {
         if (!isWhitespace(ch)) {
            sb.append(ch == '\"' ? '\'' : ch);
         }
      }
      return sb.toString();
   }

}
