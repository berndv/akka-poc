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

public interface JsonBuilderDelegate<JsonObject, JsonArray>
{
   JsonObject createJsonObject();

   void setObject(JsonObject owner, String name, JsonObject value);

   void setArray(JsonObject owner, String name, JsonArray value);

   void setNull(JsonObject owner, String name);

   void set(JsonObject owner, String name, String value);

   void set(JsonObject owner, String name, BigInteger value);

   void set(JsonObject owner, String name, BigDecimal value);

   void set(JsonObject owner, String name, Double value);

   void set(JsonObject owner, String name, Float value);

   void set(JsonObject owner, String name, Integer value);

   void set(JsonObject owner, String name, Long value);

   void set(JsonObject owner, String name, Short value);

   JsonArray createJsonArray();

   void addObject(JsonArray array, JsonObject value);

   void addArray(JsonArray array, JsonArray value);

   void addNull(JsonArray array);

   void add(JsonArray array, BigInteger value);

   void add(JsonArray array, BigDecimal value);

   void add(JsonArray array, Double value);

   void add(JsonArray array, Float value);

   void add(JsonArray array, Integer value);

   void add(JsonArray array, Long value);

   void add(JsonArray array, Short value);

   void add(JsonArray array, String value);
}
