package org.personalfebus;

import java.io.Serializable;

public class Airport implements Serializable {
    long id;
    String name;

    public Airport(long airportId) {
        id = airportId;
    }
}
