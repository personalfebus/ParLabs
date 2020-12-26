package org.personalfebus;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;

public class Queries {
    static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        ActorRef storeActor = system.actorOf(Props.create(TestActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create()
    }
}
