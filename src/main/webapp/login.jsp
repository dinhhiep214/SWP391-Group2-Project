<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" i
          ntegrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="resources/css/plugin/boostrap.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="resources/css/custom/form.css">

    <title>Login | Food Review</title>

    <link rel="shortcut icon" href="resources/images/avatar.png">

</head>

<body>

<div id="intro">
    <div class="middle signin"
         style="background: url(https://www.ieltsvietop.vn/wp-content/uploads/2020/07/hong_kong_skyline_green_building_in_asia.jpg);">
        <div class="login-panel">
            <div class="logo text-center"><br/>
                <a href="home.jsp">
                    <p>Food Review</p>
                </a><br/><br/>
            </div>
            <%
                String email = "";
                String password = "";
                String rememberPassword = "";
                Cookie[] cookieList = request.getCookies();
                if(cookieList!=null){
                    for(Cookie cookie: cookieList){
                        if(cookie.getName().equals("emailCookie")){
                            email = cookie.getValue();
                        }
                        if(cookie.getName().equals("passwordCookie")){
                            password = cookie.getValue();
                        }
                        if(cookie.getName().equals("rememberPasswordCookie")){
                            rememberPassword = cookie.getValue();
                        }
                    }
                }
            %>
            <form class="account" action="userAuthorization" method="post">
                <div class="form-group">
                    <label>
                        <i class="fas fa-user fa-fw"></i>
                    </label>
                    <input type="text" class="form-control" placeholder="Email" name="email"
                           id="InputEmail" value="<%=email%>" required/>
                </div>
                <div class="form-group">
                    <label>
                        <i class="fas fa-key fa-fw"></i>
                    </label>
                    <input type="password" class="form-control" placeholder="Password" name="password"
                           id="InputPassword" value="<%=password%>" required/>
                </div>
                <br>
                <p class="text-danger">${sessionScope.loginError == null? "":sessionScope.loginError}</p>
                <%
                    HttpSession session1 = request.getSession();
                    session1.setAttribute("loginError", null);
                %>
                <div class="form-group">
                    <div class="col">
                        <input type="hidden" name="action" value="signIn" />
                        <button type="submit" class="btn btn-sm">
                            <i class="fas fa-sign-in-alt fa-fw mr-1"></i>Login
                        </button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col">
                        <a class="link-effect" href="#">
                        </a>
                        <a class="link-effect float-right" href="reset-password.jsp">
                            <i class="fas fa-question fa-fw mr-1"></i>Forgot password
                        </a><br/><br/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<footer class="footer">
    <p>
        © 2021 Food Review
    </p>
</footer>

<!-- Bootstrap core JavaScript-->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js "></script>
<script src="resources/js/plugin/popper.min.js"></script>
<script src="resources/js/plugin/boostrap.min.js"></script>

</body>

</html>