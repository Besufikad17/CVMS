
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Organization;

@WebServlet("/OrgSignupController")
public class OrgSignupController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String phonenumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");

        try {
            if (password.equals(cpassword)) {
                if (Utils.userExists(email, "Organization")) {
                    out.print("Organization exists with the same email address please try again <a href=\"signup.html\">here</a>");
                }else if(Utils.userExists(email, "Customer")){ 
                     out.print("Customer exists with the same email address please try again <a href=\"signup.html\">here</a>");
                }else {
                    Organization org = new Organization(name, type, description, email, phonenumber, password, address);
                    Utils.orgSignup(org);
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        session.removeAttribute("user");
                    }
                    session.setAttribute("user", org);
                    RequestDispatcher rd = request.getRequestDispatcher("vehicles.jsp");
                    rd.include(request, response);
                }
            } else {
                out.print("Password doesn't match please try again <a href=\"signup.html\">here</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
