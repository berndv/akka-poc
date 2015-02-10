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

public class GenericJsonBuilder<JsonObject, JsonArray> implements JsonBuilder<JsonObject, JsonArray>
{
   private abstract class AbstractBuilder<ParentBuilder>
   {
      final ParentBuilder parentBuilder;

      public AbstractBuilder(ParentBuilder parentBuilder)
      {
         this.parentBuilder = parentBuilder;
      }
   }

   private class JsonArrayBuilderImpl<ParentBuilder> extends AbstractBuilder<ParentBuilder>
      implements
         JsonArrayBuilder<ParentBuilder>
   {
      final JsonArray array;

      public JsonArrayBuilderImpl(ParentBuilder parentBuilder)
      {
         super(parentBuilder);
         array = delegate.createJsonArray();
      }

      @Override
      public JsonArrayBuilder<ParentBuilder> add(int value)
      {
         delegate.add(array, value);
         return this;
      }

      @Override
      public JsonArrayBuilder<ParentBuilder> add(String value)
      {
         delegate.add(array, value);
         return this;
      }

      @Override
      public JsonObjectBuilder<JsonArrayBuilder<ParentBuilder>> addObject()
      {
         return new JsonObjectBuilderImpl<JsonArrayBuilder<ParentBuilder>>(this)
         {
            @Override
            public JsonArrayBuilder<ParentBuilder> endObject()
            {
               delegate.addObject(array, object);
               return super.endObject();
            }
         };
      }

      @Override
      public ParentBuilder endArray()
      {
         return parentBuilder;
      }
   }

   private class JsonObjectBuilderImpl<ParentBuilder> extends AbstractBuilder<ParentBuilder>
      implements
         JsonObjectBuilder<ParentBuilder>
   {
      final JsonObject object;

      public JsonObjectBuilderImpl(ParentBuilder parentBuilder)
      {
         super(parentBuilder);
         object = delegate.createJsonObject();
      }

      @Override
      public JsonPropertyBuilder<ParentBuilder> withField(String name)
      {
         return new JsonPropertyBuilderImpl<ParentBuilder>(this, name);
      }

      @Override
      public ParentBuilder endObject()
      {
         return parentBuilder;
      }
   }

   private class JsonPropertyBuilderImpl<ParentBuilder> extends AbstractBuilder<JsonObjectBuilderImpl<ParentBuilder>>
      implements
         JsonPropertyBuilder<ParentBuilder>
   {
      private final String name;

      public JsonPropertyBuilderImpl(JsonObjectBuilderImpl<ParentBuilder> parentBuilder, String name)
      {
         super(parentBuilder);
         this.name = name;
      }
      
      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(String value)
      {
         if (value == null)
         {
            delegate.setNull(parentBuilder.object, name);
         }
         else
         {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(BigDecimal value)
      {
         if (value == null)
         {
            delegate.setNull(parentBuilder.object, name);
         }
         else
         {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(BigInteger value)
      {
         if (value == null)
         {
            delegate.setNull(parentBuilder.object, name);
         }
         else
         {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(Double value)
      {
         if (value == null)
         {
            delegate.setNull(parentBuilder.object, name);
         }
         else
         {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(double value)
      {
         return setTo(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(Float value)
      {
         if (value == null)
         {
            delegate.setNull(parentBuilder.object, name);
         }
         else
         {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(float value)
      {
         return setTo(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(Long value)
      {
         if (value == null)
         {
            delegate.setNull(parentBuilder.object, name);
         }
         else
         {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(long value)
      {
         return setTo(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(Short value)
      {
         if (value == null)
         {
            delegate.setNull(parentBuilder.object, name);
         }
         else
         {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(short value)
      {
         return setTo(Double.valueOf(value));
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(Integer value)
      {
         if (value == null)
         {
            delegate.setNull(parentBuilder.object, name);
         }
         else
         {
            delegate.set(parentBuilder.object, name, value);
         }
         return parentBuilder;
      }

      @Override
      public JsonObjectBuilder<ParentBuilder> setTo(int value)
      {
         return setTo(Integer.valueOf(value));
      }

      @Override
      public JsonArrayBuilder<JsonObjectBuilder<ParentBuilder>> setToArray()
      {
         return new JsonArrayBuilderImpl<JsonObjectBuilder<ParentBuilder>>(parentBuilder)
         {
            @Override
            public JsonObjectBuilder<ParentBuilder> endArray()
            {
               final JsonObject owner = JsonPropertyBuilderImpl.this.parentBuilder.object;
               delegate.setArray(owner, name, array);
               return super.endArray();
            }
         };
      }

      @Override
      public JsonObjectBuilder<JsonObjectBuilder<ParentBuilder>> setToObject()
      {
         return new JsonObjectBuilderImpl<JsonObjectBuilder<ParentBuilder>>(parentBuilder)
         {
            @Override
            public JsonObjectBuilder<ParentBuilder> endObject()
            {
               final JsonObject owner = JsonPropertyBuilderImpl.this.parentBuilder.object;
               delegate.setObject(owner, name, object);
               return super.endObject();
            }
         };
      }
   }

   private final JsonBuilderDelegate<JsonObject, JsonArray> delegate;

   public GenericJsonBuilder(JsonBuilderDelegate<JsonObject, JsonArray> delegate)
   {
      this.delegate = delegate;
   }

   @Override
   public JsonObjectBuilder<JsonObject> beginObject()
   {
      return new JsonObjectBuilderImpl<JsonObject>(null)
      {
         @Override
         public JsonObject endObject()
         {
            return object;
         }
      };
   }

   @Override
   public JsonArrayBuilder<JsonArray> beginArray()
   {
      return new JsonArrayBuilderImpl<JsonArray>(null)
      {
         @Override
         public JsonArray endArray()
         {
            return array;
         }
      };
   }
}
