package utility;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Automobile;
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
                rs.getString("address"),
                rs.getDouble("balance")
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
    
    public static int createAutomobile(Automobile auto) throws Exception {
        loadDriver();
        Connection con = getConnection();
        String query = "insert into Automotive(model,manufacturer,year,style,color,no_standard_seats,img_url,engine) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, auto.getModel());
        ps.setString(2, auto.getManufacturer());
        ps.setInt(3, auto.getYear());
        ps.setString(4, auto.getStyle());
        ps.setString(5, auto.getColor());
        ps.setInt(6, auto.getNo_seats());
        ps.setString(7, auto.getImg_url());
        ps.setString(8, auto.getEngine());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return (int) rs.getLong(1);
    }
    
    public static Automobile getAutomobileById(int id) throws Exception {
        loadDriver();
        Connection con = getConnection();
        String query = "select * from Automotive where id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Automobile(
             rs.getInt("id"),
             rs.getString("model"),
             rs.getString("manufacturer"),
             rs.getInt("year"),
             rs.getString("style"),
             rs.getString("color"),
             rs.getInt("no_standard_seats"),
             rs.getString("img_url"),
             rs.getString("engine")
        );
    }
    
    public static void updateOrgBalance(int id, double newBalance) throws Exception {
        loadDriver();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("update Organization set balance=? where id=?");
        ps.setDouble(1, newBalance);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    
    public static void createPost(Post post) throws Exception {
        loadDriver();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("insert into Post(automotive_id, quantity,"
                + " type, created_at, price, organization_id, is_hidden) values(?,?,?,?,?,?,?)");
        ps.setInt(1, post.getAutomotive_id());
        ps.setInt(2, post.getQuantity());
        ps.setString(3, post.getType());
        ps.setDate(4, post.getCreated_at());
        ps.setDouble(5, post.getPrice());
        ps.setInt(6, post.getOrganization_id());
        ps.setBoolean(7, post.isIsHidden());
        ps.executeUpdate();
    }
    
}
