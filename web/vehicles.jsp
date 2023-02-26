<%@page import="models.Automobile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Post"%>
<%@page import="models.Organization"%>
<%@page import="models.Customer"%>
<%@page import="utility.Utils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehicles</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" /><br><br><br><br><br><br><br><br><br><br>

        <%
            Object user = session.getAttribute("user");
            if (user != null) {
                if (user instanceof Customer) {
                    Customer customer = (Customer) user;
                    try {
                        ArrayList<Post> posts = Utils.getAllPosts();
                        if (posts.size() == 0) {
                            %>
                                <div class="container">
                                    <div class="row">
                                        <img src="assets/imgs/empty2.png" alt="login" class="bg2" /><br>
                                    </div>
                                    <span class="msg">No posts :( register as organization <a href="signup.html">here</a> to create new posts</span>
                                </div>
                            <%
                        } else {
                                %>
                                  <div class="container">
                                        <div class="cards-row2">
                                             <%
                                              for (int i = 0; i < posts.size(); i++) {
                                                Post p = posts.get(i);
                                                Automobile a = Utils.getAutomobileById(p.getAutomotive_id());
                                                ArrayList<Integer> cars = Utils.getReservedCarsId(customer.getId());
                                                if(cars.indexOf(p.getId()) == -1){
                                                    %>
                                                        <div class="card2">
                                                            <img src="<%=(a.getImg_url())%>" />
                                                            <div class="details">
                                                                <h1><%=(a.getModel())%></h1>
                                                                <h1>Quantity: <%=(p.getQuantity())%></h1>
                                                                <h1>Type: <%=(p.getType())%></h1>
                                                                <h1>Price: <%=(p.getPrice())%> birr</h1>
                                                                <a href="checkout.jsp?post_id=<%=p.getId()%>" class="reserve"><button class="primary">Reserve</button></a>
                                                            </div>
                                                        </div>
                                                    <%
                                                }else{
                                                    %>
                                                        <div class="card2">
                                                            <img src="<%=(a.getImg_url())%>" />
                                                            <div class="details">
                                                                <h1><%=(a.getModel())%></h1>
                                                                <h1>Quantity: <%=(p.getQuantity())%></h1>
                                                                <h1>Type: <%=(p.getType())%></h1>
                                                                <h1>Price: <%=(p.getPrice())%> birr</h1>
                                                                <span class="reserve">Reserved</span>
                                                            </div>
                                                        </div>
                                                    <%
                                                }
                                               }
                                             %>
                                        </div>
                                    </div>
                                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (user instanceof Organization) {
                    Organization org = (Organization) user;
                    try {
                        ArrayList<Post> posts = Utils.getPostsByOrgId(org.getId());
                        if (posts.size() == 0) {
                            %>
                                <div class="container">
                                    <div class="row">
                                        <img src="assets/imgs/empty2.png" alt="login" class="bg3" /><br>
                                    </div>
                                    <span class="msg">No posts :( click <a href="post.html">here</a> to create new post</span>
                                </div>
                            <%
                        } else {
                                %>
                                  <div class="container">
                                        <div class="cards-row2">
                                             <%
                                              for (int i = 0; i < posts.size(); i++) {
                                                Post p = posts.get(i);
                                                Automobile a = Utils.getAutomobileById(p.getAutomotive_id());
                                                if(!p.isIsHidden()){
                                                %>
                                                       <div class="card2">
                                                            <img src="<%=(a.getImg_url())%>" />
                                                            <div class="details">
                                                                <h1><%=(a.getModel())%></h1>
                                                                <h1>Quantity: <%=(p.getQuantity())%></h1>
                                                                <h1>Type: <%=(p.getType())%></h1>
                                                                <h1>Price: <%=(p.getPrice())%> birr</h1>
                                                            </div>
                                                        </div>
                                                    <%
                                                }
                                                    
                                               }
                                             %>
                                        </div>
                                    </div>
                                <%
                              %><div class="container">
                                    <span class="msg"><a href="post.html">create new post</a></span>
                              </div><%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                %>
                    <div class="container">
                        <div class="row">
                            <img src="assets/imgs/login.jpg" alt="login" class="bg3" /><br>
                        </div>
                        <span class="msg">You must login first to browse automotive</span>
                    </div>
                <%
            }

        %>


        <jsp:include page="Footer.jsp" />
    </body>
    <style>
        <%@include file="/assets/css/Style.css"%>
    </style>
</html>
