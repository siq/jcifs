#!/bin/bash

TPWD=`pwd`

cd /deepfs/src/jcifs
ant clean
if [[ $DEBUG ]]
then
    echo "-----------------------------"
    echo "@@@ BUILDING FLXSMB DEBUG @@@"
    echo "-----------------------------"
    ant -Djavac.debug=on -Djavac.debugLevel=lines,vars,source jar
else
    echo "-------------------------------"
    echo "@@@ BUILDING FLXSMB RELEASE @@@"
    echo "-------------------------------"
    ant jar
fi

cp -f /deepfs/src/jcifs/dist/jcifs-1.3.18-flx.1.jar /deepfs/src/siqsmb/lib/jcifs-1.3.18-flx.1.jar

cd $TPWD
