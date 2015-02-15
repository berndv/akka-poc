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


public interface JsonPropertyBuilder<ParentBuilder, JsonObject, JsonArray> {
   JsonObjectBuilder<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> toOpenObject();

   JsonArrayBuilder<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> toOpenArray();

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> toObject(JsonObject value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> toArray(JsonArray value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> toNull();

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(String value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(BigDecimal value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(BigInteger value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Double value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(double value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Float value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(float value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Long value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(long value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Integer value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(int value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Short value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(short value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Boolean value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(boolean value);
}