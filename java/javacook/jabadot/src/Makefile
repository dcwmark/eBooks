#JAVAC=jikes +E
JAVAC=javac
PATH=$PATH:/usr/local/linux-jdk1.2/bin
CLASSES_DIR = ../WEB-INF/classes
CLASS_SRCS= JDConstants.java User.java UserDB.java UserDBJDBC.java \
	Ad.java AdAccessor.java \
	NewsArticle.java NewsArticleDB.java \
	Password.java \
	Chess*.java 
SERVLET_SRCS= AdServlet.java EvalServlet.java MailServlet.java
TAGHAND_SRCS= ForAllUsers.java ForAllArticles.java
AUX_SRCS=	Mailer.java
SRCS= $(CLASS_SRCS) $(SERVLET_SRCS) $(TAGHAND_SRCS) $(AUX_SRCS)
# Set to any jar or class files needed for your database.
JDBC_CLASSES= ../WEB-INF/lib/idb.jar

all:	classes ../MkServices.class

# For class files, just compile everything; jikes is fast!
classes:
	$(JAVAC) -classpath $$CLASSPATH:$(CLASSES_DIR) -d $(CLASSES_DIR) $(SRCS)

../MkServices.class: MkServices.java
	jikes -classpath $$CLASSPATH:$(CLASSES_DIR) -d .. MkServices.java

../JDAdmin.class: JDAdmin.java
	javac -classpath $$CLASSPATH:$(CLASSES_DIR) -d .. JDAdmin.java

#
# (re)Create the user database.
# There is no default rule that leads here; you must explicitly
# type "make ReCreateUserDatabase". 
# THIS WILL DESTROY ALL EXISTING USER ACCOUNTS. YOU HAVE BEEN WARNED.
ReCreateUserDatabase: $(CLASSES_DIR)/jabadot/CreateUserDatabase.class
	java -classpath $$CLASSPATH:$(CLASSES_DIR):$(JDBC_CLASSES) jabadot.CreateUserDatabase
$(CLASSES_DIR)/jabadot/CreateUserDatabase.class:	CreateUserDatabase.java
	$(JAVAC) -classpath $$CLASSPATH:$(CLASSES_DIR) -d $(CLASSES_DIR) CreateUserDatabase.java
