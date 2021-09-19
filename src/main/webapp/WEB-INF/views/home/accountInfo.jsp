<%--
    Document   : Detail
    Created on : Dec 29, 2020, 5:43:04 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        .gallery-wrap .img-big-wrap img {
            height: 450px;
            width: auto;
            display: inline-block;
            cursor: zoom-in;
        }

        .gallery-wrap .img-small-wrap .item-gallery {
            width: 60px;
            height: 60px;
            border: 1px solid #ddd;
            margin: 7px 2px;
            display: inline-block;
            overflow: hidden;
        }

        .inputPass{
            width: 80%;
            padding: 5px;
            margin: 7px 7px 7px 50px;
            border-radius: 15px;
            border-collapse: collapse;

        }

        .gallery-wrap .img-small-wrap {
            text-align: center;
        }
        .gallery-wrap .img-small-wrap img {
            max-width: 100%;
            max-height: 100%;
            object-fit: cover;
            border-radius: 4px;
            cursor: zoom-in;
        }
        .img-big-wrap img{
            width: 100% !important;
            height: auto !important;
        }
    </style>
</head>
<body>
<jsp:include page="../layout/Menu.html"></jsp:include>
<div class="${mess=="Successful !"?"alert alert-success":(mess==null)?"":"alert alert-danger"}" role="alert">
    <p style="text-align: center; font-size: larger">${mess}</p>
</div>
<div class="container">
    <div class="row">
        <jsp:include page="../layout/Left.html"></jsp:include>
        <div class="col-sm-9">
            <div class="container">
                <div class="card">
                    <div class="row">
                        <aside class="col-sm-5 border-right">
                            <article class="gallery-wrap">
                                <div class="img-big-wrap">
                                    <div> <a href="#"><img src="https://scr.vn/wp-content/uploads/2020/07/avt-con-trai-ng%E1%BA%A7u-978x1024.jpg"></a></div>
                                </div> <!-- slider-product.// -->
                                <div class="img-small-wrap">
                                </div> <!-- slider-nav.// -->
                            </article> <!-- gallery-wrap .end// -->
                        </aside>
                        <aside class="col-sm-7">
                            <article class="card-body p-5">
                                <b style="font-size: 20px">Name: ${account.name}</b>
                                <p><b>IdUser: </b>${account.id}</p>
                                <p><b>Username: </b>${account.username}</p>
                                <p><b>Email: </b>${account.email}</p>
                                <p><b>Access permissions: </b>${account.isAdmin==1?"Admin":account.isUser==1?"Mod":"User"}</p>
                                <a data-id="${account.id}" data-toggle="modal" class="btn btn-lg btn-success text-uppercase changePass"> Edit Password </a>
                            </article> <!-- card-body.// -->
                        </aside> <!-- col.// -->
                    </div> <!-- row.// -->
                </div> <!-- card.// -->


            </div>
        </div>
    </div>
</div>
<%--Modal--%>
<div id="passEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action ="/info" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Change Password</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <input class="inputPass" type="password" name="password" placeholder="Password:">
                    <input class="inputPass" type="password" name="newPassword" placeholder=" New Password:">
                    <input class="inputPass" type="password" name="reTypePassword" placeholder="Retype:">
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" name="submit" class="btn btn-danger" value="changePass">
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../layout/Footer.html"></jsp:include>
</body>
<script>
    $(".changePass").click(function () {
        let ids = $(this).attr('data-id');
        $("#idAcc").val( ids );
        $('#passEmployeeModal').modal('show');
    });
</script>
</html>
