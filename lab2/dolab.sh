mvn package
hadoop fs -rm -r output
hadoop org.personalfebus.DelayManagerApp ONTIME_SAMPLE.csv output
rm -r output
hadoop fs -copyToLocal output

