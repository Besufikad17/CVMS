import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.Organization;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet{
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");
      
        
        try {
            int flag = Utils.loginHelper(email, password);
            switch (flag) {
                case 0:
                    pw.print("Invalid credentials!! please try again <a href=\"index.html\">here</a>");
                    break;
                case 1:
                    HttpSession session = req.getSession();
                    if(Utils.userExists(email, "Customer")){
                        Customer customer = Utils.getCustomer(email);
                        session.setAttribute("user", customer);
                    }else{
                        Organization org = Utils.getOrg(email);
                        session.setAttribute("user", org);
                    }   RequestDispatcher rd = req.getRequestDispatcher("vehicles.jsp");
                    rd.include(req, res);
                    break;
                default:
                    pw.print("User not found!! please try again <a href=\"index.html\">here</a>");
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
