JAVAC=jikes +E
#JAVAC=javac

PATH=$PATH:/usr/local/jdk*
CLASSES_DIR = ../WEB-INF/classes

JAR=jar
#JAR=fastjar
#JAR=/usr/local/linux-jdk1.2/bin/jar

all:	war

war:
		$(JAR) -cvmf Manifest /tmp/jabadot.war *
