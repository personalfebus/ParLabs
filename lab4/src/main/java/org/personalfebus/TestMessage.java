package org.personalfebus;

public class TestMessage {
    private String testName;
    private String expectedResult;
    private String parameters;

    public TestMessage(String testName, String expectedResult, String parameters) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.parameters = parameters;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getTestName() {
        return testName;
    }

    public String getParameters() {
        return parameters;
    }
}
