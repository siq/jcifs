#!/bin/bash

TPWD=`pwd`

cd ${TPWD}/src/flxsmb
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

cp -f ${TPWD}/src/flxsmb/dist/jcifs-1.3.17-flx.1.jar ${TPWD}/src/siqsmb/lib/jcifs-1.3.17-flx.1.jar

cd $TPWD
