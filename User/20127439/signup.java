package finalProject;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class signup extends JFrame implements ActionListener {

    private JLabel headerLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel cfmPasswordLabel;
    private JLabel sexLabel;

    private JTextField usernameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField cfmPasswordField;

    private JRadioButton maleRadioButton, femaleRadioButton;

    private JButton signupBtn;

    int leftx = 120;
    int lefty = 150;

    int rightx = 300;
    int righty = 150;

    int step = 60;

    public signup() {
        initComponents();
    }

    public void initComponents(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel = new JLabel("Create your account");
        usernameLabel = new JLabel("Usernname:");
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        cfmPasswordLabel = new JLabel("Confirm password:");
        sexLabel = new JLabel("Sex:");

        usernameField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JTextField(15);
        cfmPasswordField = new JTextField(15);

        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");

        signupBtn = new JButton("Sign up");

        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cfmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sexLabel.setFont(new Font("Arial", Font.BOLD, 16));

        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        cfmPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        maleRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        femaleRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        signupBtn.setFont(new Font("Arial", Font.BOLD, 24));

        add(headerLabel);
        add(usernameLabel);
        add(emailLabel);
        add(passwordLabel);
        add(cfmPasswordLabel);
        add(sexLabel);
        add(usernameField);
        add(emailField);
        add(passwordField);
        add(cfmPasswordField);
        add(maleRadioButton);
        add(femaleRadioButton);
        add(signupBtn);

        headerLabel.setBounds(220, 50, 600, 60);
        usernameLabel.setBounds(leftx, lefty, 180, 40);
        emailLabel.setBounds(leftx, lefty += step,  180, 40);
        passwordLabel.setBounds(leftx, lefty += step,  180, 40);
        cfmPasswordLabel.setBounds(leftx, lefty += step,  180, 40);
        sexLabel.setBounds(leftx, lefty += step,  180, 40);

        usernameField.setBounds(rightx, righty , 310, 40);
        emailField.setBounds(rightx, righty +=step, 310, 40);
        passwordField.setBounds(rightx, righty +=step, 310, 40);
        cfmPasswordField.setBounds(rightx, righty +=step, 310, 40);

        maleRadioButton.setBounds(rightx, righty += step, 140, 40);
        femaleRadioButton.setBounds(rightx + 140, righty, 140, 40);

        signupBtn.setBackground(Color.ORANGE);
        signupBtn.setOpaque(true);
        signupBtn.setBounds(260, 500, 180, 40);

        setTitle("Sign Up");
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
                new signup();
            }
        });
    }
}