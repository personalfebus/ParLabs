package org.personalfebus;

public class GetMessage {
    private int packageId;
    private String jsScript;
    private String functionName;
    private String testName;
    private String testResult;
    private String parameters;

    public GetMessage(int packageId, String jsScript, String functionName, String testName, String testResult,
    String parameters) {
        this.packageId = packageId;
        this.parameters = parameters;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.testName = testName;
        this.testResult = testResult;
    }

    public int getPackageId() {
        return packageId;
    }
}
