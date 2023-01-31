
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
         res.setContentType("text/html");
         PrintWriter pw = res.getWriter();
         HttpSession session = req.getSession();
         session.removeAttribute("user");
         RequestDispatcher rd = req.getRequestDispatcher("index.html");
         rd.include(req, res);
    } 
    
}
