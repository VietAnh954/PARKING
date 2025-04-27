<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Account Page</title>
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
                                <h3>Table Accounts</h3>
                                <form action="/admin/account/filter" method="get">
                                    <select name="loaiTK">
                                        <option value="Quanly" <%="Quanly" .equals(request.getParameter("loaiTK"))
                                            ? "selected" : "" %>>Quản lý</option>
                                        <option value="BaoVe" <%="BaoVe" .equals(request.getParameter("loaiTK"))
                                            ? "selected" : "" %>>Bảo vệ</option>
                                        <option value="SinhVien" <%="SinhVien" .equals(request.getParameter("loaiTK"))
                                            ? "selected" : "" %>>Sinh viên</option>
                                        <option value="all" <%="all" .equals(request.getParameter("loaiTK")) ||
                                            request.getParameter("loaiTK")==null ? "selected" : "" %>>Tất cả</option>
                                    </select>
                                    <input type="submit" value="Lọc">
                                </form>
                                <a href="/admin/account/search">Tìm kiếm tài khoản</a>
                                <a href="/admin/account/create" class="btn btn-primary">Create new account</a>
                            </div>

                            <hr />
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>MÃ TK</th>
                                        <th>PASSWORD</th>
                                        <th>LOẠI TK</th>
                                        <th>MÃ NV</th>
                                        <th>MÃ SV</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="account" items="${accountList}">
                                        <tr>
                                            <td>${account.maTK}</td>
                                            <td>${account.password}</td>
                                            <td>${account.loaiTK}</td>
                                            <td>${account.maNV != null ? account.maNV.maNV : ''}</td>
                                            <td>${account.maSV != null ? account.maSV.maSV : ''}</td>
                                            <td>
                                                <a href="/admin/account/${account.maTK}"
                                                    class="btn btn-success">View</a>
                                                <a href="/admin/account/update/${account.maTK}"
                                                    class="btn btn-warning mx-2">Update</a>
                                                <a href="/admin/account/delete/${account.maTK}"
                                                    class="btn btn-danger">Delete</a>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>

                            <!-- PHÂN TRANG -->
                            <nav>
                                <ul class="pagination justify-content-center">
                                    <c:if test="${!accountPage.first}">
                                        <li class="page-item">
                                            <a class="page-link"
                                                href="?page=${accountPage.number - 1}&size=${accountPage.size}">Trước</a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="0" end="${accountPage.totalPages - 1}" var="i">
                                        <li class="page-item ${i == accountPage.number ? 'active' : ''}">
                                            <a class="page-link" href="?page=${i}&size=${accountPage.size}">${i + 1}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${!accountPage.last}">
                                        <li class="page-item">
                                            <a class="page-link"
                                                href="?page=${accountPage.number + 1}&size=${accountPage.size}">Sau</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>

                        </div>

                    </div>

                </div>
            </body>

            </html>