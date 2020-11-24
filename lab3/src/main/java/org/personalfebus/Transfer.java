package org.personalfebus;

import java.io.Serializable;

public class Transfer implements Serializable {
    private long originId;
    private long destinationId;
    private boolean isCancelled;
    private long maxDelay;

    private long numberOfFlights;
    private long numberOfCancelledOrDelayed;

    public Transfer(long origId, long destId) {
        originId = origId;
        destinationId = destId;
    }

    public void setDelay(long d) {
        maxDelay = d;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public long getDelay() {
        return maxDelay;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public long getOriginId() {
        return originId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}
