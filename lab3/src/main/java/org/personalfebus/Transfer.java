package org.personalfebus;

import java.io.Serializable;

public class Transfer implements Serializable {
    private long originId;
    private long destinationId;
    private String name;

    public Transfer(long origId, long destId) {
        id = airportId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
