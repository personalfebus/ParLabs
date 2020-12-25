package org.personalfebus;
import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import java.util.HashMap;
import java.util.Map;

public class RunnerActor extends AbstractActor {
    private HashMap<Integer, HashMap<String, Boolean>> store = new HashMap<>();

    @Override
    public Receive createReceive() {

    };
}