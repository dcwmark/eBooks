<%@page errorPage="oops.jsp" import="jabadot.*" %>
<!-- 
  -- $Id: chessboard.jsp,v 1.2 2001/04/13 01:07:34 ian Exp $
  -->
<HTML>
<%@include file="header.html" %>
<!--
  -- No ad banner here -- too repetetive!
  -->
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null) {
 %>
<TITLE>You must be logged on!</TITLE>
<BODY BGCOLOR="white">
<H1>You must be logged on!</H1>
<P>The service you are requesting requires that you be logged in to our site.
Please go <a href="/jabadot/">back</a> and try again.  Thank you!
<%	
	// return;				UNCOMMMENT WHEN WORKING
	}
 // ELSE YOU'RE LOGGED IN... Are you in a Game?
	ChessGame game = (ChessGame) session.getAttribute("jaba.chess.game");
	if (game == null) {
 %>
<TITLE>You are not in a game</TITLE>
<BODY BGCOLOR="white">
<H1>You are not in a game?</H1>
<P>To view this page you must be either in a game, or watching one.
Please go <a href="/jabadot/chess.jsp">back</a> and join one.
<P><b>for now, I'm creating a game for you... Don't hold me to it.</b>
<%
	game = new ChessGame();
	// return;						un-comment when joingame working
	}
 %>
<TITLE>Welcome to jaba.Chess!</TITLE>
<BODY BGCOLOR="white">
<H1>Welcome to jaba.Chess</H1>
<table border=1 width=100%>
<%
	for (int r=0; r<8; r++) {
	out.print("<tr>");
	for (int c=0; c<8; c++) {
 %>
		<td align="center" bgcolor=<%= (((c%2) + (r%2))%2 == 0) ? "#33cc33" : "#666666" %>>
		&nbsp;<br><%= game.board[r][c] %><br>&nbsp;
		</td>
<%
	}
	out.println("</tr>");
	}
 %>
</table>
<H2>Next move: White (joe)</H2>
<P>It's not your move now. If it were, you could fill in this form.
<form>
	Move: <input type=text> (enter in standard chess notation, ie kp4)
</form>
</body>
</html>
