<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 8/1/2021
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <title>Smart Register</title>
</head>
<body>
<header>

</header>
<section class="section-register">
    <div class="col-xs-2 col-sm-3 col-md-3 col-lg-4"></div>
    <div class="col-xs-8 col-sm-6 col-md-6 col-lg-4 form-background">
        <h3>Register now</h3>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="row">
                <form name="loginForm" method="POST" action="/loginControl">
                    <div class="form-group">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-item">
                            <input type="text" name="username" class="form-control" placeholder="User Name:">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-item">
                            <input type="password" name="password" class="form-control" placeholder="Password:">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-item">
                            <input type="password" name="repassword" class="form-control" placeholder="Retype Password:">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-item">
                            <input type="text" name="fullName" class="form-control" placeholder="Type name:">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-item">
                            <input type="email" name="email" class="form-control" placeholder="Type email:">
                        </div>
                    </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-item">
                    <h4 style="text-align:center" class="${mess=="Successful !"?"btn-success":"btn-danger"}">${mess}</h4>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-item">
                    <a href="/views/Login.jsp">Back to login</a>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-item" style="text-align:center">
                    <input type="submit" name="submit" class="btn-login" value="REGISTER">
                </div>
            </div>
            </form>
        </div>
    </div>
    </div>
    <div class="col-xs-2 col-sm-3 col-md-3 col-lg-4"></div>
</section>
</body>
</html>
