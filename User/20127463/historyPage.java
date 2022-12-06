import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Vector;

public class historyPage implements ActionListener {
    Map<String, Map<String, String>> loginHistory;
    JFrame parent;
    JPanel contentPg; // To handle event
    int init_index = 0;
    int default_nof_panel = 6;
    // data generator
    demo_database dtGenerator;
    // Create Img generator 
    img imgConfig = new img();
    // Page manegement
    Vector<JPanel[]> current_page = new Vector<>();


    historyPage(String id, demo_database dtg) {
        dtGenerator = dtg;
        loginHistory = dtGenerator.getSpecificHistory(id);
    }
    public void popupLoginHistory() {
        parent = new JFrame("History");
        parent.setLayout(new BorderLayout());
        parent.setResizable(false);

        // Thêm menu bar vào frame
        JMenuBar jmb = (new menubar()).createMenu();
        parent.setJMenuBar(jmb);

        // Header
        JLabel title = new JLabel("<html>" + 
        "<p style=\"font-family:Verdana;font-size:13px;font-weight:600\">" 
        + "LOGIN HISTORY" + "</p></html>", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(0, 0, 30, 0));

        // Content
        contentPg = new JPanel();
        contentPg.setLayout(new BoxLayout(contentPg, BoxLayout.Y_AXIS));

        // Panel hold swap page button
        JPanel swap_page_panel = new JPanel();
        swap_page_panel.setLayout(new BoxLayout(swap_page_panel, BoxLayout.X_AXIS));

        // Create next page and prev page button
        Image right_arrow_img = imgConfig.imgLoading("./assets/icons/right-arrow.png");
        ImageIcon right_arrow_icon = new ImageIcon(imgConfig.ScaleImage(right_arrow_img, 50 / 3, 50 / 3));
        JButton right_arrow_btn = new JButton(right_arrow_icon);
        right_arrow_btn.setActionCommand("next_page");

        Image left_arrow_img = imgConfig.imgLoading("./assets/icons/left-arrow.png");
        ImageIcon left_arrow_icon = new ImageIcon(imgConfig.ScaleImage(left_arrow_img, 50 / 3, 50 / 3));
        JButton left_arrow_btn = new JButton(left_arrow_icon);
        left_arrow_btn.setActionCommand("prev_page");

        right_arrow_btn.addActionListener(this);
        left_arrow_btn.addActionListener(this);

        swap_page_panel.add(left_arrow_btn);
        swap_page_panel.add(right_arrow_btn);

        // Generate default 6 users
        current_page.add(this.generateHistoryPanel(default_nof_panel));

        // Render a list of user
        contentPg.add(swap_page_panel);
        this.renderPanel(contentPg, current_page.get(0));

        // Footer
        JLabel copyright = new JLabel("<html><p style=\"font-family:Verdana;" + 
        "font-size:8px;\">Copyright © 2022 by Sunrise Company</p></html>", SwingConstants.CENTER);
        copyright.setBorder(new EmptyBorder(30, 0, 0, 0));

        parent.add(title, BorderLayout.NORTH);
        parent.add(contentPg, BorderLayout.CENTER);
        parent.add(copyright, BorderLayout.SOUTH);
        parent.pack();
        parent.setLocationRelativeTo(null);
        parent.setVisible(true);
    }
    public JPanel[] generateHistoryPanel(int size) {
        // Check if size is valid
        if(size <= 0) return null;

        // Get the valid size
        JPanel[] his_panel_list;
        int realSize;
        if(loginHistory.size() - init_index < size) {
            realSize = loginHistory.size() - init_index;
            his_panel_list = new JPanel[loginHistory.size() - init_index];
        }
        else {
            realSize = size;
            his_panel_list = new JPanel[size];
        }


        // Generate panel
        for(int i = init_index; i < init_index + realSize; i++) {
            String key = "his_" + Integer.toString(i + 1);
            //Create a sub panel
            JPanel user_info_Panel = new JPanel();
            user_info_Panel.setBorder(new LineBorder(Color.GRAY));
            user_info_Panel.setLayout(new BoxLayout(user_info_Panel, BoxLayout.X_AXIS));

                //Get avt image
            Image originalIcon = imgConfig.imgLoading(loginHistory.get(key).get("device_icon"));

                //Scale the original image
            ImageIcon scaledIcon = new ImageIcon(imgConfig.ScaleImage(originalIcon, 50, 50));
            JLabel avtLabel = new JLabel(scaledIcon);
            avtLabel.setBorder(new EmptyBorder(0, 0, 0, 10));

                // Text partial
            String userName = loginHistory.get(key).get("device_name") + " - " + loginHistory.get(key).get("location");
            String lastLoginInfo = loginHistory.get(key).get("online_status");

            JLabel textPartial = new JLabel("<html><p style=\"font-family:Verdana;font-size:10px;\">"  + userName + "</p>"
            + "<p style=\"font-family:Verdana;font-size:9px;font-weight: 400\">" + lastLoginInfo + "</p></html>");

            user_info_Panel.add(avtLabel);
            user_info_Panel.add(textPartial);

            his_panel_list[i - init_index] = user_info_Panel;
        }

        init_index += realSize;

        return his_panel_list;
    }
    public void renderPanel(JPanel target, JPanel[] element) {
        int size = element.length;
        for(int i = 0; i < size; i++)
            target.add(element[i]);
    }
    public void actionPerformed(ActionEvent ae) {
        // Lấy action command.
        String comStr = ae.getActionCommand();
        
        if(comStr.equals("next_page")) {
            if(init_index < loginHistory.size()) {
                int cur_page_index = init_index / default_nof_panel;
                if(init_index % default_nof_panel == 0)
                    --cur_page_index;

                for(JPanel pnl : current_page.get(cur_page_index)) {
                    contentPg.remove(pnl);
                    contentPg.revalidate();
                    contentPg.repaint();
                }

                // Check if next page is not generate in the past
                if(cur_page_index == current_page.size() - 1) {
                    current_page.add(this.generateHistoryPanel(default_nof_panel));
                }
                else {
                    init_index += default_nof_panel;
                }
                this.renderPanel(contentPg, current_page.get(cur_page_index + 1));   
            }
        }
        else if(comStr.equals("prev_page")) {
            int cur_page_index = init_index / default_nof_panel;
            if(init_index % default_nof_panel == 0)
                --cur_page_index;

            if(cur_page_index > 0) {
                for(JPanel pnl : current_page.get(cur_page_index)) {
                    contentPg.remove(pnl);
                    contentPg.revalidate();
                    contentPg.repaint();
                }
                this.renderPanel(contentPg, current_page.get(cur_page_index - 1));
                init_index -= default_nof_panel;
            }
        }
    }
}
