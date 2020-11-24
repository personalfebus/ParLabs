TO DO
spark-submit --class AirportsManager --master yarn-client --num-executors 3 ./target/lab3-1.0-SNAPSHOT.jar
Запуск лабы:   
(Если нужно)>hadoop fs -copyFromLocal AIRPORT_ID.csv   
(Если нужно)>hadoop fs -copyFromLocal ONTIME_SAMPLE.csv   
mvn package   
spark-submit --class org.personalfebus.AirportsManager --master yarn-client --num-executors 3 target/lab3-1.0-SNAPSHOT.jar   


