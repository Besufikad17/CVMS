<%@page import="java.util.ArrayList"%>
<%@page import="models.Automobile"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="models.Payment"%>
<%@page import="models.Post"%>
<%@page import="utility.Utils"%>
<%@page import="models.Customer"%>
<%@page import="models.Organization"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" /><br><br><br><br><br><br><br><br><br><br>
        <%
            Object user = request.getSession().getAttribute("user");
            if(user != null){
                if(user instanceof Customer){
                   Customer customer = (Customer) user;
                   HashMap<Payment, Post> history = Utils.getPaymentByCustomerId(customer.getId());
                   %>
                    <div class="container">
                        <div class="cards-row2">
                            <div class="left-child2">
                                <h1 class="h1">Profile</h1>
                                 <p class="paragraph">Name : <%=(customer.getFname())%> <%=(customer.getLname())%></p>
                                 <p class="paragraph">Username : <%=(customer.getUsername())%></p>
                                 <p class="paragraph">Email :  <%=(customer.getEmail())%></p>
                                 <p class="paragraph">Phonenumber : <%=(customer.getPhonenumber())%></p>
                                 <p class="paragraph">Balance : <%=String.format("%.2f",(customer.getBalance()))%> birr</p>
                            </div>
                            <div class="left-child2">
                                 <h1 class="h1">Reservations</h1>
                                 <%
                                     for(Map.Entry e: history.entrySet()){
                                         Post p = (Post)e.getValue();
                                         Payment payment = (Payment)e.getKey();
                                         Automobile a = Utils.getAutomobileById(p.getAutomotive_id());
                                         %>
                                             <div class="reservations">
                                                 <img src="<%=(a.getImg_url())%>" />
                                                 <p class="paragraph">Vehicle : <%=(a.getModel())%>, <%=(a.getYear())%></p>
                                                 <p class="paragraph">Quantity : <%=((int)(payment.getAmount()/p.getPrice()))%></p>
                                                 <p class="paragraph">Amount : <%=((int)(payment.getAmount()))%></p>
                                                 <p class="paragraph">Date : <%=(payment.getCreated_at())%></p>
                                             </div>
                                         <%
                                     }
                                 %>
                            </div>
                        </div>
                    </div>
                   <%
                }else if(user instanceof Organization){
                   Organization org = (Organization) user;
                   ArrayList<Post> posts = Utils.getPostsByOrgId(org.getId());
                   %>
                    <div class="container">
                        <div class="cards-row2">
                            <div class="left-child2">
                                <h1 class="h1">Profile</h1>
                                 <p class="paragraph">Name : <%=(org.getName())%> </p>
                                 <p class="paragraph">Address : <%=(org.getAddress())%></p>
                                 <p class="paragraph">Email :  <%=(org.getEmail())%></p>
                                 <p class="paragraph">Phonenumber : <%=(org.getPhonenumber())%></p>
                                 <p class="paragraph">Balance : <%=String.format("%.2f",(org.getBalance()))%> birr</p>
                            </div>
                            <div class="left-child2">
                                 <h1 class="h1">Posts</h1>
                                 <%
                                    for(Post p : posts){
                                        Automobile a = Utils.getAutomobileById(p.getAutomotive_id());
                                        %>
                                             <div class="reservations">
                                                 <img src="<%=(a.getImg_url())%>" />
                                                 <p class="paragraph">Vehicle : <%=(a.getModel())%>, <%=(a.getYear())%></p>
                                                 <p class="paragraph">Quantity : <%=(p.getQuantity())%></p>
                                                 <p class="paragraph">Price : <%=(p.getPrice())%></p>
                                                 <p class="paragraph">Date : <%=(p.getCreated_at())%></p>
                                             </div>
                                        <%
                                    }
                                 %>
                            </div>
                        </div>
                    </div>
                   <%
                }
            }
        %>
    </body>
</html>
