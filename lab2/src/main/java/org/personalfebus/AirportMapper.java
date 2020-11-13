package org.personalfebus;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, Text, Text> {
    public static final String COMMA_DELIMETER = ",";
    public static final String HEADER = "Code,Description";

    public String deleteAllQuotes(String line){
        line = line.replace("\"", "");
        return line;
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String line = value.toString();
        if (line.equals(HEADER)) {
            return;
        }
        line = deleteAllQuotes(line);
        int commaPosition = line.indexOf(COMMA_DELIMETER);
        String code = line.substring(0, commaPosition - 1);
        String name = line.substring(commaPosition + 1);
        context.write(new Text(code), new Text(name));
    }
}
