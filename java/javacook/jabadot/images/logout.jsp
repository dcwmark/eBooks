<%@page errorPage="oops.jsp"%>
<HTML>
<% HttpSession sess = request.getSession(true);
   User user = (User)sess.getValue("jabadot.login");
   if (user == null) {
 %>
<TITLE>You're not logged on!</TITLE>
<H1>You're not logged on!</H1>
<P>So you can't log out!</P>
<P>You may want to <a href="index.jsp">log in now, or get your
own account</a>.</P>
<%		return;
	}
	// Destroy references to the User object.
	sess.removeValue("jabadot.login");
	// Destroy the session object itself.
	sess.invalidate();
 %>

<TITLE>Logged out</TITLE>
<H1>Logged out</H1>
<P>Thank you, and <a href="index.jsp">come back again soon</a>.
