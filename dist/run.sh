#!/bin/bash
 
for LIB in \
    lib/*.jar \
    ;
do
    CLASSPATH="${CLASSPATH}:${LIB}"
done
export CLASSPATH

LD_LIBRARY_PATH="/usr/lib/jni${LD_LIBRARY_PATH:+:$LD_LIBRARY_PATH}"
export LD_LIBRARY_PATH
java -jar $PWD/RaspGraph.jar