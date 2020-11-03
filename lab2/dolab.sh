mvn package
hadoop fs -rm -r output
hadoop org.personalfebus.WordCountApp warandpeace1.txt output
rm -r output
hadoop fs -copyToLocal output

