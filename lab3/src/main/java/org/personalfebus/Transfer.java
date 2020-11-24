package org.personalfebus;

import java.io.Serializable;

public class Transfer implements Serializable {
    private long originId;
    private long destinationId;
    private boolean isCancelled;
    private long maxDelay;

    private long numberOfFlights;
    private long numberOfCancelledOrDelayed;

    public String originName;
    public String destinationName;

    public Transfer(long origId, long destId) {
        originId = origId;
        destinationId = destId;
        numberOfFlights = 1;
        numberOfCancelledOrDelayed = 0;
    }

    public void setDelay(long d) {
        maxDelay = d;
        if (d > 0) numberOfCancelledOrDelayed = 1;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
        if (cancelled) numberOfCancelledOrDelayed = 1;
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

    public long getNumberOfCancelledOrDelayed() {
        return numberOfCancelledOrDelayed;
    }

    public long getNumberOfFlights() {
        return numberOfFlights;
    }

    public void addFlight(long delay, long sumFlights, long sumCancelled) {
        numberOfFlights += sumFlights;
        numberOfCancelledOrDelayed = sumCancelled;
        if (delay > maxDelay) maxDelay = delay;
    }

    public void setNames(String originName, String destinationName) {
        this.originName = originName;
        this.destinationName = destinationName;
    }
}
