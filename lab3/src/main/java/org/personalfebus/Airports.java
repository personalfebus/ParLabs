package org.personalfebus;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Airports {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.println("Usage: Airports <input path> <output path>");
			System.exit(-1);
		}
		SparkConf conf = new SparkConf().setAppName("lab5");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> airportId = sc.textFile("AIRPORT_ID");
		JavaRDD<String> ontimeSample = sc.textFile("ONTIME_SAMPLE");
		airportId.mapToPair(s -> New Tuple2<>);
		System.out.println("HELLO");
	}
}
