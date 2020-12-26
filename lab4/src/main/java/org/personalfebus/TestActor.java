package org.personalfebus;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class TestActor extends AbstractActor {
    public AbstractActor.Receive createReceive() {
        private ActorRef storeActor = getContext().actorOf(Props.create(StoreActor.class));
        
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {

                })
                .match(GetMessage.class, m -> {

                });
    }
}
