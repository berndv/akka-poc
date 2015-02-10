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


public interface JsonPropertyBuilder<ParentBuilder>
{
   JsonObjectBuilder<JsonObjectBuilder<ParentBuilder>> setToObject();

   JsonArrayBuilder<JsonObjectBuilder<ParentBuilder>> setToArray();

   JsonObjectBuilder<ParentBuilder> setTo(String value);

   JsonObjectBuilder<ParentBuilder> setTo(BigDecimal value);

   JsonObjectBuilder<ParentBuilder> setTo(BigInteger value);

   JsonObjectBuilder<ParentBuilder> setTo(Double value);

   JsonObjectBuilder<ParentBuilder> setTo(double value);

   JsonObjectBuilder<ParentBuilder> setTo(Float value);

   JsonObjectBuilder<ParentBuilder> setTo(float value);

   JsonObjectBuilder<ParentBuilder> setTo(Long value);

   JsonObjectBuilder<ParentBuilder> setTo(long value);

   JsonObjectBuilder<ParentBuilder> setTo(Integer value);

   JsonObjectBuilder<ParentBuilder> setTo(int value);

   JsonObjectBuilder<ParentBuilder> setTo(Short value);

   JsonObjectBuilder<ParentBuilder> setTo(short value);

}