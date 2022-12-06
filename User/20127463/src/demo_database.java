

import java.util.HashMap;
import java.util.Map;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//This class and data just create statically for test GUI.
//The real database and how to get it will be update soon !

public class demo_database {
    static Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
    static Map<String, Map<String, Map<String, String>>> loginHistories = new HashMap<String, Map<String, Map<String, String>>>();
    
    private String DB_URL = "jdbc:mysql://localhost:3306/java_chatting_app";
    private String USER_NAME = "root";
    private String PASSWORD = "Duongminh410";

    public Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            // Return null if error occur and cannot connect to database
        } catch (Exception ex) {
            // Handle exception
            ex.printStackTrace();
        }
        return conn;
    }

    public Map<String, String> getSpecificData(String id) {
        return users.get(id);
    }
    public Map<String, Map<String, String>> getSpecificHistory(String id) {
        return loginHistories.get(id);
    }
    public Map<String, Map<String, Map<String, String>>> getHistory() {
        return loginHistories;
    }
    public Map<String, Map<String, String>> getData() {
        return users;
    }

    demo_database() {
        try {
            // Connect to database
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            // Check connetion result
            if(conn != null) {
                Statement user_stmt = conn.createStatement();
                Statement his_stmt = conn.createStatement();

                // Get data from table 'users'
                ResultSet user_rs = user_stmt.executeQuery("select * from users");
                // Import users data
                while (user_rs.next()) {
                    Map<String, String> user = new HashMap<String, String>();
                    ResultSetMetaData user_rsmd = user_rs.getMetaData();
                    int user_colCount = user_rsmd.getColumnCount();

                    for(; user_colCount > 0; user_colCount--)
                        user.put(user_rsmd.getColumnName(user_colCount), user_rs.getString(user_colCount));
                    users.put(user_rs.getString(++user_colCount), user);

                    // Get data from table 'history' follow key
                    String gethisStatement = "select * from user_login_history where user_id = '" + user_rs.getString(user_colCount) + "'";
                    //Close previous ResultSet
                    ResultSet his_rs = his_stmt.executeQuery(gethisStatement);             
                    // Import history data follow key
                    Map<String, Map<String, String>> hislist = new HashMap<String, Map<String, String>>();
                    while (his_rs.next()) {
                        Map<String, String> login_history = new HashMap<String, String>();
                        
                        ResultSetMetaData his_rsmd = his_rs.getMetaData();
                        int his_colCount = his_rsmd.getColumnCount();

                        for(; his_colCount > 0; his_colCount--)
                            login_history.put(his_rsmd.getColumnName(his_colCount), his_rs.getString(his_colCount));
                        hislist.put(his_rs.getString(++his_colCount), login_history);
                    }
                    loginHistories.put(user_rs.getString(user_colCount), hislist);
                }
            }
            
            // Close connection
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
 
