<%@page import="jabadot.*" %>
<!-- 
  -- $Id: DailyEval.jsp,v 1.5 2001/04/13 01:07:32 ian Exp $
  -->
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null) {
		session.setAttribute("jabadot.message",
		"<P><B>Please log in to JabaDot to use the Survey feature," +
		"since we use your user name to get at the database.</B></P>");
	response.sendRedirect("/jabadot/");
	}
 // ELSE YOU'RE LOGGED IN...
 %>
<HTML>
<TITLE>Learning Tree Daily Evaluation</TITLE>
<BODY BGCOLOR="#0000CC">
<%@include file="header.html" %>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<H1 ALIGN=CENTER>Daily Evaluation</H1>
<P><I>This page will not be part of the JabaDot Web Site but
is here as part of a Technology Demonstration</I>.
<P>Your answers to the following will assist in our goal
of continually improving this course's content and delivery. Thank you!
</P>

<FORM METHOD="GET" ACTION="/servlet/EvalServlet">

<P>Your Name: <%= user.getName() %>

<TABLE BORDER="1" WIDTH="100%">
<TR><TH COLSPAN=4>Course
<TR>
	<TD><INPUT TYPE=radio NAME=course VALUE="4" CHECKED>Good
	<TD><INPUT TYPE=radio NAME=course VALUE="3">OK
	<TD><INPUT TYPE=radio NAME=course VALUE="2">Fair
	<TD><INPUT TYPE=radio NAME=course VALUE="1">Poor
	<TD><INPUT TYPE=radio NAME=course VALUE="0">Unacceptable
<TR><TH COLSPAN=5>Instructor
<TR>
	<TD><INPUT TYPE=radio NAME=instr VALUE="4" CHECKED>Good
	<TD><INPUT TYPE=radio NAME=instr VALUE="3">OK
	<TD><INPUT TYPE=radio NAME=instr VALUE="2">Fair
	<TD><INPUT TYPE=radio NAME=instr VALUE="1">Poor
	<TD><INPUT TYPE=radio NAME=instr VALUE="0">Unacceptable
<TR><TH COLSPAN=5>Pace
<TR>
	<TD><INPUT TYPE=radio NAME=pace VALUE="ok" CHECKED>OK
	<TD><INPUT TYPE=radio NAME=pace VALUE="fast">Fast
	<TD><INPUT TYPE=radio NAME=pace VALUE="slow">Slow
</TABLE>

<H3>What was the best part of today's class?</H3>
<TEXTAREA NAME=best COLS=60 ROWS=4></TEXTAREA>

<H3>What improvements, if any, would you suggest?</H3>
<TEXTAREA NAME=improve COLS=60 ROWS=4></TEXTAREA>

<P ALIGN=CENTER><INPUT type=submit VALUE="Review evaluation">
</FORM>
</BODY>
