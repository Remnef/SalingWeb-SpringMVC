<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Loc's store</title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/manager.css" rel="stylesheet" type="text/css"/>
    <style>
        img{
            width: 200px;
            height: 120px;
        }
    </style>
<body>
<jsp:include page="../layout/Menu.html"></jsp:include>
<div id="message" class="${mess=="Successful !"?"alert alert-success":(mess==null)?"":"alert alert-danger"}" role="alert">
    <p style="text-align: center; font-size: larger">${mess}</p>
</div>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Product</b></h2>
                </div>
                <c:if test="${sessionScope.acc.isAdmin == 1}">
                    <div class="col-sm-6">
                        <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="fa fa-pencil-square-o"></i> <span>Add New Acount</span></a>
                    </div>
                </c:if>
            </div>
        </div>
        <table class="table table-striped table-hover" id="tblListProducts">
            <thead>
            <tr>
                <th>ID</th>
                <th>UserName</th>
                <th>Name</th>
                <th>Email</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listP}" var="o">
                <tr>
                    <td style="${o.status==1?"":"color: #95a5a6"}">${o.id}</td>
                    <td style="${o.status==1?"":"color: #95a5a6"}">${o.username}</td>
                    <td style="${o.status==1?"":"color: #95a5a6"}">${o.name}</td>
                    <td style="${o.status==1?"":"color: #95a5a6"}">${o.email}</td>
                    <td style="${o.status==1?"":"color: #95a5a6"}">${o.isAdmin==1?"Admin":(o.isUser==1)?"Mod":"User"}${o.status==1?"":"-locked"}</td>
                    <td>
                        <c:if test="${sessionScope.acc.isAdmin==1 && o.isAdmin==0}">
                            <a data-id="${o.id}" class="delete" data-toggle="modal">
                                <i style="${o.status==1?"":"color: #95a5a6"}" class="${(o.status==1)?"fa fa-unlock-alt":"fa fa-lock"}" aria-hidden="true"></i>
                            </a>
                            <a data-id="${o.id}" class="setMod" data-toggle="modal">
                                <i style="${o.status==1?"":"color: #95a5a6"}" class="${o.isUser==1?"fa fa-wrench":"fa fa-plus"}" aria-hidden="true"></i>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.acc.isUser==1 && o.isUser==0 && o.isAdmin==0}">
                            <a data-id="${o.id}" class="delete" data-toggle="modal">
                                <i style="${o.status==1?"":"color: #95a5a6"}" class="${(o.status==1)?"fa fa-unlock-alt":"fa fa-lock"}" aria-hidden="true"></i>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="clearfix">
            <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
            <ul class="pagination">
                <li class="page-item disabled"><a href="#">Previous</a></li>
                <li class="page-item"><a href="#" class="page-link">1</a></li>
                <li class="page-item"><a href="#" class="page-link">2</a></li>
                <li class="page-item active"><a href="#" class="page-link">3</a></li>
                <li class="page-item"><a href="#" class="page-link">4</a></li>
                <li class="page-item"><a href="#" class="page-link">5</a></li>
                <li class="page-item"><a href="#" class="page-link">Next</a></li>
            </ul>
        </div>
    </div>
    <a href="/home"><button type="button" class="btn btn-primary">Back to home</button></a>
</div>
<!-- Add Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/account" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Add Account</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>User name</label>
                        <input type="text" name="username" class="form-control" placeholder="User Name:">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="password" class="form-control" placeholder="Password:">
                    </div>
                    <div class="form-group">
                        <label>Retype password</label>
                        <input type="password" name="repassword" class="form-control" placeholder="Retype Password:">
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="fullName" class="form-control" placeholder="Type name:">
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" placeholder="Type email:">
                    </div>
                    <div class="form-group">
                        <label>Access permissions</label>
                        <select name="isUser" class="form-select" aria-label="Default select example">
                            <option value="0">User</option>
                            <option value="1">Mod</option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" name="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- setLock Modal HTML -->
<div id="setLock" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action ="/account" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Set Lock Account</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <input type="text" id="id" name="id" style="display: none">
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" name="submit" class="btn btn-danger" value="Set Lock">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- SetMod Modal HTML -->
<div id="setModEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action ="/account" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Set mod Account</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <input type="text" id="idAcc" name="id" style="display: none">
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" name="submit" class="btn btn-danger" value="Set mod">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    $(".delete").click(function () {
        let ids = $(this).attr('data-id');
        $("#id").val( ids );
        $('#setLock').modal('show');
    });
    $(".setMod").click(function () {
        let ids = $(this).attr('data-id');
        $("#idAcc").val( ids );
        $('#setModEmployeeModal').modal('show');
    });
    $(".alert").fadeTo(2000, 500).fadeOut(1000);
</script>
</html>