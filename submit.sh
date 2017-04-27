#! /bin/sh
#
# submit.sh
# Copyright (C) 2017 root <root@A0835-Server-2>
#
# Distributed under terms of the MIT license.
#


# compile and package with maven
# mvn clean package
# cd target/classes
# javah -d ../../ win.dutoe.IceAnalysis
# g++ -fPIC -shared -o libIceAnalysis.so -I /usr/lib/jvm/java-8-oracle/include/ -I /usr/lib/jvm/java-8-oracle/include/linux/ libIceAnalysis.cpp


# submit
export PATH=$PATH:/usr/spark/bin:/usr/hadoop/bin:/root/wyl/VideoAutoDecGpu
export HADOOP_CONF_DIR=/root/hadoop_conf
export HADOOP_USER_NAME=root
export HADOOP_PATH=hdfs://192.168.1.187:9000/user/root/wyl
export SPARK_PRINT_LAUNCH_COMMAND=1
export SPARK_DAEMON_JAVA_OPTS="-Djava.library.path=/root/wyl/iceAnalysis"
export LD_LIBRARY_PATH=/usr/local/lib/:/usr/lib/:/root/wyl/VideoAutoDecGpu
spark-submit \
  --class win.dutoe.IceAnalysis \
  --master yarn \
  --deploy-mode cluster \
  --supervise \
  /root/wyl/iceAnalysis/target/IceAnalysis-1.0.jar \
$HADOOP_USER_NAME $HADOOP_PATH/iceRowData
