<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: ennoble.jsp,v 1.6 2001/12/25 20:47:44 ian Exp $
  -->
<HTML>
<%@include file="header.html" %>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" /></P>
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null || !user.isAdminPrivileged()) {
		log("INVALID ADMIN ATTEMPT");
 %>
<TITLE>You must be logged on as an Administrator!</TITLE>
<BODY BGCOLOR="white">
<H1>You must be logged on!</H1>
<P>The service you are requesting requires that you be logged on 
with an administrative account. We don't give these accounts out to just anybody,
so maybe you should reconsider. Your attempt to access this page
has been logged.
Please go <a href="/jabadot/">back</a> and try again.  Thank you!
<%	return;
	}
 // ELSE YOU'RE SET TO GO...
 %>
<TITLE>Customize Their JabaDot</TITLE>
<BODY BGCOLOR="white">
<H1>Customize Their JabaDot</H1>
<%
	String targetUser = request.getParameter("name");
	if (targetUser == null) {
		out.println("<P>Missing value, try again.");
		return;
	}
	User targ = UserDB.getInstance().getUser(targetUser);
	if (targ == null) {
		out.println("<P>You visit the Tomb of the Unknown User. Try again.");
		return;
	}
	String mesg = "<p>So what did you want me to do today, sah?";
	String wish = request.getParameter("wish");
	if (wish == null) {
		out.println(mesg);
		return;
	}
	if (wish.equals("ennoble")) {
		targ.setAdminPrivileged(true);
		mesg = "<p>The account has been ennobled as you requested.</p>";
	} else if (wish.equals("debase")) {
		targ.setAdminPrivileged(false);
		mesg = "<p>The account has been debased as you requested.</p>";
	} else if (wish.equals("enquire")) {	// quill and quire?
		targ.setEditPrivileged(true);
		mesg = "<p>The account has been ennobled as you requested.</p>";
	} else if (wish.equals("dequire")) {
		targ.setEditPrivileged(false);
		mesg = "<p>The account has been debased as you requested.</p>";
	} else if (wish.equals("death")) {	// Hi Charles!
		UserDB.getInstance().deleteUser(targetUser);
		mesg = "<p>Bang! --more-- They die!--more-- The end!</p>";
	}

	session.setAttribute("jabadot.message", mesg);

	response.sendRedirect("/jabadot/admin.jsp");
 %>
