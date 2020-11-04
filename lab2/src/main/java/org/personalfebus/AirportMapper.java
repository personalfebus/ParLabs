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
        if (line.equals("Code,Description")) return;
        int commaPosition = line.indexOf(",");
        String code = line.substring(1, commaPosition - 1);
        String name = line.substring(commaPosition + 2, line.length() - 1);
        if (code.equals("od")) System.out.println("===" + name + "===");
        context.write(new Text(code), new Text(name));
    }
}
