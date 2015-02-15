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

import java.math.BigDecimal;
import java.math.BigInteger;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class GsonBuilderDelegate implements JsonBuilderDelegate<JsonObject, JsonArray> {

   @Override
   public JsonObject createJsonObject() {
      return new JsonObject();
   }

   @Override
   public void setObject(JsonObject owner, String name, JsonObject value) {
      owner.add(name, value);
   }

   @Override
   public void setArray(JsonObject owner, String name, JsonArray value) {
      owner.add(name, value);
   }

   @Override
   public void setNull(JsonObject owner, String name) {
      owner.add(name, JsonNull.INSTANCE);
   }

   @Override
   public void set(JsonObject owner, String name, String value) {
      owner.addProperty(name, value);
   }

   @Override
   public void set(JsonObject owner, String name, BigInteger value) {
      owner.addProperty(name, value);
   }

   @Override
   public void set(JsonObject owner, String name, BigDecimal value) {
      owner.addProperty(name, value);
   }

   @Override
   public void set(JsonObject owner, String name, Double value) {
      owner.addProperty(name, value);
   }

   @Override
   public void set(JsonObject owner, String name, Float value) {
      owner.addProperty(name, value);
   }

   @Override
   public void set(JsonObject owner, String name, Integer value) {
      owner.addProperty(name, value);
   }

   @Override
   public void set(JsonObject owner, String name, Long value) {
      owner.addProperty(name, value);
   }

   @Override
   public void set(JsonObject owner, String name, Short value) {
      owner.addProperty(name, value);
   }

   @Override
   public void set(JsonObject owner, String name, Boolean value) {
      owner.addProperty(name, value);
   }

   @Override
   public JsonArray createJsonArray() {
      return new JsonArray();
   }

   @Override
   public void addObject(JsonArray array, JsonObject value) {
      array.add(value);
   }

   @Override
   public void addArray(JsonArray array, JsonArray value) {
      array.add(value);
   }

   @Override
   public void addNull(JsonArray array) {
      array.add(JsonNull.INSTANCE);
   }

   @Override
   public void add(JsonArray array, BigInteger value) {
      array.add(new JsonPrimitive(value));
   }

   @Override
   public void add(JsonArray array, BigDecimal value) {
      array.add(new JsonPrimitive(value));
   }

   @Override
   public void add(JsonArray array, Double value) {
      array.add(new JsonPrimitive(value));
   }

   @Override
   public void add(JsonArray array, Float value) {
      array.add(new JsonPrimitive(value));
   }

   @Override
   public void add(JsonArray array, Integer value) {
      array.add(new JsonPrimitive(value));
   }

   @Override
   public void add(JsonArray array, Long value) {
      array.add(new JsonPrimitive(value));
   }

   @Override
   public void add(JsonArray array, Short value) {
      array.add(new JsonPrimitive(value));
   }

   @Override
   public void add(JsonArray array, String value) {
      array.add(new JsonPrimitive(value));
   }

   @Override
   public void add(JsonArray array, Boolean value) {
      array.add(new JsonPrimitive(value));
   }
}
