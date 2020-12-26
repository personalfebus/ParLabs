package org.personalfebus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage {
    @JsonProperty("testName") private String testName;
    @JsonProperty("params") private Object[] parameters;
    @JsonProperty("expectedResult") private String testResult;

    public TestMessage(@JsonProperty("testName") String testName, @JsonProperty("expectedResult") String testResult,
                       @JsonProperty("params") Object[] parameters) {
        this.parameters = parameters;
        this.testName = testName;
        this.testResult = testResult;
    }

    public String getTestName() {
        return testName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public String getTestResult() {
        return testResult;
    }
}
