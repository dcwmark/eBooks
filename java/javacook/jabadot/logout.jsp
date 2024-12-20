<%@page errorPage="oops.jsp" import="java.util.*,jabadot.*" %>
<!-- 
  -- $Id: logout.jsp,v 1.8 2001/12/25 20:47:45 ian Exp $
  -->
<HTML>
<%
   User user = (User)session.getAttribute("jabadot.login");
   if (user == null) {
 %>
<TITLE>You're not logged on!</TITLE>
<BODY BGCOLOR=WHITE>
<H1>You're not logged on!</H1>
<P>So you can't log out!</P>
<P>You may want to <a href="index.jsp">log in now</a>, or 
<a href="newuser.jsp">create your own account</a>.</P>
<%		return;
	}

	// else we were logged in, so log out

	// Destroy references to the User object.
	session.removeAttribute("jabadot.login");

	// Destroy the session object itself.
	// session.invalidate();
	// NO DON'T! We need it to send the message below.
	// It suffices to removeAttribute() the User object...

	session.setAttribute("jabadot.message",
	"<H1>Logged out</H1><P>You have been logged out, as you requested</P>");

	// But do remove from global user list too
	TreeMap list = (TreeMap)application.getAttribute("jabadot.wtmp");
	if (list == null) {
		log("Heh!? Logging user out but jabadot.wtmp does not exist");
	}

	// Warning - if list admin removes you while you are logged in,
	// this will throw a nullpointer exception. Probably rare enough
	// that it's not worth fixing. Administrators: be warned.
	list.remove(user.getName());

	response.sendRedirect("/jabadot/");
 %>

<TITLE>Logged out</TITLE>
<BODY BGCOLOR=WHITE>
<H1>Logged out</H1>
<P>Thank you, and <a href="index.jsp">come back again soon</a>.
