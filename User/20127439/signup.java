
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class signup extends JFrame implements ActionListener {

    private JLabel headerLabel;
    private JLabel usernameLabel;
    private JLabel fullnameLabel;
    private JLabel addressLabel;
    private JLabel dobLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel sexLabel;

    private JTextField usernameField;
    private JTextField fullnameField;
    private JTextField addressField;
    private JTextField dobField;
    private JTextField emailField;
    private JTextField passwordField;

    private ButtonGroup sexBtnGroup;
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

    public void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        signupBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupAction();
            }
        });

        add(headerLabel);
        add(usernameLabel);
        add(fullnameLabel);
        add(addressLabel);
        add(dobLabel);
        add(emailLabel);
        add(passwordLabel);
        add(sexLabel);
        add(usernameField);
        add(fullnameField);
        add(addressField);
        add(dobField);
        add(emailField);
        add(passwordField);
        add(maleRadioButton);
        add(femaleRadioButton);
        add(signupBtn);

        sexBtnGroup.add(maleRadioButton);
        sexBtnGroup.add(femaleRadioButton);

        headerLabel.setBounds(220, 50, 600, 60);
        usernameLabel.setBounds(leftx, lefty, 180, 40);
        fullnameLabel.setBounds(leftx, lefty += step, 180, 40);
        addressLabel.setBounds(leftx, lefty += step, 180, 40);
        dobLabel.setBounds(leftx, lefty += step, 180, 40);
        emailLabel.setBounds(leftx, lefty += step, 180, 40);
        passwordLabel.setBounds(leftx, lefty += step, 180, 40);
        sexLabel.setBounds(leftx, lefty += step, 180, 40);

        usernameField.setBounds(rightx, righty, 310, 40);
        fullnameField.setBounds(rightx, righty += step, 310, 40);
        addressField.setBounds(rightx, righty += step, 310, 40);
        dobField.setBounds(rightx, righty += step, 310, 40);
        emailField.setBounds(rightx, righty += step, 310, 40);
        passwordField.setBounds(rightx, righty += step, 310, 40);

        maleRadioButton.setBounds(rightx, righty += step, 140, 40);
        femaleRadioButton.setBounds(rightx + 140, righty, 140, 40);

        signupBtn.setBackground(Color.ORANGE);
        signupBtn.setOpaque(true);
        signupBtn.setBounds(260, 560, 180, 40);

        setTitle("Sign Up");
        setSize(720, 720);
        setLayout(null);
        setLocationRelativeTo(null); 
        setVisible(true);
        setResizable(false);
    }

    public void signupAction() {
        if (usernameField.getText().equals("") || fullnameField.getText().equals("")
                || addressField.getText().equals("") || dobField.getText().equals("") || emailField.getText().equals("")
                || passwordField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Lack of information");
            return; 
        }
        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(dobField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Wrong date format");
            return;
        }
        List<user> userList = new ArrayList<user>();
        (new connectDB()).getAllUser(userList);
        for (user eachUser : userList) {
            if (eachUser.getUsername().equals(usernameField.getText())) {
                JOptionPane.showMessageDialog(this, "Username has been exsits");
                return;
            }
        }
        String sex = maleRadioButton.isSelected() ? maleRadioButton.getText() : femaleRadioButton.getText();

        user newUser = new user(usernameField.getText(), fullnameField.getText(), addressField.getText(),
                dobField.getText(), emailField.getText(), passwordField.getText(), sex);
        (new connectDB()).addUser(newUser);
    }

    public void actionPerformed(ActionEvent e) {

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