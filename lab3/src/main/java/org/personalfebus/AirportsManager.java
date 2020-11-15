package org.personalfebus;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
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

	public int stringToNum(String delayStr){
		int currentDelay = 0;
		for (int j = 0; j < delayStr.length(); j++) {
			int digit = (int)delayStr.charAt(j) - 48;
			if (digit < 0) break;
			currentDelay = currentDelay * 10 + digit;
		}
		return currentDelay;
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.println("Usage: Airports <input path> <output path>");
			System.exit(-1);
		}

		SparkConf conf = new SparkConf().setAppName("lab3");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> airportId = sc.textFile("AIRPORT_ID");
		JavaRDD<String> ontimeSample = sc.textFile("ONTIME_SAMPLE");

		JavaPairRDD<Long, String> idToNameRDD = airportId.filter(s -> {
			if (s.charAt(0) == 'C') {
				System.out.println("@@@@@");
				System.out.println(s);
				System.out.println("@@@@@");
				return false;
			}
			return true;
		}).mapToPair(s -> {
			String corrected = s.replace("\"", "");
			String[] words = corrected.split(",");

			long numId = 0;
			for (int i = 0; i < code.length(); i++) {
				int digit = (int) code.charAt(i) - 48;
				numId = numId * 10 + digit;
			}
			return new Tuple2<>(numId, name);
		});
		Map<Long, String> idToNameMap = idToNameRDD.collectAsMap();

		JavaPairRDD<Tuple2<Long, Long>, String> chunk = ontimeSample.mapToPair(s -> {
			String corrected = s.replace("\"", "");
		});

//		JavaRDD<Airport> airports = airportId.flatMap(s -> {
//					String corrected = s.replace("\"", "");
//					int commaPosition = corrected.indexOf(",");
//					String code = corrected.substring(0, commaPosition);
//					String name = corrected.substring(commaPosition + 1);
//					long numId = 0;
//					for (int i = 0; i < code.length(); i++) {
//						int digit = (int)code.charAt(i) - 48;
//						numId = numId * 10 + digit;
//					}
//
//					Airport airport = new Airport(numId);
//					airport.setName(name);
//					return Arrays.asList(airport).iterator();
//				});


//		JavaRDD<String> splittedAirportId = airportId.flatMap(s ->
//				Arrays.stream(s.replace("\"", "").split(",")).iterator()).filter(s -> {
//			return !s.equals("Code") && !s.equals("Description");
//		});
		//JavaPairRDD<String, String> nameToId splittedAirportId = airportId.mapToPair(s ->
		//		s.replace("\"", ""));
		//JavaPairRDD<String, Long> nameToId = splittedAirportId.mapToPair(s -> new Tuple2<String, Long>(s, 1L));

//		airportId.mapToPair(s -> new Tuple2<>);
		System.out.println("HELLO");
	}
}
