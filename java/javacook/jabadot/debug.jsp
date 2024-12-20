<%@page errorPage="oops.jsp" %>
<%@page import="jabadot.*, java.util.*" %>
<!-- 
  -- $Id: debug.jsp,v 1.4 2000/12/03 00:45:25 ian Exp $
  -->
<%! Properties p = JDConstants.getProperties(); %>
<HTML>
<TITLE>JabaDot: Debug:</TITLE>
<BODY BGCOLOR=BLACK TEXT=WHITE LINK=WHITE VLINK=WHITE>
<H1>JabaDot: Debug:</H1>
Debug output at
<%= new java.util.Date() %>
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null || !user.isAdminPrivileged()) {
		out.println("<P>The rest of this page requires Administrative Privs");
		return;
	}
 %>
<P>Here is a list of the properties that your JabaDot system is running with.
</p>
<table bgcolor="yellow" border="1" cols="2">
<tr><th colspan="2">JabaDot Properties</th></tr>
<%	Enumeration it = p.propertyNames();
	while (it.hasMoreElements()) {
		String key = it.nextElement().toString();
		out.print("<tr><td>");
		out.print(key);
		out.print("</td><td>");
		out.print(p.getProperty(key));
		out.print("</td></tr>");
	}
 %>
</table>
