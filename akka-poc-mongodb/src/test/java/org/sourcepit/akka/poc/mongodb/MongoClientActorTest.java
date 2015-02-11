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

import static org.junit.Assert.assertEquals;

import java.net.UnknownHostException;
import java.util.List;

import org.bson.BasicBSONObject;
import org.junit.Test;
import org.sourcepit.json.BSONBuilder;

import com.mongodb.ServerAddress;

public class MongoClientActorTest
{

   @Test
   public void test() throws UnknownHostException
   {
      BSONBuilder json = new BSONBuilder();

      BasicBSONObject conf = json.openObject()
         .setField("servers")
         .toOpenArray()
         .addObject(json.openObject().setField("host").to("localhost").setField("port").to(27017).closeObject())
         .closeArray()
         .setField("credentials")
         .toOpenArray()
         .addObject(
            json.openObject()
               .setField("mechanism")
               .to("plain")
               .setField("userName")
               .to("")
               .setField("source")
               .to("$external")
               .setField("password")
               .to("")
               .closeObject())
         .closeArray()
         .setField("options")
         .toOpenObject()
         .closeObject()
         .closeObject();

      System.out.println(conf);

      List<ServerAddress> servers = MongoClientActor.toServerAddresses(MongoClientActor.getUnsafeList(conf, "servers"));
      assertEquals(1, servers.size());

   }

}
