package org.personalfebus;

public class QueryMessage {

    @JsonProperty(PROPERTY_PACKAGE_ID)          private int packageId;
    @JsonProperty(PROPERTY_FUNCTION_NAME)       private String functionName;
    @JsonProperty(PROPERTY_JS_SCRIPT)           private String jsScript;
    @JsonProperty(PROPERTY_TESTS)               private Test[] tests;
}
