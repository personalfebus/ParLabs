mvn package
spark-submit --class org.personalfebus.AirportsManager --master yarn-client --num-executors 3 target/lab3-1.0-SNAPSHOT.jar

