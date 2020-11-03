package org.personalfebus;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String line = value.toString();

        int commaPosition = line.indexOf(",");
        
        if (words[0].equals("\"Code\"") || words[0].equals("Code")) return;
        context.write(new Text(words[0]), new Text(words[1]));
    }
}
