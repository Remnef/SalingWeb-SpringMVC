<%-- 
    Document   : Cart
    Created on : Oct 31, 2020, 9:42:21 PM
    Author     : trinh
--%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loc's store</title>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>
    <style>
        /*#message{*/
        /*    position: fixed;*/
        /*    z-index: 1000;*/
        /*    bottom: 0;*/
        /*    right: 0;*/
        /*    left: 0;*/
        /*    margin-bottom: 0;*/
        /*}*/
    </style>
    <body>
    <div id="message" class="${mess=="Successful !"?"alert alert-success":(mess==null)?"":"alert alert-danger"}" role="alert">
        <p style="text-align: center; font-size: larger">${mess}</p>
    </div>
        <jsp:include page="../layout/Menu.html"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">
                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Đơn Giá</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Số Lượng</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Xóa</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${sessionScope.cardList}" var="card">
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="${card.product.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${card.product.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>${card.price}</strong></td>
                                                    <td class="align-middle">
<%--                                                        <a href="#"><button class="btnSub">-</button></a> --%>
                                                        <strong>${card.quantity}</strong>
<%--                                                        <a href="#"><button class="btnAdd">+</button></a>--%>
                                                    </td>
                                                    <td class="align-middle">
                                                        <a class="btn btn-danger delete" data-id="${card.id}" data-toggle="modal">
                                                            <i class="fa fa-trash-o" aria-hidden="true"></i><span>Delete</span>
                                                        </a>
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                                </div>
                            </div>
                            <form action="/sale" method="get">
                                <div class="row py-5 p-4 bg-white rounded shadow-sm">
                                    <div class="col-lg-6">
                                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Address</div>
                                            <div class="p-4">
                                                <div class="input-group mb-4 border rounded-pill p-2">
                                                    <input type="text" placeholder="Địa chỉ" aria-describedby="button-addon3" class="form-control border-0" required>
                                                    <div class="input-group-append border-0">
                                                    <i class="fa fa-map-marker"></i>
                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                <div class="col-lg-6">
                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                                    <div class="p-4">
                                        <ul class="list-unstyled mb-4">
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng tiền hàng</strong><strong>${bill} $</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Phí vận chuyển</strong><strong>Free ship</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>${vat}</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng thanh toán</strong>
                                            <h5 class="font-weight-bold">${AllBill} $</h5>
                                            </li>
                                        </ul>
                                        <c:if test="${sessionScope.cardList.size()>0}">
                                            <a href="#saleEmployeeModal"  class="btn btn-success" data-toggle="modal">
                                                <i class="fa fa-shopping-cart"></i> <span>Mua</span>
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

    <!-- Delete Modal HTML -->
    <div id="deleteEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action ="/sale" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete Product</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete these Records?</p>
                        <p class="text-warning"><small>This action cannot be undone.</small></p>
                    </div>
                    <input type="text" id="id" name="id" style="display: none">
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" name="submit" class="btn btn-danger" value="Delete">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Sale Modal HTML -->
    <div id="saleEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action ="/sale" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Xác nhận mua</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p><b>Đơn hàng của bạn có giá trị là: ${AllBill}$ </b></p>
                        <p><b>Đơn hàng sẽ được chuyển đến địa chỉ bạn vừa nhập trong vòng 3-5 ngày</b></p>
                        <p><b>Cám ơn ${sessionScope.acc.name} đã tin tưởng và ủng hộ</b></p>
                        <p class="text-warning"><small>This action cannot be undone.</small></p>
                    </div>
<%--                    <input type="text" id="addresss" name="id">--%>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" name="submit" class="btn btn-danger" value="Sale">
                    </div>
                </form>
            </div>
        </div>
    </div>
<%--        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>
<%--        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>--%>
<%--        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>--%>
    </body>
    <script>
        $(".delete").click(function () {
            let ids = $(this).attr('data-id');
            $("#id").val( ids );
            $('#deleteEmployeeModal').modal('show');
        });
        $(".alert").fadeTo(2000, 500).fadeOut(1000);
        // $(".sale").click(function () {
        //     let ids = $(this).attr('data-id');
        //     let address = $("#address").val();
        //     $("#addresss").val( address );
        //     $("#idsale").val( ids );
        //     $('#saleEmployeeModal').modal('show');
        // });
    </script>
</html>
</html>
