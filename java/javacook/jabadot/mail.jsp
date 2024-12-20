<%@page errorPage="oops.jsp" import="jabadot.*" %>
<TITLE>JabaDot freE-Mail - Not Working Yet</TITLE>
<!-- 
  -- $Id: mail.jsp,v 1.8 2001/12/25 20:47:45 ian Exp $
  -->
<BODY BGCOLOR=WHITE>
<%@include file="header.html" %>
<H1>JabaDot freE-Mail</H1>
<P>This is trying to become a generalized Web-based mail client 
using the JavaMail API.
<p>It is not decided whether JabaDot will provide its own mail service
or will expect you to have a POP/IMAP account at a server or ISP.
The code here was cobbled together for Ian's own use, and is
hardly what you'd call a finished product.
</p>
<p>Note in particular that <b>folders are not supported</b>
so you can NOT move, undelete, etc.</b>
<hr />
<jsp:include page="/servlet/MailServlet" flush="true"/>
<hr />
