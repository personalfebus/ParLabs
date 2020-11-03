package org.personalfebus;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String line = value.toString();

        String[] words = line.split(",");
        if (words[0].equals("\"YEAR\"") || words[0].equals("YEAR")) return;
        int number = 0;
        for (int j = 0; j < words[18].length(); j++) {
            int digit = (int)words[18].charAt(j) - 48;
            if (digit < 0) break;
            number = number * 10 + digit;
        }
        context.write(new Text(words[14]), new IntWritable(number));
    }
}
