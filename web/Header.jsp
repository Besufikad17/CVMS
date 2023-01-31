<%@page import="models.Organization"%>
<%@page import="models.Customer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <header class="header ">
            <a href="index.html" class="logo"><span>rapid</span>rental</a>
            <nav class="navbar">
                <a href="#vehicles">vehicles</a>
                <a href="#service">service</a>
                <a href="#contact">contact</a>
            </nav> 

            <%
                Object user = session.getAttribute("user");

                if (user != null) {
                    if (user instanceof Customer) {
                        Customer customer = (Customer) user;
                        %>
                            <form method="post" action="LogoutController" class="form-row">
                                <p class="user"><%=customer.getUsername()%></p>
                                <button class="secondary">logout</button>
                            </form>
                        <%
                    } else if (user instanceof Organization) {
                        Organization org = (Organization) user;
                        %>
                            <form method="post" action="LogoutController" class="form-row">
                                <p class="user"><%=org.getEmail()%></p>
                                <button class="secondary">logout</button>
                            </form>
                        <%
                    } 
                }else {
                        %>
                            <div id="login-btn">
                                 <button class="btn" onclick="openLoginForm()">login</button>
                            </div>
                        <%
                }
            %>
            <div class="form-popup" id="myForm">
                <form action="LoginController" method="post" class="form-container">
                    <h1>Login</h1>

                    <input type="email" placeholder="email" name="email" class="text2" required>
                    <input type="password" placeholder="password" name="password" class="text2" required><br>

                    <a href="signup.html" class="link">Dont have account ?</a><br>

                    <button type="submit" class="secondary">Login</button>
                    <button type="button" class="cancel" onclick="javascript:closeLoginForm()">Close</button>
                </form>
            </div>
        </header>
    </body>
    <style>
        <%@include file="/assets/css/Style.css"%>
    </style>
    <script language="JavaScript">
        function openLoginForm() {
            document.getElementById("myForm").style.display = "flex";
        }

        function closeLoginForm() {
            document.getElementById("myForm").style.display = "none";
        }
    </script>
</html>
