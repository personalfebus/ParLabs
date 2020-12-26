package org.personalfebus;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class TestActor extends AbstractActor {
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage);
    }
}
