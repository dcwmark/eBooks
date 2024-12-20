<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: mysite.jsp,v 1.10 2001/04/13 01:07:37 ian Exp $
  -->
<HTML>
<%@include file="header.html" %>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null) {
 %>
<TITLE>You must be logged on!</TITLE>
<BODY BGCOLOR="white">
<H1>You must be logged on!</H1>
<P>The service you are requesting requires that you be logged in to our site.
Please go <a href="/jabadot/">back</a> and try again.  Thank you!
<%	return;
	}
 // ELSE YOU'RE LOGGED IN...
 %>
<TITLE>Customize My JabaDot</TITLE>
<BODY BGCOLOR="white">
<H1>Customize My JabaDot</H1>
<%
	String newPassword = request.getParameter("newPassword");
	String newPassword2 = request.getParameter("newPassword2");
	if (newPassword == null) {
 %>
<P>From this page you can change your password.
Other customization functions will be added.
<FORM ACTION="mysite.jsp" METHOD="post">
	<table><!-- inner table for neatness -->
	<tr><td>New Password</td>
		<td><INPUT TYPE=PASSWORD SIZE=15 NAME="newPassword"></td></tr>
	<tr><td>Again:</td>
		<td> <INPUT TYPE=PASSWORD SIZE=15 NAME="newPassword2"></td></tr>
	<tr><td></td>
		<td><INPUT TYPE=SUBMIT VALUE="Change it"></td></tr>
	</table>
</FORM>
<% } else if (!newPassword.equals(newPassword2)) { %>
	<P>They don't match! Please go back and try again.</P>
<% } else {
		UserDB.getInstance().setPassword(user.getName(), newPassword);
 %>
		<P>Your password has been changed as you requested.
		<P>We suggest you <a href="logout.jsp">log out now</a>
		and log back in, to give yourself one extra chance at memorizing
		this new password!</P>
<%	}
 %>
