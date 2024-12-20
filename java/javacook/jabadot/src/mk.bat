set CLASSES_DIR=../WEB-INF/classes
set RT_JAR=/jdk/jre/lib/rt.jar
set SERVLET_JAR=/j2ee/lib/j2ee.jar
\jikes_dev\jikes +E -classpath %RT_JAR%;%SERVLET_JAR%;%CLASSES_DIR% -d %CLASSES_DIR% *.java
