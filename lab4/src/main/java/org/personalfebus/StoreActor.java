package org.personalfebus;
import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private HashMap<Integer, HashMap<String, Boolean>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {
                    HashMap<String, Boolean> testToResult = store.get(m.getPackageId());
                    if (testToResult == null) {
                        testToResult = new HashMap<>();
                        store.put(m.getPackageId(), testToResult);
                    }
                    testToResult.put(m.getTestName(), m.getTestResult());
                    System.out.println("receive message: " + m.getPackageId() + ";" + m.getTestName() + ";" + m.getTestResult());
                })
                .match(GetMessage.class, req -> sender().tell(
                       new GetMessage(req.getPackageId(), store.get(req.getPackageId())), self())
                ).build();
    }
}