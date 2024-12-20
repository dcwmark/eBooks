<%@page errorPage="oops.jsp" import="jabadot.*, java.io.*,java.util.Date" %>
<!-- 
  -- $Id: submit.jsp,v 1.15 2001/06/02 00:42:01 ian Exp $
  -->
<html>
<%
	HttpSession sess = request.getSession(true);
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null) {
%>		<TITLE>Anonymous User Article Submission</TITLE>
<%	} else {
%>
		<TITLE>Article Submission</TITLE>
<%	}
 %>
	<body bgcolor="white">
<%
	if (request.getParameter("message") == null) {		// FIRST HALF: MAKE FORM
		if (user == null) {
 %>
<jsp:include page="header.html" flush="true"/>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<H1>Anonymous User Submission</H1>
<P>We prefer that you be <A href="login.jsp">logged in</A> when submitting,
so we can have a slightly better chance to verify your identity.
If you go ahead and submit without logging in, your article may
be delayed.</P>
<%
	} else { // YOU'RE LOGGED IN...
 %>
<TITLE>Submission by <%= user.getName()%>!</TITLE>
<jsp:include page="header.html" flush="true"/>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<BODY BGCOLOR="white">
<H1>Submission by <%= user.getName()%></H1>
<P>
Welcome, <%= user.getName()%>. The following is your submission form.
<%
	}	// Done Make Header, rest of Make FORM is common.
 %>
	<FORM ACTION="/jabadot/submit.jsp" METHOD=POST>
	<!-- Tiny table for lining up entry items -->
	<table border="0" columns="2">
	<tr><td>Name:</td><td><%= user==null?"<INPUT NAME=\"name\" SIZE=30></INPUT>":user.getName() %></td></tr>
	<tr><td>EMail:</td><td><%= user==null?"<INPUT NAME=\"email\" SIZE=30></INPUT>":user.getEmail() %></td></tr>
	<tr><td>Category:</td><td><select name="category">
				<%@include file="categories.i" %>
				</select></td></tr>
	<tr><td>Subject:</td><td><INPUT NAME="subject" size=50"></INPUT></td></tr>
	<tr><td>URL:</td><td><INPUT NAME="url" size=50"></INPUT></td></tr>
	<tr><td colspan="2">Your comments, please:</td></tr>
	<tr><td colspan="2">
		<TEXTAREA NAME="message" ROWS="8" COLS="60" WRAP="physical"></TEXTAREA>
		</td></tr>
	<tr><td colspan="2"><INPUT TYPE="submit" VALUE="Submit Article"></P></td></tr>
	</table>
	</FORM>
<%
	return;		// END OF TOP HALF -- MAKE FORM
	}
	String subject = request.getParameter("subject");
	String url = request.getParameter("url");
	String name, email;
	if (user == null) {
		name = request.getParameter("name");
		email = request.getParameter("email");
	} else {
		name = user.getName();
		email = user.getEmail();
	}
	File f = File.createTempFile("submit", ".txt", 
		new File(JDConstants.getProperty("jabadot.queue_dir")));
	// out.println("FILE = " + f.getPath());
	PrintWriter pw = new PrintWriter(new FileWriter(f));
	pw.print("From: ");
	if (user != null) {
		pw.print("User ");
	} else {
		pw.print("Anonymous user ");
	}
	pw.print(name);
	pw.print(" <");
	pw.print(email);
	pw.println(">");
	pw.print("Subject: ");
	pw.println(subject);
	pw.print("Date: ");
	pw.println(new Date());
	pw.print("URL: ");
	pw.println(url);
	pw.println();
	pw.println(request.getParameter("message"));
	pw.close();

	session.setAttribute("jabadot.message", 
		"<H1>Thank you</H1>" +
		"<P>Your article has been submitted to our editorial review board." +
		"<P>Contributions from readers such as you help to make this " +
		"the very best news site on the web.");
 %>

	<jsp:include page="/jabadot/index.jsp" flush="true"/>
