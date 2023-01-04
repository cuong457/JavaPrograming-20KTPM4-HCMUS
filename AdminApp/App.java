package AdminApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class App implements ActionListener {
    JFrame jfrm;
    // Admin Config
    Socket admin = null;
    int portnumber = 2014;

    App() {
        // Run admin socket thread
        Thread admin_thread = new Thread(new AdminProcess());
        admin_thread.start();

        // Tạo thể hiện của frame
        jfrm = new JFrame("Users_main");

        // Chỉ định một BorderLayout
        jfrm.setLayout(new BorderLayout());
        jfrm.setResizable(false);

        // Tùy chỉnh kích thước     
        jfrm.setSize(412, 600);
        
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Thêm menu bar vào frame
        JMenuBar jmb = (new menubar()).createMenu();
        jfrm.setJMenuBar(jmb);
        jfrm.setVisible(true); // Hiển thị frame

        // ------------ Create main panels ------------
        content ctn = new content();

        JPanel hdrPanel = HeaderPanel();
        JPanel ctnPanel = ctn.ContentPanel();
        JPanel ftrPanel = FooterPanel();

        // ------------ Add main panels to content page ------------ 
        jfrm.getContentPane().add(hdrPanel, BorderLayout.NORTH);
        jfrm.getContentPane().add(ctnPanel, BorderLayout.CENTER);
        jfrm.getContentPane().add(ftrPanel, BorderLayout.SOUTH);
    }

    public class AdminProcess implements Runnable {
        PrintWriter pw = null;
        BufferedReader br = null;
        String msg = "";
        Thread msgReceiver;

        AdminProcess() {
            try {
                // Create a admin socket
                admin = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("admin socket is created " + admin);
                
                // Create an output stream of the admin socket
                OutputStream adminOut = admin.getOutputStream();
                pw = new PrintWriter(adminOut, true);
                
                // Create an input stream of the admin socket
                InputStream adminIn = admin.getInputStream();
                br = new BufferedReader(new InputStreamReader(adminIn));

                
                
            } catch (IOException ioe) {
                System.err.println("Error: " + ioe);
            }
            
            msgReceiver = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String command = br.readLine();
                            if(command.contains("newmsg@")) {
                            }
                            else if(command.contains("sending_chat_history")) {
                            }
                        }
                    } catch(IOException ioe) {
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

    JPanel HeaderPanel() {
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
    JPanel FooterPanel() {
        JPanel FooterPanel = new JPanel();
        FooterPanel.setLayout(new FlowLayout());

        JLabel copyright = new JLabel("<html><p style=\"font-family:Verdana;font-size:8px;\">Copyright © 2022 by Sunrise Company</p></html>");
        FooterPanel.add(copyright);

        return FooterPanel;
    }
    

    public void actionPerformed(ActionEvent ae) {
        // Lấy action command.
        String comStr = ae.getActionCommand();
        // Thiết lập ngắt khi bấm quit
        if (comStr.equals("Exit")) 
            System.exit(0);
        // Nếu không phải quit
    }
    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { new App(); }
        });
    }
}
