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
        <jsp:include page="Header.jsp" />
        
        <%
            Object user = session.getAttribute("user");
            if(user != null){
               if(user instanceof Customer){
                  Customer customer = (Customer)user;
                  try{
                    ArrayList<Post> posts = Utils.getAllPosts();
                    if(posts.size() == 0){
                        %>
                            <div class="container">
                                <div class="row">
                                    <img src="assets/imgs/empty-removebg-preview.png" alt="login" class="bg2" /><br>
                                </div>
                                <span class="msg">No posts :( register as organization <a href="signup.html">here</a> to create new posts</span>
                            </div>
                        <%
                    }else{
                       
                    }
                  }catch(Exception e){
                    e.printStackTrace();
                  }
               }else if(user instanceof Organization){
                  Organization org = (Organization)user;
                  try{
                    ArrayList<Post> posts = Utils.getPostsByOrgId(org.getId());
                    if(posts.size() == 0){
                        %>
                            <div class="container">
                                <div class="row">
                                    <img src="assets/imgs/empty.png" alt="login" class="bg3" /><br>
                                </div>
                                <span class="msg">No posts :( click <a href="post.html">here</a> to create new post</span>
                            </div>
                        <%
                    }else{
                       
                    }
                  }catch(Exception e){
                    e.printStackTrace();
                  }
               }
            }else{
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
