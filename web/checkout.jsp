<%@page import="java.sql.Date"%>
<%@page import="models.Payment"%>
<%@page import="models.Post"%>
<%@page import="utility.Utils"%>
<%@page import="models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" /><br><br><br><br><br><br><br><br><br><br>
        <h1>
            <%
               int post_id = Integer.parseInt(request.getParameter("post_id"));
               Post p = Utils.getPostById(post_id);
               request.getSession().setAttribute("post", p);
                %>
                   <div class="container">
                       <div class="row">
                           <div class="right-child">
                               <form action="PaymentController" method="post">
                                   <input type="number" min="1" max="<%=(p.getQuantity())%>" name="qt" class="text2" placeholder="quantity" required/>
                                   <button class="secondary">Pay</button>
                               </form>
                           </div>
                       </div>
                   </div>
                <%                   
            %>
        </h1>
        <jsp:include page="Footer.jsp" />
    </body>
</html>
