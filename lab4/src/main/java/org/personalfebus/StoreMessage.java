package org.personalfebus;

public class StoreMessage {
    private int packageId;
    private String testName;
    private boolean testResult;

    public StoreMessage(int packageId, String testName, boolean testResult) {
        this.packageId = packageId;
        this.testName = testName;
        this.testResult = testResult;
    }
}
