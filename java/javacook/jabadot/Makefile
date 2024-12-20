JAVAC=jikes +E
#JAVAC=javac

PATH=$PATH:/usr/local/jdk*
CLASSES_DIR = ../WEB-INF/classes

JAR=jar
#JAR=fastjar
#JAR=/usr/local/linux-jdk1.2/bin/jar

all:	html war

html:	public_services.html logged_in_services.html

public_services.html logged_in_services.html: MkServices.class services.txt
		java MkServices
MkServices.class: src/MkServices.java
		$(JAVAC) src/MkServices.java

war:
		$(JAR) -cvmf Manifest /tmp/jabadot.war *
