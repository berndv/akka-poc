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


public interface JsonPropertyBuilder<ParentBuilder, JsonObject, JsonArray>
{
   JsonObjectBuilder<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> setToObject();

   JsonArrayBuilder<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> setToArray();

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setToObject(JsonObject value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setToArray(JsonArray value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(String value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(BigDecimal value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(BigInteger value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(Double value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(double value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(Float value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(float value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(Long value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(long value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(Integer value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(int value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(Short value);

   JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> setTo(short value);

}