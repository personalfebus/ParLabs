package org.personalfebus;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class DelayReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws
			IOException, InterruptedException {
		Iterator iter = values.iterator();
		int count = 0;
		int max_delay = 0;
		int min_delay = 0;
		int sum_delay = 0;
		boolean first_iter = true;

		while(iter.hasNext()){
			Text delayOrName = (Text)iter.next();
			String delayOrNameStr = delayOrName.toString();
			int current_delay = 0;
			if (first_iter) {
				max_delay = current_delay;
				min_delay = current_delay;
				first_iter = false;
			} else {
				if (current_delay < min_delay) min_delay = current_delay;
				if (current_delay > max_delay) max_delay = current_delay;
			}
			sum_delay += current_delay;
			count++;
		}
		int average_delay = sum_delay / count;
		StringBuilder answerBuilder = new StringBuilder();
		answerBuilder.append("min delay = ").append(min_delay).append("; max delay = ")
		.append(max_delay).append(";average delay = ").append(average_delay);
		context.write(key, new Text(answerBuilder.toString()));
	}
}
