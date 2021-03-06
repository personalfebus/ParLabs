package org.personalfebus;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.PairRDDFunctions.*;
import scala.Tuple2;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

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
		SparkConf conf = new SparkConf().setAppName("lab3");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> airportId = sc.textFile("AIRPORT_ID.csv");
		JavaRDD<String> ontimeSample = sc.textFile("ONTIME_SAMPLE.csv");

		JavaPairRDD<Long, String> idToNameRDD = airportId.filter(s -> s.charAt(0) != 'C')
				.mapToPair(s -> {
			String corrected = s.replace("\"", "");
			int commaPosition = corrected.indexOf(",");
			String code = corrected.substring(0, commaPosition);
			String name = corrected.substring(commaPosition + 1);
			return new Tuple2<>(stringToNum(code), name);
		});
		Map<Long, String> idToNameMap = idToNameRDD.collectAsMap();

		JavaPairRDD<Tuple2<Long, Long>, Transfer> allTransfers = ontimeSample.filter(s -> s.charAt(0) != '\"')
				.mapToPair(s -> {
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

		JavaPairRDD<Tuple2<Long, Long>, Transfer> groupedTransfers = allTransfers.reduceByKey((transfer, transfer2) -> {
			transfer.addFlight(transfer2.getDelay(), transfer2.getNumberOfFlights(), transfer2.getNumberOfCancelledOrDelayed());
			return transfer;
		});

		final Broadcast<Map<Long, String>> airportsBroadcasted = sc.broadcast(idToNameMap);

		List<Transfer> listOfTransfers = groupedTransfers.map(info -> {
			Map<Long, String> airportNames = airportsBroadcasted.value();
			long origId = info._2.getOriginId();
			long destId = info._2.getDestinationId();
			info._2.setNames(airportNames.get(origId), airportNames.get(destId));
			return info._2;
		}).collect();
		//System.out.println("HELLO");

		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		System.setOut(out);

		Iterator<Transfer> iterator = listOfTransfers.iterator();
		while (iterator.hasNext()) {
			Transfer transfer = iterator.next();
			System.out.println("===================");
			System.out.println("From: " + transfer.originName + ";");
			System.out.println("To: " + transfer.destinationName + ";");
			System.out.println("Maximum Delay: " + transfer.getDelay() + ";");
			float percent = (float)transfer.getNumberOfCancelledOrDelayed() / transfer.getNumberOfFlights();
			percent *= 100;
			System.out.println("Percent of delayed or cancelled flights: " + percent + ";");
			//System.out.println("NumberOfCancelledOrDelayed: " + transfer.getNumberOfCancelledOrDelayed() + ";");
			//System.out.println("getNumberOfFlights: " + transfer.getNumberOfFlights() + ";");
		}
		//System.out.println("HELLO");
	}
}
