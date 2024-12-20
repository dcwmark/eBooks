<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  ** $Id: forgot1.jsp,v 1.1 2001/05/28 03:21:43 ian Exp $
  ** Forgotten Password Handler, part 1
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
<p>This will mail your password to your email address of record.
<p>If you got here from the "mailing list signup" form, your JabaDex name
would be your first initial plus your last name. For example,
Robin Smith would be rsmith.
<br flush=all/>
<FORM ACTION="forgot2.jsp" METHOD=POST>
	<tr><td>Name:</td><td><INPUT TYPE=TEXT SIZE=8 NAME=nick></td></tr>
	<tr><td></td><td><INPUT TYPE=SUBMIT VALUE="Mail it!" ALIGN=CENTER></td></tr>
	</table>
</FORM>
If all else fails, email the site administrator and we'll try
to get you back on the air.
</BODY>
</HTML>
