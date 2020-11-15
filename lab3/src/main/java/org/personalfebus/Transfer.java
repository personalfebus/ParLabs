package org.personalfebus;

import java.io.Serializable;

public class Transfer implements Serializable {
    private long id;
    private String name;

    public Transfer(long airportId) {
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
