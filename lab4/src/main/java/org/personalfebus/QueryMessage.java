package org.personalfebus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryMessage {

    @JsonProperty("packageId")          private int packageId;
    @JsonProperty("functionName")       private String functionName;
    @JsonProperty("jsScript")           private String jsScript;
    @JsonProperty("tests")               private Test[] tests;

    public int getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Test[] getTests() {
        return tests;
    }

    public QueryMessage(@JsonProperty("packageId") int packageId, @JsonProperty("functionName") String functionName,
                        @JsonProperty("jsScript") String jsScript, @JsonProperty("tests") Test[] tests){
        
    }
}
