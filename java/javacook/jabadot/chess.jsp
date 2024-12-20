<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: chess.jsp,v 1.2 2001/04/13 01:07:33 ian Exp $
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
<TITLE>JabaDot: jaba.Chess!</TITLE>
<BODY BGCOLOR="white">
<H1>JabaDot: jaba.Chess</H1>
<P>Welcome to jaba.Chess!
<h2>Active Games</h2>
<p>The following games are in progress</p>
<ul>
<li>Spaceky vs Fischer :-)</li>
</ul>
<h2>Waiting to play</h2>
<p>The following people are waiting to play:</p>
<ul>
<li>joe <a href="ChessServlet?cmd=challenge&partner=joe">Start game</a>
</ul>
