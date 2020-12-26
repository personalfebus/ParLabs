package org.personalfebus;
import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

public class RunnerActor extends AbstractActor {
    private static final String JS_ENGINE_NAME = "nashorn";
    private static final String EQ_FUNCTION_NAME = "eq";
    private static final String EQ_SCRIPT = "var " + EQ_FUNCTION_NAME + " = function(a, b) {return a == b}";
    private static final Invocable eq;

    static {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(JS_ENGINE_NAME);
        try {
            scriptEngine.eval(EQ_SCRIPT);
        } catch (Exception ex) {
            throw new RuntimeException("equal function eval error", ex);
        }
        eq = (Invocable)scriptEngine;
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(RunTestMessage.class, m -> {
                    ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(JS_ENGINE_NAME);
                    scriptEngine.eval(m.getJsScript());
                    Invocable invocable = (Invocable)
                }).build();
    };
}