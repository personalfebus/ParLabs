package org.personalfebus;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DelayMapper extends Mapper<LongWritable, Text, Text, Text> {
	public static final String COMMA_DELIMETER = ",";
	public static final String HEAD = "\"YEAR\"";
	public static final int AIRPORT_ID_POSITION = 14;
	public static final int DELAY_POSITION = 18;

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException,
	InterruptedException {
		String line = value.toString();

		String[] words = line.split(COMMA_DELIMETER);
		if (words[0].equals(HEAD) || words[DELAY_POSITION].isEmpty()) return;
		context.write(new Text(words[AIRPORT_ID_POSITION]), new Text(words[DELAY_POSITION]));
	}
}
