
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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

    // NEW
    ArrayList<Map<String, String>> chat_history_list = new ArrayList<Map<String, String>>();
    ArrayList<Map<String, ImageIcon>> friend_avt = new ArrayList<Map<String, ImageIcon>>();
    ArrayList<Map<String, ImageIcon>> chat_avt = new ArrayList<Map<String, ImageIcon>>();

    String id, usn, prevRoom;

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
        logIn.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cp.getPrintWriter().println("exit_without_login");
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });

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
        signUp.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cp.getPrintWriter().println("exit");
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
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
        // DECLARE FRAME
        friendList.setLayout(null);
        friendList.setResizable(false);
        friendList.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cp.getPrintWriter().println("exit");
                try {
                    client.close();
                } catch(IOException ioe) {}
                System.exit(0);
            }
        });

        friendList.setSize(720, 720);
        friendList.setLocationRelativeTo(null);
        friendList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // IMPORTANT VARIABLE
        JPanel contentPane;
        JButton friendButton;
        JTextField searchHistoryInput;
        JTextField messageInput;

		friendList.setBounds(100, 100, 985, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		friendList.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(300, 10));
		contentPane.add(panel, BorderLayout.WEST);
		
		JScrollPane friendScrollPane = new JScrollPane();
		friendScrollPane.setBackground(new Color(255, 255, 255));
		friendScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		friendScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		friendScrollPane.setPreferredSize(new Dimension(300, 460));
		panel.add(friendScrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Chat");
		lblNewLabel_1.setBackground(new Color(64, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		friendScrollPane.setColumnHeaderView(lblNewLabel_1);
		
		JPanel friendPanel = new JPanel();
		friendPanel.setPreferredSize(new Dimension(280, 2000));
		friendPanel.setBackground(new Color(255, 255, 255));
		friendScrollPane.setViewportView(friendPanel);
		friendPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton[] fr_btn = this.generateUserPanelFromChatHis();
        this.renderPanel(friendPanel, fr_btn);
		
		JPanel functionPanel = new JPanel();
		functionPanel.setPreferredSize(new Dimension(280, 140));
		panel.add(functionPanel);
		
		JButton viewListFriendButton = new JButton("Danh sách bạn bè");
		viewListFriendButton.setBackground(new Color(0, 255, 255));
		viewListFriendButton.setHorizontalTextPosition(SwingConstants.CENTER);
		viewListFriendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		functionPanel.setLayout(new GridLayout(0, 2, 0, 0));
		functionPanel.add(viewListFriendButton);
		
		JButton createGroupButton = new JButton("Tạo nhóm");
		createGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createGroupButton.setBackground(new Color(0, 255, 255));
		functionPanel.add(createGroupButton);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBackground(new Color(0, 255, 255));
		functionPanel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBackground(new Color(0, 255, 255));
		functionPanel.add(btnNewButton_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(650, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1);
		
		JTextArea chatArea = new JTextArea();
		chatArea.setPreferredSize(new Dimension(5, 420));
		scrollPane_1.setViewportView(chatArea);
		
		JLabel currentChatFriend = new JLabel("Bạn bè 1");
		currentChatFriend.setIcon(new ImageIcon("E:\\java - dh\\final project\\ChatPrivateUI\\src\\user2.png"));
		currentChatFriend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentChatFriend.setPreferredSize(new Dimension(43, 60));
		panel_5.add(currentChatFriend, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_5.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		messageInput = new JTextField();
		panel_3.add(messageInput);
		messageInput.setColumns(10);
		
		JButton sendMessageButton = new JButton("Gửi tin");
		sendMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sendMessageButton.setBackground(new Color(0, 255, 255));
		panel_3.add(sendMessageButton, BorderLayout.EAST);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.CENTER);
		
		JButton deleteHistoryButton = new JButton("Xóa lịch sử chat");
		deleteHistoryButton.setBackground(new Color(0, 255, 255));
		deleteHistoryButton.setBounds(23, 20, 137, 31);
		deleteHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_6.setLayout(null);
		panel_6.add(deleteHistoryButton);
		
		searchHistoryInput = new JTextField();
		searchHistoryInput.setBounds(23, 84, 327, 48);
		searchHistoryInput.setPreferredSize(new Dimension(2000, 30));
		panel_6.add(searchHistoryInput);
		searchHistoryInput.setColumns(10);
		
		JButton sendSearchHistoryButton = new JButton("Tìm");
		sendSearchHistoryButton.setBackground(new Color(0, 128, 192));
		sendSearchHistoryButton.setForeground(new Color(255, 255, 255));
		sendSearchHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sendSearchHistoryButton.setBounds(362, 80, 74, 52);
		panel_6.add(sendSearchHistoryButton);
		
		JButton deleteHistoryButton2 = new JButton("Xóa lịch sử chat2");
		deleteHistoryButton2.setBackground(new Color(0, 255, 255));
		deleteHistoryButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteHistoryButton2.setBounds(170, 20, 136, 31);
		panel_6.add(deleteHistoryButton2);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 61, 79, 13);
		panel_6.add(lblNewLabel);

        // SET MAIN FRAME TRUE
        friendList.setVisible(true);
        // headerLabel = new JLabel("Friend");
        // usernameLabel = new JLabel("Your username");
        // addfriendLabel = new JLabel("Add friend by username");
        // unfriendLabel = new JLabel("Unfriend by username");

        // usernameField = new JTextField(15);
        // addfriendField = new JTextField(15);
        // unfriendField = new JTextField(15);

        // addfriendBtn = new JButton("Add friend");
        // unfriendBtn = new JButton("Unfriend");

        // friendTextArea = new JTextArea(16,16);
        // friendScrollPane = new JScrollPane(friendTextArea);

        // friendStrList = new ArrayList<String>();

        // //set font
        // headerLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        // usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        // addfriendLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        // unfriendLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        // usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        // addfriendField.setFont(new Font("Arial", Font.PLAIN, 18));
        // unfriendField.setFont(new Font("Arial", Font.PLAIN, 17));
        // addfriendBtn.setFont(new Font("Arial", Font.BOLD, 17));
        // unfriendBtn.setFont(new Font("Arial", Font.BOLD, 18));
        // friendTextArea.setFont(new Font("Arial", Font.PLAIN, 18));

        // //add action
        // // showAction();
        // cp.getPrintWriter().println("friendlist@" + usn);
        
        // addfriendBtn.setActionCommand("addfriend@");
        // addfriendBtn.addActionListener(this);
        
        // unfriendBtn.setActionCommand("unfriend@");
        // unfriendBtn.addActionListener(this);

        // //add to frame
        // friendList.add(headerLabel);
        // friendList.add(usernameLabel);
        // friendList.add(addfriendLabel);
        // friendList.add(unfriendLabel);
        // friendList.add(usernameField);
        // friendList.add(addfriendField);
        // friendList.add(unfriendField);
        // friendList.add(addfriendBtn);
        // friendList.add(unfriendBtn);
        // friendList.add(friendTextArea);
        // friendList.add(friendScrollPane);
        
        // //set color
        // addfriendBtn.setBackground(new Color(103,201,250));
        // addfriendBtn.setOpaque(true);

        // unfriendBtn.setBackground(new Color(255,84,84));
        // unfriendBtn.setOpaque(true);

        // friendTextArea.setBackground(new Color(246, 250, 142));
        // friendTextArea.setOpaque(true);

        // //set scrollpane
        // friendScrollPane.setViewportView(friendTextArea);

        // //set size and location
        // headerLabel.setBounds(30, 20, 300, 60);
        // usernameLabel.setBounds(360, 30, 300, 40);
        // addfriendLabel.setBounds(360, 30 + 50*3, 300, 40);
        // unfriendLabel.setBounds(360, 30 + 50*7, 300, 40);

        // usernameField.setBounds(360, 30 + 50, 300, 40);
        // addfriendField.setBounds(360, 30 + 50*4, 300, 40);
        // unfriendField.setBounds(360, 30 + 50*8, 300, 40);

        // addfriendBtn.setBounds(360, 30 + 50*5, 300, 40);
        // unfriendBtn.setBounds(360, 30 + 50*9, 300, 40);
        
        // friendScrollPane.setBounds(30 , 20 + 60, 300, 560);

        // friendTextArea.setBounds(30, 20 + 60, 300, 560);

        // //set text field enabled
        // usernameField.setEnabled(false);
        // friendTextArea.setEditable(false);

        // //just for username of current user
        // usernameField.setText(usn);

        // friendList.setVisible(true);

    }

    // void runUsnRequest() {
        // // GUI
        // usn_jfrm.setLayout(new FlowLayout());
        // usn_jfrm.setResizable(false);

        // // Tùy chỉnh kích thước
        // usn_jfrm.setSize(800, 600);
        // usn_jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel wrapper = new JPanel();
        // wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        // wrapper.setPreferredSize(new Dimension(800, 600));

        // JLabel welcome = new JLabel("WELCOME TO MESSENGER (SHOPEE VERSION)");
        // JLabel title = new JLabel("Enter your username");
        // welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        // welcome.setFont(new Font("Verdana", Font.BOLD, 20));
        // welcome.setBorder(new EmptyBorder(40, 0, 170, 0));
        // title.setAlignmentX(Component.CENTER_ALIGNMENT);
        // title.setFont(new Font("Verdana", Font.BOLD, 18));

        // JPanel usn_input_field = new JPanel();
        // usn_input_field.setLayout(new FlowLayout());

        // JTextField usn_input = new JTextField();
        // usn_input.setColumns(12);
        // usn_input.setFont(new Font("Verdana", Font.PLAIN, 18));
        // JButton get_usn_btn = new JButton("Enter");
        // get_usn_btn.addActionListener(new ActionListener() {
        //     public void actionPerformed(java.awt.event.ActionEvent ae) {
        //         usn = usn_input.getText();
        //         if (usn != null) {
        //             usn_jfrm.setVisible(false);
        //             runChat();
        //         }
        //     }
        // });

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

    JButton[] generateUserPanelFromChatHis() {
        // if(chat_avt.size() <= 0) {
        //     cp.getPrintWriter().println("get_chat_avt@" + id);
        //     // GET IMAGE FROM SERVER
        //     while(chat_avt.size() <= 0) {Thread.sleep(100);}
        // }
        // Get the valid size
        JButton[] user_panel_list = new JPanel[chat_history_list.size()];

        // // Generate panel
        // for (int i = 0; i < chat_history_list.size(); i++) {



        //     JButton btn_user = new JButton();
        //     btn_user.setText("Bạn bè 1");
        //     btn_user.setVerticalTextPosition(AbstractButton.CENTER);
        //     btn_user.setHorizontalTextPosition(AbstractButton.EAST);
        //     btn_user.setPreferredSize(new Dimension(270, 50));
        //     btn_user.setMinimumSize(new Dimension(20, 21));
        //     btn_user.setMaximumSize(new Dimension(20, 21));
        //     btn_user.setHorizontalTextPosition(SwingConstants.RIGHT);
        //     btn_user.setHorizontalAlignment(SwingConstants.LEFT);
        //     btn_user.setFont(new Font("Tahoma", Font.PLAIN, 16));
        //     btn_user.setBackground(Color.CYAN);

        //     JPanel btnPanel = new JPanel();
        //     btnPanel.setLayout(new BorderLayout());
        //     btnPanel.setMaximumSize(new Dimension(250, 60));

        //     // Get avt image
        //     Image originalIcon = new ImageIcon(chatrooms.get(i).get(2)).getImage();

        //     // Scale the original image
        //     ImageIcon scaledIcon = new ImageIcon(originalIcon.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        //     JButton userBtn = new JButton(chatrooms.get(i).get(1), scaledIcon);
        //     userBtn.setHorizontalAlignment(SwingConstants.LEFT);
        //     userBtn.setMargin(new Insets(0, 0, 0, 0));
        //     userBtn.setContentAreaFilled(false);
        //     userBtn.setActionCommand("@toroomid@" + chatrooms.get(i).get(0));
        //     userBtn.addActionListener(this);

        //     btnPanel.add(userBtn);
        //     user_panel_list[i] = btnPanel;
        // }

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
                            } else if (command.contains("loginsuccess@")) {
                                String m;
                                while(!(m = br.readLine()).equals("end")) {
                                    Map<String, String> ch = new HashMap<String, String>();
                                    ch.put("send", m);

                                    m=br.readLine();
                                    ch.put("receive", m);

                                    m=br.readLine();
                                    ch.put("message", m);

                                    m=br.readLine();
                                    ch.put("send_at", m);
                                    chat_history_list.add(ch);
                                }
                                usn = usernameField.getText();
                                id=command.split("@")[1];
                                System.out.println(chat_history_list.size());
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
                                int nofFriend = Integer. valueOf(br.readLine());
                                System.out.println(nofFriend);
                                friendTextArea.setText("");
                                while (nofFriend > 0){
                                    friendTextArea.setText(friendTextArea.getText() + br.readLine() + '\n');
                                    nofFriend--;
                                }
                            
                            } else if (command.contains("friend_wrongusernamme")) {
                                JOptionPane.showMessageDialog(logIn, "This user does not exist!");
                                
                            } else if (command.contains("addfriendsuccess")) {
                                cp.getPrintWriter().println("friendlist@" + usn);

                            } else if (command.contains("addfriend_isfriend")) {
                                JOptionPane.showMessageDialog(logIn, "Friends already exist in the list!");
                            
                            } else if (command.contains("addfriendfail")) {
                                JOptionPane.showMessageDialog(logIn, "Add friend fail!");
                            
                            } else if (command.contains("unfriendsuccess")) {
                                cp.getPrintWriter().println("friendlist@" + usn);

                            } else if (command.contains("unfriend_isnotfriend")) {
                                JOptionPane.showMessageDialog(logIn, "The friend does not exist in the list yet!");
                            
                            } else if (command.contains("unfriendfail")) {
                                JOptionPane.showMessageDialog(logIn, "Un friend fail!");
                            
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
            
            String sex = maleRadioButton.isSelected() ? maleRadioButton.getText() : femaleRadioButton.getText();
            String msg = usernameField.getText() + "?" + fullnameField.getText() + "?" + addressField.getText() + "?" + dobField.getText() + "?" + sex + "?" + emailField.getText() + "?" + passwordField.getText();
            cp.getPrintWriter().println(comStr + msg);
        } else if (comStr.contains("friendlist@")) {
            cp.getPrintWriter().println(comStr + usn);
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