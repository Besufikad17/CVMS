
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import models.Automobile;
import models.Organization;
import models.Post;
import utility.Utils;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

@WebServlet("/PostController")
public class PostController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String model = req.getParameter("model");
        String manufacturer = req.getParameter("manufacturer");
        int year = Integer.parseInt(req.getParameter("year"));
        String style = req.getParameter("style");
        int no_seats = Integer.parseInt(req.getParameter("number-of-seats"));
        String color = req.getParameter("color");
        String engine = req.getParameter("engine");
        
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String type = req.getParameter("type");
        double price = Double.parseDouble(req.getParameter("price"));
    
        try {
            
            // uploading file
            Part filePart = req.getPart("img");
            String fileName = filePart.getSubmittedFileName();
            filePart.write("/home/bes/NetBeansProjects/CVMS/web/assets/imgs/upload/" + fileName);
            
            // creating automobile data           
            Automobile a = new Automobile(model, manufacturer, year, style, color,no_seats, "assets/imgs/upload/" + fileName ,engine);
            int id = Utils.createAutomobile(a);
            
            // getting org info
            HttpSession session = req.getSession();
            Organization org = (Organization)session.getAttribute("user");
            int org_id = org.getId();
            
            // creating post data
            Post p = new Post(id, quantity, type, new Date(System.currentTimeMillis()), price, org_id, false);
            
            // checking balance of org and cutting of 10% price listed in the post
            double newBalance = org.getBalance() - (0.1 * price);
            Utils.updateOrgBalance(id, newBalance);
            if(newBalance < 0){
                pw.print("Insufficient balance!!");
            }else{
                Utils.updateOrgBalance(id, newBalance);
                Utils.createPost(p);
                RequestDispatcher rd = req.getRequestDispatcher("vehicles.jsp");
                rd.include(req, res);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
