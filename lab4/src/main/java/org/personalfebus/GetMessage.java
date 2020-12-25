package org.personalfebus;

public class GetMessage {
    private int packageId;
    private String jsScript;
    private String functionName;
    private String testName;
    private String testResult;
    private String parameters;

    public GetMessage(int packageId) {
        this.packageId = packageId;
    }

    public int getPackageId() {
        return packageId;
    }
}
