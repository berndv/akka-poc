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

package org.sourcepit.akka.poc.mongodb;

import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import akka.actor.UntypedActor;

public class MongoClientActor extends UntypedActor
{
   final MongoClient client;

   public MongoClientActor(DBObject clientDef)
   {
      // { "users": { "find": [ { "age": { "$gt": 18 } }, { "name": 1, "address": 1 } ] } }
      // vs.
      // { "collection": "users", "method": "find", "criteria": { "age": { "$gt": 18 } }, "projection": { "name": 1,
      // "address": 1 } }

      List<ServerAddress> servers = toServerAddresses(getUnsafeList(clientDef, "servers"));
      List<MongoCredential> credentials = toMongoCredentials(getUnsafeList(clientDef, "credentials"));
      client = new MongoClient(servers, credentials); // TODO: git_user_name Auto-generated constructor stub
   }

   private List<MongoCredential> toMongoCredentials(List<Object> unsafeList)
   {
      throw new UnsupportedOperationException();
   }

   private static List<ServerAddress> toServerAddresses(List<DBObject> servers)
   {
      throw new UnsupportedOperationException();
   }

   @SuppressWarnings("unchecked")
   private static <T> List<T> getUnsafeList(final DBObject obj, String field)
   {
      return (List<T>) obj.get(field);
   }

   @Override
   public void onReceive(Object arg0) throws Exception
   {
      client.getDB("").getCollection("").find();
   }

   @Override
   public void postStop() throws Exception
   {
      client.close();
      super.postStop();
   }
}
