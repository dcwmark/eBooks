/**** MusicCDInsertRecords.java ****/

import java.sql.*;

public class MusicCDInsertRecords {
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

      stmt.executeUpdate("insert into MUSICCD values ('74646732120', 'Retrospective', 'Rosanne Cash', 'Country', 13.95)");
      stmt.executeUpdate("insert into MUSICCD values ('731454058728', 'Sheryl Crow', 'Sheryl Crow', 'Rock', 13.95)");
      stmt.executeUpdate("insert into MUSICCD values ('77778929727', 'Common Ground', 'Everette Harp', 'Jazz', 13.95)");
      stmt.executeUpdate("insert into MUSICCD values ('8811163020', 'Piel de Angel', 'Lucero', 'Latin', 13.95)");
      stmt.executeUpdate("insert into MUSICCD values ('706301572627', 'Las Cosas Que Vives', 'Laura Pausini', 'Latin', 13.95)");
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
          "Usage: java MusicCDInsertRecords" +
          " [<driver>] [<url>]");
      return true;
    }
    else
      return false;
  }
} // MusicCDInsertRecords class //
