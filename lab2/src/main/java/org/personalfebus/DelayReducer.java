package org.personalfebus;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class DelayReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws
			IOException, InterruptedException {
		Iterator iter = values.iterator();
		String airportName = "";
		int count = 0;
		int maxDelay = 0;
		int minDelay = 0;
		int sumDelay = 0;
		boolean firstIter = true;

		while(iter.hasNext()){
			Text delayOrName = (Text)iter.next();
			String delayOrNameStr = delayOrName.toString();
			int firstLetterCode = (int)delayOrName.charAt(0);
			if (((firstLetterCode > 64) && (firstLetterCode < 91)) || (firstLetterCode == 34)){
				String keyString = key.toString();
				if (keyString.compareTo("10333")) {
					System.out.println(count);
				}
				airportName = delayOrNameStr;
			} else {
				int current_delay = 0;
				for (int j = 0; j < delayOrNameStr.length(); j++) {
					int digit = (int)delayOrNameStr.charAt(j) - 48;
					if (digit < 0) break;
					current_delay = current_delay * 10 + digit;
				}
				if (firstIter) {
					maxDelay = current_delay;
					minDelay = current_delay;
					firstIter = false;
				} else {
					if (current_delay < minDelay) minDelay = current_delay;
					if (current_delay > maxDelay) maxDelay = current_delay;
				}
				sumDelay += current_delay;
				count++;
			}
		}
		int average_delay = 0;
		if (!firstIter) {
			average_delay = sumDelay / count;
			StringBuilder answerBuilder = new StringBuilder();
			answerBuilder.append("name = ").append(airportName).append("; min delay = ").append(minDelay).append("; max delay = ")
					.append(maxDelay).append(";average delay = ").append(average_delay);
			context.write(key, new Text(answerBuilder.toString()));
		}
	}
}
