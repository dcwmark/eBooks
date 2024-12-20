<%@page errorPage="oops.jsp" import="jabadot.*, java.util.*" %>
<head>
<!-- 
  -- $Id: search.jsp,v 1.8 2001/04/13 01:07:38 ian Exp $
  -->
<TITLE>JabaDot: Search</TITLE>
</head>
<BODY BGCOLOR="white">
<%@include file="header.html" %>
<H1>JabaDot: Search</H1>
<% String query = request.getParameter("query");
	if (query == null || query.length() == 0) {
		out.println("Please enter your query in the search box.");
		out.println("At this point the matching is text-only and");
		out.println("is also case sensitive (sniff).");
		return;
	}
	NewsArticleDB theNews = new NewsArticleDB();
	Iterator it = theNews.getCurrentArticles().iterator();
	int found = 0;
	out.print("<ul>");
	while (it.hasNext()) {
		NewsArticle a = (NewsArticle)it.next();
		if (a.contains(query)) {
			found++;
			out.print("<li><a href=\"article.jsp?article=" + 
				a.getFilename() + "\">" +
				a.getSubject() + "</a>" + a.getDate() + "</li>");
		}
	}
	out.print("</ul>");
	switch (found) {
		case 0:
			out.print("Sorry, nothing found.");
			break;
		case 1:
			out.print("One article found.");
			break;
		default:
			out.print("We found ");
			out.print(found);
			out.print(" articles for your query.");
		}
%>

