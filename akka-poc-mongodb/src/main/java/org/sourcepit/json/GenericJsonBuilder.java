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

public class GenericJsonBuilder<JsonObject, JsonArray> implements JsonBuilder<JsonObject, JsonArray> {
   private abstract class AbstractBuilder<ParentBuilder> {
      final ParentBuilder parentBuilder;

      public AbstractBuilder(ParentBuilder parentBuilder) {
         this.parentBuilder = parentBuilder;
      }
   }

   private class JsonArrayBuilderImpl<ParentBuilder> extends AbstractBuilder<ParentBuilder>
      implements
         JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> {
      final JsonArray array;

      public JsonArrayBuilderImpl(ParentBuilder parentBuilder) {
         super(parentBuilder);
         array = delegate.createJsonArray();
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> addObject(JsonObject value) {
         delegate.addObject(array, value);
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> addArray(JsonArray value) {
         delegate.addArray(array, value);
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(String value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(BigDecimal value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(BigInteger value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Double value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(double value) {
         return add(Double.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Float value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(float value) {
         return add(Float.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Long value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(long value) {
         return add(Long.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Integer value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(int value) {
         return add(Integer.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Short value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(short value) {
         return add(Short.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(Boolean value) {
         if (value == null) {
            delegate.addNull(array);
         }
         else {
            delegate.add(array, value);
         }
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> add(boolean value) {
         return add(Boolean.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> addNull() {
         delegate.addNull(array);
         return this;
      }

      @Override
      public JsonObjectBuilder<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> addOpenObject() {
         return new JsonObjectBuilderImpl<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>>(this) {
            @Override
            public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> closeObject() {
               delegate.addObject(array, object);
               return super.closeObject();
            }
         };
      }

      @Override
      public JsonArrayBuilder<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> addOpenArray() {
         return new JsonArrayBuilderImpl<JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray>>(this) {
            @Override
            public JsonArrayBuilder<ParentBuilder, JsonObject, JsonArray> closeArray() {
               delegate.addArray(JsonArrayBuilderImpl.this.array, array);
               return super.closeArray();
            }
         };
      }

      @Override
      public ParentBuilder closeArray() {
         return parentBuilder;
      }
   }

   private class JsonObjectBuilderImpl<ParentBuilder> extends AbstractBuilder<ParentBuilder>
      implements
         JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> {
      final JsonObject object;

      public JsonObjectBuilderImpl(ParentBuilder parentBuilder) {
         super(parentBuilder);
         object = delegate.createJsonObject();
      }

      @Override
      public JsonPropertyBuilder<ParentBuilder, JsonObject, JsonArray> setField(String name) {
         return new JsonPropertyBuilderImpl<ParentBuilder>(this, name);
      }

      @Override
      public ParentBuilder closeObject() {
         return parentBuilder;
      }
   }

   private class JsonPropertyBuilderImpl<ParentBuilder> extends AbstractBuilder<JsonObjectBuilderImpl<ParentBuilder>>
      implements
         JsonPropertyBuilder<ParentBuilder, JsonObject, JsonArray> {
      private final String name;

      public JsonPropertyBuilderImpl(JsonObjectBuilderImpl<ParentBuilder> parentBuilder, String name) {
         super(parentBuilder);
         this.name = name;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(String value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(BigDecimal value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(BigInteger value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Double value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(double value) {
         return to(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Float value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(float value) {
         return to(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Long value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(long value) {
         return to(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Short value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(short value) {
         return to(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Integer value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(int value) {
         return to(Integer.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(Boolean value) {
         if (value == null) {
            delegate.setNull(parentBuilder.object, name);
         }
         else {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> to(boolean value) {
         return to(Boolean.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> toNull() {
         delegate.setNull(parentBuilder.object, name);
         return parentBuilder;
      }

      @Override
      public JsonArrayBuilder<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> toOpenArray() {
         return new JsonArrayBuilderImpl<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>>(parentBuilder) {
            @Override
            public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> closeArray() {
               final JsonObject owner = JsonPropertyBuilderImpl.this.parentBuilder.object;
               delegate.setArray(owner, name, array);
               return super.closeArray();
            }
         };
      }

      @Override
      public JsonObjectBuilder<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>, JsonObject, JsonArray> toOpenObject() {
         return new JsonObjectBuilderImpl<JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray>>(parentBuilder) {
            @Override
            public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> closeObject() {
               final JsonObject owner = JsonPropertyBuilderImpl.this.parentBuilder.object;
               delegate.setObject(owner, name, object);
               return super.closeObject();
            }
         };
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> toObject(JsonObject value) {
         delegate.setObject(parentBuilder.object, name, value);
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder, JsonObject, JsonArray> toArray(JsonArray value) {
         delegate.setArray(parentBuilder.object, name, value);
         return parentBuilder;
      }
   }

   private final JsonBuilderDelegate<JsonObject, JsonArray> delegate;

   public GenericJsonBuilder(JsonBuilderDelegate<JsonObject, JsonArray> delegate) {
      this.delegate = delegate;
   }

   @Override
   public JsonObjectBuilder<JsonObject, JsonObject, JsonArray> openObject() {
      return new JsonObjectBuilderImpl<JsonObject>(null) {
         @Override
         public JsonObject closeObject() {
            return object;
         }
      };
   }

   @Override
   public JsonArrayBuilder<JsonArray, JsonObject, JsonArray> openArray() {
      return new JsonArrayBuilderImpl<JsonArray>(null) {
         @Override
         public JsonArray closeArray() {
            return array;
         }
      };
   }
}
