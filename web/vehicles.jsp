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
                            for (int i = 0; i < posts.size(); i++) {
                                if (i % 2 != 0) {
                                    Post p = posts.get(i);
                                    Post p2 = posts.get(i + 1);
                                    Automobile a = Utils.getAutomobileById(p.getAutomotive_id());
                                    Automobile a2 = Utils.getAutomobileById(p2.getAutomotive_id());
                                    %>
                                    <div class="container">
                                        <div class="cards-row">
                                            <div class="card">
                                                <img src="<%=(a.getImg_url())%>" />
                                                <h1><%=(a.getModel())%></h1>
                                                <h1>Quantity: <%=(p.getQuantity())%></h1>
                                                <h1>Type: <%=(p.getType())%></h1>
                                                <h1>Price: <%=(p.getPrice())%></h1>
                                            </div>
                                            <div class="card">
                                                <img src="<%=(a2.getImg_url())%>" />
                                                <h1><%=(a2.getModel())%></h1>
                                                <h1>Quantity: <%=(p2.getQuantity())%></h1>
                                                <h1>Type: <%=(p2.getType())%></h1>
                                                <h1>Price: <%=(p2.getPrice())%></h1>
                                            </div>
                                        </div>
                                    </div>

                                    <%
                                        }
                                    }
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
                            for (Post p : posts) {
                                Automobile a = Utils.getAutomobileById(p.getAutomotive_id());
                                %>
                                    <div>
                                        <img src="<%=(a.getImg_url())%>" />
                                        <h1><%=(a.getModel())%></h1>
                                        <h1>Quantity: <%=(p.getQuantity())%></h1>
                                        <h1>Type: <%=(p.getType())%></h1>
                                        <h1>Price: <%=(p.getPrice())%></h1>
                                    </div>
                                <%
                            }
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
