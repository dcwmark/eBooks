/**** MusicCDCreateTables.java ****/

import java.sql.*;

public class MusicCDCreateTables {
  public static void main(String[] args) {
    if (usageOnly(args))
      return;
    String driver = "COM.cloudscape.core.JDBCDriver";
    if (args.length > 0)
      driver = args[0];
    String url = "jdbc:cloudscape:MusicStoreDB";
    if (args.length > 1)
      url = args[1];
    try {
      Class.forName(driver).newInstance();
      Connection con = DriverManager.getConnection(url);
      System.out.println("Connected to: " + url);
      Statement stmt = con.createStatement();

      stmt.execute(
        "create table MUSICCD (" +
        "UPC varchar(30) primary key," +
        "TITLE varchar(50)," +
        "ARTIST varchar(30)," +
        "TYPE varchar(20)," +
        "PRICE decimal(10,2))"
      );
      stmt.close();
      con.close();
    }
    catch (SQLException ex) {
      System.out.println("\nSQLException...\n");
      while (ex != null) {
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("Message:  " + ex.getMessage());
        System.out.println("Vendor:   " + ex.getErrorCode());
        ex = ex.getNextException();
        System.out.println("");
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static boolean usageOnly(String[] args) {
    if (args.length > 0) {
      if (args[0].equalsIgnoreCase("-help") ||
          args[0].equalsIgnoreCase("-h") ||
          args[0].equalsIgnoreCase("-usage") ||
          args.length > 2)
        System.out.println(
          "Usage: java MusicCDCreateTables" +
          " [<driver>] [<url>]");
      return true;
    }
    else
      return false;
  }
} // MusicCDCreateTables class //
