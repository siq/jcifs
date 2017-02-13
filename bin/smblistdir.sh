#!/bin/sh
PROJECTDIR=`dirname %0`

echo p: $PROJECTDIR

CLASSPATH=$PROJECTDIR/jcifs-1.3.18.jar

java -cp $CLASSPATH flxsmb.cli.ListDirectoryCommand "$@"
