<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: download.jsp,v 1.11 2001/10/27 18:52:33 ian Exp $
  -->
<HTML>
<TITLE>JabaDot: Download: Moved</TITLE>
<body bgcolor="white">
<%@include file="header.html" %>
<P ALIGN=CENTER><jsp:include page="/servlet/AdServlet" flush="true"/></P>
<H1>JabaDot: Download: Moved</H1>
<P>The software is included in the download for
Ian Darwin's <a href="http://javacook.darwinsys.com/">Java Cookbook</a> 
(O'Reilly & Associates, 2001).
<p>In addition, you will need the following:
<UL>
<LI>A JSP-enabled web server
(<a href="http://jakarta.apache.org/tomcat/">Apache Tomcat</a> will do nicely)
<LI>A relational database and JDBC driver!
I used <a href="http://www.lutris.com/">InstantDB from Lutris</A>.
<LI>
<a href="http://java.sun.com/products/javamail/">Java Mail and Activation</a>
(also included as part of 
<a href="http://java.sun.com/products/j2ee">J2EE</a>)
<LI>Possibly a mail reading protocol add-on, such as pop3.jar or
"file.jar" 
</UL>
<P>Thanks for visiting!
