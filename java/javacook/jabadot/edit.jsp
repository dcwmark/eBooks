<%@page errorPage="oops.jsp" import="jabadot.*, java.io.*"%>
<!-- 
  -- $Id: edit.jsp,v 1.8 2001/05/28 03:20:44 ian Exp $
  -->
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null) {
		log("INVALID ADMIN ATTEMPT");
 %>
	<%@include file="header.html" %>
	<TITLE>You must be logged on!</TITLE>
	<H1>You must be logged on!</H1>
	<P>The service you are requesting requires that you 
	be logged in to our site.
	Please go <a href="/jabadot/">back</a> and try again.  Thank you!
<%	return;
	}
 %>
<%	if (!user.isEditPrivileged()) {
 %>
	<%@include file="header.html" %>
	<TITLE>Rank hath its privilege</TITLE>
	<H1>Rank hath its privilege</H1>
	<P>The service you are requesting requires more privilege
	than your account currently has.
	Please go <a href="/jabadot/">back</a> and try a different service.
	Thank you!
<%	return;
	}
	// ELSE
 %>
	<P>Here is a list of submitted articles ready for editing:
	
<%
	File[] files = 
		new File(JDConstants.getProperty("jabadot.dir.web") + "/q").listFiles();
	for (int i=0; i<files.length; i++) {
 		out.print("<li><a href=edit1.jsp?article=\"q/");
		out.print(files[i].getName());
		out.print("\">");
		out.print(files[i].getName());
		out.println("</a></li>");
	}
 %>
	<P>Thanks for listening.
