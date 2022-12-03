package finalProject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class grouplist extends JFrame implements ActionListener, ListSelectionListener {

    private JLabel groupLabel;
    private JLabel memberLabel;
    private JLabel adminLabel;

    private JList groupList;
    private JList memberList;
    private JList adminList;

    private JScrollPane groupScrollPanes;
    private JScrollPane memberScrollPanes;
    private JScrollPane adminScrollPanes;

    int leftx = 45;
    int lefty = 20;

    int rightx = 370;
    int righty = 20;

    int step = 50;
    public grouplist() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //init
        groupLabel = new JLabel("Group chat list");
        memberLabel = new JLabel("Members list");
        adminLabel = new JLabel("Admins list");

        String[] demoGroup = {"user1", "user 2", "Tuesday","Wednesday", "Thursday","Friday","Saturday","Sunday"
                , "Thursday","Friday","Saturday","Sunday", "Thursday","Friday","Saturday","Sunday", "Thursday"
                , "Friday","Saturday","Sunday", "Thursday","Friday","Saturday","Sunday", "Thursday","Friday","Saturday","Sunday"};
        
        groupList = new JList(demoGroup);
        memberList = new JList(demoGroup);
        adminList = new JList(demoGroup);

        groupScrollPanes = new JScrollPane();
        memberScrollPanes = new JScrollPane();
        adminScrollPanes = new JScrollPane();
        
        //set font        
        groupLabel.setFont(new Font("Arial", Font.BOLD, 22));
        memberLabel.setFont(new Font("Arial", Font.BOLD, 22));
        adminLabel.setFont(new Font("Arial", Font.BOLD, 22));

        groupList.setFont(new Font("Arial", Font.PLAIN, 18));
        memberList.setFont(new Font("Arial", Font.PLAIN, 18));
        adminList.setFont(new Font("Arial", Font.PLAIN, 18));

        //add to frame
        add(groupLabel);
        add(memberLabel);
        add(adminLabel);
        add(groupList);
        add(memberList);
        add(adminList);
        add(groupScrollPanes);
        add(memberScrollPanes);
        add(adminScrollPanes);
        
        //set color
        groupList.setBackground(new Color(242, 223, 170));
        groupList.setOpaque(true);

        memberList.setBackground(new Color(178, 217, 234));
        memberList.setOpaque(true);

        adminList.setBackground(new Color(252, 192, 201));
        adminList.setOpaque(true);

        //set scrollpane
        groupScrollPanes.setViewportView(groupList);
        memberScrollPanes.setViewportView(memberList);
        adminScrollPanes.setViewportView(adminList);

        //set size, location and space
        groupLabel.setBounds(leftx, lefty,  200, 50);
        memberLabel.setBounds(rightx, righty,  150, 50);
        adminLabel.setBounds(rightx, righty + step*6,  150, 50);

        // groupList.setBounds(leftx, lefty + step,  300, 535);
        // memberList.setBounds(rightx, righty + step,  300, 230);
        // adminList.setBounds(rightx, righty + step * 7,  300, 230);

        groupScrollPanes.setBounds(leftx, lefty +step,  300, 535);
        memberScrollPanes.setBounds(rightx, righty + step,  280, 240);
        adminScrollPanes.setBounds(rightx, righty + step * 7,  280, 240);

        //set main frame
        setTitle("Group chat list");
        setSize(720, 720);
        setLayout(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] argv) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new grouplist();
            }
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
