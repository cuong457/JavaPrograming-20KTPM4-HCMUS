package finalProject;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class friendlist extends JFrame implements ActionListener {

    private JLabel headerLabel;
    private JLabel usernameLabel;
    private JLabel addfriendLabel;
    private JLabel unfriendLabel;

    private JTextField usernameField;
    private JTextField addfriendField;
    private JTextField unfriendField;

    private JButton addfriendBtn;
    private JButton unfriendBtn;

    private JList firendList;   
    private JScrollPane friendScrollPane;


    // private JButton loginBtn;
    // private JButton signupBtn;

    int leftx = 30;
    int lefty = 20;

    int rightx = 360;
    int righty = 30;

    int step = 50;

    public friendlist() {
        initComponents();
    }

    public void initComponents(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //init
        headerLabel = new JLabel("Friend");
        usernameLabel = new JLabel("Your username");
        addfriendLabel = new JLabel("Add friend by username");
        unfriendLabel = new JLabel("Unfriend by username");

        usernameField = new JTextField(15);
        addfriendField = new JTextField(15);
        unfriendField = new JTextField(15);

        addfriendBtn = new JButton("Add friend");
        unfriendBtn = new JButton("Unfriend");

        String[] demoFriend = {"user1", "user 2", "Tuesday","Wednesday", "Thursday","Friday","Saturday","Sunday"
                , "Thursday","Friday","Saturday","Sunday", "Thursday","Friday","Saturday","Sunday", "Thursday"
                , "Friday","Saturday","Sunday", "Thursday","Friday","Saturday","Sunday", "Thursday","Friday","Saturday","Sunday"};
        firendList = new JList<>(demoFriend);
        friendScrollPane = new JScrollPane(firendList);

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
        firendList.setFont(new Font("Arial", Font.PLAIN, 18));

        //add to frame
        add(headerLabel);
        add(usernameLabel);
        add(addfriendLabel);
        add(unfriendLabel);
        add(usernameField);
        add(addfriendField);
        add(unfriendField);
        add(addfriendBtn);
        add(unfriendBtn);
        add(firendList);
        add(friendScrollPane);
        
        //set color
        addfriendBtn.setBackground(new Color(103,201,250));
        addfriendBtn.setOpaque(true);

        unfriendBtn.setBackground(new Color(255,84,84));
        unfriendBtn.setOpaque(true);

        firendList.setBackground(new Color(246, 250, 142));
        firendList.setOpaque(true);

        //set scrollpane
        friendScrollPane.setViewportView(firendList);

        //set size and location
        headerLabel.setBounds(leftx, lefty, 300, 60);
        usernameLabel.setBounds(rightx, righty, 300, 40);
        addfriendLabel.setBounds(rightx, righty + step*3, 300, 40);
        unfriendLabel.setBounds(rightx, righty + step*7, 300, 40);

        usernameField.setBounds(rightx, righty + step, 300, 40);
        addfriendField.setBounds(rightx, righty + step*4, 300, 40);
        unfriendField.setBounds(rightx, righty + step*8, 300, 40);

        addfriendBtn.setBounds(rightx, righty + step*5, 300, 40);
        unfriendBtn.setBounds(rightx, righty + step*9, 300, 40);
        
        friendScrollPane.setBounds(leftx , lefty + 60, 300, 560);

        firendList.setBounds(leftx, lefty + 60, 300, 560);

        //set text field enabled
        usernameField.setEnabled(false);

        //just for demo
        usernameField.setText("demo");

        //set main frame
        setTitle("Friend list");
        setSize(720, 720);
        setLayout(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e ){

    }

    public static void main(String[] args) {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new friendlist();
            }
        });
    }
}