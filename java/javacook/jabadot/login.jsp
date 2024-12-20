<%@page errorPage="oops.jsp" import="java.util.*,jabadot.*" %>
<!-- 
  -- $Id: login.jsp,v 1.19 2001/10/12 23:02:16 ian Exp $
  -->
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user != null) {
		session.setAttribute("jabadot.message", 
			"<H1>You're already logged on!</H1>"+
			"(as user " + user.getName() + "). Please" +
			"<a href=\"logout.jsp\">" +
			"logout</a> if you wish to log in as a different user.");
		response.sendRedirect("/jabadot/");
	}
	String nick = request.getParameter("nick");
	String pass = request.getParameter("pass");
	if (nick == null || nick.length() == 0 ||
		pass == null || pass.length() == 0) {
 %>
		<!-- Must use jsp include not @ include here since
		 ** tomcat complains if it sees the @ include twice.
		 ** Can't just include it once at beginning, since we
		 ** do a redirect at the end of this jsp if successful.
		 -->
		<html>
		<jsp:include page="./header.html" flush="true" />
		<TITLE>Missing name/password!</TITLE>
		<BODY BGCOLOR=WHITE>
		<H1>Missing name/password!</H1>
		<P>Please enter both a name and a password in the form.
<%		return;
	}
	
	User u = UserDB.getInstance().getUser(nick); 
	if (u == null || !u.checkPassword(pass)) {	
%>
		<html>
		<jsp:include page="./header.html" flush="true" />
		<TITLE>Invalid name/password</TITLE>
		<BODY BGCOLOR=WHITE>
		<H1>Invalid name/password</H1>
		<P>We could not find that name and password combination.
		Please try again if you have an account, else go create one.
<%		return;
	}

	// Hallelujeah! WE FINALLY GOT THIS ONE LOGGED IN.

	session.setAttribute("jabadot.login", u); // login flag
	//session.setAttribute("jabadot.ads", new AdServlet());
	session.setAttribute("jabadot.message", 
		"<H1>Welcome back, " + u.getFullName() + "</H1>");

	// Maintain list of who is logged in.
	TreeMap list = (TreeMap)application.getAttribute("jabadot.wtmp");
	if (list == null) {
		list = new TreeMap();
		application.setAttribute("jabadot.wtmp", list);
	}
	list.put(u.getName(), u);
	session.setAttribute("jabadot.timer", 
		new WhosonCleaner(list, u.getName()));

	// For non-admin logins, provide a 3-hour timeout
	if (!u.isAdminPrivileged()) {
		session.setMaxInactiveInterval(3600*3);
	}

	// Send Redirect back to top, so user sees just this in URL textfield.
	response.sendRedirect("/jabadot/");
%>

<%!
/** Inner class to ensure that users 
 * get removed from the list of users
 * when they logout or get timed out.
 */
public class WhosonCleaner implements HttpSessionBindingListener {
	/** The name to remove when we get un-bound from session */
	String key;
	/** The Collection to remove it from */
	TreeMap map;
	
	/* Construct a WhosonCleaner for a given user */
	public WhosonCleaner(TreeMap map, String key) {
		this.map = map;
		this.key = key;
	}

	/** Unused, required by interface */
	public void valueBound(HttpSessionBindingEvent event) {
		// nothing to do
	}

	/** Called when the object is being unbound from the session */
	public void valueUnbound(HttpSessionBindingEvent event) {
		map.remove(key);
	}
}
%>
