<html>
<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: index.jsp,v 1.20 2001/12/25 20:47:45 ian Exp $
  -->
<head>
	<title>JabaDot - Java News For Ever(yone)</title>
	<link rel="stylesheet" type="text/css" 
		href="custom/jabadot_green.css" title="Style">
</head>
<body>

<%@include file="header.html"%>

<%
	User user = (User)session.getAttribute("jabadot.login");
 %>

<!-- Main table for rest of page -->
<TABLE>
<TR>
<TD WIDTH=75% ALIGN="TOP">
	<!-- Most of page, at left -->
	<jsp:include page="/servlet/AdServlet" flush="true"/>
	<br flush=all/>
	<%@include file="printJabadotMessage.i"%>
	<H1>JabaDot. News about <i><b>java.*</b></i>&reg;.</H1>
	<jsp:include page="news.jsp" flush="true"/>
</TD>
<TD WIDTH=25% ALIGN="TOP">
	<!-- Rest of page, at right -->
	<% if (user == null) { %>
	<FORM action="login.jsp" method="post">
		<table>
		<tr><td>Name:</td><td><INPUT TYPE=TEXT SIZE=8 NAME=nick></td></tr>
		<tr><td>Password:</td><td><INPUT TYPE=PASSWORD SIZE=8 NAME=pass></td></tr>
		<tr><td></td><td><INPUT TYPE=SUBMIT VALUE="Login" ALIGN=CENTER></td></tr>
		<tr><td align=center colspan=2><b>Cookies must be enabled</b>.</td></tr>
		<tr><td align=center colspan=2><a href="forgot1.jsp" id="LinkLocal">Forgotten password?</a></td></tr>
		</table>
	</FORM>
	<jsp:include page="public_services.html" flush="true"/>
	<br>
	<% } else { %>
	Logged in as <%= user.getName() %>
	<jsp:include page="logged_in_services.html" flush="true"/>
	<li><a href="logout.jsp">Log out</a>
	<% } %>
</TD>
</TR>
</TABLE>
<p align="center">
<a href="http://www.openbsd.org/">
	<img src="images/openbsd_pb.gif" alt="Powered by OpenBSD"></a>
and
<a href="http://www.apache.org/">
	<img src="images/apache_pb.gif" alt="and by Apache"></a>
</BODY>
</HTML>
