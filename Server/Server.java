
/*
 * Copyright © 2022 by Phuc Dat
 * HCMUS Student 2022 Information
 *      Fullname: Phan Phuc Dat
 *      ID: 20127463
 *      Contact: sunrisecontinent.company@gmail.com
 */

import javax.swing.*;
import javax.swing.GroupLayout.Group;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
 
  
 public class Server {
    JFrame jfrm; 
    // Config server
    int port_number;
    ServerSocket server;
    ServerProcess sp;
    ArrayList<HandleClient> clientList;
    ArrayList<HandleClient> adminList;

    // ArrayList<user> allUser;
    JTextArea logHistory;

    // Demo database
    String[] chatroom_1 = {"0", "Chisato and Takina team", "./assets/imgs/avts/avt_3.png"};
    String[] chatroom_2 = {"1", "Uzui Tengen team", "./assets/imgs/avts/avt_1.png"};
    ArrayList<String[]> chatrooms = new ArrayList<String[]>();

    ArrayList<String> r1_content = new ArrayList<String>();
    ArrayList<String> r2_content = new ArrayList<String>();
    ArrayList<ArrayList<String>> cr_content = new ArrayList<ArrayList<String>>();

    // Database config
    private String DB_URL = "jdbc:mysql://localhost:3306/java_chatting_app";
    private String USER_NAME = "root";
    private String PASSWORD = "12345";
    Connection conn = null;
    user current_user = null;
  
    Server() {
        // Config server
        port_number = 2014;
        server = null;
        sp = new ServerProcess();
        clientList = new ArrayList<HandleClient>();
        adminList = new ArrayList<HandleClient>();
        // allUser = new ArrayList<user>();
        // allUser = (new db()).getAllUser();

        // Config database
        chatrooms.add(chatroom_1);
        chatrooms.add(chatroom_2);
        cr_content.add(r1_content);
        cr_content.add(r2_content);

        // Try to create a Server Socket
        try {
            server = new ServerSocket(port_number);
        } catch (IOException ioe) {
            System.out.println("Unable to create socket." + ioe);
            System.exit(1);
        }

        // Try to connect to database
            // Connect to database
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // Check connetion result
        if(conn == null) {
            System.out.println("Unable to connect to database.");
            System.exit(1);
        }

        // Create GUI
        jfrm = new JFrame("Server");
        // Chỉ định một BoxLayout
        jfrm.setLayout(new BoxLayout(jfrm.getContentPane(), BoxLayout.Y_AXIS));
        jfrm.setResizable(false);

        // Tùy chỉnh kích thước     
        jfrm.setSize(412, 800);
        
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Header
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setPreferredSize(new Dimension(412, 80));
        JLabel title = new JLabel("<html><br><i style=\"font-family:Verdana;font-size:14px;\">Server</i></html>");
        header.add(title);

        // Content
        JPanel infoSide = new JPanel();
        infoSide.setLayout(new BoxLayout(infoSide, BoxLayout.Y_AXIS));
        infoSide.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel logSide = new JPanel();
        logSide.setLayout(new BorderLayout());
        logSide.setPreferredSize(new Dimension(412, 300));
        logSide.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Info side config
        JLabel ss_localport = new JLabel("Local Port: " + server.getLocalPort());
        infoSide.add(ss_localport);            
        // Log side config
        logHistory = new JTextArea();
        logSide.add(logHistory);

        // Footer
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout());
        JLabel copyright = new JLabel("<html><p style=\"font-family:Verdana;font-size:8px;\">Copyright © 2022 by Phan Phuc Dat</p></html>");
        footer.add(copyright);

        jfrm.getContentPane().add(header);
        jfrm.getContentPane().add(infoSide);
        jfrm.getContentPane().add(logSide);
        jfrm.getContentPane().add(footer);
        jfrm.pack();
        jfrm.setVisible(true);

        Thread sv_thread = new Thread(sp);
        sv_thread.start();
    }

    public class ServerProcess implements Runnable {
        ServerProcess() {
            
        }
        public void run() {
        
            // Start listen to client
            try {
                while(true) {
                    Socket client = server.accept();
                    
                    logHistory.append(" Client (" + client.getInetAddress().getHostAddress()
                    + ", " + client.getPort() + ") connected \n");

                    HandleClient hc = new HandleClient(client);
                    clientList.add(hc);
                    Thread hc_thread = new Thread(hc);
                    hc_thread.start();
                } 
            } catch (IOException ie) {
                // Handle Exception
            } finally {
                if(server != null) {   
                    try {
                        server.close();
                    } catch(IOException ioe) {
                        System.out.println("Unable to close server socket." + ioe);
                    }
                }
            }
        }
    }
    
    public Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            // Return null if error occur and cannot connect to database
        } catch (ClassNotFoundException ex) {
            // Handle exception
            ex.printStackTrace();
        } catch (SQLException ex) {
            // Handle exception
            ex.printStackTrace();
        }
        return conn;
    }

    public class HandleClient implements Runnable {
        Socket client;
        InputStream clientIn;
        OutputStream clientOut;
        BufferedReader br;
        PrintWriter pw;
        String roomID;
        String who;

        HandleClient(Socket c) {
            this.roomID = "";
            this.who = "";

            this.client = c;
            
            try {
                clientIn = this.client.getInputStream();
                br = new BufferedReader(new InputStreamReader(clientIn));

                clientOut = this.client.getOutputStream();
                pw = new PrintWriter(clientOut, true);
            } catch (IOException ioe) {

            }
        }
        void broadcastMsg(String rid, String sender, String msg) {
            for(int i = 0; i < clientList.size(); i++) {
                if(clientList.get(i).roomID.equals(rid)) {
                    if(sender.equals(null) || !sender.equals(clientList.get(i).who))
                        clientList.get(i).pw.println("newmsg@" + msg);
                    
                }
            }
        }
        void sendImage(String path) {
            try {
                File fi = new File(path);
                byte[] b = Files.readAllBytes(fi.toPath());
                DataOutputStream dos = new DataOutputStream(clientOut);
                dos.writeInt(b.length);
                dos.write(b);
                dos.flush();
            } catch(IOException ioe) { ioe.printStackTrace(); }
        }
        // Entry point of thread.
        public void run() {
            // Wait for the data from the client and reply
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                        
            try {
                while(true) {
                    // Read data from the client
                    String msgFromClient;
                    while((msgFromClient = br.readLine()) == null) {} // Waiting for valid message
                    // Send response to the client
                    String lowerCaseMsg = msgFromClient.toLowerCase();
                    if(lowerCaseMsg.equals("get_chatroom_data")) {
                        for(String[] chatroom : chatrooms) {
                            for(String data : chatroom) 
                                pw.println(data);
                            pw.println("end");
                        }
                        pw.println("end");
                    }
                    else if(lowerCaseMsg.contains("usn@")) {
                        this.who = msgFromClient.substring(msgFromClient.lastIndexOf("@") + 1);
                    }
                    else if(lowerCaseMsg.contains("@toroomid@")) {
                        String rid = lowerCaseMsg.substring(lowerCaseMsg.lastIndexOf("@") + 1);
                        for(int i = 0; i < chatrooms.size(); i++) {
                            if(chatrooms.get(i)[0].equals(rid)) {
                                // Give data of room
                                pw.println("sending_chat_history");
                                cr_content.get(i).add(who + " has joined");
                                broadcastMsg(Integer.toString(i), this.who, who + " has joined");
                                for(String r_content : cr_content.get(i))
                                    pw.println(r_content);
                                pw.println("end");

                                this.roomID = Integer.toString(i);
                                
                                int firstsym = lowerCaseMsg.indexOf("@");
                                if(firstsym != 0) {
                                    String prev_rid = lowerCaseMsg.substring(0, firstsym);
                                    
                                    for(int j = 0; j < chatrooms.size(); j++) {
                                        if(chatrooms.get(j)[0].equals(prev_rid)) {
                                            cr_content.get(j).add(who + " has left");
                                            break;
                                        }
                                    }

                                    broadcastMsg(prev_rid, this.who, who + " has left");
                                }
                                
                                break;
                            }
                        }
                    }
                    else if(lowerCaseMsg.contains("chat@")) {
                        String[] dataPackage = msgFromClient.split("@");
                        // UPDATE IN DATABASE
                        LocalDateTime now = LocalDateTime.now();  
                        String query = "insert into chat_history values ('" + dataPackage[1] 
                                    + "', '"+ dataPackage[2] +"', '"+ dataPackage[3] +"', '" + dtf.format(now) + "')";
                        try{
                            conn.prepareStatement(query).execute(); 
                        } catch(SQLException sqle) {
                            sqle.printStackTrace();
                        }

                        // BROADCAST TO CURRENT ONLINE MEMBER
                            // If send to group
                        if(dataPackage[2].contains("group")) {
                            for(int i = 0; i < clientList.size(); i++) {
                                System.out.println("Check: " + dataPackage[1] + "----" + clientList.get(i).who);
                                if(!dataPackage[1].equals(clientList.get(i).who))
                                    clientList.get(i).pw.println("set_chat@"+dataPackage[2]+"@"+dataPackage[3] + "@" + dataPackage[1]);
                            }
                        }
                            // If send to user
                        else {
                            System.out.println("private");
                            for(int i = 0; i < clientList.size(); i++) {
                                clientList.get(i).pw.println("set_chat@"+dataPackage[1]+"@"+dataPackage[3] + "@" + dataPackage[1]);
                            }
                        }
                        
                    }
                    else if(lowerCaseMsg.contains("login@")) {
                        String msg = msgFromClient.substring(msgFromClient.lastIndexOf("@") + 1);
                        String username = msg.substring(0, msg.indexOf("?") );
                        String password = msg.substring(msg.lastIndexOf("?") +1);
                        try {
                            System.out.println(username + "  ..  " + password);

                            //check db
                            if ((new db()).accountVerification(username, password)){
                                current_user = (new db()).getUserByUsername(username);
                                pw.println("loginsuccess@" + current_user.getId());
                                who=current_user.getId();
                                LocalDateTime now = LocalDateTime.now();  
                                for(int i = 0; i < adminList.size(); i++) {
                                    adminList.get(i).pw.println("set_log@" + dtf.format(now) + ": Client ("+current_user.getName()+", "+current_user.getUsername()+") log in");
                                }
                                logHistory.append(" Client (" + client.getInetAddress().getHostAddress()
                                + ", " + client.getPort() + ") log in \n");

                                // Send chat history
                                String query = "call getAllHistory('" + current_user.getId() +"')";
                                ResultSet rs = conn.createStatement().executeQuery(query);
                                while(rs.next()) {
                                    pw.println(rs.getString("send"));
                                    pw.println(rs.getString("sender_name"));
                                    pw.println(rs.getString("receive"));
                                    pw.println(rs.getString("receiver_name"));
                                    pw.println(rs.getString("message"));
                                }
                                pw.println("end");
                            }
                            else
                                pw.println("loginfail");
                        } catch(Exception e) {
                            System.err.println("Error: " + e);
                        }
                    }
                    else if(lowerCaseMsg.contains("signup@")) {
                        String msg = msgFromClient.substring(msgFromClient.indexOf("@") + 1);
                        String username = msg.substring(0, msg.indexOf("?") );
                        msg = msg.substring(msg.indexOf("?") +1 );
                        String fullname = msg.substring(0, msg.indexOf("?") );
                        msg = msg.substring(msg.indexOf("?") +1 );
                        String address = msg.substring(0, msg.indexOf("?") );
                        msg = msg.substring(msg.indexOf("?") +1 );
                        String dob = msg.substring(0, msg.indexOf("?") );
                        msg = msg.substring(msg.indexOf("?") +1 );
                        String sex = msg.substring(0, msg.indexOf("?") );
                        msg = msg.substring(msg.indexOf("?") +1 );
                        String email = msg.substring(0, msg.indexOf("?") );
                        String password = msg.substring(msg.lastIndexOf("?") +1);
                        try {
                            // System.out.println(username + "  ..  " + fullname + "  ..  " + address + "  ..  " + dob + sex + "  ..  " + email + "  ..  " + password);
                            if ((new db()).checkUsn(username)){
                                if((new db()).createNewUser((new user(fullname, username, password, address, dob, sex, email))) == 1)
                                    pw.println("signupsuccess");
                                else
                                    pw.println("signupfail");
                                } else
                                pw.println("signupfail");
                                
                        } catch(Exception e) {
                            System.err.println("Error: " + e);
                        }
                    }
                    else if(lowerCaseMsg.contains("friendlist@")) {
                        String username = msgFromClient.substring(msgFromClient.lastIndexOf("@") + 1);
                        try {
                            // System.out.println(username);
                            String friendRs = (new db()).getAllFriend(username);
                                if (friendRs != null){
                                String[] frl = friendRs.split("/");
                                for (int i = 0;i<frl.length;++i){
                                    frl[i] = (new db()).getNameById(frl[i]);
                                }
                                System.out.println(frl);
                                pw.println("sending_friend_list");
                                pw.println(Integer.toString(frl.length));
                                for (String item : frl){
                                    pw.println(item);
                                }
                            }
                        } catch(Exception e) {
                            System.err.println("Error: " + e);
                        }
                    }
                    else if(lowerCaseMsg.contains("addfriend@")) {
                        String msg = msgFromClient.substring(msgFromClient.lastIndexOf("@") + 1);
                        String username = msg.substring(0, msg.indexOf("?") );
                        String usernameFr = msg.substring(msg.lastIndexOf("?") +1);
                        try {
                            // System.out.println(username + "  ..  " + usernameFr);
                            if (!(new db()).checkUsn(usernameFr)){
                                if (!(new db()).isFriend(username, usernameFr) && !(new db()).isFriend(usernameFr, username)){
                                    if ((new db()).addFriend2Users(username, usernameFr) != 0  && (new db()).addFriend2Users(usernameFr, username) != 0)
                                            pw.println("addfriendsuccess");
                                    else
                                    pw.println("addfriendfail");
                                } else
                                    pw.println("addfriend_isfriend");
                            } else
                                pw.println("addfriend_wrongusernamme");
                            

                        } catch(Exception e) {
                            System.err.println("Error: " + e);
                        }
                    }
                    else if(lowerCaseMsg.contains("unfriend@")) {
                        String msg = msgFromClient.substring(msgFromClient.lastIndexOf("@") + 1);
                        String username = msg.substring(0, msg.indexOf("?") );
                        String usernameFr = msg.substring(msg.lastIndexOf("?") +1);
                        try {
                            System.out.println(username + "  ..  " + usernameFr);
                            //check db
                            if (!(new db()).checkUsn(usernameFr)){
                                if ((new db()).isFriend(username, usernameFr) && (new db()).isFriend(usernameFr, username)){
                                    if ((new db()).unFriend2Users(username, usernameFr) != 0  && (new db()).unFriend2Users(usernameFr, username) != 0)
                                            pw.println("unfriendsuccess");
                                    else
                                    pw.println("unfriendfail");
                                } else
                                    pw.println("unfriend_isnotfriend");
                            } else
                                pw.println("friend_wrongusernamme");
                            //send result
                            // pw.println("unfriendsuccess");
                            // pw.println("unfriendfail");
                        } catch(Exception e) {
                            System.err.println("Error: " + e);
                        }
                    }
                    else if (lowerCaseMsg.equals("exit")) {
                        // Send end msg to client and noti on server then close client socket
                        logHistory.append(" Client (" + client.getInetAddress().getHostAddress()
                        + ", " + client.getPort() + ") log out \n");
                        LocalDateTime now = LocalDateTime.now();  
                        for(int i = 0; i < adminList.size(); i++) {
                            adminList.get(i).pw.println("set_log@" + dtf.format(now) + ": Client ("+current_user.getName()+", "+current_user.getUsername()+") log out");
                        }
                        cr_content.get(Integer.parseInt(this.roomID)).add(this.who + " has left");
                        broadcastMsg(this.roomID, this.who, this.who + " has left");
                        client.close();
                        pw.close();
                        br.close();
                        break;
                    }
                    else if (lowerCaseMsg.equals("exit_without_login")) {
                        // Send end msg to client and noti on server then close client socket
                        logHistory.append(" Client (" + client.getInetAddress().getHostAddress()
                        + ", " + client.getPort() + ") disconnected \n");
                        client.close();
                        pw.close();
                        br.close();
                        break;
                    }
                    else if(lowerCaseMsg.equals("admin_get_alluser")) {
                        // Set admin to admin list
                        adminList.add(this);
                        String query = "select * from users";
                        try {
                            ResultSet rs = conn.createStatement().executeQuery(query);
                            pw.println("accepted");
                            while(rs.next()) {
                                pw.println(rs.getString("id"));     
                                pw.println(rs.getString("ban_status"));     
                                pw.println(rs.getString("name"));    
                                pw.println(rs.getString("online_status"));  
                                pw.println(rs.getString("usn"));    
                                pw.println(rs.getString("psw"));   
                                pw.println(rs.getString("address"));    
                                pw.println(rs.getString("dob"));    
                                pw.println(rs.getString("sex"));    
                                pw.println(rs.getString("email"));  
                                pw.println(rs.getString("nofFriends"));     
                                pw.println(rs.getString("friend"));        
                                pw.println(rs.getString("createAt"));   
                                
                                this.sendImage(rs.getString("image"));
                                this.sendImage(rs.getString("bg"));
                                br.readLine();
                            }
                            pw.println("end");
                        } catch(SQLException e) {
                            System.err.println("Error admin_get_alluser: " + e);
                        }
                    }
                    else if(lowerCaseMsg.equals("admin_exit")) {
                        logHistory.append(" Client (" + client.getInetAddress().getHostAddress()
                        + ", " + client.getPort() + ") log out \n");
                        client.close();
                        pw.close();
                        br.close();
                        clientIn.close();
                        clientOut.close();
                        break;
                    }
                    else if(lowerCaseMsg.contains("admin_set_user")) {
                        String[] data = msgFromClient.split("&");

                        String query = "update users set name='" + data[2] + "',"
                                        + " usn='" + data[3] + "',"
                                        + " email='" + data[4] + "',"
                                        + " address='" + data[5] + "'"
                                        + " where id='" + data[1] + "';";
                        try {
                            conn.prepareStatement(query).execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(lowerCaseMsg.contains("admin_get_all_group_chat")) {
                        pw.println("send_all_group_chat_data");
                        String query = "select * from group_chat";
                        try {
                            ResultSet rs = conn.createStatement().executeQuery(query);
                            ArrayList<String> imgs_path = new ArrayList<>();
                            while(rs.next()) {
                                pw.println(rs.getString("id"));
                                pw.println(rs.getString("group_name"));
                                pw.println(rs.getString("createdAt"));
                                pw.println(rs.getString("admin_id"));

                                imgs_path.add(rs.getString("image"));
                            }
                            pw.println("end");
                            for(int i = 0; i < imgs_path.size(); i++) {
                                this.sendImage(imgs_path.get(i));
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(lowerCaseMsg.contains("admin_get_group_user@")) {
                        String gr_id = lowerCaseMsg.split("@")[1];
                        pw.println("send_group_user_data");
                        String query = "call getUsersOfGroup('"+gr_id+"')";
                        try {
                            ResultSet rs = conn.createStatement().executeQuery(query);
                            while(rs.next()) {
                                pw.println(rs.getString("user_id"));
                            }
                            pw.println("end");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(lowerCaseMsg.contains("get_history@")) {
                        try{ 
                            String targetId = lowerCaseMsg.split("@")[1];
                            String query = "select * from user_login_history where user_id = '" + targetId + "'";
                            ResultSet rs = conn.createStatement().executeQuery(query);
                            pw.println("send_history@" + targetId);
                            while(rs.next()) {
                                pw.println(rs.getString("his_id"));
                                pw.println(rs.getString("user_id"));
                                pw.println(rs.getString("device_name"));
                                pw.println(rs.getString("location"));
                                pw.println(rs.getString("online_status"));
                            }
                            pw.println("end");
                        } catch(SQLException e) { e.printStackTrace(); }
                    }
                    else if(lowerCaseMsg.contains("ban_user@")) {
                        String target, query;
                        if(lowerCaseMsg.contains("unban")) {
                            target = lowerCaseMsg.substring(11);
                            query = "update users set ban_status = 'normal' where id = '" + target + "'";
                        }
                        else {
                            target = lowerCaseMsg.substring(9);
                            query = "update users set ban_status = 'ban' where id = '" + target + "'";
                        }
                        try {
                            conn.prepareStatement(query).execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(lowerCaseMsg.contains("change_psw_user@")) {
                        String uid = lowerCaseMsg.split("@")[1];
                        String new_psw = lowerCaseMsg.split("@")[2];
                        String query = "update users set psw = '" + new_psw + "' where id = '" + uid + "'";
                        try {
                            conn.prepareStatement(query).execute();
                            pw.println("commit_change_psw@" + uid + "@" + new_psw);
                        } catch (SQLException e) {
                            System.err.println("Cannot change password");
                            e.printStackTrace();
                        }
                    }
                    else if(lowerCaseMsg.contains("get_chat_avt@")) {
                        System.out.println("c");
                        String id = msgFromClient.split("@")[1];
                        pw.println("send_chat_avt@" + id);
                        String query = "call getFriendsAndGroups('"+id+"')";
                        try {
                            ResultSet rs = conn.createStatement().executeQuery(query);
                            ArrayList<String> imgpath_list = new ArrayList<String>();
                            while(rs.next()) {
                                pw.println(rs.getString("id"));
                                pw.println(rs.getString("name"));
                                imgpath_list.add(rs.getString("image"));
                            }
                            pw.println("end");
                            
                            // Send avt img
                            for(int i = 0; i < imgpath_list.size(); i++) {
                                this.sendImage(imgpath_list.get(i));
                            }
                        } catch(SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                    else if(lowerCaseMsg.contains("get_chat_group_data@")) {
                        String[] dataPackage = msgFromClient.split("@");
                        pw.println("send_chat_data@" + dataPackage[2]);
                        String query = "call getGroupChatHistory('"+dataPackage[1]+"', '"+dataPackage[2]+"')";
                        try {
                            ResultSet rs = conn.createStatement().executeQuery(query);
                            while(rs.next()) {
                                pw.println(rs.getString("send"));
                                pw.println(rs.getString("sender_name"));
                                pw.println(rs.getString("receive"));
                                pw.println(rs.getString("receiver_name"));
                                pw.println(rs.getString("message"));
                                pw.println(rs.getString("send_at"));
                            }
                            pw.println("end");
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                    else if(lowerCaseMsg.contains("get_chat_private_data@")) {
                        String[] dataPackage = msgFromClient.split("@");
                        pw.println("send_chat_data@" + dataPackage[2]);
                        String query = "call getPrivateChatHistory('"+dataPackage[1]+"', '"+dataPackage[2]+"')";
                        try {
                            ResultSet rs = conn.createStatement().executeQuery(query);
                            while(rs.next()) {
                                pw.println(rs.getString("send"));
                                pw.println(rs.getString("sender_name"));
                                pw.println(rs.getString("receive"));
                                pw.println(rs.getString("receiver_name"));
                                pw.println(rs.getString("message"));
                                pw.println(rs.getString("send_at"));
                            }
                            pw.println("end");
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                }
            
            } catch (IOException ioe) {
                System.out.println("Error while comunicating with client" + ioe);
            } catch (NumberFormatException ne) {
                System.out.println("Error while changing cr_content: " + ne);
            }
        }
    }  

     
    public static void main(String args[]) throws Exception {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { new Server(); }
        });
    }
 }
  
 