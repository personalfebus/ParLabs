package org.personalfebus;

import akka.NotUsed;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpMessage;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.entity;
import static akka.http.javadsl.server.Directives.post;

public class Queries {
    private static final String PROPERTY_PACKAGE_ID = "packageId";
    private static final int QUERY_TIMEOUT = 10000;

    public static Route createRoute(ActorRef actorRef) {
        return post(() -> entity(Jackson.unmarshaller(QueryMessage.class), m -> {
            for (TestMessage t : m.getTests()) {
                actorRef.tell(new RunTestMessage());
            }

        }
    }
    static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        ActorRef storeActor = system.actorOf(Props.create(TestActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> flow = ;
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer);
        System.in.read();
        binding.thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }
}
