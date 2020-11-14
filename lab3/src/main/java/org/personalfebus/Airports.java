package org.personalfebus;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

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
		JavaRDD<String> nameToId = airportId.flatMap(s ->
				Arrays.stream(s.replace("\"", "").split(",")).iterator()).filter(s -> {
					
		});
//		airportId.mapToPair(s -> new Tuple2<>);
		System.out.println("HELLO");
	}
}
