# Makefile for Servlet directory package
# $Id: Makefile,v 1.5 2001/08/16 02:52:58 ian Exp $

JAVAC?=	jikes +E

all:	checkpath classes

checkpath:
		@(cd ..; java TestForClass javax.servlet.http.HttpServlet) || { \
			cat classpath.txt; exit 1; \
		}

classes:
		@echo "===> Building in `pwd`"
		$(JAVAC) *.java

InitParams:
	jikes -d $$HOME/webs/daroadweb/WEB-INF/classes InitParams.java

CodeRed:
	jikes -d $$HOME/webs/daroadweb/WEB-INF/classes CodeRed.java

clean:
		@rm -f *.class
