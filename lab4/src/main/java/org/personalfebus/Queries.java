package org.personalfebus;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class Queries {
    static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        ActorRef storeActor = system.actorOf(Props.create(TestActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, >
    }
}
