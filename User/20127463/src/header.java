
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class header {
    protected JPanel HeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());

        JLabel title_lbl = new JLabel("USER LIST", SwingConstants.CENTER);
        title_lbl.setFont(new Font("Verdana", Font.BOLD, 20));
        title_lbl.setBorder(new EmptyBorder(10, 0, 15, 0));

        //Creating the panel at top and adding components
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        JLabel srchlabel = new JLabel("Find user");
        JTextField textField = new JTextField(10); // accepts upto 10 characters
        JButton sendBtn = new JButton("Search");
        sendBtn.setPreferredSize(new Dimension(100, 19));

        searchPanel.add(srchlabel);
        searchPanel.add(textField);
        searchPanel.add(sendBtn);
        searchPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        header.add(title_lbl, BorderLayout.NORTH);
        header.add(searchPanel, BorderLayout.SOUTH);

        return header;
    }
}
