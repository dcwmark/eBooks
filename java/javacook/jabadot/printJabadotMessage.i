<%
	// Any message for user?
	String mesg;
	if ((mesg = (String)session.getAttribute("jabadot.message")) != null) {
		out.println(mesg);
		session.removeAttribute("jabadot.message");
	}
 %>
