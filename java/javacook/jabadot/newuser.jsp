<%@page errorPage="oops.jsp" import="jabadot.*, java.io.*" %>
<!-- 
  -- $Id: newuser.jsp,v 1.16 2001/10/27 18:52:34 ian Exp $
  -- Creates a new user account.
  -->
<%! java.util.Random r = new java.util.Random(); %>
<jsp:useBean id="newUserBean" scope="request" class="jabadot.User">
	<jsp:setProperty name="newUserBean" property="*"/>
</jsp:useBean>
<jsp:useBean id="mailBean" scope="request" class="jabadot.Mailer"/>
<html>
<%@include file="header.html" %>
<%
	// out.println("<!-- in header -->");
	User user = (User)session.getAttribute("jabadot.login");
	if (user != null) {
 %>
<TITLE>You're already logged on!</TITLE>
<H1>You are logged on!</H1>
<P>Please <A href="logout.jsp">log out</A> before
trying to create a new account.  Thank you!
<%	return;
	}
 %>
<% // Now see if they already filled in the form or not...
	if (!newUserBean.isComplete()) {
		// out.println("<!-- in new -->");
 %>
		<TITLE>Welcome New User - Please fill in this form.</TITLE>
		<BODY BGCOLOR=White>
		<H1>Welcome New User - Please fill in this form.</H1>
		<p><b>The only required fields are login name,
		first name, last name, and email address</b>.</p>
		<P>Please be sure your email address is correct -
		we'll make up an initial password for you,
		and email to you a URL to download the password from.
		Watch your email closely after you complete the form. Thank you!
		<FORM ACTION="newuser.jsp" METHOD=post>
			<!-- inner table so fields line up -->
			<table border="0" cellspacing="2" cellpadding="2">
			<tr><td bgcolor="#ffffcc" width="25%"><b>Login name: *</b></td>
				<td><input type=text size="30" name="name"></td></tr>
			<tr>
			<td bgcolor="#ffffcc"><font size="3"><b>First Name: * </b></font></td>
			<td><input size="30" name="firstName"/></td>
			</tr>
			<tr>
			<td bgcolor="#ffffcc"><font size="3"><b>Last Name: * </b></font></td>
			<td><input size="30" name="lastName"/></td>
			</tr>
			<tr>
			<td bgcolor="#ffffcc"><font size="3"><b>Email Address: *</b></font></td>
			<td><input size="30" name="email"/></td>
			</tr>
			<tr>
			<td bgcolor="#ffffcc"><font size="3"><b>Company:</b></font></td>
			<td><input size="30" name="company"/></td>
			</tr>
			<tr> 
			<td bgcolor="#ffffcc"><font size="3"><b>Address:</b></font></td>
			<td><input size="30" name="address1"/></td> 
			</tr> 
			<tr> 
			<td bgcolor="#ffffcc"><font size="3"><b>Address (continued):</b></font></td>
			<td><input size="30" name="address2"/></td> 
			</tr> 
			<tr> 
			<td bgcolor="#ffffcc"><font size="3"><b>City:</b></font></td>
			<td><input size="30" name="city"/></td>
			</tr>
			<tr>
			<td bgcolor="#ffffcc"><font size="3"><b>State/Province:</b></font></td>
			<td><input size="30" name="prov"/></td>
			</tr>
			<tr>
			<td bgcolor="#ffffcc"><font size="3"><b>Postal Code:</b></font></td>
			<td><input size="30" name="code"/></td>
			</tr>
			<tr><td bgcolor="#ffffcc"><b>Country</b></td>
				<td><select name="location">
				<jsp:include page="country_select.html" flush="true"/>
				</select>
				</td></tr>
			<tr>
				<td bgcolor="#ffffcc"><font size="3"><b>Appearance</b></font></td>
				<td><select name="skin">
					<option selected value="jabadot_green">JabaDot Green
					<option value="red_mishmash">Red Mishmash
					<option value="bare_yellow">Bare Yellow
				</select>
				</td>
			</tr>


			<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

			<tr bgcolor="yellow">
			<td colspan="2">
				Warning: fields below here are not being collected
				into the database, so, like, why bother?
			</tr>

			<tr>
			<td valign="top" bgcolor="#ffffcc">
			<font size="3"><b>What is your job description?</b></font><br />
			<font size="-1">
			<td>
			<input type="radio" name="job_desc" value="System"/>Security Consultant<br />
			<input type="radio" name="job_desc" value="System"/>System Administrator<br />
			<input type="radio" name="job_desc" value="Network"/>Network Administrator<br />
			<input type="radio" name="job_desc" value="Web"/>Web Developer<br />
			<input type="radio" name="job_desc" value="Programmer"/>Programmer<br />
			<input type="radio" name="job_desc" value="Educator"/>Educator/Teacher<br />
			<input type="radio" name="job_desc" value="Other">Other: </font> <input type="text" size="28" name="job_desc_other"/></td>
			</tr>

			<tr>
			<td valign="top" bgcolor="#ffffcc">
			<font size="3"><b>What operating system(s) do you use?</b></font><br />
			<font size="-1">(Check all that apply)
			<td>
			<input type="checkbox" name="os" value="OpenBSD"/>OpenBSD<br />
			<input type="checkbox" name="os" value="NetBSD"/>NetBSD<br />
			<input type="checkbox" name="os" value="FreeBSD"/>FreeBSD<br />
			<input type="checkbox" name="os" value="BSD/OS"/>BSD/OS<br />
			<input type="checkbox" name="os" value="Linux"/>Linux<br />
			<input type="checkbox" name="os" value="Solaris"/>Solaris<br />
			<input type="checkbox" name="os" value="SunOS4"/>SunOS4<br />
			<input type="checkbox" name="os" value="HP/UX"/>HP/UX<br />
			<input type="checkbox" name="os" value="AIX"/>AIX<br />
			<input type="checkbox" name="os" value="Other Commercial UNIX"/>Other Commercial UNIX<br />
			<input type="checkbox" name="os" value="Windows"/>MS-Windows NT<br />
			<input type="checkbox" name="os" value="macos678"/>Macintosh OS6,7,8<br />
			<input type="checkbox" name="os" value="macosX"/>Macintosh OS X<br />
			<input type="checkbox" name="os" value="PC"/>PC (Windows 3/DOS)<br />
			<input type="radio" name="job_desc" value="Other">Other: </font> <input type="text" name="os_other" size="24"/>
			</td>
			</tr>

			<tr>
			<td valign="top" bgcolor="#ffffcc">
			<font size="3"><b>If a UNIX user, which GUI(s) do you use?</b></font><br />
			<font size="-1">(Check all that apply)
			<td>
			<input type="checkbox" name="uxgui" value="CDE"/>CDE<br />
			<input type="checkbox" name="uxgui" value="KDE"/>KDE<br />
			<input type="checkbox" name="uxgui" value="Gnome"/>Gnome<br />
			<input type="checkbox" name="uxgui" value="E"/>E/Enlightenment<br />
			<input type="checkbox" name="uxgui" value="W"/>WindowMaker<br />
			<input type="radio" name="job_desc" value="Other">Other: </font> <input type="text" name="uxgui_other" size="24"/>
			</tr>

			<tr>
			<td valign="top" bgcolor="#ffffcc">
			<font size="3"><b>If a developer, what language(s) do you use regularly?</b></font>
			<font size="-1">(Check all that apply)
			<td>
			<input type="checkbox" name="proglang" value="Java"/>Java (general)<br />
			<input type="checkbox" name="proglang" value="servlet"/>Java Servlet/JSP<br />
			<input type="checkbox" name="proglang" value="C"/>C<br />
			<input type="checkbox" name="proglang" value="C++"/>C++<br />
			<input type="checkbox" name="proglang" value="Perl"/>Perl<br />
			<input type="checkbox" name="proglang" value="Python"/>Python<br />
			<input type="checkbox" name="proglang" value="JavaScript"/>JavaScript<br />
			<input type="radio" name="job_desc" value="Other">Other: </font> <input type="text" name="proglang_other" size="24"/>
			</td>
			</tr>

			XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

			<tr><td colspan=2 align="center">
				<INPUT TYPE=SUBMIT VALUE="Create My JabaDot!"></tr>
			</table><!-- Inner table for forms. -->
		</FORM>
		<p>Speaking of email, you can join several email lists by visiting
		<a href="http://www.darwinsys.com/lists/">our lists page</a>.
<%		return;
		}

		// out.println("<!-- in get -->");
		String nick = newUserBean.getName();
		if (UserDB.getInstance().getUser(nick) != null) {
 %>
			<P>It seems that that user name is already in use!
			Please go back and pick another name.
			<% return;
			} %>
