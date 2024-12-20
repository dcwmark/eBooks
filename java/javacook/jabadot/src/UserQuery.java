import jabadot.*;

import java.sql.*;
import java.io.*;
import java.util.*;

/** Look up one use from the relational database using JDBC.
 */
public class UserQuery {

	public static void main(String[] fn)
	throws ClassNotFoundException, SQLException, IOException {

		// Load the database driver
		Class.forName(JDConstants.getProperty("jabadot.jabadb.driver"));

		System.out.println("Getting Connection");
		Connection conn = DriverManager.getConnection(
			JDConstants.getProperty("jabadot.dburl"));

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(
			"SELECT * from jabadb where name='ian'");

		// Now retrieve (all) the rows that matched the query
		while (rs.next()) {

			// Field 1 is login name
			String name = rs.getString(1);

			// Password is field 2 - do not display.

			// Column 3 is fullname
			String fullName = rs.getString(3);

			System.out.println("User " + name + " is named " + fullName);
		}

		rs.close();			// All done with that resultset
		stmt.close();		// All done with that statement
		conn.close();		// All done with that DB connection
		System.exit(0);		// All done with this program.
	}
}
