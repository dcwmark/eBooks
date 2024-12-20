<%@page import="jabadot.*" %>
<%@page errorPage="oops.jsp"%>
<!-- 
  -- $Id: chat.jsp,v 1.9 2001/04/13 01:07:33 ian Exp $
  -->
<HTML>
<%@include file="header.html" %>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null) {
 %>
<TITLE>You must be logged on!</TITLE>
<body bgcolor="white">
<H1>You must be logged on!</H1>
<P>The service you are requesting requires that you be logged in to our site.
Please go <a href="/jabadot/">back</a> and try again.  Thank you!
<%	return;
	}
 // ELSE YOU'RE LOGGED IN...
 %>
<TITLE>Welcome to the Chat Room!</TITLE>
<BODY BGCOLOR="white">
<H1>The Chat Room</H1>
<jsp:plugin type="applet" code="ChatRoom" jreversion="1.2"
	width="500" height="100" name="chatRoom">
<jsp:fallback>Your browser must allow Java to use the ChatRoom</jsp:fallback>
</jsp:plugin>
</body>
</html>
