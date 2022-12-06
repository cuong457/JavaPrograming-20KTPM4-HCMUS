import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.*;

public class content implements ActionListener {
    // Create main panel
    JPanel content = new JPanel();

    // User page
    personalPage usrPage;

    // Create Img generator 
    img imgConfig = new img();

    // the default number of users per page and initial index
    int default_nof_users = 6; 
    int init_index = 0;

    // empty data generate
    demo_database data_generate;

    //This data just create statically for test GUI.
    //The real database and how to get data will be update soon !
    Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
    Vector<JPanel[]> current_page = new Vector<>();
    content() {
        data_generate = new demo_database();
        users = data_generate.getData();
    }

    public JPanel ContentPanel() {
        // Set the main layout
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

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
        current_page.add(this.generateUserPanel(default_nof_users));
        
        // Render a list of user
        content.add(swap_page_panel);
        this.renderPanel(content, current_page.get(0));

        return content;
    }
    public void renderPanel(JPanel target, JPanel[] element) {
        int size = element.length;
        for(int i = 0; i < size; i++)
            target.add(element[i]);
    }
    public JPanel[] generateUserPanel(int size) {
        // Check if size is valid
        if(size <= 0) return null;

        // Get the valid size
        JPanel[] user_panel_list;
        int realSize;
        if(users.size() - init_index < size) {
            realSize = users.size() - init_index;
            user_panel_list = new JPanel[users.size() - init_index];
        }
        else {
            realSize = size;
            user_panel_list = new JPanel[size];
        }


        // Generate panel
        for(int i = init_index; i < init_index + realSize; i++) {
            String key = "user_" + Integer.toString(i + 1);
            //Create a sub panel
            JPanel user_info_Panel = new JPanel();
            user_info_Panel.setBorder(new LineBorder(Color.GRAY));
            user_info_Panel.setLayout(new BoxLayout(user_info_Panel, BoxLayout.X_AXIS));

                //Get avt image
            Image originalIcon = imgConfig.imgLoading(users.get(key).get("image"));

                //Scale the original image
            ImageIcon scaledIcon = new ImageIcon(imgConfig.ScaleImage(originalIcon, 50, 50));
            JLabel avtLabel = new JLabel(scaledIcon);
            avtLabel.setBorder(new EmptyBorder(0, 0, 0, 10));

                // Text partial
            String userName = users.get(key).get("name");
            String lastLoginInfo = users.get(key).get("online_status");

            JLabel textPartial = new JLabel("<html><p style=\"font-family:Verdana;font-size:10px;\">"  + userName + "</p>"
            + "<p style=\"font-family:Verdana;font-size:9px;font-weight: 400\">" + lastLoginInfo + "</p></html>");

            // Panel hold 2 btn align Y
            JPanel btnHolder = new JPanel();
            btnHolder.setLayout(new BoxLayout(btnHolder, BoxLayout.Y_AXIS));

                // Create ban button
            ImageIcon banicon = new ImageIcon(imgConfig.ScaleImage((imgConfig.imgLoading("./assets/icons/ban.png")), 50 / 3, 50 / 3));
            JButton banBtn = new JButton(banicon);
            // banBtn_2.setBorderPainted(false);
            banBtn.setFocusPainted(false);
            banBtn.setContentAreaFilled(false);
            banBtn.setHorizontalTextPosition(SwingConstants.RIGHT);

                //Create view button
            ImageIcon viewicon = new ImageIcon(imgConfig.ScaleImage((imgConfig.imgLoading("./assets/icons/view.png")), 50 / 3, 50 / 3));
            JButton viewBtn = new JButton(viewicon);
            // viewicon_2.setBorderPainted(false);
            viewBtn.setFocusPainted(false);
            viewBtn.setContentAreaFilled(false);
            viewBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
            viewBtn.setActionCommand("about_" + key);

            viewBtn.addActionListener(this);
            banBtn.addActionListener(this);
            btnHolder.add(viewBtn);
            btnHolder.add(banBtn);

            user_info_Panel.add(avtLabel);
            user_info_Panel.add(textPartial);
            user_info_Panel.add(btnHolder);

            user_panel_list[i - init_index] = user_info_Panel;
        }

        init_index += realSize;

        return user_panel_list;
    }
    
    public void actionPerformed(ActionEvent ae) {
        // Lấy action command.
        String comStr = ae.getActionCommand();
        
        if(comStr.indexOf("about_user") != -1) {
            String thisId = comStr.split("_", 2)[1];
            usrPage = new personalPage(users.get(thisId), thisId, data_generate);
            usrPage.popupView();
        }
        else if(comStr.equals("next_page")) {
            if(init_index < users.size()) {
                int cur_page_index = init_index / default_nof_users;
                if(init_index % default_nof_users == 0)
                    --cur_page_index;

                for(JPanel pnl : current_page.get(cur_page_index)) {
                    content.remove(pnl);
                    content.revalidate();
                    content.repaint();
                }

                // Check if next page is not generate in the past
                if(cur_page_index == current_page.size() - 1) {
                    current_page.add(this.generateUserPanel(default_nof_users));
                }
                else {
                    init_index += default_nof_users;
                }
                this.renderPanel(content, current_page.get(cur_page_index + 1));   
            }
        }
        else if(comStr.equals("prev_page")) {
            int cur_page_index = init_index / default_nof_users;
            if(init_index % default_nof_users == 0)
                --cur_page_index;

            if(cur_page_index > 0) {
                for(JPanel pnl : current_page.get(cur_page_index)) {
                    content.remove(pnl);
                    content.revalidate();
                    content.repaint();
                }
                this.renderPanel(content, current_page.get(cur_page_index - 1));
                init_index -= default_nof_users;
            }
        }
    }
}
