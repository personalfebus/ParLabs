package org.personalfebus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage {
    private int packageId;
    @JsonProperty("testName") private String testName;
    @JsonProperty("parameters") private String parameters;
    @JsonProperty("testResult") private String testResult;

    public TestMessage(int packageId, String testName, String testResult,
                      String parameters) {
        this.packageId = packageId;
        this.parameters = parameters;
        this.testName = testName;
        this.testResult = testResult;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getTestName() {
        return testName;
    }

    public String getParameters() {
        return parameters;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getTestResult() {
        return testResult;
    }
}
