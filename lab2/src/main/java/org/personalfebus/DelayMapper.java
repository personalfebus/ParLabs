package org.personalfebus;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DelayMapper extends Mapper<LongWritable, Text, Text, Text> {
	public static final String COMMADELIMETER = ",";
	public static final String HEAD = "\"YEAR\"";

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException,
	InterruptedException {
		String line = value.toString();

		String[] words = line.split(COMMADELIMETER);
		if (words[0].equals(HEAD) || words[18].isEmpty()) return;
		context.write(new Text(words[14]), new Text(words[18]));
	}
}
