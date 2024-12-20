<%@page isErrorPage="true" import="jabadot.*" %>
<!-- 
  -- $Id: oops.jsp,v 1.8 2001/12/25 20:47:45 ian Exp $
  -->
<HTML>
<HEAD>
	<TITLE>Ummm...</TITLE>
</HEAD>
<BODY>
<%@include file="header.html" %>
<H1>Oh dear!</H1>
<P>This came as a <B>complete surprise</B> to me at this point
in my programming.
Somehow, your interactions with JabaDot have
led to an error being reported.
<P>It would help us if you could 
<a href="mailto: 
	<%= JDConstants.getProperty("jabadot.mail_comments_to") %>">
report what you were doing when this error occurred</a>.
<!--
  <P>The page that blew is
  <%= (String)request.getAttribute("sourcePage") %>
  -->
<P>The exception is:
<PRE>
<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
</PRE>
</FONT>
</BODY>
</HTML>
