import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class users implements ActionListener {
    JFrame jfrm = new JFrame("Users_main");

    users() {
        // Tạo thể hiện của frame
        // JFrame jfrm = new JFrame("Users_main");

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
        header hdr = new header();
        content ctn = new content();
        footer ftr = new footer();

        JPanel hdrPanel = hdr.HeaderPanel();
        JPanel ctnPanel = ctn.ContentPanel();
        JPanel ftrPanel = ftr.FooterPanel();

        // ------------ Add main panels to content page ------------ 
        jfrm.getContentPane().add(hdrPanel, BorderLayout.NORTH);
        jfrm.getContentPane().add(ctnPanel, BorderLayout.CENTER);
        jfrm.getContentPane().add(ftrPanel, BorderLayout.SOUTH);
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
            public void run() { new users(); }
        });
    }
}
