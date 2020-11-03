package org.personalfebus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DelayManagerApp {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: WordCountApp <input path> <output path>");
			System.exit(-1);
		}
		Job job = Job.getInstance();
		job.setJarByClass(DelayManagerApp.class);
		job.setJobName("Delay Manager");
		MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, CallsJoinMapper.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapperClass(DelayMapper.class);
		job.setReducerClass(DelayReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setNumReduceTasks(2);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
