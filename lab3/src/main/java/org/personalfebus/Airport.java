package org.personalfebus;

import java.io.Serializable;

public class Airport implements Serializable {
    private long id;
    private String name;

    public Airport(long airportId) {
        id = airportId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
