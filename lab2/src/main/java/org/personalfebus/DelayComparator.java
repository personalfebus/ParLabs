package org.personalfebus;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class DelayComparator extends WritableComparator {
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        String aString = a.toString();
        String bString = b.toString();
//        a.compareTo(b);
        return (-1) * aString.compareTo(bString);
    }
}
