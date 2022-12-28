

public class user {
    private String  username;
    private String  fullname;
    private String  address;
    private String  dob;
    private String  email;
    private String  password;
    private String  sex;

    public user(String username, String fullname, String address, String dob, String email, String password, String sex){
        this.username = username;
        this.fullname = fullname;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.sex = sex;
    }

    public String getUsername(){
        return this.username;
    }

    public String getFullname(){
        return this.fullname;
    }

    public String getAddress(){
        return this.address;
    }

    public String getDob(){
        return this.dob;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
    
    public String getSex(){
        return this.sex;
    }

}
