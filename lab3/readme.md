Запуск лабы:   
(Если нужно)>hadoop fs -copyFromLocal AIRPORT_ID.csv   
(Если нужно)>hadoop fs -copyFromLocal ONTIME_SAMPLE.csv   
mvn package   
spark-submit --class org.personalfebus.AirportsManager --master yarn-client --num-executors 3 target/lab3-1.0-SNAPSHOT.jar   

Так как спарк забил все место в руте моего компьютера последнюю версию кода я не запускал. Если вдруг она неправильно работает (такого быть не должно) посмотрите пожалуста коммит https://github.com/personalfebus/ParLabs/tree/77002b40887a4ea06c26c4bdb9d76516f465e541
