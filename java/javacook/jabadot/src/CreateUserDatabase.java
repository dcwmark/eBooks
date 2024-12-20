package jabadot;

import java.sql.*;
import java.io.*;
import java.util.*;

/** Create the database using raw JDBC; create the Admin user
 * using UserDBJDBC. Used on any new JabaDot installation;
 * contains strong echoes of The Initial Magick.
 */
public class CreateUserDatabase {

	/** The database. XXX get from JDConstants */
	protected final static String DB_URL = "jdbc:idb:../jabadb.prp";

	BufferedReader is;

	public static void main(String[] fn)
	throws ClassNotFoundException, SQLException, IOException {
		CreateUserDatabase prog = new CreateUserDatabase();
		if (prog.ok())
			prog.goForthAndBePlentiful();
	}

	public CreateUserDatabase() throws IOException {
		is = new BufferedReader(new InputStreamReader(System.in));
	}

	/** Make sure you want to do this */
	public boolean ok() throws IOException {
		System.out.println(
			"+==============================================+");
		System.out.println(
			"Warning: This will wipe out any and all accounts");
		System.out.println(
			"+==============================================+");
		String response = prompt("Are you sure you really mean to do this?");
		return "YES I MEAN IT".equals(response);

	}

	/** Do the work */
	public void goForthAndBePlentiful()
	throws ClassNotFoundException, SQLException, IOException {

		// Load the database driver
		Class.forName("jdbc.idbDriver");

		System.out.println("Getting Connection");
		Connection conn = DriverManager.getConnection(
			DB_URL, "ian", "");	// user, password

		System.out.println("Creating Statement");
		Statement stmt = conn.createStatement();

		System.out.println("Creating table and index");
		stmt.executeUpdate("DROP TABLE userdb");	// old
		stmt.executeUpdate("DROP TABLE users");		// new
		stmt.executeUpdate("CREATE TABLE users (\n" +
			"name		char(12) PRIMARY KEY,\n" +	// 1
			"password	char(20),\n" +				// 2
			"firstName 	char(30),\n" +				// 3
			"lastName 	char(30),\n" +				// 4
			"email		char(40),\n" +				// 5
			"company	char(30),\n" +				// 6
			"address	char(30),\n" +				// 7
			"address2	char(30),\n" +				// 8
			"city		char(20),\n" +				// 9
			"prov		char(20),\n" +				// 10
			"country	char(20),\n" +				// 11
			"credt		date,\n" +					// 12
			"lastlog	date,\n" +					// 13
			"jobDescr   char(30),\n" +				// 14
			"opsys      char(30),\n" +				// 15
			"unixGUI    char(30),\n" +				// 16
			"proglang   char(30),\n" +				// 17
			"skin       char(12),\n" +				// 18
			"privs		int\n" +					// 19
			")");
		stmt.executeUpdate("CREATE INDEX nickIndex ON users (name)");
		stmt.close();

		// Database is now empty. Create the first user, out of the void
		String nick = prompt("Enter admin name:");
		String pass = prompt("Enter password (will echo!):");
		String fname = prompt("Enter first name:");
		String lname = prompt("Enter last name:");
		String email = prompt("Enter admin's email:");
		String city = prompt("Enter this JabaDot's city:");
		String prov = prompt("Enter this JabaDot's province:");
		String country = prompt("Enter this JabaDot's country:");


		int privs = User.P_ADMIN | User.P_EDIT;

		PreparedStatement ins = 
			conn.prepareStatement(UserDBJDBC.SQL_INSERT_USER);
		ins.setString(1, nick);
		ins.setString(2, pass);
		ins.setString(3, fname);
		ins.setString(4, lname);
		ins.setString(5, email);
		ins.setString(6, "JabaDot International");
		ins.setString(7, "");		// address
		ins.setString(8, "");		// address2
		ins.setString(9, city);
		ins.setString(10, prov);
		ins.setString(11, country);
		// ins.setDate(12, null);
		// ins.setDate(13, null);
		ins.setString(14, "JabaDot Site");
		ins.setString(15, "OpenBSD");
		ins.setString(16, "KDE");
		ins.setString(17, "Java");
		ins.setString(18, "bare_yellow");
		ins.setInt(19, privs);
		ins.executeUpdate();
			
		ins.close();	// Done with this statement
		conn.close();	// Done with this Connection
		return;			// All done with this program.
	}

	public String prompt(String message) throws IOException {
		String result = null;
		do {
			System.out.print(message); 
			System.out.print(' ');
			System.out.flush();
			result = is.readLine();
			if (result == null)	{
				System.err.println("Read end of file in prompt");
				System.exit(1);
			}
		} while (result.length() == 0);
		return result;
	}
}
