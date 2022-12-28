
import java.sql.*;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class connectDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/FinalProjectDB";
    static final String USER = "root";
    static final String PASS = "12345";

    // public void searchComparisons(comparison a, List<comparison> list) {
    // Connection conn = null;
    // try {
    // Class.forName(JDBC_DRIVER);
    // conn = DriverManager.getConnection(DB_URL, USER, PASS);
    // Statement st = conn.createStatement();
    // String sql = "SELECT * FROM comparisons where ";
    // if (!a.getCode().equals("")){
    // sql = String.format(sql + "MaSo = '%s' and ", a.getCode());
    // }
    // if (!a.getType().equals("")){
    // sql = String.format(sql + "LoaiTietKiem = '%s' and ", a.getType());
    // }
    // if (!a.getClient().equals("")){
    // sql = String.format(sql + "KhachHang = '%s' and ", a.getClient());
    // }
    // if (!a.getId().equals("")){
    // sql = String.format(sql + "CMND = '%s' and ", a.getId());
    // }
    // if (!a.getAddress().equals("")){
    // sql = String.format(sql + "DiaChi = '%s' and ", a.getAddress());
    // }
    // if (!a.getDate().equals("")){
    // sql = String.format(sql + "NgayMoSo = '%s' and ", a.getDate());
    // }
    // if (!a.getMoney().equals("")){
    // sql = String.format(sql + "SoTien = '%s' and ", a.getMoney());
    // }
    // sql = sql.substring(0, sql.length() -4);
    // ResultSet rs = st.executeQuery(sql);
    // while (rs.next()) {
    // comparison temp = new comparison(rs.getString("MaSo"),
    // rs.getString("LoaiTietKiem"),
    // rs.getString("KhachHang"), rs.getString("CMND"), rs.getString("DiaChi"),
    // rs.getString("NgayMoSo"), rs.getString("SoTien"));

    // list.add(temp);
    // }

    // } catch (SQLException se) {
    // se.printStackTrace();
    // } catch (Exception e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // if (conn != null)
    // conn.close();
    // } catch (SQLException se) {
    // se.printStackTrace();
    // }
    // }
    // }

    public void getAllUser(List<user> userList) {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM users;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                user temp = new user(rs.getString("username"), rs.getString("fullname"),
                        rs.getString("address"), rs.getString("dob"), rs.getString("email"),
                        rs.getString("password"), rs.getString("sex"));

                userList.add(temp);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public user getUserByUsername(String username) {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            String sql = String.format("SELECT * FROM users where username = '%s' ;", username);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                user temp = new user(rs.getString("username"), rs.getString("fullname"),
                        rs.getString("address"), rs.getString("dob"), rs.getString("email"),
                        rs.getString("password"), rs.getString("sex"));
                return temp;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public void addUser(user newUser) {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();

            String sql = String.format("insert into users values('%s','%s','%s','%s','%s','%s', '%s');",
                    newUser.getUsername(), newUser.getFullname(), newUser.getAddress(), newUser.getDob(),
                    newUser.getEmail(),
                    newUser.getPassword(), newUser.getSex());
            st.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void getAllFriends(String username, List<String> friendList) {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            String sql = String.format("SELECT * FROM friends where username = '%s' ;", username);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                friendList.add(rs.getString("username_friend"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void addNewFriend(String username, String newFriend){
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();

            String sql = String.format("insert into friends values('%s','%s');", username, newFriend);
            st.executeUpdate(sql);

            sql = String.format("insert into friends values('%s','%s');", newFriend, username);
            st.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void deleteFriend(String username, String newFriend){
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            
            st.executeUpdate("set SQL_SAFE_UPDATES = 0;");
            String sql = String.format("delete from friends where username = '%s' and username_friend = '%s';", username, newFriend);
            st.executeUpdate(sql);

            sql = String.format("delete from friends where username = '%s' and username_friend = '%s';", newFriend, username);
            st.executeUpdate(sql);
            
            st.executeUpdate("set SQL_SAFE_UPDATES = 1;");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
