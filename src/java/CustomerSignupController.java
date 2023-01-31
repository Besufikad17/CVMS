import utility.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;


@WebServlet("/CustomerSignupController")
public class CustomerSignupController extends HttpServlet {

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phonenumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        
        try{
           if(password.equals(cpassword)){
                if (Utils.userExists(email, "Organization")) {
                    out.print("Organization exists with the same email address please try again <a href=\"signup.html\">here</a>");
                }else if(Utils.userExists(email, "Customer")){ 
                     out.print("Customer exists with the same email address please try again <a href=\"signup.html\">here</a>");
                }else {
                    Customer customer = new Customer(fname,lname,username, email, phonenumber, password, address, "customer", Math.random()*10000);
                    int id = Utils.customerSignup(customer);
                    customer.setId(id);
                    HttpSession session = request.getSession();
                    if(session.getAttribute("user") != null){
                        session.removeAttribute("user");
                    }
                     session.setAttribute("user", customer);
                    RequestDispatcher rd = request.getRequestDispatcher("vehicles.jsp");
                    rd.include(request, response);
                }
           }else{
               out.print("Password doesn't match please try again <a href=\"signup.html\">here</a>");
           }
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
}
