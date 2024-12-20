/**** DatabaseTool.java ****/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.sql.*;

public class DatabaseTool extends Frame implements ActionListener {
    //
    // Constants:
    //
    private static final String APP_NAME = "DatabaseTool";
    private static final String TITLE = "Database Tool";
//  private static final String DRIVER =
//    "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String DRIVER =
    "COM.cloudscape.core.JDBCDriver";
    private static final String PROTOCOL = "jdbc";
//  private static final String SUBPROTOCOL = "odbc";
    private static final String SUBPROTOCOL = "cloudscape";
    private static final String SUB_DATA   = "datasource";
    private static final int DRIVER_WIDTH  = 25;
    private static final int URL_WIDTH     = 25;
    private static final int USER_WIDTH    = 15;
    private static final int PW_WIDTH      = 15;
    private static final int MESSAGE_WIDTH = 70;
    private static final int SQL_WIDTH     = 80;
    private static final int RESULT_WIDTH  = 80;
    private static final int STARTS_WITH_LEN = 40;
    private static final String LINEFEED = "\n";
    private static final String DOUBLE_LINEFEED = "\n\n";
    //
    // Instance variables:
    //
    private Button connect, previous, next, submit, clear;
    private TextField urlField, driverField;
    private TextField userField, passwordField;
    private TextArea sqlArea;
    private TextArea resultArea;
    private TextField messageField;
    private DatabaseMetaData dmd;
    private Vector submissions;
    private int submitIndex = -1;
    private Connection con = null;
    private Statement stmt = null;

