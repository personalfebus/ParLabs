Запуск лабы:   
>export HADOOP_CLASSPATH=target/lab2-1.0-SNAPSHOT.jar   
(Если нужно)>hadoop fs -copyFromLocal AIRPORT_ID.csv   
(Если нужно)>hadoop fs -copyFromLocal ONTIME_SAMPLE.csvmvn package   
mvn package   
(Если нужно)>hadoop fs -rm -r output   
hadoop org.personalfebus.DelayManagerApp ONTIME_SAMPLE.csv AIRPORT_ID.csv output   
(Если нужно)>rm -r output   
hadoop fs -copyToLocal output   

