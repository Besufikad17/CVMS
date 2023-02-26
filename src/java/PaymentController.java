
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import models.Customer;
import models.Payment;
import models.Post;
import utility.Utils;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Post p = (Post) request.getSession().getAttribute("post");
        int quantity = Integer.parseInt(request.getParameter("qt"));
        Customer customer = (Customer) request.getSession().getAttribute("user");
        double newBalance = customer.getBalance() - (quantity * p.getPrice());
        if (newBalance <= 0) {
           pw.print("Insufficet balance!!");
        } else {
           try{
                Utils.updateCustomerBalance(customer.getId(), newBalance);
                int quant = p.getQuantity() - 1;
                if (quant <= 0) {
                    Utils.hidePost(p.getId());
                }
                Utils.updatePostQuantity(p.getId(), quant);
                Payment payment = new Payment(customer.getId(), p.getOrganization_id(),p.getId(), p.getPrice(), new Date(System.currentTimeMillis()));
                Utils.pay(payment);
                response.sendRedirect("vehicles.jsp");
           }catch(Exception e){
               e.printStackTrace();
           }
        }
    }

}
