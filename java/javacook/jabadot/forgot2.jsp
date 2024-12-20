<%@page errorPage="oops.jsp" import="jabadot.*,javax.mail.*" %>
<!-- 
  ** $Id: forgot2.jsp,v 1.1 2001/05/28 03:21:43 ian Exp $
  ** Forgotten Password Handler, part 2
  -->
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user != null) {
		session.setAttribute("jabadot.message",
			"You are already logged in," +
			" so you can't have forgotten your password");
		response.sendRedirect("/jabadot/");
	}
 %>

<HTML>
<TITLE>JabaDot - Forgotten Password</TITLE>
<BODY BGCOLOR="#f0f0f0">

<%@include file="header.html"%>

<jsp:include page="/servlet/AdServlet" flush="true"/>

<h1>JabaDot - Forgotten Password</h1>
<%	String nick = request.getParameter("nick");
	if (nick == null) {
 %>
		<h1>Error</h1>
		<p>Please go back and put your user name in the text field.
<%
		return;
	}
	User u = UserDB.getInstance().getUser(nick); 
	if (u == null) {
 %>
		<p>Sorry, we could not find that name. Please try again.</p>.
<%		return;
	}
	String text = "Your JabaDot password is " + u.getPassword();
	try {
	Mailer.send("localhost",
		u.getEmail(),
		"jabadot@darwinsys.com",
		"Re: JabaDot",
		text);
	} catch (MessagingException ex) {
		log("FORGOT2: " + ex);
 %>
		<p>Sorry, we couldn't mail it after all. The problem was:<pre>
<%
		ex.printStackTrace(new PrintWriter(out));
		return;
	}
 %>

<!-- Success is ours! -->
<p>We have mailed your password to your email address of record.
</BODY>
</HTML>
