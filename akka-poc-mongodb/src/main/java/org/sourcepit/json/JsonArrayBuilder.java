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


public interface JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>
{
   JsonObjectBuilder<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> addOpenObject();

   JsonArrayBuilder<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> addOpenArray();

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> addObject(JsonObject value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> addArray(JsonArray value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> addNull();

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(String value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(BigDecimal value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(BigInteger value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Double value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(double value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Float value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(float value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Long value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(long value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Integer value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(int value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Short value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(short value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Boolean value);

   JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(boolean value);

   ParentBuilder closeArray();
}