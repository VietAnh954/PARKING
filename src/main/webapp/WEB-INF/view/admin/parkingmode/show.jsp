<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Danh sách Hình Thức Gửi Xe</title>
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
                                <h3>Hình Thức Gửi Xe</h3>
                                <a href="/admin/parkingmode/create" class="btn btn-primary">Thêm Hình Thức Gửi Xe</a>
                            </div>

                            <hr />
                            <table class="table table-bordered table-hover">
                                <thead>

                                    <tr>
                                        <th>Mã Hình Thức</th>
                                        <th>Tên Hình Thức</th>
                                        <th>Action</th>
                                    </tr>

                                </thead>
                                <tbody>
                                    <c:forEach var="hinhThuc" items="${hinhThucList}">
                                        <tr>
                                            <td>${hinhThuc.maHinhThuc}</td>
                                            <td>${hinhThuc.tenHinhThuc}</td>

                                            <td>
                                                <a href="/admin/parkingmode/update/${hinhThuc.maHinhThuc}"
                                                    class="btn btn-warning mx-2">Update</a>
                                                <a href="/admin/parkingmode/delete/${hinhThuc.maHinhThuc}"
                                                    class="btn btn-danger">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>

                        </div>

                    </div>

                </div>




            </body>

            </html>