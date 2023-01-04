package ClientApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;

public class Client implements ActionListener {
    JFrame chat_jfrm, usn_jfrm;
    // Client Config
    int portnumber;
    Socket client;
    ClientProcess cp;

    String send_msg_icon = "./assets/imgs/icons/send-message.png";
    ArrayList<ArrayList<String>> chatrooms;

    JPanel currenUser, prevUserPanel, chatPanel, chatSide;
    JLabel headerLabel, usernameLabel, fullnameLabel, addressLabel, dobLabel, emailLabel, passwordLabel, sexLabel, addfriendLabel, unfriendLabel;
    JTextField inputField, usernameField, fullnameField, addressField, dobField, emailField, passwordField, addfriendField, unfriendField;
    JTextArea ta_chat, friendTextArea;
    JButton loginBtn, signupBtn, resetPasswordBtn, addfriendBtn, unfriendBtn;
    ButtonGroup sexBtnGroup;
    JRadioButton maleRadioButton, femaleRadioButton;
    JScrollPane friendScrollPane;
    private List<String> friendStrList;

    String usn, prevRoom;

    JFrame signUp, logIn, friendList;

    Client() {
        this.chat_jfrm = new JFrame("Chatting");
        this.usn_jfrm = new JFrame("Username Request");
        // Client Config
        this.portnumber = 2014;
        this.client = null;
        this.currenUser = new JPanel();
        this.prevUserPanel = null;
        this.chatPanel = new JPanel();
        this.inputField = new JTextField();
        this.ta_chat = new JTextArea();
        this.chatSide = new JPanel();
        this.usn = "";
        this.prevRoom = "";

        this.chatrooms = new ArrayList<ArrayList<String>>();

        // runUsnRequest();

        this.cp = new ClientProcess();
        Thread cl_thrd = new Thread(cp);
        cl_thrd.start();

        this.signUp = new JFrame("Sign up");
        this.logIn = new JFrame("Log in");
        this.friendList = new JFrame("Friend List");
        login();
    }

    void login() {

        logIn.setLayout(null);
        logIn.setResizable(false);

        logIn.setSize(720, 720);
        logIn.setLocationRelativeTo(null);
        logIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel = new JLabel("LOGIN");
        usernameLabel = new JLabel("Usernname:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(15);
        passwordField = new JTextField(15);

        loginBtn = new JButton("Log in");
        signupBtn = new JButton("Sign up");
        resetPasswordBtn = new JButton("Reset password");

        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));

        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        loginBtn.setFont(new Font("Arial", Font.BOLD, 24));
        signupBtn.setFont(new Font("Arial", Font.BOLD, 24));
        resetPasswordBtn.setFont(new Font("Arial", Font.BOLD, 18));

        loginBtn.setActionCommand("login@");
        loginBtn.addActionListener(this);

        signupBtn.setActionCommand("logintosignup");
        signupBtn.addActionListener(this);

        resetPasswordBtn.setActionCommand("resetpass");
        resetPasswordBtn.addActionListener(this);

        logIn.add(headerLabel);
        logIn.add(usernameLabel);
        logIn.add(passwordLabel);
        logIn.add(usernameField);
        logIn.add(passwordField);
        logIn.add(loginBtn);
        logIn.add(signupBtn);
        logIn.add(resetPasswordBtn);

        headerLabel.setBounds(300, 150, 600, 60);
        usernameLabel.setBounds(150, 250, 180, 40);
        passwordLabel.setBounds(150, 310, 180, 40);

        usernameField.setBounds(250, 250, 310, 40);
        passwordField.setBounds(250, 310, 310, 40);

        loginBtn.setBackground(Color.ORANGE);
        loginBtn.setOpaque(true);
        loginBtn.setBounds(280, 380, 180, 40);

        signupBtn.setBackground(Color.PINK);
        signupBtn.setOpaque(true);
        signupBtn.setBounds(280, 440, 180, 40);

        resetPasswordBtn.setBackground(Color.green);
        resetPasswordBtn.setOpaque(true);
        resetPasswordBtn.setBounds(280, 500, 180, 40);

