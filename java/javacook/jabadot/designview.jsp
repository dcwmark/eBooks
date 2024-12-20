<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: designview.jsp,v 1.12 2001/04/13 01:07:35 ian Exp $
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
 %>
<TITLE>JabaDot: The Design View</TITLE>
<BODY BGCOLOR="white">
<H1>JabaDot: The Design View</H1>
This is intended to be a website, like /. or PHPnet or DaemonNews.
It will be done using 100% Pure Java, JSP and Servlets.
Who knows if it will ever be remotely ready to show?
<H2>See Also</H2>
<UL>
<li><a href="design00.png">Image - the first design document :-)</a>
<li><A href="TODO.txt">Todo list</A>
</UL>
<H2>Design Overview</H2>
<pre>
index.jsp -- main page:
	news articles on left (NewsServlet)
	login and/or links on right
Public services.html
	register for email notices
	about
	What you could get if logged in (now shown in italic)
Logged In Services.html
	# made from same list as "what you could get"
	# either static, at compile time, or via a Static method
	See services.txt
Copyright
	BSD license, change "Must acknowledge" to "must link"
Download.jsp
	must be logged in, then create transient link and send it.
Register.jsp, uses User as a Bean
	put user names in flat file, for now
	User: name, email, class (unknown, logged in, priv)
Mail (hotmail anybody? :-)