<%
		String fullname = newUserBean.getFullName();
		String email = newUserBean.getEmail();
 %>
		<!-- Give the user a welcome -->
		Welcome <%= fullname %>.
		We will mail you (at <%= email %>) with a URL
		from which you can download your initial password.

		<jsp:setProperty name="newUserBean" 
			property="editPrivileged" value="false"/>
		<jsp:setProperty name="newUserBean" 
			property="adminPrivileged" value="false"/>
<%
		// Generate initial random password and store it in the User
		String newPass = Password.getNext().toString();
		newUserBean.setPassword(newPass);

		// NOW add the user to the persistent database.
		UserDB.getInstance().addUser(newUserBean);

		// Create a temporary HTML file containing the full name
		// and the new password, and mail the URL for it to the user.
		// This will confirm that the user gave us a working email.
		// NEVER show the nickname and the password together!
		String tempDir = JDConstants.getProperty("jabadot.tmp_links_dir");
		File tempLink = File.createTempFile(
			r.nextInt()+"$PW", ".html", new File(tempDir));
		PrintWriter pw = new PrintWriter(new FileWriter(tempLink));
		pw.print("<HTML><BODY>");
		pw.print("Greetings ");
		pw.print(newUserBean.getFullName());
		pw.print(". Your new password for accessing JabaDot is <B>");
		pw.print(newPass);
		pw.print("</B>. Please remember this, or better yet, ");
		pw.print("<a href=\"/jabadot/index.jsp\">");
		pw.print("login</a> now!");
		pw.print("You may want to visit \"My Jabadot\"");
		pw.print("and change this password after you log in.");
		pw.println("</HTML>");
		pw.close();

		// Now we have to mail the URL to the user.
		mailBean.setFrom(JDConstants.getProperty("jabadot.mail_from"));
		mailBean.setSubject("Welcome to JabaDot!");
		mailBean.addTo(email);
		mailBean.setServer(JDConstants.getProperty("jabadot.mail.server.smtp"));

		// Get our URL, strip off "newuser.jsp", append "/tmp/"+tmpname
		StringBuffer getPW_URL = HttpUtils.getRequestURL(request);
		int end = getPW_URL.length();
		int start = end - "newuser.jsp".length();
		getPW_URL.delete(start,end).append("tmp/").append(tempLink.getName());
		mailBean.setBody("To receive your JabaDot password,\n" +
			"please visit the URL " + getPW_URL);

		// Now send the mail.
		mailBean.doSend();

		// AVOID the temptation to sess.setAttribute() here, since
		// the user has not yet verified their password!
 %>
