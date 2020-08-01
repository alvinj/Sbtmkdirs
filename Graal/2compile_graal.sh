
# this script assumes that this file was already created
# in this directory with `sbt package` or `sbt assembly`
JAR_FILE=sbtmkdirs_2.13-0.2.jar
JAR_DIR=../target/scala-2.13

echo "deleting old JAR file ..."
rm $JAR_FILE 2> /dev/null

echo "copying JAR file to current dir ..."
cp ${JAR_DIR}/${JAR_FILE} .

echo "running 'native-image' command on ${JAR_FILE} ..."
# create a native image from the jar file and name
# the resulting executable 'sbtmkdirs'
native-image -cp .:${SCALA_HOME}/lib/scala-library.jar:${JAR_FILE} \
    --no-server \
    --no-fallback \
    --initialize-at-build-time \
    -jar ${JAR_FILE} sbtmkdirs

# NOTE: --no-fallback requires that --initialize-at-build-time be used.
# SEE: https://github.com/oracle/graal/issues/802
# SEE MethodHandle on this page: https://github.com/vmencik/akka-graal-native
