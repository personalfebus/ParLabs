mvn package
hadoop fs -rm -r output
hadoop org.personalfebus.DelayManagerApp ONTIME_SAMPLE.csv AIRPORT_ID.csv output
rm -r output
hadoop fs -copyToLocal output

