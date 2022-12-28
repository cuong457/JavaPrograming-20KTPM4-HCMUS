
import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class login extends JFrame implements ActionListener {

    private JLabel headerLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField usernameField;
    private JTextField passwordField;

    private JButton loginBtn;
    private JButton signupBtn;
    private JButton resetPasswordBtn;

    int leftx = 150;
    int lefty = 250;

    int rightx = 250;
    int righty = 250;

    int step = 60;

    public login() {
        initComponents();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });

        signupBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupAction();
            }
        });

        resetPasswordBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    resetPasswordAction();
                } catch (UnsupportedEncodingException | MessagingException e1) {
                    // TODO Auto-generated catch block
                    // e1.printStackTrace();
                }
            }
        });

        add(headerLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(usernameField);
        add(passwordField);
        add(loginBtn);
        add(signupBtn);
        add(resetPasswordBtn);

        headerLabel.setBounds(300, 150, 600, 60);
        usernameLabel.setBounds(leftx, lefty, 180, 40);
        passwordLabel.setBounds(leftx, lefty += step, 180, 40);

        usernameField.setBounds(rightx, righty, 310, 40);
        passwordField.setBounds(rightx, righty += step, 310, 40);

        loginBtn.setBackground(Color.ORANGE);
        loginBtn.setOpaque(true);
        loginBtn.setBounds(280, 380, 180, 40);

        signupBtn.setBackground(Color.PINK);
        signupBtn.setOpaque(true);
        signupBtn.setBounds(280, 440, 180, 40);

        resetPasswordBtn.setBackground(Color.green);
        resetPasswordBtn.setOpaque(true);
        resetPasswordBtn.setBounds(280, 500, 180, 40);

        setTitle("Log in");
        setSize(720, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public boolean checkUsername() {
        if (usernameField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Username is empty");
            return false;
        }
        List<user> userList = new ArrayList<user>();
        (new connectDB()).getAllUser(userList);
        for (user eachUser : userList) {
            if (eachUser.getUsername().equals(usernameField.getText())) {
                return true;
            }
        }
        JOptionPane.showMessageDialog(this, "Username does not exsits");
        return false;
    }

    public void loginAction() {
        if (checkUsername() && passwordField.getText()
                .equals((new connectDB()).getUserByUsername(usernameField.getText()).getPassword())) {
            dispose();
            new friendlist(usernameField.getText());
        } else
            JOptionPane.showMessageDialog(this, "Password is wrong");
    }

    public void signupAction() {
        dispose();
        new signup();
    }

    public void resetPasswordAction() throws UnsupportedEncodingException, MessagingException{
        if (!checkUsername())
            return;
        (new resetpassword()).send(usernameField.getText());
    }

    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new login();
            }
        });
    }
}