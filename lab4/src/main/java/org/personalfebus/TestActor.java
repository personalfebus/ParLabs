package org.personalfebus;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

public class TestActor extends AbstractActor {
    private ActorRef storeActor = getContext().actorOf(Props.create(StoreActor.class));
    private ActorRef testRunners = getContext().actorOf(new RoundRobinPool())
    public AbstractActor.Receive createReceive() {


        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {

                })
                .match(GetMessage.class, m -> {

                });
    }
}
