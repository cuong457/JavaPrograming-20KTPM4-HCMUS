
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Map;

public class test {
    public static void main(String[] args) {
        try {
            File myObj = new File("data.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        try {
            FileWriter myWriter = new FileWriter("data.txt");
            demo_database data = new demo_database();

            // for (Map.Entry<String, Map<String, String>> entry : data.getData().entrySet()) {
            //     myWriter.write("INSERT INTO users (id,name,image,bg,online_status,usn,address,dob,sex,email,nofFriends,friend) VALUES ('" + entry.getKey() + "', '" + entry.getValue().get("name") + "', '" + entry.getValue().get("image") + "', '" + entry.getValue().get("bg") + "', '" + entry.getValue().get("online_status") + "', '" + entry.getValue().get("usn") + "', '" + entry.getValue().get("address") + "', '" + entry.getValue().get("dob") + "', '" + entry.getValue().get("sex") + "', '" + entry.getValue().get("email") + "', '" + entry.getValue().get("nofFriends") + "', '" + entry.getValue().get("friend") + "');\n");
            // }

            // for (Map.Entry<String, Map<String, Map<String, String>>> entry : data.getHistory().entrySet()) {
            //     String userid = entry.getKey();
            //     for(int i = 1; i <= 7; i++) {
            //         String hisid = "his_" + Integer.toString(i);
            //         myWriter.write("INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('" + hisid + "', '" + userid + "', '" + entry.getValue().get("his_" + Integer.toString(i)).get("device_name") + "', '" + entry.getValue().get("his_" + Integer.toString(i)).get("location") + "', '" + entry.getValue().get("his_" + Integer.toString(i)).get("online_status") + "', '" + entry.getValue().get("his_" + Integer.toString(i)).get("device_icon") + "');\n");
            //     }
            // }           

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }   catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
}

