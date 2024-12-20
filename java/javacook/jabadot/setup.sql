# The initial setup for jabadot
# This isn't necessarily how I actually created it.

# This line is not needed with IDB; you do this in the idb setup file.
# create database jabadot;

echo Creating user table
DROP TABLE userdb
CREATE TABLE userdb (
	name		char(12) PRIMARY KEY,
	password	char(20),
	fullName 	char(30),
	email		char(60),
	city		char(20),
	prov		char(20),
	country	char(20),
	privs		int
	)
CREATE INDEX nickIndex ON userdb (name)");

echo Creating submissions table
create table submissions {
	subid		char(10) PRIMARY KEY,
	submittor	char(15),
	subdate		datetime,
	subject		char(50),
	subsref		char(80),
	substance	text
)

echo Creating Categories table
create table categories {
	n			int PRIMARY KEY,
	cat			char(15),
	fromthe		char(20)
}
insert into categories (
	1, "Java Language",		"Back to Basics",
	2, "Core API",			"Whatshamecallit",
	3, "Strings",			"Strings and Things",
	4, "Dates/Times",		"Who/what/when",
	5, "Numbers",			"How shall I count thee?",
	6, "Servlets",			"Jeeves was here",
	7, "Java Server Pages", "Let's get rid of ASP",
)
