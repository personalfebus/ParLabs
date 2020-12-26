package org.personalfebus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryMessage {

    @JsonProperty("packageId")          private int packageId;
    @JsonProperty("functionName")       private String functionName;
    @JsonProperty("jsScript")           private String jsScript;
    @JsonProperty("tests")               private Test[] tests;

    
}
