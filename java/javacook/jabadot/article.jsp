<%@page import="jabadot.*, java.io.*"%>
<!-- 
  -- $Id: article.jsp,v 1.4 2001/10/27 18:52:33 ian Exp $
  -->
<%
	String aid = request.getParameter("article");
	if (aid == null) {
		session.setAttribute("jabadot.message",
		"<B>No article ID</B><P>This page is designed to be called " +
		"with a parameter of the article ID.");
		response.sendRedirect("index.jsp");
	}
	try {
		int id = Integer.parseInt(aid);
	} catch (NumberFormatException ex) {
		session.setAttribute("jabadot.message",
		"<B>Invalid article ID</B><P>This page is designed to be called " +
		"with a NUMERIC parameter of the article ID." +
		"Our software was not impressed with \"" + aid + "\" as an article number");
		response.sendRedirect("index.jsp");
	}
	File f = new File(JDConstants.getProperty("jabadot.articles_dir") + aid);
	if (!f.exists()) {
		session.setAttribute("jabadot.message",
		"<B>Unknown article ID</B>" +
		"<P>We could not find article number \"" + aid + '.');
		response.sendRedirect("index.jsp");
	}
 %>
<HTML>
<TITLE>JabaDot: Article View</TITLE>
<%@include file="header.html" %>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<BODY BGCOLOR="white">
<H1>JabaDot: Article View</H1>
<%
	// String file = "file:" + dir + aid; // XXX Try this??
	String file = "articles/" + aid;
 %>
<pre>
<jsp:include page="<%= file %>"/>
</pre>
