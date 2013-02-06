#!/bin/bash
echo "" > classpath.txt
for file in `ls target/lib/*.jar`;
        do echo -n '' >> classpath.txt;
        echo -n $file >> classpath.txt;
        echo -n ':' >> classpath.txt;
done

export CLASSPATH=$(cat classpath.txt)
export JAVA_OPTS="${JAVA_OPTS} -XX:MaxPermSize=256M -Xmx1024M -DloggerPath=conf/log4j.properties"
JAVA_OPTS=$JAVA_OPTS scala -cp $CLASSPATH "$@" 
