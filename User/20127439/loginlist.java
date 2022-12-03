package finalProject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class loginlist extends JFrame implements ActionListener, ListSelectionListener {

    private JLabel headerLabel;
    private JTable loginTable;
    private JScrollPane loginScrollPanes;

    private String[] columnNames = new String[] { "Time", "Username", "Name" };
    private Object loginObj = new Object[][] {};

    int leftx = 80;
    int lefty = 80;

    int step = 50;

    public loginlist() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        headerLabel = new JLabel("Log in list");
        loginTable = new JTable();
        loginScrollPanes = new JScrollPane();
        
        loginTable.setModel(new DefaultTableModel((Object[][]) loginObj, columnNames));
        
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        loginTable.setFont(new Font("Arial", Font.PLAIN, 18));
        loginTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        loginTable.setRowHeight(25);
        

        add(headerLabel);
        add(loginTable);
        add(loginScrollPanes);

        headerLabel.setBounds(80, 20, 300, 60);
        loginTable.setBounds(leftx, lefty,  580, 530);
        loginScrollPanes.setViewportView(loginTable);
        loginScrollPanes.setBounds(leftx, lefty ,  580, 535);

        showList();

        setTitle("Log in list");
        setSize(720, 720);
        setLayout(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] argv) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new loginlist();
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

    public void showList() {
        Object[][] user = new Object[100][5];
        for (int i = 0; i < 100; i++) {
            user[i][0] = String.format("time demo "+ i);
            user[i][1] = String.format("udername "+ i);
            user[i][2] = String.format("name "+ i);

        }
        loginTable.setModel(new DefaultTableModel(user, columnNames));
    }

}
