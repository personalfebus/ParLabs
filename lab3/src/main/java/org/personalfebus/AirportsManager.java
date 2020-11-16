package org.personalfebus;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.PairRDDFunctions.*;
import scala.Tuple2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AirportsManager {
	public static final int ORIGIN_AIRPORT_ID_POSITION = 11;
	public static final int DEST_AIRPORT_ID_POSITION = 14;
	public static final int DELAY_POSITION = 18;
	public static final int CANCELLED_POSITION = 19;

	public static long stringToNum(String Str){
		long number = 0;
		for (int j = 0; j < Str.length(); j++) {
			int digit = (int)Str.charAt(j) - 48;
			if (digit < 0) break;
			number = number * 10 + digit;
		}
		return number;
	}

	public static void main(String[] args) throws Exception {
//		if (args.length != 3) {
//			System.err.println("Usage: Airports <input path> <output path>");
//			System.exit(-1);
//		}

		SparkConf conf = new SparkConf().setAppName("lab3");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> airportId = sc.textFile("AIRPORT_ID.csv");
		JavaRDD<String> ontimeSample = sc.textFile("ONTIME_SAMPLE.csv");

		JavaPairRDD<Long, String> idToNameRDD = airportId.filter(s -> {
			int acxv = (int)s.charAt(0);
			System.out.println(s.charAt(0));
			if (s.charAt(0) == 'C') {
				System.out.println("@@@@@");
				System.out.println(s);
				System.out.println("@@@@@");
				return false;
			}
			return true;
		}).mapToPair(s -> {
			String corrected = s.replace("\"", "");
			int commaPosition = corrected.indexOf(",");
			String code = corrected.substring(0, commaPosition);
			String name = corrected.substring(commaPosition + 1);
			return new Tuple2<>(stringToNum(code), name);
		});
		Map<Long, String> idToNameMap = idToNameRDD.collectAsMap();

		JavaPairRDD<Tuple2<Long, Long>, Transfer> chunk = ontimeSample.filter(s -> {
			if (s.charAt(0) == '\"') {
				System.out.println("@@@@@");
				System.out.println(s);
				System.out.println("@@@@@");
				return false;
			}
			return true;
		}).mapToPair(s -> {
			String corrected = s.replace("\"", "");
			String[] words = corrected.split(",");

			Long originId = stringToNum(words[ORIGIN_AIRPORT_ID_POSITION]);
			Long destinationId = stringToNum(words[DEST_AIRPORT_ID_POSITION]);
			Tuple2<Long, Long> origAndDestId = new Tuple2<>(originId, destinationId);
			Transfer transfer = new Transfer(originId, destinationId);
			Long cancelCode = stringToNum(words[CANCELLED_POSITION]);
			if (cancelCode == 0) transfer.setDelay(stringToNum(words[DELAY_POSITION]));
			transfer.setCancelled(cancelCode != 0);
			return new Tuple2<>(origAndDestId, transfer);
		});

//		final Broadcast<Map<Long, String>> airportsBroadcasted = sc.broadcast(idToNameMap);
		System.out.println(chunk.collect());
		System.out.println("HELLO");
	}
}
