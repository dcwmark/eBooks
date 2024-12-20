<%@page import="jabadot.*, java.util.*" %>
<%@taglib uri="/jabatags" prefix="jabadot" %>
<!-- 
  -- $Id: admin.jsp,v 1.10 2001/12/25 20:47:44 ian Exp $
  -->
<HTML>
<%@include file="header.html" %>
<!-- This page is exempted from the AdServlet policy. Imperiat Rex! -->
<%
	User user = (User)session.getAttribute("jabadot.login");
	// YOU MUST BE LOGGED IN... And BTW ARE YOU AN ADMIN?
	if (user == null || !user.isAdminPrivileged()) {
 %>
<TITLE>Administrators Only</TITLE>
<BODY BGCOLOR="white">
<H1>Administrators Only</H1>
<P>To access this page, you must be logged in as an administrator.
<%	// log("INVALID ADMIN ATTEMPT");
	return;
	}
	// ELSE GO AHEAD...
 %>
<TITLE>JabaDot: Administration</TITLE>
<BODY BGCOLOR="white">
<H1>JabaDot: Administration</H1>
<%@include file="printJabadotMessage.i"%>
<p>This is the start of an administration screen.
<P>
<table border=2>
<tr><th>Nickname<th>FullName<th>Passwd<th>Admin<th>Edit<th>Terminate</tr>
<!-- Use JSP custom tag to iterate over all users. -->
<jabadot:forAllUsers element="user">
		<tr>
		<td><jsp:getProperty name="user" property="name" /></td>
		<td><jsp:getProperty name="user" property="fullName" /></td>
 
		<td>
		<a href="passwd.jsp?name="<jsp:getProperty 
			name="user" property="name"/>">Change passwd</a>
		</td>
<% 
		// Rest of this section uses stuff that isn't in tag library yet :-(
		// Map "user" into Java code space
		User u = (User)pageContext.getAttribute("user");
		if (u == null) {
			out.println("</table>");
			out.println("<br />");
			out.println("ERROR: can't map user into code space");
			out.flush();
			return;
		}
		String theURL;
		out.print("<td>");
		out.print("<a href=");
		if (u.isAdminPrivileged()) {
			theURL = "ennoble.jsp?wish=debase&name="+u.getName();
			out.print(response.encodeURL(theURL));
			out.print(">Make Lowly</a>");
		} else {
			theURL = "ennoble.jsp?wish=ennoble&name="+u.getName();
			out.print(response.encodeURL(theURL));
			out.print(">Make Admin</a>");
		}
		out.print("</td>");

		out.print("<td>");
		out.print("<a href=");
		if (u.isEditPrivileged()) {
			theURL = "ennoble.jsp?wish=dequire&name="+u.getName();
			out.print(response.encodeURL(theURL));
			out.print(">Revoke Edit</a>");
		} else {
			theURL = "ennoble.jsp?wish=enquire&name="+u.getName();
			out.print(response.encodeURL(theURL));
			out.print(">Grant Edit</a>");
		}
		out.print("</td>");
 %>
		<td>
		<a href="ennoble.jsp?wish=death&name=<jsp:getProperty 
			name="user" property="name"/>">Close account</a>
		</td>

		</tr>

</jabadot:forAllUsers>
</table>
