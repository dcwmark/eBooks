# Makefile for javax.comm package
# $Id: Makefile,v 1.3 2001/03/15 19:54:59 ian Exp $

JAVAC?=	jikes +E

all:	checkpath classes

checkpath:
		@(cd ..; java TestForClass javax.comm.CommPortOpen) || { \
			cat javax.comm.txt; exit 1; \
		}

classes:
		@echo "===> Building in `pwd`"
		$(JAVAC) *.java

clean:
		rm -f *.class
