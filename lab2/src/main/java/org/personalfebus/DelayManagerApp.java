package org.personalfebus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DelayManagerApp {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.println("Usage: WordCountApp <input path> <output path>");
			System.exit(-1);
		}
		Job job = Job.getInstance();
		job.setJarByClass(DelayManagerApp.class);
		job.setJobName("Delay Manager");
		MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, DelayMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, AirportMapper.class);
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		//job.setGroupingComparatorClass(DelayComparator.class);
		job.setReducerClass(DelayReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(2);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
