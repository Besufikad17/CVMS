
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Test {
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/CVMS", "root", "");
    }

    public static void loadDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    
    public static void updateOrgBalance(int id, double newBalance) throws Exception {
        loadDriver();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("update Organization set balance=? where id=?");
        ps.setDouble(1, newBalance);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    
    public static void main(String[] args){
        double balance = 1230.0;
        try{
            updateOrgBalance(6, balance);
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
}
