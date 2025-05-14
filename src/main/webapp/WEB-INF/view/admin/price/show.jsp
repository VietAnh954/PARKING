<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Danh sách Bảng Giá</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            </head>

            <body>

                <div class="container mt-5">
                    <div class="row">
                        <div class=" col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>Table Price</h3>
                                <a href="/admin/price/create" class="btn btn-primary">Create new price</a>
                            </div>

                            <hr />
                            <table class="table table-bordered table-hover">
                                <thead>

                                    <tr>
                                        <th>MÃ BẢNG GIÁ</th>
                                        <th>LOẠI XE</th>
                                        <th>Hình Thức Gửi</th>
                                        <th>Giá</th>
                                        <th>Action</th>
                                    </tr>

                                </thead>
                                <tbody>
                                    <c:forEach var="bangGia" items="${bangGiaList}">
                                        <tr>
                                            <td>${bangGia.maBangGia}</td>
                                            <td>${bangGia.maLoaiXe.tenLoaiXe}</td>
                                            <td>${bangGia.maHinhThuc.tenHinhThuc}</td>
                                            <td>${bangGia.gia}</td>
                                            <td>
                                                <a href="/admin/price/update/${bangGia.maBangGia}"
                                                    class="btn btn-warning mx-2">Update</a>
                                                <a href="/admin/price/delete/${bangGia.maBangGia}"
                                                    class="btn btn-danger">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                            <!-- PHÂN TRANG -->
                            <!-- <nav>
                                <ul class="pagination justify-content-center">
                                    <c:if test="${!bangGiaPage.first}">
                                        <li class="page-item">
                                            <a class="page-link"
                                                href="?page=${bangGiaPage.number - 1}&size=${bangGiaPage.size}">Trước</a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="0" end="${bangGiaPage.totalPages - 1}" var="i">
                                        <li class="page-item ${i == bangGiaPage.number ? 'active' : ''}">
                                            <a class="page-link" href="?page=${i}&size=${bangGiaPage.size}">${i + 1}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${!bangGiaPage.last}">
                                        <li class="page-item">
                                            <a class="page-link"
                                                href="?page=${bangGiaPage.number + 1}&size=${bangGiaPage.size}">Sau</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav> -->
                        </div>

                    </div>

                </div>




            </body>

            </html>