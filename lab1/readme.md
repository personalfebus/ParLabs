Запуск лабы:  
>export HADOOP_CLASSPATH=target/lab1-1.0-SNAPSHOT.jar  
(Если нужно)>hadoop fs -copyFromLocal warandpeace1.txt  
>mvn package  
(Если нужно)>hadoop fs -rm -r output  
>hadoop org.personalfebus.WordCountApp warandpeace1.txt output  
(Если нужно)>rm -r output  
>hadoop fs -copyToLocal output  


