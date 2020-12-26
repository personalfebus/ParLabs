package org.personalfebus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryMessage {

    @JsonProperty("packageId")          private int packageId;
    @JsonProperty("functionName")       private String functionName;
    @JsonProperty(PROPERTY_JS_SCRIPT)           private String jsScript;
    @JsonProperty(PROPERTY_TESTS)               private Test[] tests;
}
