<%@page errorPage="oops.jsp" import="jabadot.*, java.util.*" %>
<title>ServletContext information.</title>
<h1>ServletContext itself.</h1>
<%
	ServletContext ctx  = getServletContext();
	out.println("ServletContext = " + ctx);
 %>
<hr></hr>
<h1>List of init parameters.</h1>
<ul>
<%
	Enumeration e = ctx.getInitParameterNames();
	while (e.hasMoreElements()) {
		String x = (String)e.nextElement();
		out.println("<li>" + x + " = " + 
			ctx.getInitParameter(x));
	}
 %>
</ul>
<hr></hr>
<h1>List of attributes.</h1>
<ul>
<%
	Enumeration a = ctx.getAttributeNames();
	while (a.hasMoreElements()) {
		String x = (String)a.nextElement();
		out.println("<li>" + x + " = " + 
			ctx.getAttribute(x));
	}
 %>
</ul>
<hr></hr>
