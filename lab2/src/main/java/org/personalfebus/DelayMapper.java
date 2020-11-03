package org.personalfebus;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DelayMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException,
	InterruptedException {
		String line = value.toString();

//		for (int i = 0; i < tmp.length(); i++) {
//			int num = (int)tmp.charAt(i);
//			if (((num != 32)  && (num < 97)) || ((num > 122) && (num < 1072)) || ((num > 1103) && (num != 1105))){
//				tmp.deleteCharAt(i);
//				i--;
//			}
//		}

		String[] words = line.split(",");
		if (words[0].equals("\"YEAR\"") || words[0].equals("YEAR")) return;

		for (int i = 0; i < words.length; i++) {
			if (word.length() != 0) {
				context.write(new Text(word), new IntWritable(1));
			}
		}
	}
}
