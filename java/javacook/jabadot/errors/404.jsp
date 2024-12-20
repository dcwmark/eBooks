<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: 404.jsp,v 1.2 2001/06/02 00:42:01 ian Exp $
  -->
<HTML>
<%@include file="../header.html" %>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user != null) {
	String name = user.getName();
 %>
<TITLE>404 - can't find page requested by <%=user.getName()%></TITLE>
<BODY BGCOLOR="white">
<H1>404 - can't find page requested by <%=user.getName()%></H1>
<P>Sorry, <%=user.getName()%>, but the URL you requested cannot be found.
<%
	} else { // NOBODY LOGGED IN...
 %>
<TITLE>404 - can't find page</TITLE>
<BODY BGCOLOR="white">
<H1>404 - can't find page </H1>
<P>Sorry, but the URL you requested cannot be found.
<% } 
 %>
Please go <i>back</I> and try again.  Thank you!
