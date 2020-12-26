package org.personalfebus;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

public class TestActor extends AbstractActor {
    static final int ROBBIN_NUMBER = 5;
    private ActorRef storeActor = getContext().actorOf(Props.create(StoreActor.class));
    private ActorRef testRunners = getContext().actorOf(new RoundRobinPool(ROBBIN_NUMBER).props(Props.create(RunnerActor.class)))

    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {

                })
                .match(GetMessage.class, m -> {
                    storeActor.tell(m, sender());
                });
    }
}