        logIn.setVisible(true);

    }

    void signup() {
        signUp.setLayout(null);
        signUp.setResizable(false);

        signUp.setSize(720, 720);
        signUp.setLocationRelativeTo(null);
        signUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel = new JLabel("Create your account");
        usernameLabel = new JLabel("Username:");
        fullnameLabel = new JLabel("Full name:");
        addressLabel = new JLabel("Address:");
        dobLabel = new JLabel("Day of birth:");
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        sexLabel = new JLabel("Sex:");

        usernameField = new JTextField(15);
        fullnameField = new JTextField(15);
        addressField = new JTextField(15);
        dobField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JTextField(15);

        sexBtnGroup = new ButtonGroup();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");

        signupBtn = new JButton("Sign up");

        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fullnameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dobLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sexLabel.setFont(new Font("Arial", Font.BOLD, 16));

        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        fullnameField.setFont(new Font("Arial", Font.PLAIN, 16));
        addressField.setFont(new Font("Arial", Font.PLAIN, 16));
        dobField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        maleRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        femaleRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        signupBtn.setFont(new Font("Arial", Font.BOLD, 24));

        signupBtn.setActionCommand("signup@");
        signupBtn.addActionListener(this);

        signUp.add(headerLabel);
        signUp.add(usernameLabel);
        signUp.add(fullnameLabel);
        signUp.add(addressLabel);
        signUp.add(dobLabel);
        signUp.add(emailLabel);
        signUp.add(passwordLabel);
        signUp.add(sexLabel);
        signUp.add(usernameField);
        signUp.add(fullnameField);
        signUp.add(addressField);
        signUp.add(dobField);
        signUp.add(emailField);
        signUp.add(passwordField);
        signUp.add(maleRadioButton);
        signUp.add(femaleRadioButton);
        signUp.add(signupBtn);

        sexBtnGroup.add(maleRadioButton);
        sexBtnGroup.add(femaleRadioButton);

        headerLabel.setBounds(220, 50, 600, 60);
        usernameLabel.setBounds(120, 150, 180, 40);
        fullnameLabel.setBounds(120, 210, 180, 40);
        addressLabel.setBounds(120, 270, 180, 40);
        dobLabel.setBounds(120, 330, 180, 40);
        emailLabel.setBounds(120, 390, 180, 40);
        passwordLabel.setBounds(120, 450, 180, 40);
        sexLabel.setBounds(120, 510, 180, 40);

        usernameField.setBounds(300, 150, 310, 40);
        fullnameField.setBounds(300, 210, 310, 40);
        addressField.setBounds(300, 270, 310, 40);
        dobField.setBounds(300, 330, 310, 40);
        emailField.setBounds(300, 390, 310, 40);
        passwordField.setBounds(300, 450, 310, 40);

        maleRadioButton.setBounds(300, 510, 140, 40);
        femaleRadioButton.setBounds(440, 150, 140, 40);

        signupBtn.setBackground(Color.ORANGE);
        signupBtn.setOpaque(true);
        signupBtn.setBounds(260, 560, 180, 40);

        signUp.setVisible(true);
    }

    void friendlist(){

        friendList.setLayout(null);
        friendList.setResizable(false);

        friendList.setSize(720, 720);
        friendList.setLocationRelativeTo(null);
        friendList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        headerLabel = new JLabel("Friend");
        usernameLabel = new JLabel("Your username");
        addfriendLabel = new JLabel("Add friend by username");
        unfriendLabel = new JLabel("Unfriend by username");

        usernameField = new JTextField(15);
        addfriendField = new JTextField(15);
        unfriendField = new JTextField(15);

        addfriendBtn = new JButton("Add friend");
        unfriendBtn = new JButton("Unfriend");

        friendTextArea = new JTextArea(16,16);
        friendScrollPane = new JScrollPane(friendTextArea);

        friendStrList = new ArrayList<String>();

        //set font
        headerLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        addfriendLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        unfriendLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        addfriendField.setFont(new Font("Arial", Font.PLAIN, 18));
        unfriendField.setFont(new Font("Arial", Font.PLAIN, 17));
        addfriendBtn.setFont(new Font("Arial", Font.BOLD, 17));
        unfriendBtn.setFont(new Font("Arial", Font.BOLD, 18));
        friendTextArea.setFont(new Font("Arial", Font.PLAIN, 18));

        //add action
        // showAction();
        cp.getPrintWriter().println("friendlist@" + usn);
        
        addfriendBtn.setActionCommand("addfriend@");
        addfriendBtn.addActionListener(this);
        
        unfriendBtn.setActionCommand("unfriend@");
        unfriendBtn.addActionListener(this);

        //add to frame
        friendList.add(headerLabel);
        friendList.add(usernameLabel);
        friendList.add(addfriendLabel);
        friendList.add(unfriendLabel);
        friendList.add(usernameField);
        friendList.add(addfriendField);
        friendList.add(unfriendField);
        friendList.add(addfriendBtn);
        friendList.add(unfriendBtn);
        friendList.add(friendTextArea);
        friendList.add(friendScrollPane);
        
        //set color
        addfriendBtn.setBackground(new Color(103,201,250));
        addfriendBtn.setOpaque(true);

        unfriendBtn.setBackground(new Color(255,84,84));
        unfriendBtn.setOpaque(true);

        friendTextArea.setBackground(new Color(246, 250, 142));
        friendTextArea.setOpaque(true);

        //set scrollpane
        friendScrollPane.setViewportView(friendTextArea);

        //set size and location
        headerLabel.setBounds(30, 20, 300, 60);
        usernameLabel.setBounds(360, 30, 300, 40);
        addfriendLabel.setBounds(360, 30 + 50*3, 300, 40);
        unfriendLabel.setBounds(360, 30 + 50*7, 300, 40);

        usernameField.setBounds(360, 30 + 50, 300, 40);
        addfriendField.setBounds(360, 30 + 50*4, 300, 40);
        unfriendField.setBounds(360, 30 + 50*8, 300, 40);

        addfriendBtn.setBounds(360, 30 + 50*5, 300, 40);
        unfriendBtn.setBounds(360, 30 + 50*9, 300, 40);
        
        friendScrollPane.setBounds(30 , 20 + 60, 300, 560);

        friendTextArea.setBounds(30, 20 + 60, 300, 560);

        //set text field enabled
        usernameField.setEnabled(false);
        friendTextArea.setEditable(false);

        //just for username of current user
        usernameField.setText(usn);

        friendList.setVisible(true);

    }

    // void runUsnRequest() {
    //     // GUI
    //     usn_jfrm.setLayout(new FlowLayout());
    //     usn_jfrm.setResizable(false);

    //     // Tùy chỉnh kích thước
    //     usn_jfrm.setSize(800, 600);
    //     usn_jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //     JPanel wrapper = new JPanel();
    //     wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
    //     wrapper.setPreferredSize(new Dimension(800, 600));

    //     JLabel welcome = new JLabel("WELCOME TO MESSENGER (SHOPEE VERSION)");
    //     JLabel title = new JLabel("Enter your username");
    //     welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
    //     welcome.setFont(new Font("Verdana", Font.BOLD, 20));
    //     welcome.setBorder(new EmptyBorder(40, 0, 170, 0));
    //     title.setAlignmentX(Component.CENTER_ALIGNMENT);
    //     title.setFont(new Font("Verdana", Font.BOLD, 18));

    //     JPanel usn_input_field = new JPanel();
    //     usn_input_field.setLayout(new FlowLayout());

    //     JTextField usn_input = new JTextField();
    //     usn_input.setColumns(12);
    //     usn_input.setFont(new Font("Verdana", Font.PLAIN, 18));
    //     JButton get_usn_btn = new JButton("Enter");
    //     get_usn_btn.addActionListener(new ActionListener() {
    //         public void actionPerformed(java.awt.event.ActionEvent ae) {
    //             usn = usn_input.getText();
    //             if (usn != null) {
    //                 usn_jfrm.setVisible(false);
    //                 runChat();
    //             }
    //         }
    //     });

    //     usn_input_field.add(usn_input);
    //     usn_input_field.add(get_usn_btn);

    //     wrapper.add(welcome);
    //     wrapper.add(title);
    //     wrapper.add(usn_input_field);

    //     usn_jfrm.getContentPane().add(wrapper);
    //     usn_jfrm.pack();
    //     usn_jfrm.setVisible(true);
    // }

    // void runChat() {
    //     // Run Client
    //     this.cp = new ClientProcess();
    //     Thread cl_thrd = new Thread(cp);
    //     cl_thrd.start();

    //     // GUI
    //     chat_jfrm.setLayout(new FlowLayout());
    //     chat_jfrm.setResizable(false);

    //     // Tùy chỉnh kích thước
    //     chat_jfrm.setSize(800, 600);

    //     chat_jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     chat_jfrm.addWindowListener(new java.awt.event.WindowAdapter() {
    //         @Override
    //         public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    //             cp.getPrintWriter().println("exit");
    //             try {
    //                 if (client != null)
    //                     client.close();
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //             }
    //             System.exit(0);
    //         }
    //     });

    //     // Contact side
    //     JPanel contactSide = new JPanel();
    //     contactSide.setLayout(new BoxLayout(contactSide, BoxLayout.Y_AXIS));
    //     contactSide.setPreferredSize(new Dimension(250, 600));
    //     contactSide.setBackground(new Color(255, 255, 255));

    //     JLabel chatLabel = new JLabel("<html><p style=\"font-family:Verdana;font-size:14px;\">Chats</p></html>");
    //     chatLabel.setBorder(new EmptyBorder(20, 0, 30, 20));
    //     contactSide.add(chatLabel);

    //     this.renderPanel(contactSide, this.generateUserPanel());

    //     // Chat side
    //     currenUser.setLayout(new BoxLayout(currenUser, BoxLayout.Y_AXIS));
    //     chatSide.setLayout(new BorderLayout());
    //     chatSide.setPreferredSize(new Dimension(450, 600));
    //     chatSide.setBackground(new Color(255, 255, 255));

    //     // Chat Field
    //     chatPanel.setLayout(new BorderLayout());
    //     ta_chat.setMargin(new Insets(30, 10, 20, 10));
    //     chatPanel.add(ta_chat);
    //     chatPanel.setVisible(false);

    //     // Input Field
    //     JPanel inputMsgPanel = new JPanel();
    //     inputMsgPanel.setLayout(new FlowLayout());
    //     inputMsgPanel.setBackground(new Color(255, 255, 255));
    //     inputMsgPanel.setPreferredSize(new Dimension(450, 60));
    //     // Create input field
    //     inputField.setColumns(18);
    //     inputField.setFont(new Font("Verdana", Font.ITALIC, 20));
    //     inputField.setMargin(new Insets(0, 10, 0, 10));
    //     // Create send btn
    //     ImageIcon scaledIcon = new ImageIcon(ScaleImage(imgLoading(send_msg_icon), 20, 20));
    //     JButton sendBtn = new JButton(scaledIcon);
    //     sendBtn.setActionCommand("chat@");
    //     sendBtn.addActionListener(this);
    //     inputMsgPanel.add(inputField);
    //     inputMsgPanel.add(sendBtn);

    //     chatSide.add(currenUser, BorderLayout.NORTH);
    //     chatSide.add(chatPanel, BorderLayout.CENTER);
    //     chatSide.add(inputMsgPanel, BorderLayout.SOUTH);

    //     chat_jfrm.getContentPane().add(contactSide);
    //     chat_jfrm.getContentPane().add(chatSide);
    //     chat_jfrm.pack();
    //     chat_jfrm.setVisible(true);
    // }

    void renderCurrentUser(JPanel root, String id) {
        if (prevUserPanel != null) {
            root.remove(prevUserPanel);
        }
        for (int i = 0; i < chatrooms.size(); i++) {
            if (chatrooms.get(i).get(0).equals(id)) {
                // Create a sub panel
                JPanel user_info_Panel = new JPanel();
                user_info_Panel.setLayout(new BoxLayout(user_info_Panel, BoxLayout.X_AXIS));

                // Get avt image
                Image originalIcon = imgLoading(chatrooms.get(i).get(2));

                // Scale the original image
                ImageIcon scaledIcon = new ImageIcon(ScaleImage(originalIcon, 50, 50));
                JLabel avtLabel = new JLabel(scaledIcon);
                avtLabel.setBorder(new EmptyBorder(0, 0, 0, 10));

                // Text partial
                String userName = chatrooms.get(i).get(1);

                JLabel textPartial = new JLabel(
                        "<html><p style=\"font-family:Verdana;font-size:10px;\">" + userName + "<html>");

                user_info_Panel.add(avtLabel);
                user_info_Panel.add(textPartial);

                root.add(user_info_Panel);
                prevUserPanel = user_info_Panel;
                break;
            }
        }
    }

    void renderPanel(JPanel target, JPanel[] element) {
        int size = element.length;
        for (int i = 0; i < size; i++)
            target.add(element[i]);
    }

    JPanel[] generateUserPanel() {
        // Get the valid size
        JPanel[] user_panel_list = new JPanel[chatrooms.size()];

        // Generate panel
        for (int i = 0; i < chatrooms.size(); i++) {
            JPanel btnPanel = new JPanel();
            btnPanel.setLayout(new BorderLayout());
            btnPanel.setMaximumSize(new Dimension(250, 60));

            // Get avt image
            Image originalIcon = new ImageIcon(chatrooms.get(i).get(2)).getImage();

            // Scale the original image
            ImageIcon scaledIcon = new ImageIcon(originalIcon.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
            JButton userBtn = new JButton(chatrooms.get(i).get(1), scaledIcon);
            userBtn.setHorizontalAlignment(SwingConstants.LEFT);
            userBtn.setMargin(new Insets(0, 0, 0, 0));
            userBtn.setContentAreaFilled(false);
            userBtn.setActionCommand("@toroomid@" + chatrooms.get(i).get(0));
            userBtn.addActionListener(this);

            btnPanel.add(userBtn);
            user_panel_list[i] = btnPanel;
        }

        return user_panel_list;
    }

    public class ClientProcess implements Runnable {
        PrintWriter pw = null;
        BufferedReader br = null;
        String msg = "";
        Thread msgReceiver;

        ClientProcess() {
            try {
                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);

                // Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                pw = new PrintWriter(clientOut, true);

                // Create an input stream of the client socket
                InputStream clientIn = client.getInputStream();
                br = new BufferedReader(new InputStreamReader(clientIn));

                // Get chatroom data
                pw.println("get_chatroom_data");
                String res = br.readLine();
                while (!res.equals("end")) {
                    ArrayList<String> chatroom = new ArrayList<String>();
                    while (!res.equals("end")) {
                        chatroom.add(res);
                        res = br.readLine();
                    }
                    chatrooms.add(chatroom);
                    // Get next chatroom
                    res = br.readLine();
                }

                // Set username
                pw.println("usn@" + usn);

            } catch (IOException ioe) {
                System.err.println("Error: " + ioe);
            }

            msgReceiver = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String command = br.readLine();
                            if (command.contains("newmsg@")) {
                                String msg = command.substring(command.lastIndexOf("@") + 1);
                                // Gui handle
                                ta_chat.append(msg + "\n");
                                chatSide.revalidate();
                                chatSide.repaint();
                            } else if (command.contains("sending_chat_history")) {
                                String crdata = "";
                                String res = br.readLine();
                                while (!res.equals("end")) {
                                    crdata += (res + "\n");
                                    res = br.readLine();
                                }
                                // Gui handle

                                ta_chat.setText(crdata);
                                chatSide.revalidate();
                                chatSide.repaint();
                            } else if (command.contains("loginsuccess")) {
                                System.out.println("ok vo");
                                usn = usernameField.getText();
                                logIn.setVisible(false);
                                friendlist();
                            } else if (command.contains("loginfail")) {
                                JOptionPane.showMessageDialog(logIn, "Username or password is wrong!");
                            
                            } else if (command.contains("signupsuccess")) {
                                usn = usernameField.getText();
                                signUp.setVisible(false);
                                friendlist();
                            } else if (command.contains("signupfail")) {
                                JOptionPane.showMessageDialog(logIn, "Sign up fail!");
                            
                            } else if (command.contains("sending_friend_list")) {
                                // do sth
                            
                            } else if (command.contains("addfriendsuccess")) {
                                // do sth
                            
                            } else if (command.contains("unfriendsuccess")) {
                                // do sth
                            }
                        }
                    } catch (IOException ioe) {
                        System.err.println("Error receiver: " + ioe);
                    }
                }
            });
        }

        // Public method
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

    Image imgLoading(String filepath) {
        ImageIcon icon = new ImageIcon(filepath);
        return icon.getImage();
    }

    ImageIcon IconLoading(String filepath) {
        ImageIcon icon = new ImageIcon(filepath);
        return icon;
    }

    Image ScaleImage(Image img, int width, int height) {
        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return scaled;
    }

    public void actionPerformed(java.awt.event.ActionEvent ae) {
        // Lấy action command.
        String comStr = ae.getActionCommand();
        if (comStr.contains("@toroomid@")) {
            String nextRoom = comStr.substring(comStr.lastIndexOf("@") + 1);
            if (currenUser.getComponentCount() == 0) {
                chatPanel.setVisible(true);
            }
            this.renderCurrentUser(currenUser, nextRoom);
            cp.getPrintWriter().println(prevRoom + comStr);
            prevRoom = nextRoom;
        } else if (comStr.contains("chat@")) {
            String msg = inputField.getText();
            if (msg != null && currenUser.getComponentCount() != 0) {
                cp.getPrintWriter().println(comStr + msg);
                // Gui handle
                ta_chat.append(usn + ": " + msg + "\n");
                inputField.setText(null);
            }
        } else if (comStr.contains("login@")) {
            if (usernameField.getText().equals("")) {
                JOptionPane.showMessageDialog(logIn, "Username is empty");
                return;
            }
            if (passwordField.getText().equals("")) {
                JOptionPane.showMessageDialog(logIn, "Password is empty");
                return;
            }

            String msg = usernameField.getText() + "?" + passwordField.getText();
            cp.getPrintWriter().println(comStr + msg);
        } else if (comStr.contains("logintosignup")) {
            logIn.setVisible(false);
            signup();
        } else if (comStr.contains("resetpass")) {
            // send email
        } else if (comStr.contains("signup@")) {
            if (usernameField.getText().equals("") || fullnameField.getText().equals("")
                    || addressField.getText().equals("") || dobField.getText().equals("")
                    || emailField.getText().equals("")
                    || passwordField.getText().equals("")) {
                JOptionPane.showMessageDialog(signUp, "Lack of information");
                return;
            }
            try {
                new SimpleDateFormat("dd/MM/yyyy").parse(dobField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(signupBtn, this, "Wrong date format", portnumber);
                return;
            }
            
            String msg = usernameField.getText() + "?" + fullnameField.getText() + "?" + addressField.getText() + "?" + dobField.getText() + "?" + emailField.getText() + "?" + passwordField.getText();
            cp.getPrintWriter().println(comStr + msg);
        } else if (comStr.contains("addfriend@")) {
            if (addfriendField.getText().equals("")) {
                JOptionPane.showMessageDialog(signUp, "Input username to add");
                return;
            }
            
            String msg = usn + "?" + addfriendField.getText();
            cp.getPrintWriter().println(comStr + msg);
        } else if (comStr.contains("unfriend@")) {
            if (unfriendField.getText().equals("")) {
                JOptionPane.showMessageDialog(signUp, "Input username to delete");
                return;
            }            
            String msg = usn + "?" + unfriendField.getText();
            cp.getPrintWriter().println(comStr + msg);
        }

    }

    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Client();
            }
        });
    }
}