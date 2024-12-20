<%@page errorPage="oops.jsp" import="java.util.*,jabadot.*" %>
<html>
<head><title>JabaDot: Who's On?</title></head>
<!-- 
  -- $Id: whoson.jsp,v 1.6 2001/10/12 23:02:16 ian Exp $
  -->
<H1>JabaDot: Who's On First?</H1>
<%
	User u = (User)session.getAttribute("jabadot.login");
	if (u == null) {
 %>
 	<h2>Jabadot: login first</h2>
 	<p>You must be logged in to view the active users.</p>
<%
	return;
	}
 %>
<P>Is anybody home? Let's see:

<% TreeMap list = (TreeMap)application.getAttribute("jabadot.wtmp");
	if (list == null) {
 %>
 		<b>Nobody home!</b>
<% } else {
 %>
<ul>
<%
		Iterator lister = list.values().iterator();
		String nick = null;
		int n = 0;
		while (lister.hasNext()) {
			++n;
			nick = ((User)lister.next()).getName();
			out.println("<li>" + nick + "</li>");
		}
		out.println("</ul>");
		if (n == 0) {
			out.println("<b>Nobody here (spooky!)</b>");
			log("Spooky!");
		} else if (n == 1) {
			if (nick.equals(u.getName())) {
				out.println("<b>just you, boss!</b>");
			} else {
				out.println("<b>Weird! You're not the one!</b>");
				log("Weird: u.getName() " + u.getName() + 
					", but 1 nick = " + nick);
			}
		} else {
			out.println(n + " users");
		}
  	}
 %>
</html>
