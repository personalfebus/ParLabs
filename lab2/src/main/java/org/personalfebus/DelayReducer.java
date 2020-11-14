package org.personalfebus;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class DelayReducer extends Reducer<Text, Text, Text, Text> {
	public boolean isName(int code) {
		return (code > 64) && (code < 91);
	}

	public int stringToNum(String delayStr){
		int currentDelay = 0;
		for (int j = 0; j < delayStr.length(); j++) {
			int digit = (int)delayStr.charAt(j) - 48;
			if (digit < 0) break;
			currentDelay = currentDelay * 10 + digit;
		}
		return currentDelay;
	}

	public String buildAnswer(String airportName, int minDelay, int maxDelay, int averageDelay) {
		
		return ""
	}
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
			if (isName(firstLetterCode)){
				airportName = delayOrNameStr;
			} else {
				int currentDelay = stringToNum(delayOrNameStr);
				if (firstIter) {
					maxDelay = currentDelay;
					minDelay = currentDelay;
					firstIter = false;
				} else {
					if (currentDelay < minDelay) minDelay = currentDelay;
					if (currentDelay > maxDelay) maxDelay = currentDelay;
				}
				sumDelay += currentDelay;
				count++;
			}
		}
		int averageDelay = 0;
		if (!firstIter) {
			averageDelay = sumDelay / count;

			context.write(key, new Text(answerBuilder.toString()));
		}
	}
}
