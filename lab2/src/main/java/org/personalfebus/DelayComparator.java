package org.personalfebus;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class DelayComparator extends WritableComparator {
    DelayComparator() {
        super();
    }
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return a.compareTo(b);
    }
}
