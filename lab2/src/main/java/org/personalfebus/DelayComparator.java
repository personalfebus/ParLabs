package org.personalfebus;

import org.apache.hadoop.io.RawComparator;

public class DelayComparator implements RawComparator {
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return a.compareTo(b);
    }
}
