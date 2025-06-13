<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Vehicle Page</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <link href="/css/styles.css" rel="stylesheet" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">

                                <h2 class="mt-4"> Đăng ký xe</h2>
                                <div class="container mt-5">
                                    <div class="row">
                                        <div class=" col-12 mx-auto">
                                            <div class="d-flex justify-content-between">
                                                <h3>Danh sách xe </h3>
                                                <a href="/admin/vehicle/create" class="btn btn-primary">Thêm xe mới
                                                </a>
                                                <a href="/admin/vehicleType" class="btn btn-secondary">Xem loại
                                                    xe</a>
                                            </div>

                                            <hr />
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>BIỂN SỐ XE</th>
                                                        <th>MÃ LOẠI XE</th>
                                                        <th>MÃ NV</th>
                                                        <th>MÃ SV</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="vehicle" items="${vehicleList}">
                                                        <tr>
                                                            <td>${vehicle.bienSoXe}</td>
                                                            <td>
                                                                <c:if test="${vehicle.maLoaiXe != null}">
                                                                    ${vehicle.maLoaiXe.maLoaiXe}
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${vehicle.maNV != null}">
                                                                    ${vehicle.maNV.maNV}
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${vehicle.maSV != null}">
                                                                    ${vehicle.maSV.maSV}
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <!-- <a href="/admin/vehicle/${vehicle.bienSoXe}"
                                                                    class="btn btn-success">View</a> -->
                                                                <a href="/admin/vehicle/update/${vehicle.bienSoXe}"
                                                                    class="btn btn-warning mx-2">Update</a>
                                                                <a href="/admin/vehicle/delete/${vehicle.bienSoXe}"
                                                                    class="btn btn-danger">Delete</a>

                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                            <!-- PHÂN TRANG -->
                                            <nav>
                                                <ul class="pagination justify-content-center">

                                                    <c:if test="${!vehiclePage.first && vehiclePage.totalPages > 0}">

                                                        <li class="page-item">
                                                            <a class="page-link"
                                                                href="?page=${vehiclePage.number - 1}&size=${vehiclePage.size}">Trước</a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${vehiclePage.totalPages > 0}">
                                                        <c:forEach begin="0" end="${vehiclePage.totalPages - 1}"
                                                            var="i">
                                                            <li
                                                                class="page-item ${i == vehiclePage.number ? 'active' : ''}">
                                                                <a class="page-link"
                                                                    href="?page=${i}&size=${vehiclePage.size}">${i
                                                                    + 1}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${!vehiclePage.last && vehiclePage.totalPages > 0}">

                                                        <li class="page-item">
                                                            <a class="page-link"
                                                                href="?page=${vehiclePage.number + 1}&size=${vehiclePage.size}">Sau</a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>

            </body>

            </html>