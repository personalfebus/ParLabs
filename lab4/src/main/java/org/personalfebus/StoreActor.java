package org.personalfebus;
import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private Map<Integer, Map<String, Boolean>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, m -> {
                    Map<String, Boolean> mp = store.get();
                    System.out.println("receive message! "+m.toString());
                })
                .match(GetMessage.class, req -> sender().tell(

                        new StoreMessage(req.getPackageId(), store.get(req.getPackageId())), self())
                ).build();
    }
}