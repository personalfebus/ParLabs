package org.personalfebus;

import java.util.HashMap;

public class MapTestMessage {
    private HashMap<String, Boolean> result;
    private int packageId;

    public MapTestMessage(HashMap<String, Boolean> result, int packageId) {
        this.result = result;
        this.packageId = packageId;
    }

    public int getPackageId() {
        return packageId;
    }

    public HashMap<String, Boolean> getResult() {
        return result;
    }
}
