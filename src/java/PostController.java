
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
            Part filePart = req.getPart("img");
            String fileName = filePart.getSubmittedFileName();
            filePart.write("/home/bes/NetBeansProjects/CVMS/build/web/assets/imgs/upload/" + fileName);
            Automobile a = new Automobile(model, manufacturer, year, style, color,no_seats, "/home/bes/NetBeansProjects/CVMS/build/web/assets/imgs/upload/" + fileName ,engine);
            int id = Utils.createAutomobile(a);
            HttpSession session = req.getSession();
            int org_id = ((Organization)session.getAttribute("user")).getId();
            Post p = new Post(id, quantity, type, new Date(System.currentTimeMillis()), price, org_id, false);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