    public DatabaseTool(String title, String subprotocol,
                        String data, String driver) {
        super(title);
        submissions = new Vector(50);
        setLayout(new BorderLayout(5, 5));
        Panel outerPanel = new Panel() {
            public Insets getInsets() {
                return new Insets(5, 5, 5, 5);
            }
        };
        outerPanel.setLayout(new BorderLayout(5, 5));
        add(outerPanel, BorderLayout.CENTER);
        Panel urlPanel = new Panel();
        outerPanel.add(urlPanel, BorderLayout.NORTH);
        Panel middlePanel = new Panel();
        middlePanel.setLayout(new BorderLayout(5, 5));
        outerPanel.add(middlePanel, BorderLayout.CENTER);
        Panel securityPanel = new Panel();
        middlePanel.add(securityPanel, BorderLayout.NORTH);

        urlPanel.add(new Label("Driver:"));
        driver = checkValue(DRIVER, driver);
        driverField = new TextField(driver, DRIVER_WIDTH);
        driverField.addActionListener(this);
        urlPanel.add(driverField);
        urlPanel.add(new Label("    URL:"));
        String url = PROTOCOL + ":";
        url += checkValue(SUBPROTOCOL, subprotocol);
        url += ":";
        url += (data == null || data.length() == 0) ? SUB_DATA : data;
        urlField = new TextField(url, URL_WIDTH);
        urlField.addActionListener(this);
        urlPanel.add(urlField);
        connect = new Button(" Connect ");
        connect.addActionListener(this);
        urlPanel.add(connect);

        securityPanel.add(new Label("User:"));
        userField = new TextField(USER_WIDTH);
        userField.addActionListener(this);
        securityPanel.add(userField);
        securityPanel.add(new Label("    Password:"));
        passwordField = new TextField(PW_WIDTH);
        passwordField.addActionListener(this);
        securityPanel.add(passwordField);

        Panel innerPanel = new Panel();
        innerPanel.setLayout(new BorderLayout(5, 5));
        middlePanel.add(innerPanel, BorderLayout.CENTER);
        Panel sqlPanel = new Panel();
        sqlPanel.setLayout(new BorderLayout(5, 5));
        innerPanel.add(sqlPanel, BorderLayout.NORTH);

        sqlPanel.add(new Label("SQL:", Label.CENTER), BorderLayout.NORTH);
        sqlArea = new TextArea(5, SQL_WIDTH);
        sqlPanel.add(sqlArea, BorderLayout.CENTER);
        Panel buttonPanel = new Panel();
        clear = new Button(" Clear ");
        clear.addActionListener(this);
        clear.setEnabled(false);
        buttonPanel.add(clear);
        previous = new Button(" Previous ");
        previous.addActionListener(this);
        previous.setEnabled(false);
        buttonPanel.add(previous);
        next = new Button(" Next ");
        next.addActionListener(this);
        next.setEnabled(false);
        buttonPanel.add(next);
        submit = new Button(" Submit ");
        submit.addActionListener(this);
        submit.setEnabled(false);
        buttonPanel.add(submit);
        sqlPanel.add(buttonPanel, BorderLayout.SOUTH);

        Panel resultPanel = new Panel();
        resultPanel.setLayout(new BorderLayout(5, 5));
        innerPanel.add(resultPanel, BorderLayout.CENTER);
        resultPanel.add(new Label("Result:", Label.CENTER), BorderLayout.NORTH);
        resultArea = new TextArea(15, RESULT_WIDTH);
        resultArea.setEditable(false);
        resultPanel.add(resultArea, BorderLayout.CENTER);

        Panel messagePanel = new Panel();
        outerPanel.add(messagePanel, BorderLayout.SOUTH);
        messagePanel.add(new Label("Status:"));
        messageField = new TextField(MESSAGE_WIDTH);
        messageField.setEditable(false);
        messagePanel.add(messageField);
        pack();
        setSize(getPreferredSize());
        setVisible(true);
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                        closeStatement();
                        closeDB();
                        System.exit(0);
                    }
                }
            }
        );
    }

  public DatabaseTool(String title, String subprotocol,
      String data) {
    this(title, subprotocol, data, DRIVER);
  } // DatabaseTool //

  public DatabaseTool(String title, String subprotocol) {
    this(title, subprotocol, SUB_DATA, DRIVER);
  } // DatabaseTool //

  public DatabaseTool(String title) {
    this(title, SUBPROTOCOL, SUB_DATA, DRIVER);
  } // DatabaseTool //

  public DatabaseTool() {
    this(TITLE, SUBPROTOCOL, SUB_DATA, DRIVER);
  } // DatabaseTool //

  public static void main(String[] args) {
    if (args.length > 3 ||
        (args.length > 0 &&
          (args[0].equalsIgnoreCase("usage") ||
          args[0].equalsIgnoreCase("-usage") ||
          args[0].equalsIgnoreCase("help") ||
          args[0].equalsIgnoreCase("-help")))) {
      System.out.println("Usage: java " + APP_NAME +
        " [<subprotocol> <data> <driver>]");
      return;
    }
    if (args.length == 1) {
      new DatabaseTool(TITLE, args[0]);
    }
    else if (args.length == 2) {
      new DatabaseTool(TITLE, args[0], args[1]);
    }
    else if (args.length == 3) {
      new DatabaseTool(TITLE, args[0], args[1], args[2]);
    }
    else
      new DatabaseTool();
  } // main //

    private String checkValue(String alternateValue, String str) {
        return (str == null || str.length() == 0) ? alternateValue : str;
    }

    private void handleMessage(String msg) {
        System.out.println(msg);
        messageField.setText(msg);
    }

    private boolean connectToDB() {
        closeDB();
        submit.setEnabled(false);
        try {
            String driver = driverField.getText();
            if (driver.length() == 0)
                driver = DRIVER;
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(urlField.getText(),
                                              userField.getText(),                                                            passwordField.getText());
            stmt = con.createStatement();
            dmd = con.getMetaData();
            String result = "Connected to: " + dmd.getURL() + LINEFEED +
                            "Driver: "  + dmd.getDriverName() + LINEFEED +
                            "Version: " + dmd.getDriverVersion();
            resultArea.setText(result);
            messageField.setText("Successfully connected to database.");
            submit.setEnabled(true);
            return true;
        }
        catch (Exception e) {
            handleMessage("Error connecting to database: " + urlField.getText());
            return false;
        }
    }

    private void closeDB() {
        try {
            if (con != null)
                con.close();
        }
        catch (Exception e) {
            System.out.println("Error closing the database connection: " +
                               urlField.getText());
        }
    }

    private void closeStatement() {
        try {
            if (stmt != null)
                stmt.close();
        }
        catch (Exception e) {
            System.out.println("Error closing the current statement.");
        }
    }

  private void displayResultSet(ResultSet rs) {
    String result = "";
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int cols = rsmd.getColumnCount();
      for (int i = 1; i <= cols; i++) {
        if (i > 1)
          result += ", ";
        result += rsmd.getColumnLabel(i);
      }
      result += DOUBLE_LINEFEED;
      while (rs.next()) {
        for (int i = 1; i <= cols; i++) {
          if (i > 1)
            result += ", ";
          result += rs.getString(i);
        }
        result += LINEFEED;
      }
      resultArea.setText(result);
      messageField.setText("");
    }
    catch (Exception e) {
      handleMessage("Error displaying the current statement.");
    }
  } // displayResultSet //

    private void executeSQL() {
        if (con == null) {
            messageField.setText("Please connect to a database.");
            return;
        }
        String cmd = sqlArea.getText();
        int len = cmd.length();
        if (len == 0) {
            messageField.setText("SQL statement area is empty.");
            return;
        }
        if (len < 6) {
            messageField.setText("SQL statement is invalid.");
            return;
        }
        submissions.addElement(cmd);
        submitIndex = submissions.lastIndexOf(cmd, submissions.size() - 1);
        updateButtons();
        try {
            String keyword = cmd.substring(0, 6);
            int startLength = (len < STARTS_WITH_LEN) ? len : STARTS_WITH_LEN;
            String cmdStart = cmd.substring(0, startLength);
            if (keyword.equalsIgnoreCase("select")) {
                ResultSet rs = stmt.executeQuery(cmd);
                displayResultSet(rs);
                rs.close();
                messageField.setText("Executed query: " +
                cmdStart + "...");
            }
            else if (keyword.equalsIgnoreCase("update") ||
                keyword.equalsIgnoreCase("insert") ||
                keyword.equalsIgnoreCase("delete")) {
                int result = stmt.executeUpdate(cmd);
                messageField.setText("Executed update: " + cmdStart +
                                     "... with result: " + result);
            }
            else {
                stmt.execute(cmd);
                messageField.setText("Executed: " + cmdStart + "...");
            }
        }
        catch (Exception e) {
            handleMessage("Error executing the current statement.");
        }
    }

    private void getPreviousSubmission() {
        if (submitIndex <= 0)
            return;
        submitIndex--;
        sqlArea.setText((String) submissions.elementAt(submitIndex));
        updateButtons();
    }

    private void getNextSubmission() {
        if (submitIndex >= submissions.size() - 1)
            return;
        submitIndex++;
        sqlArea.setText((String) submissions.elementAt(submitIndex));
        updateButtons();
    }

    private void updateButtons() {
        previous.setEnabled(submitIndex > 0);
        next.setEnabled(submitIndex < submissions.size() - 1);
        clear.setEnabled(sqlArea.getText().length() > 0);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == connect ||
            source == driverField || source == urlField ||
            source == userField || source == passwordField) {
            connectToDB();
        }
        else if (source == previous) {
            getPreviousSubmission();
        }
        else if (source == next) {
            getNextSubmission();
        }
        else if (source == submit) {
            executeSQL();
        }
        else if (source == clear) {
          sqlArea.setText("");
          resultArea.setText("");
          messageField.setText("");
        }
    }
}
