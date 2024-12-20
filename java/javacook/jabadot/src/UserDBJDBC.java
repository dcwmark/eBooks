package jabadot;

import java.sql.*;
import java.io.*;
import java.util.*;

/** A UserDB using JDBC and a relational DBMS..
 * We use the inherited getUser ("Find the User object for a given nickname")
 * since we keep everything in memory in this version.
 */
public class UserDBJDBC extends UserDB {

	protected PreparedStatement setPasswordStatement;
	protected PreparedStatement addUserStmt;
	protected PreparedStatement setLastLoginStmt;
	protected PreparedStatement deleteUserStmt;

	/** insert the 19 fields into the user database */
	final static String SQL_INSERT_USER =
		"insert into users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/** Default constructor */
	protected UserDBJDBC() 
	throws ClassNotFoundException, SQLException, IOException {
		super();


		// Load the database driver
		String className = JDConstants.getProperty("jabadot.jabadb.driver");
		Class.forName(className);

		Connection conn = DriverManager.getConnection(
			JDConstants.getProperty("jabadot.jabadb.url"),
			JDConstants.getProperty("jabadot.jabadb.user"),
			JDConstants.getProperty("jabadot.jabadb.password"));

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from users");

		while (rs.next()) {
			//name:password:fullname:City:Prov:Country:privs

			// Get the fields from the query.
			// Should be an Entity EJB with CMP: this is unnecessarily 
			// chummy with the SQL. See CreateUserDatabase.java for field#'s!
			String nick = rs.getString(1);
			String pass = rs.getString(2);
			String first = rs.getString(3);
			String last = rs.getString(4);
			String email = rs.getString(5);
			String company = rs.getString(6);
			String addr1 = rs.getString(7);
			String addr2 = rs.getString(8);
			String city = rs.getString(9);
			String prov = rs.getString(10);
			String ctry = rs.getString(11);
			// java.sql.Date credt = rs.getDate(12);
			// java.sql.Date lastlog = rs.getDate(13);
			String skin = rs.getString(14);
			String job = rs.getString(15);
			String os = rs.getString(16);
			String gui = rs.getString(17);
			String lang = rs.getString(18);
			int iprivs = rs.getInt(19);

			// Construct a user object from the fields
			User u = new User(nick, pass, first, last, email,
				company, addr1, addr2, city, prov, ctry, 
				skin, job, os, gui, lang);
			// u.setCreationDate(credt);
			// u.setLastLoginDate(lastlog);
			u.setPrivs(iprivs);

			// Add it to the in-memory copy.
			users.add(u);
		}
		stmt.close();
		rs.close();		// All done with that resultset

		// Set up the PreparedStatements now so we don't have to
		// re-create them each time needed.
		addUserStmt = conn.prepareStatement(SQL_INSERT_USER);
		setPasswordStatement = conn.prepareStatement(
			"update users SET password = ? where name = ?");
		setLastLoginStmt = conn.prepareStatement(
			"update users SET lastLogin = ? where name = ?");
		deleteUserStmt = conn.prepareStatement(
			"delete from users where name = ?");
	}

	/** Add one user to the list, both in-memory and on disk. */
	public synchronized void addUser(User nu)
	throws IOException, SQLException {
		// Add it to the in-memory list
		super.addUser(nu);

		// Copy fields from user to DB
		// XXX WAY INCOMPLETE NOW
		addUserStmt.setString(1, nu.name);
		addUserStmt.setString(2, nu.password);
		addUserStmt.setString(3, nu.firstName); 
		addUserStmt.setString(4, nu.lastName);
		addUserStmt.setString(5, nu.email);
		addUserStmt.setString(6, nu.company);
		addUserStmt.setString(7, nu.address);
		addUserStmt.setString(8, nu.address2);
		addUserStmt.setString(9, nu.city);
		addUserStmt.setString(10, nu.prov);
		addUserStmt.setString(11, nu.country);
		// java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
		// addUserStmt.setDate(12, now);
		// addUserStmt.setDate(13, now);
		addUserStmt.setString(14, nu.jobDescr);
		addUserStmt.setString(15, nu.os);
		addUserStmt.setString(16, nu.unixGUI);
		addUserStmt.setString(17, nu.proglang);
		addUserStmt.setString(18, nu.skin);
		addUserStmt.setInt   (19, nu.getPrivs());

		// Store in persistent DB
		addUserStmt.executeUpdate();
	}

	public void deleteUser(String nick) throws SQLException {
		// Find the user object
		User u = getUser(nick);
		if (u == null) {
			throw new SQLException("User " + nick + " not in in-memory DB");
		}
		deleteUserStmt.setString(1, nick);
		int n = deleteUserStmt.executeUpdate();
		if (n != 1) {	// not just one row??
			/*CANTHAPPEN */
			throw new SQLException("ERROR: deleted " + n + " rows!!");
		}

		// IFF we deleted it from the DB, also remove from the in-memory list
		users.remove(u);
	}

	public synchronized void setPassword(String nick, String newPass) 
	throws SQLException {

		// Find the user object
		User u = getUser(nick);

		// Change it in DB first; if this fails, the info in
		// the in-memory copy won't be changed either.
		setPasswordStatement.setString(1, newPass);
		setPasswordStatement.setString(2, nick);
		setPasswordStatement.executeUpdate();

		// Change it in-memory
		u.setPassword(newPass);
	}

	/** Update the Last Login Date field. */
	public synchronized void setLoginDate(String nick, java.util.Date date) 
	throws SQLException {
	
		// Find the user object
		User u = getUser(nick);

		// Change it in DB first; if this fails, the date in
		// the in-memory copy won't be changed either.
		// Have to convert from java.util.Date to java.sql.Date here.
		// Would be more efficient to use java.sql.Date everywhere.
		setLastLoginStmt.setDate(1, new java.sql.Date(date.getTime()));
		setLastLoginStmt.setString(2, nick);
		setLastLoginStmt.executeUpdate();

		// Change it in-memory
		u.setLastLoginDate(date);
	}
}
