package models;

public class Organization {
    
    private int id;
    
    private String name;

    private String type;

    private String description;

    private String email;

    private String phonenumber;

    private String password;

    private String address;
    
    private double balance;

    public Organization(int id, String name, String type, String description, String email, String phonenumber, String password, String address, double balance) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.address = address;
        this.balance = balance;
    }

    public Organization(String name, String type, String description, String email, String phonenumber, String password, String address, double balance) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.address = address;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
