
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

public class App implements ActionListener {
    JFrame jfrm;
    // Admin Config
    int portnumber = 2014;
    AdminProcess ap = null;
    boolean on_process = false;

    // Data Config
    static ArrayList<Map<String, String>> users_sort_backup = null;
    static ArrayList<Map<String, String>> users_backup = null;
    static ArrayList<Map<String, String>> users = new ArrayList<Map<String, String>>();
    static Map<String, ImageIcon> user_avts = new HashMap<String, ImageIcon>();
    static Map<String, ImageIcon> user_bgs = new HashMap<String, ImageIcon>();
    static Map<String, Map<String, Map<String, String>>> loginHistories = new HashMap<String, Map<String, Map<String, String>>>();

    // --------- CONTENT PANEL --------------
    // Create main panel
    JPanel content = new JPanel();
    // User page
    personalPage usrPage;
    // Create Img generator 
    img imgConfig = new img();
    // the default number of users per page and initial index
    int default_nof_users = 8; 
    int init_index = 0;
    
    JTextField textField;
    JButton cancelBtn;
    ArrayList<JPanel[]> current_page = new ArrayList<>();
    JComboBox<String> sortInput, sortOrderInput;

    App() {
        // Run admin socket thread
        ap = new AdminProcess();
        Thread admin_thread = new Thread(ap);
        admin_thread.start();

        // Tạo thể hiện của frame
        jfrm = new JFrame("Users_main");
        jfrm.setSize(824, 735);

        // Chỉ định một BorderLayout
        jfrm.setLayout(null);
        jfrm.setResizable(false);
        
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ap.getPrintWriter().println("admin_exit");
                ap.tryToClose();
                System.exit(0);
            }
        });

        // Thêm menu bar vào frame
        JMenuBar jmb = (new menubar()).createMenu();
        jfrm.setJMenuBar(jmb);
        jfrm.setVisible(true); // Hiển thị frame

        //Create Home Panel
        JPanel homePnl = new JPanel();
        homePnl.setLayout(new BorderLayout());
        homePnl.setBounds(397, 0, 412, 620);
        homePnl.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));

        // ------------ Create main panels ------------

        JPanel hdrPanel = HeaderPanel();
        JPanel ctnPanel = ContentPanel();
        JPanel ftrPanel = FooterPanel();

        // ------------ Add main panels to content page ------------ 
        homePnl.add(hdrPanel, BorderLayout.NORTH);
        homePnl.add(ctnPanel, BorderLayout.CENTER);
        homePnl.add(ftrPanel, BorderLayout.SOUTH);
        jfrm.getContentPane().add(homePnl);
    }

    public class AdminProcess implements Runnable {
        PrintWriter pw = null;
        BufferedReader br = null;
        OutputStream adminOut;
        InputStream adminIn;
        String msg = "";
        Thread msgReceiver;
        Socket admin = null;

        AdminProcess() {
            try {
                // Create a admin socket
                admin = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("admin socket is created " + admin);
                
                // Create an output stream of the admin socket
                adminOut = admin.getOutputStream();
                pw = new PrintWriter(adminOut, true);
                
                // Create an input stream of the admin socket
                adminIn = admin.getInputStream();
                br = new BufferedReader(new InputStreamReader(adminIn));

                // Get all user data from server
                pw.println("admin_get_alluser");
                String msg = null;
                while((msg = br.readLine()) == null) {}
                if(msg.equals("accepted")) {
                    while(!((msg = br.readLine()).equals("end"))) {
                        Map<String, String> user = new HashMap<String, String>();
                        System.out.println(msg);
                        user.put("id", msg);   

                        msg = br.readLine();
                        user.put("ban_status", msg);

                        msg = br.readLine();
                        user.put("name", msg);

                        msg = br.readLine();
                        user.put("online_status", msg);

                        msg = br.readLine();
                        user.put("usn", msg);

                        msg = br.readLine();
                        user.put("psw", msg);

                        msg = br.readLine();
                        user.put("address", msg);

                        msg = br.readLine();
                        user.put("dob", msg);

                        msg = br.readLine();
                        user.put("sex", msg);

                        msg = br.readLine();
                        user.put("email", msg);

                        msg = br.readLine();
                        user.put("nofFriends", msg);

                        msg = br.readLine();
                        user.put("friend", msg);

                        msg = br.readLine();
                        user.put("createAt", msg);

                        ImageIcon avt = this.receiveImage();
                        user_avts.put(user.get("id"), avt);

                        ImageIcon bg = this.receiveImage();
                        user_bgs.put(user.get("id"), bg);

                        users.add(user);
                        pw.println("done");
                    }
                }
                System.out.println("Finish");
                
            } catch (IOException ioe) {
                System.err.println("Error: " + ioe);
            }
            
            msgReceiver = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String command = br.readLine();
                            System.out.println("MSG RECEIVER: " + command);
                            if(command.contains("send_history@")) {
                                String userId = command.split("@")[1];
                                Map<String, Map<String, String>> userLoginHis = new HashMap<String, Map<String, String>>();
                                    
                                String msg_his;
                                while(!((msg_his = br.readLine()).equals("end"))) {
                                    Map<String, String> his_data = new HashMap<String, String>();
                                    his_data.put("his_id", msg_his);

                                    msg_his = br.readLine();
                                    his_data.put("user_id", msg_his);

                                    msg_his = br.readLine();
                                    his_data.put("device_name", msg_his);

                                    if(msg_his.equals("Window PC"))
                                        msg_his = "./assets/icons/window.png";
                                    else if(msg_his.equals("iMac"))
                                        msg_his = "./assets/icons/mac.png";
                                    else if(msg_his.equals("Linux PC"))
                                        msg_his = "./assets/icons/linux.png";
                                    else
                                        msg_his = "./assets/icons/unknown_device.png";
                                    his_data.put("device_icon", msg_his);

                                    msg_his = br.readLine();
                                    his_data.put("location", msg_his);

                                    msg_his = br.readLine();
                                    his_data.put("online_status", msg_his);

                                    userLoginHis.put(his_data.get("his_id"), his_data);
                                }

                                loginHistories.put(userId, userLoginHis);
                                on_process = false;
                            }
                            else if(command.contains("sending_chat_history")) {
                            }
                            else if(command.contains("commit_change_psw@")) {
                                String[] data_package = command.split("@");
                                if(data_package.length == 3){
                                    int index = findUserById(data_package[1]);
                                    if(index != -1)
                                        users.get(index).put("psw", data_package[2]);
                                }
                            }
                        }
                    } catch(IOException ioe) {
                        System.err.println("Error receiver: " + ioe);
                    }
                }
            });
        }

        // Public method
        public ImageIcon receiveImage() {
            ImageIcon result = null;
            try {
                DataInputStream dis = new DataInputStream(adminIn);
                int length = dis.readInt();
                if(length > 0) {
                    byte[] message = new byte[length];
                    dis.readFully(message, 0, message.length);
                    ByteArrayInputStream bais = new ByteArrayInputStream(message);
                    BufferedImage bi = ImageIO.read(bais);
                    if(bi != null) {
                        result = new ImageIcon(bi);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error receive image file");
                e.printStackTrace();
            }
            return result;
        }
        public void tryToClose() {
            try {
                if(admin != null) {
                    pw.close();
                    br.close();
                    adminIn.close();
                    adminOut.close();
                    admin.close();
                }
                if(!msgReceiver.isInterrupted()) {
                    msgReceiver.interrupt();
                }
            } catch(IOException ioe) {
                System.err.println("Error close socket: " + ioe);
            }
        }
        public PrintWriter getPrintWriter() {
            return pw;
        }
        public BufferedReader BufferedReader() {
            return br;
        }

        public void run() {
            // Waiting for any command from server such as "update, refresh,..."
            msgReceiver.start();
        }
    }
    public class personalPage implements ActionListener {
        private Map<String, String> user;
        String ID;
        int default_num = 6;
        JPanel imgPanel;
        JTextField newPswInput;
        JFrame changePswFrame, editDetailFrame, parent;
        JPanel viewWrapper = null, allFriendWrapper = null;
    
        personalPage(Map<String, String> data, String userId) {
            user = data;
            ID = userId;
            // MAIN FRAME
            parent = new JFrame("Personal Information"){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(824, 735);
                };
            };
            parent.setLayout(new BoxLayout(parent.getContentPane(), BoxLayout.Y_AXIS));
            parent.setResizable(false);
        }
        public void runView() {
            viewWrapper = new JPanel(){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(824, 735);
                };
            };
            viewWrapper.setLayout(null);
            // Thêm menu bar vào frame
            JMenuBar jmb = (new menubar()).createMenu();
            parent.setJMenuBar(jmb);
    
            ImageIcon avt = new ImageIcon(imgConfig.ScaleImage(user_avts.get(ID).getImage(), 120, 120));
            ImageIcon bg = new ImageIcon(imgConfig.ScaleImage(user_bgs.get(ID).getImage(), 824, 200));
    
            // Create panel of avt and bg
            imgPanel = new JPanel(){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(824, 350);
                };
            };
            imgPanel.setLayout(null);
    
            JLabel bgLbl = new JLabel(bg);
            JLabel avtLbl = new JLabel(avt);
            avtLbl.setBorder(new LineBorder(Color.WHITE, 5));
    
            avtLbl.setBounds(20, 140, 120, 120);
            bgLbl.setBounds(0, 0, 824, 200);
    
            JLabel name = new JLabel("<html>" + "<p style=\"font-family:Verdana;font-size:11px;\">"
            + 
            (user).get("name") + "</p></html>");
    
            JLabel usn = new JLabel("<html>" + "<p style=\"font-family:Verdana;font-size:8px;font-weight:400;\">@"
            + (user).get("usn") + "</p></html>");
            // Apply padding by using empty border
            name.setBounds(new Rectangle(new Point(20, 270), name.getPreferredSize()));
            usn.setBounds(new Rectangle(new Point(20, 290), usn.getPreferredSize()));
    
            // Create some function button
            ImageIcon historyIcon = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading("./assets/icons/history.png"), 50/3, 50/3));
            ImageIcon banIcon = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading("./assets/icons/ban.png"), 50/3, 50/3));
            ImageIcon penIcon = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading("./assets/icons/pen.png"), 50/3, 50/3));
    
            JButton seeLoginHistoryBtn = new JButton(historyIcon);
            seeLoginHistoryBtn.setActionCommand("see_login_history");
            JButton banBtn = new JButton(banIcon);
            banBtn.setActionCommand("ban");
            JButton changePasswordBtn = new JButton(penIcon);
            changePasswordBtn.setActionCommand("change_password");
    
            seeLoginHistoryBtn.addActionListener(this);
            banBtn.addActionListener(this);
            changePasswordBtn.addActionListener(this);
    
            seeLoginHistoryBtn.setBounds(680, 220, 30, 30);
            banBtn.setBounds(715, 220, 30, 30);
            changePasswordBtn.setBounds(750, 220, 30, 30);
    
            // Add 2 image, button and label to panel
            imgPanel.add(avtLbl);
            imgPanel.add(bgLbl);
            imgPanel.add(name);
            imgPanel.add(usn);
            imgPanel.add(seeLoginHistoryBtn);
            imgPanel.add(banBtn);
            imgPanel.add(changePasswordBtn);
    
            // Create panel include information
            JPanel inforPanel = new JPanel();
            inforPanel.setLayout(new BoxLayout(inforPanel, BoxLayout.Y_AXIS));
    
                // Infor label
            //Title
            JLabel aboutTitle = new JLabel("<html>__________<br>"
            + "<i style=\"font-size:13px;font-weight:800;\">ABOUT</i></html>");
            
            JLabel address = new JLabel("<html>" + 
            "<br>Address: " + "<span style=\"font-weight:400;white-space:normal\">"
            + (user).get("address") + "</span></html>");
            address.setPreferredSize(new Dimension(200, 50));
    
            JLabel dob = new JLabel("<html>" + 
            "Date of birth: " + "<span style=\"font-weight:400;\">"
            + (user).get("dob") + "</span></html>");
    
            JLabel sex = new JLabel("<html>" + 
            "Sex: " + "<span style=\"font-weight:400;\">"
            + (user).get("sex") + "</span></html>");
    
            JLabel email = new JLabel("<html>" + 
            "Email: " + "<span style=\"font-weight:400;\">"
            + (user).get("email") + "</span></html>");
            email.setPreferredSize(new Dimension(200, 20));
    
            inforPanel.add(aboutTitle);
            inforPanel.add(address);
            inforPanel.add(email);
            inforPanel.add(dob);
            inforPanel.add(sex);
            inforPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
    
            // Friend Title
            JLabel friendTitle = new JLabel("<html><br>__________<br>" + 
            "<i style=\"font-size:13px;font-weight:800;\">" + "FRIEND" + "</i></html>");
                // Subtitle the number of friends
            JLabel nofFriends = new JLabel("<html><span style=\"font-size:8px;font-weight:400;\">"
            + (user).get("nofFriends") + " friends" + "</span></html>");
    
            friendTitle.setBorder(new EmptyBorder(0, 20, 0, 20));
            nofFriends.setBorder(new EmptyBorder(0, 20, 30, 20));

            JButton seeAllFriendBtn = new JButton("See all");
            seeAllFriendBtn.setActionCommand("see_all_friend");
            seeAllFriendBtn.addActionListener(this);
    
            // Create panel include friend image
            JPanel friendImagePanel = new JPanel();
            friendImagePanel.setLayout(new GridLayout(0, 3));
            friendImagePanel.setMaximumSize(new Dimension(240, 300));
            friendImagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
            String[] friendIdList = user.get("friend").split("/");
            int nofFriendShowed = 0;
            for(String frId : friendIdList) {
                // Get friend data
                int index = findUserById(frId);
                if(index != - 1) {
                    Map<String, String> friend = users.get(index);
                    // Create friend thumnail
                    ImageIcon imgicon = new ImageIcon(imgConfig.ScaleImage(user_avts.get(frId).getImage(), 131, 131));
                    JButton friend_imgbtn = new JButton(friend.get("name"), imgicon);
                    // Text below image
                    friend_imgbtn.setVerticalTextPosition(SwingConstants.BOTTOM);
                    friend_imgbtn.setHorizontalTextPosition(SwingConstants.CENTER);
                    // Erase border and fill color
                    friend_imgbtn.setContentAreaFilled(false);
                    friend_imgbtn.setPreferredSize(new Dimension(131, 131));
                    friend_imgbtn.setMargin(new Insets(0,0,10,0));
                    friend_imgbtn.setActionCommand("about_" + frId);
                    friend_imgbtn.addActionListener(this);
        
                    friendImagePanel.add(friend_imgbtn);
        
                    if(++nofFriendShowed == default_num)
                        break;
                }
            }
    
            // Add panel to contentpane and display
            imgPanel.setBounds(0, 0, 824, 320);
    
            friendTitle.setBounds(0, 300, 412, 50);
            nofFriends.setBounds(0, 355, 412, 40);
            seeAllFriendBtn.setBounds(310, 335, 80, 20);
            friendImagePanel.setBounds(0, 375, 395, 300);
    
            inforPanel.setBounds(412, 315, 412, 150);
    
            viewWrapper.add(imgPanel);
            viewWrapper.add(inforPanel);
            viewWrapper.add(friendTitle);
            viewWrapper.add(nofFriends);
            viewWrapper.add(seeAllFriendBtn);
            viewWrapper.add(friendImagePanel);
            parent.getContentPane().add(viewWrapper);
            parent.pack();
            parent.setVisible(true);
        }
        public void runAllFriend() {
            allFriendWrapper = new JPanel(){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(824, 735);
                };
            };
            allFriendWrapper.setLayout(new BoxLayout(allFriendWrapper, BoxLayout.Y_AXIS));

            JPanel buttonWrap = new JPanel();
            buttonWrap.setPreferredSize(new Dimension(824, 25));
            buttonWrap.setLayout(null);

            JPanel titleWrap = new JPanel();
            titleWrap.setLayout(new BorderLayout());
            titleWrap.setBorder(new EmptyBorder(20, 0, 50, 0));

            JButton backBtn = new JButton("< Back");
            backBtn.setActionCommand("back_to_view");
            backBtn.addActionListener(this);
            backBtn.setBounds(0, 0, 80, 20);

            JLabel title = new JLabel("<html><p style=\"font-family:Verdana;font-size:13px;\">FRIENDS</p></html>", SwingConstants.CENTER);

            buttonWrap.add(backBtn);
            titleWrap.add(title, BorderLayout.CENTER);

            // Create panel include friend image
            JPanel friendImagePanel = new JPanel();
            friendImagePanel.setLayout(new GridLayout(0, 4));
            friendImagePanel.setMaximumSize(new Dimension(790, 635));
            friendImagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
            String[] friendIdList = user.get("friend").split("/");
            for(String frId : friendIdList) {
                // Get friend data
                int index = findUserById(frId);
                if(index != -1) {
                    Map<String, String> friend = users.get(index);
                    // Create friend thumnail
                    ImageIcon imgicon = new ImageIcon(imgConfig.ScaleImage(user_avts.get(frId).getImage(), 190, 190));
                    JButton friend_imgbtn = new JButton(friend.get("name"), imgicon);
                    // Text below image
                    friend_imgbtn.setVerticalTextPosition(SwingConstants.BOTTOM);
                    friend_imgbtn.setHorizontalTextPosition(SwingConstants.CENTER);
                    // Erase border and fill color
                    friend_imgbtn.setContentAreaFilled(false);
                    friend_imgbtn.setMargin(new Insets(0,0,10,0));
                    friend_imgbtn.setActionCommand("about_" + frId);
                    friend_imgbtn.addActionListener(this);
        
                    friendImagePanel.add(friend_imgbtn);
                }
            }

            // make a scroll pane
            JScrollPane jsp = new JScrollPane(friendImagePanel);
            jsp.setMaximumSize(new Dimension(824, 400));

            allFriendWrapper.add(buttonWrap);
            allFriendWrapper.add(titleWrap);
            allFriendWrapper.add(jsp);
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            parent.getContentPane().add(allFriendWrapper);
        }
        
        public void runChangePassword() {
            changePswFrame = new JFrame(){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(300, 150);
                };
            };;
            changePswFrame.setLayout(new BorderLayout());
    
            // TITLE
            JLabel changePswTitle = new JLabel("<html><p style=\"font-family:Verdana;font-size:13px;\">CHANGE PASSWORD</p></html>", SwingConstants.CENTER);
    
            // INPUT FIELD
            JPanel inputPswFieldWrapper = new JPanel();
            inputPswFieldWrapper.setLayout(new BoxLayout(inputPswFieldWrapper, BoxLayout.X_AXIS));
            inputPswFieldWrapper.setPreferredSize(new Dimension(300, 150));
            inputPswFieldWrapper.setAlignmentY(Component.CENTER_ALIGNMENT);
    
            JPanel inputPswField = new JPanel();
            inputPswField.setLayout(new BorderLayout());
            inputPswField.setMaximumSize(new Dimension(300, 60));
    
                // INPUT ELEMENT
            JPanel oldPswPanel = new JPanel();
            oldPswPanel.setLayout(new BorderLayout());
            oldPswPanel.setMaximumSize(new Dimension(300, 25));
            oldPswPanel.setBorder(new EmptyBorder(0, 0, 0, 16));
            JPanel newPswPanel = new JPanel();
            newPswPanel.setLayout(new BorderLayout());
            newPswPanel.setMaximumSize(new Dimension(300, 25));
            newPswPanel.setBorder(new EmptyBorder(0, 0, 0, 16));
    
            JLabel oldPswLbl = new JLabel("Old Password");
            oldPswLbl.setBorder(new EmptyBorder(0, 10, 0, 16));
            JLabel newPswLbl = new JLabel("New Password");
            newPswLbl.setBorder(new EmptyBorder(0, 10, 0, 10));
            JTextField oldPswInput = new JTextField();
            oldPswInput.setText(users.get(findUserById(ID)).get("psw"));
            oldPswInput.setEditable(false);
            newPswInput = new JTextField();
    
            oldPswPanel.add(oldPswLbl, BorderLayout.WEST);
            oldPswPanel.add(oldPswInput, BorderLayout.CENTER);
            newPswPanel.add(newPswLbl, BorderLayout.WEST);
            newPswPanel.add(newPswInput, BorderLayout.CENTER);
            inputPswField.add(oldPswPanel, BorderLayout.NORTH);
            inputPswField.add(newPswPanel, BorderLayout.SOUTH);
            inputPswFieldWrapper.add(inputPswField);
    
            // SUMIT BUTTON
            JButton commitChangeBtn = new JButton("COMMIT");
            commitChangeBtn.setActionCommand("commit_change_psw");
            commitChangeBtn.addActionListener(this);
    
            changePswFrame.getContentPane().add(changePswTitle, BorderLayout.NORTH);
            changePswFrame.getContentPane().add(inputPswFieldWrapper, BorderLayout.CENTER);
            changePswFrame.getContentPane().add(commitChangeBtn, BorderLayout.SOUTH);
            changePswFrame.pack();
            changePswFrame.setVisible(true);
        }
        public void actionPerformed(ActionEvent ae) {
            // Lấy action command.
            String comStr = ae.getActionCommand();
    
            if(comStr.contains("about_user")) {
                String thisId = comStr.split("_", 2)[1];
                personalPage usrPage = new personalPage(users.get(findUserById(thisId)), thisId);
                usrPage.runView();
            }
            else if(comStr.contains("see_login_history")) {
                on_process = true;
                ap.getPrintWriter().println("get_history@" + ID);
                historyPage hisPage = new historyPage(ID);
                hisPage.popupLoginHistory();
            }
            else if(comStr.contains("ban")) {
                if(users.get(findUserById(ID)).get("ban_status").equals("normal")) {
                    int n = JOptionPane.showConfirmDialog(
                                imgPanel, 
                                "Are you sure you want to BAN this account?", 
                                "Alert", 
                                JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        ap.getPrintWriter().println("ban_user@" + ID);
                    }
                }
                else if(users.get(findUserById(ID)).get("ban_status").equals("ban")) {
                    int n = JOptionPane.showConfirmDialog(
                                imgPanel, 
                                "Are you sure you want to UNBAN this account?", 
                                "Alert", 
                                JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        ap.getPrintWriter().println("unban_user@" + ID);
                    }
                }
            }
            else if(comStr.contains("change_password")) {
                runChangePassword();
            }
            else if(comStr.contains("commit_change_psw")) {
                String new_psw = newPswInput.getText();
                if(!new_psw.equals("")) {
                    if(new_psw.equals(users.get(findUserById(ID)).get("psw"))) {
                        JOptionPane.showMessageDialog(
                            changePswFrame, 
                            "New password must be DIFFERENT with old password", 
                            "Alert", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        int n = JOptionPane.showConfirmDialog(
                                    changePswFrame, 
                                    "Are you sure you want to change password?", 
                                    "Alert", 
                                    JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION){
                            ap.getPrintWriter().println("change_psw_user@" + ID + "@" + new_psw);
                            JOptionPane.showMessageDialog(
                                changePswFrame, 
                                "Change success", 
                                "Alert", 
                                JOptionPane.INFORMATION_MESSAGE);
                            changePswFrame.setVisible(false);
                        }
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(
                        changePswFrame, 
                        "Please enter new password!", 
                        "Alert", 
                        JOptionPane.ERROR_MESSAGE);
                }                  
            }
            else if(comStr.contains("see_all_friend")) {
                viewWrapper.setVisible(false);
                if(allFriendWrapper == null) {
                    runAllFriend();
                }
                else {
                    allFriendWrapper.setVisible(true);
                }
            }
            else if(comStr.contains("back_to_view")) {
                allFriendWrapper.setVisible(false);
                if(viewWrapper == null) {
                    runView();
                }
                else {
                    viewWrapper.setVisible(true);
                }
            }
        }
    }     
    public class historyPage implements ActionListener {
        Map<String, Map<String, String>> loginHistory;
        JFrame parent;
        JPanel contentPg; // To handle event
        int init_index = 0;
        int default_nof_panel = 6;
        // Create Img generator 
        img imgConfig = new img();
        // Page manegement
        ArrayList<JPanel[]> current_page = new ArrayList<>();
    
    
        historyPage(String id) {
            while(on_process) {
                if(loginHistories.containsKey(id)) {
                    loginHistory = loginHistories.get(id);
                }
                else {
                    loginHistory = null;
                }
            }
        }
        public void popupLoginHistory() {
            parent = new JFrame("History");
            parent.setLayout(new BorderLayout());
            parent.setResizable(false);
    
            // Thêm menu bar vào frame
            JMenuBar jmb = (new menubar()).createMenu();
            parent.setJMenuBar(jmb);
    
            // Header
            JLabel title = new JLabel("<html>" + 
            "<p style=\"font-family:Verdana;font-size:13px;font-weight:600\">" 
            + "LOGIN HISTORY" + "</p></html>", SwingConstants.CENTER);
            title.setBorder(new EmptyBorder(0, 0, 30, 0));
    
            // Content
            contentPg = new JPanel();
            contentPg.setLayout(new BoxLayout(contentPg, BoxLayout.Y_AXIS));
    
            // Panel hold swap page button
            JPanel swap_page_panel = new JPanel();
            swap_page_panel.setLayout(new BoxLayout(swap_page_panel, BoxLayout.X_AXIS));
    
            // Create next page and prev page button
            Image right_arrow_img = imgConfig.imgLoading("./assets/icons/right-arrow.png");
            ImageIcon right_arrow_icon = new ImageIcon(imgConfig.ScaleImage(right_arrow_img, 50 / 3, 50 / 3));
            JButton right_arrow_btn = new JButton(right_arrow_icon);
            right_arrow_btn.setActionCommand("next_page");
    
            Image left_arrow_img = imgConfig.imgLoading("./assets/icons/left-arrow.png");
            ImageIcon left_arrow_icon = new ImageIcon(imgConfig.ScaleImage(left_arrow_img, 50 / 3, 50 / 3));
            JButton left_arrow_btn = new JButton(left_arrow_icon);
            left_arrow_btn.setActionCommand("prev_page");
    
            right_arrow_btn.addActionListener(this);
            left_arrow_btn.addActionListener(this);
    
            swap_page_panel.add(left_arrow_btn);
            swap_page_panel.add(right_arrow_btn);
            
            if(loginHistory != null) {
                // Generate default 6 users
                current_page.add(this.generateHistoryPanel(default_nof_panel));
        
                // Render a list of user
                contentPg.add(swap_page_panel);
                this.renderPanel(contentPg, current_page.get(0));
            }
    
            // Footer
            JLabel copyright = new JLabel("<html><p style=\"font-family:Verdana;" + 
            "font-size:8px;\">Copyright © 2022 by Sunrise Company</p></html>", SwingConstants.CENTER);
            copyright.setBorder(new EmptyBorder(30, 0, 0, 0));
    
            parent.add(title, BorderLayout.NORTH);
            parent.add(contentPg, BorderLayout.CENTER);
            parent.add(copyright, BorderLayout.SOUTH);
            parent.pack();
            parent.setLocationRelativeTo(null);
            parent.setVisible(true);
        }
        public JPanel[] generateHistoryPanel(int size) {
            // Check if size is valid
            if(size <= 0) return null;
    
            // Get the valid size
            JPanel[] his_panel_list;
            int realSize;
            if(loginHistory.size() - init_index < size) {
                realSize = loginHistory.size() - init_index;
                his_panel_list = new JPanel[loginHistory.size() - init_index];
            }
            else {
                realSize = size;
                his_panel_list = new JPanel[size];
            }
    
    
            // Generate panel
            for(int i = init_index; i < init_index + realSize; i++) {
                String key = "his_" + Integer.toString(i + 1);
                //Create a sub panel
                JPanel user_info_Panel = new JPanel();
                user_info_Panel.setBorder(new LineBorder(Color.GRAY));
                user_info_Panel.setLayout(new BoxLayout(user_info_Panel, BoxLayout.X_AXIS));
    
                    //Get avt image
                Image originalIcon = imgConfig.imgLoading(loginHistory.get(key).get("device_icon"));
    
                    //Scale the original image
                ImageIcon scaledIcon = new ImageIcon(imgConfig.ScaleImage(originalIcon, 50, 50));
                JLabel avtLabel = new JLabel(scaledIcon);
                avtLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
    
                    // Text partial
                String userName = loginHistory.get(key).get("device_name") + " - " + loginHistory.get(key).get("location");
                String lastLoginInfo = loginHistory.get(key).get("online_status");
    
                JLabel textPartial = new JLabel("<html><p style=\"font-family:Verdana;font-size:10px;\">"  + userName + "</p>"
                + "<p style=\"font-family:Verdana;font-size:9px;font-weight: 400\">" + lastLoginInfo + "</p></html>");
    
                user_info_Panel.add(avtLabel);
                user_info_Panel.add(textPartial);
    
                his_panel_list[i - init_index] = user_info_Panel;
            }
    
            init_index += realSize;
    
            return his_panel_list;
        }
        public void renderPanel(JPanel target, JPanel[] element) {
            int size = element.length;
            for(int i = 0; i < size; i++)
                target.add(element[i]);
        }
        public void actionPerformed(ActionEvent ae) {
            // Lấy action command.
            String comStr = ae.getActionCommand();
            
            if(comStr.equals("next_page")) {
                if(init_index < loginHistory.size()) {
                    int cur_page_index = init_index / default_nof_panel;
                    if(init_index % default_nof_panel == 0)
                        --cur_page_index;
    
                    for(JPanel pnl : current_page.get(cur_page_index)) {
                        contentPg.remove(pnl);
                        contentPg.revalidate();
                        contentPg.repaint();
                    }
    
                    // Check if next page is not generate in the past
                    if(cur_page_index == current_page.size() - 1) {
                        current_page.add(this.generateHistoryPanel(default_nof_panel));
                    }
                    else {
                        init_index += default_nof_panel;
                    }
                    this.renderPanel(contentPg, current_page.get(cur_page_index + 1));   
                }
            }
            else if(comStr.equals("prev_page")) {
                int cur_page_index = init_index / default_nof_panel;
                if(init_index % default_nof_panel == 0)
                    --cur_page_index;
    
                if(cur_page_index > 0) {
                    for(JPanel pnl : current_page.get(cur_page_index)) {
                        contentPg.remove(pnl);
                        contentPg.revalidate();
                        contentPg.repaint();
                    }
                    this.renderPanel(contentPg, current_page.get(cur_page_index - 1));
                    init_index -= default_nof_panel;
                }
            }
        }
    }

    public JPanel HeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());

        JLabel title_lbl = new JLabel("USER LIST", SwingConstants.CENTER);
        title_lbl.setFont(new Font("Verdana", Font.BOLD, 20));
        title_lbl.setBorder(new EmptyBorder(10, 0, 15, 0));

        //Creating the panel at top and adding components
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        JLabel srchlabel = new JLabel("Find user");
        textField = new JTextField(10); // accepts upto 10 characters
        // Search btn
        JButton sendBtn = new JButton("Search");
        sendBtn.setActionCommand("search_user");
        sendBtn.addActionListener(this);
        sendBtn.setPreferredSize(new Dimension(80, 19));

        // Cancel btn only show when searched
        cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("cancel_search");
        cancelBtn.addActionListener(this);
        cancelBtn.setPreferredSize(new Dimension(80, 19));
        cancelBtn.setVisible(false);

        searchPanel.add(srchlabel);
        searchPanel.add(textField);
        searchPanel.add(sendBtn);
        searchPanel.add(cancelBtn);
        searchPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        header.add(title_lbl, BorderLayout.NORTH);
        header.add(searchPanel, BorderLayout.SOUTH);

        return header;
    }
    public JPanel ContentPanel() {
        // Set the main layout
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // Panel hold swap page button
        JPanel swap_page_panel = new JPanel();
        swap_page_panel.setLayout(new BoxLayout(swap_page_panel, BoxLayout.X_AXIS));

        // Create next page and prev page button
        Image right_arrow_img = imgConfig.imgLoading("./assets/icons/right-arrow.png");
        ImageIcon right_arrow_icon = new ImageIcon(imgConfig.ScaleImage(right_arrow_img, 50 / 3, 50 / 3));
        JButton right_arrow_btn = new JButton(right_arrow_icon);
        right_arrow_btn.setActionCommand("next_page");

        Image left_arrow_img = imgConfig.imgLoading("./assets/icons/left-arrow.png");
        ImageIcon left_arrow_icon = new ImageIcon(imgConfig.ScaleImage(left_arrow_img, 50 / 3, 50 / 3));
        JButton left_arrow_btn = new JButton(left_arrow_icon);
        left_arrow_btn.setActionCommand("prev_page");

        right_arrow_btn.addActionListener(this);
        left_arrow_btn.addActionListener(this);

        swap_page_panel.add(left_arrow_btn);
        swap_page_panel.add(right_arrow_btn);

        // Generate default 6 users
        current_page.add(this.generateUserPanel(default_nof_users));
        
        // Render a list of user
        content.add(swap_page_panel);
        this.renderPanel(content, current_page.get(0));

        return content;
    }
    public JPanel FooterPanel() {
        JPanel FooterPanel = new JPanel();
        FooterPanel.setLayout(new BorderLayout());

        JPanel sortOptionPnl = new JPanel();
        sortOptionPnl.setLayout(new FlowLayout());

        JLabel sortLbl = new JLabel("Sort option: ");
        String[] sortOption = {"None", "By name", "By create time"};
        sortInput = new JComboBox<String>(sortOption);
        sortInput.setActionCommand("sort");
        sortInput.addActionListener(this);

        String[] sortOrder = {"Increase", "Decrease"};
        sortOrderInput = new JComboBox<String>(sortOrder);
        sortOrderInput.setActionCommand("sort");
        sortOrderInput.addActionListener(this);
        sortOrderInput.setVisible(false);

        sortOptionPnl.add(sortLbl);
        sortOptionPnl.add(sortInput);
        sortOptionPnl.add(sortOrderInput);

        FooterPanel.add(sortOptionPnl, BorderLayout.WEST);
        return FooterPanel;
    }
    
    public int findUserById(String id) {
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).get("id").equals(id))
                return i;
        }
        return -1;
    }
    public void renderPanel(JPanel target, JPanel[] element) {
        int size = element.length;
        for(int i = 0; i < size; i++) {
            target.add(element[i]);
        }
    }
    public JPanel[] generateUserPanel(int size) {
        // Check if size is valid
        if(size <= 0) return null;

        // Get the valid size
        JPanel[] user_panel_list;
        int realSize;
        if(users.size() - init_index < size) {
            realSize = users.size() - init_index;
            user_panel_list = new JPanel[users.size() - init_index];
        }
        else {
            realSize = size;
            user_panel_list = new JPanel[size];
        }

        // Generate panel
        for(int i = init_index; i < init_index + realSize; i++) {
            //Create a sub panel
            JPanel user_info_Panel = new JPanel();
            user_info_Panel.setBorder(new LineBorder(Color.GRAY));
            user_info_Panel.setLayout(new BoxLayout(user_info_Panel, BoxLayout.X_AXIS));

                //Get avt image
            Image originalIcon = user_avts.get(users.get(i).get("id")).getImage();

                //Scale the original image
            ImageIcon scaledIcon = new ImageIcon(imgConfig.ScaleImage(originalIcon, 50, 50));
            JLabel avtLabel = new JLabel(scaledIcon);
            avtLabel.setBorder(new EmptyBorder(0, 0, 0, 10));

                // Text partial
            
            String userName = users.get(i).get("name");
            String lastLoginInfo = "Create at: " + users.get(i).get("createAt");

            JLabel textPartial = new JLabel("<html><p style=\"font-family:Verdana;font-size:10px;\">"  + userName + "</p>"
            + "<p style=\"font-family:Verdana;font-size:9px;font-weight: 400\">" + lastLoginInfo + "</p></html>");

            // Panel hold 2 btn align Y
            JPanel btnHolder = new JPanel();
            btnHolder.setLayout(new BoxLayout(btnHolder, BoxLayout.Y_AXIS));

                //Create view button
            ImageIcon viewicon = new ImageIcon(imgConfig.ScaleImage((imgConfig.imgLoading("./assets/icons/view.png")), 50 / 3, 50 / 3));
            JButton viewBtn = new JButton(viewicon);
            viewBtn.setFocusPainted(false);
            viewBtn.setContentAreaFilled(false);
            viewBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
            viewBtn.setActionCommand("about_" + users.get(i).get("id"));

            viewBtn.addActionListener(this);
            btnHolder.add(viewBtn);

            user_info_Panel.add(avtLabel);
            user_info_Panel.add(textPartial);
            user_info_Panel.add(btnHolder);

            user_panel_list[i - init_index] = user_info_Panel;                                                
        }

        init_index += realSize;
        return user_panel_list;
    }
    public void reRenderUserPanel() {
        // Config change page
            // Remove current
        int cur_page_index = init_index / default_nof_users;
        if(init_index % default_nof_users == 0)
            --cur_page_index;

        for(JPanel pnl : current_page.get(cur_page_index)) {
            content.remove(pnl);
            content.revalidate();
            content.repaint();
        }
        current_page = new ArrayList<JPanel[]>();

            // Renew data
        init_index = 0;
        current_page.add(this.generateUserPanel(default_nof_users));
        this.renderPanel(content, current_page.get(0));
    }
    public void userSortEngine(ArrayList<Map<String, String>> arr, int type, int order) {
        int n = arr.size();
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            if(type == 0) {
                for (int j = i+1; j < n; j++) {
                    if(order == 1) {
                        if (arr.get(j).get("name").charAt(0) < arr.get(min_idx).get("name").charAt(0))
                            min_idx = j;
                    }
                    else if(order == -1) {
                        if (arr.get(j).get("name").charAt(0) > arr.get(min_idx).get("name").charAt(0))
                            min_idx = j;
                    }
                }
            }
            else if(type == 1) {
                for (int j = i+1; j < n; j++) {
                    Date dj;
                    try {
                        dj = pattern.parse(arr.get(j).get("createAt"));
                        Date dmin = pattern.parse(arr.get(min_idx).get("createAt"));
                        if(order == 1) {
                            if (dj.compareTo(dmin) < 0)
                                min_idx = j;
                        }
                        else if(order == -1) {
                            if (dj.compareTo(dmin) > 0)
                                min_idx = j;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Swap the found minimum element with the first element
            Map<String, String> temp = arr.get(min_idx);
            arr.set(min_idx, arr.get(i));
            arr.set(i, temp);
        }
    }
    public void actionPerformed(ActionEvent ae) {
        // Lấy action command.
        String comStr = ae.getActionCommand();
        if(comStr.indexOf("about_user") != -1) {
            String thisId = comStr.split("_", 2)[1];
            usrPage = new personalPage(users.get(findUserById(thisId)), thisId);
            usrPage.runView();
        }
        else if(comStr.equals("next_page")) {
            if(init_index < users.size()) {
                int cur_page_index = init_index / default_nof_users;
                if(init_index % default_nof_users == 0)
                    --cur_page_index;

                for(JPanel pnl : current_page.get(cur_page_index)) {
                    content.remove(pnl);
                    content.revalidate();
                    content.repaint();
                }

                // Check if next page is not generate in the past
                if(cur_page_index == current_page.size() - 1) {
                    current_page.add(this.generateUserPanel(default_nof_users));
                }
                else {
                    init_index += default_nof_users;
                }
                this.renderPanel(content, current_page.get(cur_page_index + 1));   
            }
        }
        else if(comStr.equals("prev_page")) {
            int cur_page_index = init_index / default_nof_users;
            if(init_index % default_nof_users == 0)
                --cur_page_index;

            if(cur_page_index > 0) {
                for(JPanel pnl : current_page.get(cur_page_index)) {
                    content.remove(pnl);
                    content.revalidate();
                    content.repaint();
                }
                this.renderPanel(content, current_page.get(cur_page_index - 1));
                init_index -= default_nof_users;
            }
        }
        else if(comStr.equals("search_user")) {
            String key = textField.getText();
            if(key.trim().length() == 0) {
                JOptionPane.showMessageDialog(
                    jfrm, 
                    "Search field is empty!", 
                    "Alert", 
                    JOptionPane.ERROR_MESSAGE);
            }
            else {
                ArrayList<Map<String, String>> new_users = new ArrayList<Map<String, String>>();
                users_backup = users; // Backup data
                key = key.toLowerCase(); // Lowercase to search
                for(int i = 0; i < users.size(); i++) {
                    if(users.get(i).get("usn").toLowerCase().contains(key) || users.get(i).get("name").toLowerCase().contains(key)) {
                        new_users.add(users.get(i));
                    }
                }
                users = new_users;

                
                reRenderUserPanel();

                // Handle show cancel btn
                cancelBtn.setVisible(true);
                jfrm.revalidate();
                jfrm.repaint();
            }
        }
        else if(comStr.equals("cancel_search")) {
            textField.setText("");
            users = users_backup;
            cancelBtn.setVisible(false);
            jfrm.revalidate();
            jfrm.repaint();
            reRenderUserPanel();
        }
        else if(comStr.equals("sort")) {
            String val_type = ((String)sortInput.getSelectedItem()).toLowerCase();
            String val_order = ((String)sortOrderInput.getSelectedItem()).toLowerCase();
            if(val_type.contains("name")) {
                if(users_sort_backup == null) {
                    users_sort_backup = new ArrayList<Map<String, String>>(users);
                }
                if(val_order.equals("increase")) {
                    userSortEngine(users, 0, 1);
                    reRenderUserPanel();
                    // Handle show des and inc button
                    sortOrderInput.setVisible(true);
                    jfrm.revalidate();
                    jfrm.repaint();
                }
                else if(val_order.equals("decrease")) {
                    userSortEngine(users, 0, -1);
                    reRenderUserPanel();
                }
            }
            else if(val_type.contains("time")) {
                if(users_sort_backup == null) {
                    users_sort_backup = new ArrayList<Map<String, String>>(users);
                }
                if(val_order.equals("increase")) {
                    userSortEngine(users, 1, 1);
                    reRenderUserPanel();
                    // Handle show des and inc button
                    sortOrderInput.setVisible(true);
                    jfrm.revalidate();
                    jfrm.repaint();
                }
                else if(val_order.equals("decrease")) {
                    userSortEngine(users, 1, -1);
                    reRenderUserPanel();
                }
            }
            else if(val_type.contains("none")) {
                users = new ArrayList<Map<String, String>>(users_sort_backup);
                // Free memory
                users_sort_backup = null;
                reRenderUserPanel();
                sortOrderInput.setVisible(false);
                jfrm.revalidate();
                jfrm.repaint();
            }
        }

    }
    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { new App(); }
        });
    }
}
// public void popupEditDetail() {
        //     editDetailFrame = new JFrame() {
        //         @Override
        //         public Dimension getPreferredSize() {
        //             return new Dimension(824, 735);
        //         };
        //     };;
        //     editDetailFrame.setLayout(new BoxLayout(editDetailFrame.getContentPane(), BoxLayout.Y_AXIS));

        //     // Title
        //     JLabel title = new JLabel("<html><p style=\"font-family:Verdana;font-size:13px;\">PERSONAL INFOMATION</p></html>", SwingConstants.CENTER);
        //     // Name
        //     JPanel usnPnl = new JPanel();
        //     usnPnl.setLayout(new FlowLayout());
        //     JLabel usnLbl = new JLabel("Username");
        //     JTextField usnTf = new JTextField(users.get(ID).get("usn"));
        //     usnPnl.add(usnLbl);
        //     usnPnl.add(usnTf);
        //     // USN
        //     JPanel usnPnl = new JPanel();
        //     usnPnl.setLayout(new FlowLayout());
        //     JLabel usnLbl = new JLabel("Username");
        //     JTextField usnTf = new JTextField(users.get(ID).get("usn"));
        //     usnPnl.add(usnLbl);
        //     usnPnl.add(usnTf);
        //     // PSW
        //     JPanel usnPnl = new JPanel();
        //     usnPnl.setLayout(new FlowLayout());
        //     JLabel usnLbl = new JLabel("Username");
        //     JTextField usnTf = new JTextField(users.get(ID).get("usn"));
        //     usnPnl.add(usnLbl);
        //     usnPnl.add(usnTf);
        //     // EMAIL
        //     JPanel usnPnl = new JPanel();
        //     usnPnl.setLayout(new FlowLayout());
        //     JLabel usnLbl = new JLabel("Username");
        //     JTextField usnTf = new JTextField(users.get(ID).get("usn"));
        //     usnPnl.add(usnLbl);
        //     usnPnl.add(usnTf);
        //     // ID
        //     JPanel usnPnl = new JPanel();
        //     usnPnl.setLayout(new FlowLayout());
        //     JLabel usnLbl = new JLabel("Username");
        //     JTextField usnTf = new JTextField(users.get(ID).get("usn"));
        //     usnPnl.add(usnLbl);
        //     usnPnl.add(usnTf);

        // }