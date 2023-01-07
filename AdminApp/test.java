import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class test {
    public static void popupChangePassword() {
        JFrame changePswFrame = new JFrame();
        changePswFrame.setLayout(new BorderLayout());
        changePswFrame.setSize(300, 200);
        changePswFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TITLE
        JLabel changePswTitle = new JLabel("<html><p style=\"font-family:Verdana;font-size:13px;\">CHANGE PASSWORD</p></html>", SwingConstants.CENTER);

        // INPUT FIELD
        JPanel inputPswField = new JPanel();
        inputPswField.setLayout(new BoxLayout(inputPswField, BoxLayout.X_AXIS));
        inputPswField.setPreferredSize(new Dimension(300, 150));
        inputPswField.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel inputPswField1 = new JPanel();
        inputPswField1.setLayout(new BorderLayout());
        inputPswField1.setMaximumSize(new Dimension(300, 60));

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
        oldPswInput.setText("123456");
        oldPswInput.setEditable(false);
        JTextField newPswInput = new JTextField();

        oldPswPanel.add(oldPswLbl, BorderLayout.WEST);
        oldPswPanel.add(oldPswInput, BorderLayout.CENTER);
        newPswPanel.add(newPswLbl, BorderLayout.WEST);
        newPswPanel.add(newPswInput, BorderLayout.CENTER);
        inputPswField1.add(oldPswPanel, BorderLayout.NORTH);
        inputPswField1.add(newPswPanel, BorderLayout.SOUTH);
        inputPswField.add(inputPswField1);

        // SUMIT BUTTON
        JButton commitChangeBtn = new JButton("COMMIT");

        changePswFrame.getContentPane().add(changePswTitle, BorderLayout.NORTH);
        changePswFrame.getContentPane().add(inputPswField, BorderLayout.CENTER);
        changePswFrame.getContentPane().add(commitChangeBtn, BorderLayout.SOUTH);
        changePswFrame.pack();
        changePswFrame.setVisible(true);
    }
    public static void main(String[] args) {
        popupChangePassword();
    }
}
