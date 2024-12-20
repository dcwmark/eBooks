# Makefile.simple -- simple makefile to build all Java classes.
# Remember to "make makefiles.clean" before editing this file!
# $Id: Makefile.simple,v 1.7 2001/03/15 19:54:56 ian Exp $

JAVAC?=	jikes +E

all:	classes

classes:
		@echo "===> Building in `pwd`"
		$(JAVAC) *.java

clean:
		@rm -f *.class
