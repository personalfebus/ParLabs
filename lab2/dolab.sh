mvn package
hadoop fs -rm -r output
hadoop org.personalfebus.WordCountApp ONTIME_SAMPLE.csv output
rm -r output
hadoop fs -copyToLocal output

