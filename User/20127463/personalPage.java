import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Map;
import java.awt.event.*;


public class personalPage implements ActionListener {
    private Map<String, String> user;
    String ID;
    int default_num = 6;

    // Create Img generator 
    img imgConfig = new img();

    // data generator
    demo_database dtGenerator;

    personalPage(Map<String, String> data, String userId, demo_database dtg) {
        user = data;
        ID = userId;
        dtGenerator = dtg;
    }
    public void popupView() {
        JFrame parent = new JFrame("Personal Information"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(824, 735);
            };
        };
        parent.setLayout(null);
        parent.setResizable(false);

        // Thêm menu bar vào frame
        JMenuBar jmb = (new menubar()).createMenu();
        parent.setJMenuBar(jmb);

        ImageIcon avt = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading(
            (user).get("image")), 120, 120));
        ImageIcon bg = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading(
            (user).get("bg")), 824, 200));

        // Create panel of avt and bg
        JPanel imgPanel = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(824, 350);
            };
        };
        imgPanel.setLayout(null);

        JLabel bgLbl = new JLabel(bg);
        JLabel avtLbl = new JLabel(avt);
        avtLbl.setBorder(new LineBorder(Color.WHITE, 5));

        avtLbl.setBounds(20, 140, 120, 120);
        bgLbl.setBounds(0, 0, 824, 200);

        JLabel name = new JLabel("<html>" + "<p style=\"font-family:Verdana;font-size:11px;\">"
        + 
        (user).get("name") + "</p></html>");

        JLabel usn = new JLabel("<html>" + "<p style=\"font-family:Verdana;font-size:8px;font-weight:400;\">@"
        + (user).get("usn") + "</p></html>");
        // Apply padding by using empty border
        name.setBounds(new Rectangle(new Point(20, 270), name.getPreferredSize()));
        usn.setBounds(new Rectangle(new Point(20, 290), usn.getPreferredSize()));

        // Create some function button
        ImageIcon historyIcon = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading("./assets/icons/history.png"), 50/3, 50/3));
        ImageIcon banIcon = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading("./assets/icons/ban.png"), 50/3, 50/3));
        ImageIcon penIcon = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading("./assets/icons/pen.png"), 50/3, 50/3));

        JButton seeLoginHistoryBtn = new JButton(historyIcon);
        seeLoginHistoryBtn.setActionCommand("see_login_history");
        JButton banBtn = new JButton(banIcon);
        banBtn.setActionCommand("ban");
        JButton changePasswordBtn = new JButton(penIcon);
        changePasswordBtn.setActionCommand("change_password");

        seeLoginHistoryBtn.addActionListener(this);
        banBtn.addActionListener(this);
        changePasswordBtn.addActionListener(this);

        seeLoginHistoryBtn.setBounds(680, 220, 30, 30);
        banBtn.setBounds(715, 220, 30, 30);
        changePasswordBtn.setBounds(750, 220, 30, 30);

        // Add 2 image, button and label to panel
        imgPanel.add(avtLbl);
        imgPanel.add(bgLbl);
        imgPanel.add(name);
        imgPanel.add(usn);
        imgPanel.add(seeLoginHistoryBtn);
        imgPanel.add(banBtn);
        imgPanel.add(changePasswordBtn);

        // imgContainer.add(imgPanel);

        // Create panel include information
        JPanel inforPanel = new JPanel();
        inforPanel.setLayout(new BoxLayout(inforPanel, BoxLayout.Y_AXIS));

            // Infor label
        //Title
        JLabel aboutTitle = new JLabel("<html>__________<br>"
        + "<i style=\"font-size:13px;font-weight:800;\">ABOUT</i></html>");
        
        JLabel address = new JLabel("<html>" + 
        "<br>Address: " + "<span style=\"font-weight:400;white-space:normal\">"
        + (user).get("address") + "</span></html>");
        address.setPreferredSize(new Dimension(200, 50));

        JLabel dob = new JLabel("<html>" + 
        "Date of birth: " + "<span style=\"font-weight:400;\">"
        + (user).get("dob") + "</span></html>");

        JLabel sex = new JLabel("<html>" + 
        "Sex: " + "<span style=\"font-weight:400;\">"
        + (user).get("sex") + "</span></html>");

        JLabel email = new JLabel("<html>" + 
        "Email: " + "<span style=\"font-weight:400;\">"
        + (user).get("email") + "</span></html>");
        email.setPreferredSize(new Dimension(200, 20));

        inforPanel.add(aboutTitle);
        inforPanel.add(address);
        inforPanel.add(email);
        inforPanel.add(dob);
        inforPanel.add(sex);
        inforPanel.setBorder(new EmptyBorder(0, 20, 0, 20));

        // Friend Title
        JLabel friendTitle = new JLabel("<html><br>__________<br>" + 
        "<i style=\"font-size:13px;font-weight:800;\">" + "FRIEND" + "</i></html>");
            // Subtitle the number of friends
        JLabel nofFriends = new JLabel("<html><span style=\"font-size:8px;font-weight:400;\">"
        + (user).get("nofFriends") + " friends" + "</span></html>");

        friendTitle.setBorder(new EmptyBorder(0, 20, 0, 20));
        nofFriends.setBorder(new EmptyBorder(0, 20, 30, 20));

        // Create panel include friend image
        JPanel friendImagePanel = new JPanel();
        friendImagePanel.setLayout(new GridLayout(0, 3));
        friendImagePanel.setMaximumSize(new Dimension(240, 300));
        friendImagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] friendIdList = user.get("friend").split("/");
        int nofFriendShowed = 0;
        for(String frId : friendIdList) {
            // Get friend data
            Map<String, String> friend = dtGenerator.getSpecificData(frId);
            // Create friend thumnail
            ImageIcon imgicon = new ImageIcon(imgConfig.ScaleImage(imgConfig.imgLoading(friend.get("image")), 131, 131));
            JButton friend_imgbtn = new JButton(friend.get("name"), imgicon);
            // Text below image
            friend_imgbtn.setVerticalTextPosition(SwingConstants.BOTTOM);
            friend_imgbtn.setHorizontalTextPosition(SwingConstants.CENTER);
            // Erase border and fill color
            friend_imgbtn.setContentAreaFilled(false);
            friend_imgbtn.setPreferredSize(new Dimension(131, 131));
            friend_imgbtn.setMargin(new Insets(0,0,10,0));
            friend_imgbtn.setActionCommand("about_" + frId);
            friend_imgbtn.addActionListener(this);

            friendImagePanel.add(friend_imgbtn);

            if(++nofFriendShowed == default_num)
                break;
        }

        // Add panel to contentpane and display
        imgPanel.setBounds(0, 0, 824, 320);

        friendTitle.setBounds(0, 300, 412, 50);
        nofFriends.setBounds(0, 355, 412, 40);
        friendImagePanel.setBounds(0, 375, 395, 300);

        inforPanel.setBounds(412, 315, 412, 150);

        parent.getContentPane().add(imgPanel);
        parent.getContentPane().add(inforPanel);
        parent.getContentPane().add(friendTitle);
        parent.getContentPane().add(nofFriends);
        parent.getContentPane().add(friendImagePanel);
        parent.pack();
        parent.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        // Lấy action command.
        String comStr = ae.getActionCommand();

        if(comStr.indexOf("about_user") != -1) {
            String thisId = comStr.split("_", 2)[1];
            personalPage usrPage = new personalPage(dtGenerator.getSpecificData(thisId), thisId, dtGenerator);
            usrPage.popupView();
        }
        else if(comStr.indexOf("see_login_history") != -1) {
            historyPage hisPage = new historyPage((ID + "_log_his"), dtGenerator);
            hisPage.popupLoginHistory();
        }
        else if(comStr.indexOf("ban") != -1) {
            //todo
        }
        else if(comStr.indexOf("change_password") != -1) {
            //todo
        }
    }
}
