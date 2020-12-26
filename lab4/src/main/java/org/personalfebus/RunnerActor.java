package org.personalfebus;
import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import java.util.HashMap;
import java.util.Map;

public class RunnerActor extends AbstractActor {
    private static final String JS_ENGINE_NAME = "nashorn";
    private static final String EQUALLER_FUNCTION_NAME = "eq";
    private static final String EQUALLER_SCRIPT = "var " + EQUALLER_FUNCTION_NAME + " = function(a, b) {return a == b}";
    private static final Invocable equaller;

    @Override
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(RunTestMessage.class m -> )
    };
}