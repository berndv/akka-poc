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

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JacksonBuilderDelegate implements JsonBuilderDelegate<ObjectNode, ArrayNode>
{
   private final JsonNodeFactory nodeFactory = JsonNodeFactory.instance;

   @Override
   public ObjectNode createJsonObject()
   {
      return nodeFactory.objectNode();
   }

   @Override
   public void setObject(ObjectNode owner, String name, ObjectNode value)
   {
      owner.set(name, value);
   }

   @Override
   public void setArray(ObjectNode owner, String name, ArrayNode value)
   {
      owner.set(name, value);
   }

   @Override
   public void setNull(ObjectNode owner, String name)
   {
      owner.set(name, nodeFactory.nullNode());
   }

   @Override
   public void set(ObjectNode owner, String name, String value)
   {
      owner.set(name, nodeFactory.textNode(value));
   }

   @Override
   public void set(ObjectNode owner, String name, BigInteger value)
   {
      owner.set(name, nodeFactory.numberNode(value));
   }

   @Override
   public void set(ObjectNode owner, String name, BigDecimal value)
   {
      owner.set(name, nodeFactory.numberNode(value));
   }

   @Override
   public void set(ObjectNode owner, String name, Double value)
   {
      owner.set(name, nodeFactory.numberNode(value));
   }

   @Override
   public void set(ObjectNode owner, String name, Float value)
   {
      owner.set(name, nodeFactory.numberNode(value));
   }

   @Override
   public void set(ObjectNode owner, String name, Integer value)
   {
      owner.set(name, nodeFactory.numberNode(value));
   }

   @Override
   public void set(ObjectNode owner, String name, Long value)
   {
      owner.set(name, nodeFactory.numberNode(value));
   }

   @Override
   public void set(ObjectNode owner, String name, Short value)
   {
      owner.set(name, nodeFactory.numberNode(value));
   }

   @Override
   public ArrayNode createJsonArray()
   {
      return nodeFactory.arrayNode();
   }

   @Override
   public void addObject(ArrayNode array, ObjectNode value)
   {
      array.add(value);
   }

   @Override
   public void addArray(ArrayNode array, ArrayNode value)
   {
      array.add(value);
   }

   @Override
   public void addNull(ArrayNode array)
   {
      array.add(nodeFactory.nullNode());
   }

   @Override
   public void add(ArrayNode array, BigInteger value)
   {
      array.add(nodeFactory.numberNode(value));
   }

   @Override
   public void add(ArrayNode array, BigDecimal value)
   {
      array.add(nodeFactory.numberNode(value));
   }

   @Override
   public void add(ArrayNode array, Double value)
   {
      array.add(nodeFactory.numberNode(value));
   }

   @Override
   public void add(ArrayNode array, Float value)
   {
      array.add(nodeFactory.numberNode(value));
   }

   @Override
   public void add(ArrayNode array, Integer value)
   {
      array.add(nodeFactory.numberNode(value));
   }

   @Override
   public void add(ArrayNode array, Long value)
   {
      array.add(nodeFactory.numberNode(value));
   }

   @Override
   public void add(ArrayNode array, Short value)
   {
      array.add(nodeFactory.numberNode(value));
   }

   @Override
   public void add(ArrayNode array, String value)
   {
      array.add(nodeFactory.textNode(value));
   }


}
