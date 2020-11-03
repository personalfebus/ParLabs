package org.personalfebus;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class DelayReducer extends Reducer<Text, IntWritable, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws
			IOException, InterruptedException {
		Iterator iter = values.iterator();

		int max_delay = 0;
		int min_delay = 0;
		int sum_delay = 0;
		boolean first_iter = true;

		while(iter.hasNext()){
			IntWritable delay = (IntWritable)iter.next();
			int current_delay = delay.get();
			if (first_iter) {
				max_delay = current_delay;
				min_delay = current_delay;
				first_iter = false;
			}
			sum_delay += current_delay;
		}

		context.write(key, new LongWritable(count));
	}
}
