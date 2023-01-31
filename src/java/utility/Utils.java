package utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;
import models.Organization;
import models.Post;

public class Utils {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/CVMS", "root", "");
    }

    public static void loadDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static boolean userExists(String email, String table) throws SQLException, ClassNotFoundException {
        boolean exists;
        loadDriver();
        Connection con = getConnection();
        String query = "select * from " + table + " where email=?";
        System.out.println("query");
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        exists = rs.next();
        return exists;
    }
    
    public static int customerSignup(Customer customer) throws Exception{
        loadDriver();
        Connection con = getConnection();
        String query = "insert into Customer(fname,lname,username,email,phonenumber,password,address,role,balance) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps =con.prepareStatement(query);
        ps.setString(1, customer.getFname());
        ps.setString(2, customer.getLname());
        ps.setString(3, customer.getUsername());
        ps.setString(4, customer.getEmail());
        ps.setString(5, customer.getPhonenumber());
        ps.setString(6, customer.getPassword());
        ps.setString(7, customer.getAddress());
        ps.setString(8, customer.getRole());
        ps.setDouble(9, customer.getBalance());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public static boolean customerLogin(String email, String password) throws Exception {
        boolean flag = false;
        loadDriver();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("select * from Customer where email=? and password=?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        flag = rs.next();
        return flag;
    }
    
    public static int orgSignup(Organization org) throws Exception {
        loadDriver();
        Connection con = getConnection();
        String query = "insert into Organization(name,type,description,phonenumber,address,email) values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, org.getName());
        ps.setString(2, org.getType());
        ps.setString(3, org.getDescription());
        ps.setString(4, org.getPhonenumber());
        ps.setString(5, org.getAddress());
        ps.setString(6, org.getEmail());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public static boolean orgLogin(String email, String password) throws Exception {
        boolean flag = false;
        loadDriver();
        Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement("select * from Organization where email=? and password=?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        flag = rs.next();
        return flag;
    }

    public static int loginHelper(String email, String password) {
        int stats = 0;
        try {
            if (userExists(email, "Customer")) {
                stats = customerLogin(email, password) ? 1 : 0;
            } else if (userExists(email, "Organization")) {
                stats = orgLogin(email, password) ? 1 : 0;
            } else {
               stats = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stats;
    }

    public static Customer getCustomer(String email) throws Exception {
        boolean flag = false;
        loadDriver();
        Connection con = getConnection();
        String query = "select * from Customer where email=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Customer(
                rs.getInt("id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("phonenumber"),
                rs.getString("password"),
                rs.getString("address"),
                rs.getString("role"),
                rs.getDouble("balance")
        );
    }

    public static Organization getOrg(String email) throws Exception {
        loadDriver();
        Connection con = getConnection();
        String query = "select * from Organization where email=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Organization(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("type"),
                rs.getString("description"),
                rs.getString("email"),
                rs.getString("phonenumber"),
                rs.getString("password"),
                rs.getString("address")
        );
    }
    
    public static ArrayList<Post> getAllPosts() throws Exception{
        ArrayList<Post> posts = new ArrayList<>();
        loadDriver();
        Connection con = getConnection();
        String query = "select * from Post";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Post post = new Post(
                 rs.getInt("automotive_id"),
                 rs.getInt("quantity"),
                 rs.getString("type"),
                 rs.getDate("created_at"),
                 rs.getDouble("price"),
                 rs.getInt("organization_id"),
                 rs.getBoolean("is_hidden")
            );
            posts.add(post);
        }
        return posts;
    }
    
    public static ArrayList<Post> getPostsByOrgId(int org_id) throws Exception{
        ArrayList<Post> posts = new ArrayList<>();
        loadDriver();
        Connection con = getConnection();
        String query = "select * from Post where organization_id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, org_id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Post post = new Post(
                 rs.getInt("automotive_id"),
                 rs.getInt("quantity"),
                 rs.getString("type"),
                 rs.getDate("created_at"),
                 rs.getDouble("price"),
                 rs.getInt("organization_id"),
                 rs.getBoolean("is_hidden")
            );
            posts.add(post);
        }
        return posts;
    } 
}
