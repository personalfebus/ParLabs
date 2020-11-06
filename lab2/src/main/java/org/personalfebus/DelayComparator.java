package org.personalfebus;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class DelayComparator extends WritableComparator {
    protected DelayComparator() {
        super(Text.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Text tmp1 = (Text)a;
        Text tmp2 = (Text)b;
        return a.compareTo(b);
    }
}
