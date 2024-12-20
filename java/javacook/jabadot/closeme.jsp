<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: closeme.jsp,v 1.5 2001/04/13 01:07:34 ian Exp $
  -->
<HTML>
<%@include file="header.html" %>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null) {
 %>
<TITLE>You must be logged on!</TITLE>
<H1>You must be logged on!</H1>
<P>The service you are requesting requires that you be logged in to our site.
Please go <a href="/jabadot/">back</a> and try again.  Thank you!
<%	return;
	}
	// ELSE THEY'RE LOGGED IN...

	// XXX Add code to send the email and keep a queue of pending closures.
 %>
<TITLE>Close Account</TITLE>
<H1>Close Account</H1>
<P>You have requested that we close your account.
An email will be sent to your registered email address;
if you confirm this, we will actually close your account.
<P>Thanks for visiting!
