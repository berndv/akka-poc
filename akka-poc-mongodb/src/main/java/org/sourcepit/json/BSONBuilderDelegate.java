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

import org.bson.BasicBSONObject;
import org.bson.types.BasicBSONList;

public class BSONBuilderDelegate implements JsonBuilderDelegate<BasicBSONObject, BasicBSONList>
{

   @Override
   public BasicBSONObject createJsonObject()
   {
      return new BasicBSONObject();
   }

   @Override
   public void setObject(BasicBSONObject owner, String name, BasicBSONObject value)
   {
      owner.put(name, value);
   }

   @Override
   public void setArray(BasicBSONObject owner, String name, BasicBSONList value)
   {
      owner.put(name, value);
   }

   @Override
   public void setNull(BasicBSONObject owner, String name)
   {
      owner.put(name, null);
   }

   @Override
   public void set(BasicBSONObject owner, String name, String value)
   {
      owner.put(name, value);
   }

   @Override
   public void set(BasicBSONObject owner, String name, BigInteger value)
   {
      owner.put(name, value);
   }

   @Override
   public void set(BasicBSONObject owner, String name, BigDecimal value)
   {
      owner.put(name, value);
   }

   @Override
   public void set(BasicBSONObject owner, String name, Double value)
   {
      owner.put(name, value);
   }

   @Override
   public void set(BasicBSONObject owner, String name, Float value)
   {
      owner.put(name, value);
   }

   @Override
   public void set(BasicBSONObject owner, String name, Integer value)
   {
      owner.put(name, value);
   }

   @Override
   public void set(BasicBSONObject owner, String name, Long value)
   {
      owner.put(name, value);
   }

   @Override
   public void set(BasicBSONObject owner, String name, Short value)
   {
      owner.put(name, value);
   }

   @Override
   public void set(BasicBSONObject owner, String name, Boolean value)
   {
      owner.put(name, value);
   }

   @Override
   public BasicBSONList createJsonArray()
   {
      return new BasicBSONList();
   }

   @Override
   public void addObject(BasicBSONList array, BasicBSONObject value)
   {
      array.add(value);
   }

   @Override
   public void addArray(BasicBSONList array, BasicBSONList value)
   {
      array.add(value);
   }

   @Override
   public void addNull(BasicBSONList array)
   {
      array.add(null);
   }

   @Override
   public void add(BasicBSONList array, BigInteger value)
   {
      array.add(value);
   }

   @Override
   public void add(BasicBSONList array, BigDecimal value)
   {
      array.add(value);
   }

   @Override
   public void add(BasicBSONList array, Double value)
   {
      array.add(value);
   }

   @Override
   public void add(BasicBSONList array, Float value)
   {
      array.add(value);
   }

   @Override
   public void add(BasicBSONList array, Integer value)
   {
      array.add(value);
   }

   @Override
   public void add(BasicBSONList array, Long value)
   {
      array.add(value);
   }

   @Override
   public void add(BasicBSONList array, Short value)
   {
      array.add(value);
   }

   @Override
   public void add(BasicBSONList array, String value)
   {
      array.add(value);
   }

   @Override
   public void add(BasicBSONList array, Boolean value)
   {
      array.add(value);
   }

}
