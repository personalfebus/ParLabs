package org.personalfebus;
import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private HashMap<Integer, Map<String, Boolean>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {
                    HashMap<String, Boolean> testToResult = store.get(m.getPackageId());
                    if (testToResult == null) {
                        testToResult = new HashMap<>();
                        testToResult.put(m.getTestName(), m.isTestResult());
                    }
                    System.out.println("receive message! "+m.toString());
                })
                .match(GetMessage.class, req -> sender().tell(

//                        new StoreMessage(req.getPackageId(), store.get(req.getPackageId())), self())
                ).build();
    }
}