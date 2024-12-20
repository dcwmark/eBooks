import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

/** A User Database Dump program
 * <p>
 * DUMPS PASSWORDS -- BEWARE SHOULDER SURFERS!!!!!!!!!!!!!!!
 *
 * @version $Id: JDDump.java,v 1.4 2001/10/12 23:02:17 ian Exp $
 */
public class JDDump {

	/** Main program -- all-in-one */
	public static void main(String av[]) throws Exception {

		String dbDriver = JDConstants.getProperty("jabadot.jabadb.driver");
		Class.forName(dbDriver);
		Connection conn = DriverManager.getConnection(
			JDConstants.getProperty("jabadot.jabadb.url"));

		Statement myStmt = conn.createStatement();

		ResultSet rs = myStmt.executeQuery("select * from UserDB");
		while (rs.next()) {
			String nick = rs.getString(UserDB.NAME);
			String mpw  = rs.getString(UserDB.PASSWORD);
			String fname= rs.getString(UserDB.FULLNAME);
			String email= rs.getString(UserDB.EMAIL); 
			String city = rs.getString(UserDB.CITY);
			String prov = rs.getString(UserDB.PROVINCE);
			String cntry= rs.getString(UserDB.COUNTRY);
			int privs   = rs.getInt(UserDB.PRIVS);
			System.out.print(nick + ":" + mpw + ":" + fname);
			if (privs != 0) System.out.print(" (privs="+privs+")");
			System.out.println();
		}
		rs.close();
	}
}
