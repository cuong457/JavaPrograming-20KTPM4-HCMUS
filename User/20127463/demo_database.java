

import java.util.HashMap;
import java.util.Map;

//This class and data just create statically for test GUI.
//The real database and how to get it will be update soon !

public class demo_database {
    static Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
    static Map<String, Map<String, Map<String, String>>> loginHistories = new HashMap<String, Map<String, Map<String, String>>>();


    public Map<String, String> getSpecificData(String id) {
        return users.get(id);
    }
    public Map<String, Map<String, String>> getSpecificHistory(String id) {
        return loginHistories.get(id);
    }
    public Map<String, Map<String, Map<String, String>>> getHistory() {
        return loginHistories;
    }
    public Map<String, Map<String, String>> getData() {
        return users;
    }

    demo_database() {
        // USER DATA
        Map<String, String> user_1 = new HashMap<String, String>();
        user_1.put("name", "Phan Duong Minh");
        user_1.put("image", "./assets/imgs/user_1/avt.png");
        user_1.put("bg", "./assets/imgs/user_1/bg.png");
        user_1.put("online_status", "Last online about an hour ago");
        user_1.put("usn", "minhminh0410");
        user_1.put("address", "27/476 Cho Hang Moi Street, Du Hang Kenh Ward, Hai Phong City");
        user_1.put("dob", "4/10/2002");
        user_1.put("sex", "male");
        user_1.put("email", "dm410@gmail.com");
        user_1.put("nofFriends", "8");
        user_1.put("friend", "user_3/user_2/user_6/user_11/user_12/user_4");
        
        Map<String, String> user_2 = new HashMap<String, String>();
        user_2.put("name", "Phan Phuc Dat");
        user_2.put("image", "./assets/imgs/user_2/avt.png");
        user_2.put("bg", "./assets/imgs/user_2/bg.png");
        user_2.put("online_status", "Online now");
        user_2.put("usn", "PhucDat2808");
        user_2.put("address", "Hamlet Binh Tien 1, lo 825, Xa Duc Hoa Ha, Long An");
        user_2.put("dob", "28/8/2002");
        user_2.put("sex", "male");
        user_2.put("email", "pd2808@gmail.com");
        user_2.put("nofFriends", "6");
        user_2.put("friend", "user_1/user_3/user_2/user_7/user_10/user_4");

        Map<String, String> user_3 = new HashMap<String, String>();
        user_3.put("name", "Nguyen Ba Phuong");
        user_3.put("image", "./assets/imgs/user_3/avt.png");
        user_3.put("bg", "./assets/imgs/user_3/bg.png");
        user_3.put("online_status", "Last online November 27 at 11:28 AM");
        user_3.put("usn", "nguyenbaphuong");
        user_3.put("address", "unknown");
        user_3.put("dob", "unknown");
        user_3.put("sex", "female");  
        user_3.put("email", "unknown");
        user_3.put("nofFriends", "11");
        user_3.put("friend", "user_1/user_5/user_2/user_6/user_7/user_9/user_10/user_11/user_12/user_4/user_12");

        Map<String, String> user_4 = new HashMap<String, String>();
        user_4.put("name", "Hoang Dieu Linh");
        user_4.put("image", "./assets/imgs/user_4/avt.png");
        user_4.put("bg", "./assets/imgs/user_4/bg.png");
        user_4.put("online_status", "Last online October 30 at 9:55 PM");
        user_4.put("usn", "dieulinh113");
        user_4.put("address", "1/18 Su Van Hanh Street, Ward 12, District 10, Ho Chi Minh City");
        user_4.put("dob", "27/8/2003");
        user_4.put("sex", "female");
        user_4.put("email", "dl1130@gmail.com");
        user_4.put("nofFriends", "10");
        user_4.put("friend", "user_3/user_1/user_5/user_2/user_6/user_7/user_9/user_10/user_11/user_12");

        Map<String, String> user_5 = new HashMap<String, String>();
        user_5.put("name", "Ngo Anh Hung");
        user_5.put("image", "./assets/imgs/user_5/avt.png");
        user_5.put("bg", "./assets/imgs/user_5/bg.png");
        user_5.put("online_status", "Last online 23 hours ago");
        user_5.put("usn", "anhhungngo");
        user_5.put("address", "18B Cong Hoa Street, Ward 4, Tan Binh District, Ho Chi Minh City");
        user_5.put("dob", "28/2/2000");
        user_5.put("sex", "male");
        user_5.put("email", "anhhungngo@gmail.com");
        user_5.put("nofFriends", "4");
        user_5.put("friend", "user_4/user_10/user_1/user_3");

        Map<String, String> user_6 = new HashMap<String, String>();
        user_6.put("name", "Phan Duong Tung Anh");
        user_6.put("image", "./assets/imgs/user_6/avt.png");
        user_6.put("bg", "./assets/imgs/user_6/bg.png");
        user_6.put("online_status", "Last online about an hour ago");
        user_6.put("usn", "tunganhdaddy");
        user_6.put("address", "37/8A Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City");
        user_6.put("dob", "7/8/1987");
        user_6.put("sex", "male");
        user_6.put("email", "tunganh@gmail.com");
        user_6.put("nofFriends", "7");
        user_6.put("friend", "user_1/user_2/user_3/user_5/user_4/user_7/user_9");

        Map<String, String> user_7 = new HashMap<String, String>();
        user_7.put("name", "Phan Duong Tung Anh");
        user_7.put("image", "./assets/imgs/user_7/avt.png");
        user_7.put("bg", "./assets/imgs/user_7/bg.png");
        user_7.put("online_status", "Last online about an hour ago");
        user_7.put("usn", "tunganhdaddy");
        user_7.put("address", "37/8A Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City");
        user_7.put("dob", "7/8/1987");
        user_7.put("sex", "male");
        user_7.put("email", "tunganh@gmail.com");
        user_7.put("nofFriends", "7");
        user_7.put("friend", "user_1/user_2/user_3/user_5/user_4/user_7/user_9");

        Map<String, String> user_8 = new HashMap<String, String>();
        user_8.put("name", "Phan Duong Tung Anh");
        user_8.put("image", "./assets/imgs/user_8/avt.png");
        user_8.put("bg", "./assets/imgs/user_8/bg.png");
        user_8.put("online_status", "Last online about an hour ago");
        user_8.put("usn", "tunganhdaddy");
        user_8.put("address", "37/8A Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City");
        user_8.put("dob", "7/8/1987");
        user_8.put("sex", "male");
        user_8.put("email", "tunganh@gmail.com");
        user_8.put("nofFriends", "7");
        user_8.put("friend", "user_1/user_2/user_3/user_5/user_4/user_7/user_9");

        Map<String, String> user_9 = new HashMap<String, String>();
        user_9.put("name", "Phan Duong Tung Anh");
        user_9.put("image", "./assets/imgs/user_9/avt.png");
        user_9.put("bg", "./assets/imgs/user_9/bg.png");
        user_9.put("online_status", "Last online about an hour ago");
        user_9.put("usn", "tunganhdaddy");
        user_9.put("address", "37/8A Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City");
        user_9.put("dob", "7/8/1987");
        user_9.put("sex", "male");
        user_9.put("email", "tunganh@gmail.com");
        user_9.put("nofFriends", "7");
        user_9.put("friend", "user_1/user_2/user_3/user_5/user_4/user_7/user_9");

        Map<String, String> user_10 = new HashMap<String, String>();
        user_10.put("name", "Phan Duong Tung Anh");
        user_10.put("image", "./assets/imgs/user_10/avt.png");
        user_10.put("bg", "./assets/imgs/user_10/bg.png");
        user_10.put("online_status", "Last online about an hour ago");
        user_10.put("usn", "tunganhdaddy");
        user_10.put("address", "37/8A Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City");
        user_10.put("dob", "7/8/1987");
        user_10.put("sex", "male");
        user_10.put("email", "tunganh@gmail.com");
        user_10.put("nofFriends", "7");
        user_10.put("friend", "user_1/user_2/user_3/user_5/user_4/user_7/user_9");

        Map<String, String> user_11 = new HashMap<String, String>();
        user_11.put("name", "Phan Duong Tung Anh");
        user_11.put("image", "./assets/imgs/user_11/avt.png");
        user_11.put("bg", "./assets/imgs/user_11/bg.png");
        user_11.put("online_status", "Last online about an hour ago");
        user_11.put("usn", "tunganhdaddy");
        user_11.put("address", "37/8A Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City");
        user_11.put("dob", "7/8/1987");
        user_11.put("sex", "male");
        user_11.put("email", "tunganh@gmail.com");
        user_11.put("nofFriends", "7");
        user_11.put("friend", "user_1/user_2/user_3/user_5/user_4/user_7/user_9");

        Map<String, String> user_12 = new HashMap<String, String>();
        user_12.put("name", "Phan Duong Tung Anh");
        user_12.put("image", "./assets/imgs/user_12/avt.png");
        user_12.put("bg", "./assets/imgs/user_12/bg.png");
        user_12.put("online_status", "Last online about an hour ago");
        user_12.put("usn", "tunganhdaddy");
        user_12.put("address", "37/8A Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City");
        user_12.put("dob", "7/8/1987");
        user_12.put("sex", "male");
        user_12.put("email", "tunganh@gmail.com");
        user_12.put("nofFriends", "7");
        user_12.put("friend", "user_1/user_2/user_3/user_5/user_4/user_7/user_9");

        users.put("user_1", user_1);
        users.put("user_2", user_2);
        users.put("user_3", user_3);
        users.put("user_4", user_4);
        users.put("user_5", user_5);
        users.put("user_6", user_6);
        users.put("user_7", user_7);
        users.put("user_8", user_8);
        users.put("user_9", user_9);
        users.put("user_10", user_10);
        users.put("user_11", user_11);
        users.put("user_12", user_12);

        // LOGIN HISTORY
        Map<String, Map<String, String>> user_1_hislist = new HashMap<String, Map<String, String>>();
        Map<String, String> user_1_his_1 = new HashMap<String, String>();
        user_1_his_1.put("device_name", "Windows PC");
        user_1_his_1.put("location", "Ho Chi Minh City, Vietnam");
        user_1_his_1.put("online_status", "Active now");
        user_1_his_1.put("device_icon", "./assets/icons/window.png");
        
        Map<String, String> user_1_his_2 = new HashMap<String, String>();
        user_1_his_2.put("device_name", "iMac");
        user_1_his_2.put("location", "Ho Chi Minh City, Vietnam");
        user_1_his_2.put("online_status", "about an hour ago");
        user_1_his_2.put("device_icon", "./assets/icons/mac.png");
        
        Map<String, String> user_1_his_3 = new HashMap<String, String>();
        user_1_his_3.put("device_name", "Linux PC");
        user_1_his_3.put("location", "Ho Chi Minh City, Vietnam");
        user_1_his_3.put("online_status", "Yesterday at 9:54 PM");
        user_1_his_3.put("device_icon", "./assets/icons/linux.png");

        Map<String, String> user_1_his_4 = new HashMap<String, String>();
        user_1_his_4.put("device_name", "Window PC");
        user_1_his_4.put("location", "Ho Chi Minh City, Vietnam");
        user_1_his_4.put("online_status", "November 27 at 11:28 AM");
        user_1_his_4.put("device_icon", "./assets/icons/window.png");

        Map<String, String> user_1_his_5 = new HashMap<String, String>();
        user_1_his_5.put("device_name", "Window PC");
        user_1_his_5.put("location", "Ho Chi Minh City, Vietnam");
        user_1_his_5.put("online_status", "November 5 at 2:38 PM");
        user_1_his_5.put("device_icon", "./assets/icons/window.png");

        Map<String, String> user_1_his_6 = new HashMap<String, String>();
        user_1_his_6.put("device_name", "iMac");
        user_1_his_6.put("location", "Ho Chi Minh City, Vietnam");
        user_1_his_6.put("online_status", "October 30 at 9:55 PM");
        user_1_his_6.put("device_icon", "./assets/icons/mac.png");

        Map<String, String> user_1_his_7 = new HashMap<String, String>();
        user_1_his_7.put("device_name", "Window PC");
        user_1_his_7.put("location", "Ho Chi Minh City, Vietnam");
        user_1_his_7.put("online_status", "October 15 at 9:15 PM");
        user_1_his_7.put("device_icon", "./assets/icons/window.png");

        user_1_hislist.put("his_1", user_1_his_1);
        user_1_hislist.put("his_2", user_1_his_2);
        user_1_hislist.put("his_3", user_1_his_3);
        user_1_hislist.put("his_4", user_1_his_4);
        user_1_hislist.put("his_5", user_1_his_5);
        user_1_hislist.put("his_6", user_1_his_6);
        user_1_hislist.put("his_7", user_1_his_7);

        loginHistories.put("user_1_log_his", user_1_hislist);
        loginHistories.put("user_2_log_his", user_1_hislist);
        loginHistories.put("user_3_log_his", user_1_hislist);
        loginHistories.put("user_4_log_his", user_1_hislist);
        loginHistories.put("user_5_log_his", user_1_hislist);
        loginHistories.put("user_6_log_his", user_1_hislist);
        loginHistories.put("user_7_log_his", user_1_hislist);
        loginHistories.put("user_8_log_his", user_1_hislist);
        loginHistories.put("user_9_log_his", user_1_hislist);
        loginHistories.put("user_10_log_his", user_1_hislist);
        loginHistories.put("user_11_log_his", user_1_hislist);
        loginHistories.put("user_12_log_his", user_1_hislist);
    }
}
