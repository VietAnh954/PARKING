<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Quản lý đăng ký tháng</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            </head>

            <body>
                <div class="container mt-4">
                    <h2>Danh sách đăng ký tháng</h2>

                    <!-- Form tìm kiếm -->
                    <div class="row mb-3 align-items-center">
                        <div class="col-lg-8 col-md-12 mb-2 mb-lg-0 d-flex align-items-center">
                            <form action="<c:url value='/admin/registermonth/search'/>" method="get"
                                class="d-flex flex-grow-1">
                                <input type="text" name="tuKhoa" class="form-control mr-2"
                                    placeholder="Nhập biển số xe..." value="${param.tuKhoa}">
                                <button type="submit" class="btn btn-primary mr-3 text-nowrap">Tìm kiếm</button>
                            </form>
                        </div>
                        <div class="col-lg-4 col-md-12 text-lg-end text-md-start mt-2 mt-lg-0">
                            <a href="<c:url value='/admin/registermonth/create'/>" class="btn btn-primary">Thêm mới</a>
                        </div>
                    </div>

                    <!-- Thông báo kết quả tìm kiếm -->
                    <c:if test="${not empty param.tuKhoa}">
                        <div class="alert alert-info">
                            Kết quả tìm kiếm cho: <strong>${param.tuKhoa}</strong>
                        </div>
                    </c:if>
                    <c:if test="${not empty filter && filter == 'active'}">
                        <div class="alert alert-success">
                            Đang hiển thị các đăng ký còn hiệu lực
                        </div>
                    </c:if>
                    <c:if test="${not empty filter && filter == 'expired'}">
                        <div class="alert alert-warning">
                            Đang hiển thị các đăng ký đã hết hạn
                        </div>
                    </c:if>

                    <!-- Hiển thị thông báo thành công/lỗi -->
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success">${successMessage}</div>
                    </c:if>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">${errorMessage}</div>
                    </c:if>

                    <!-- Dòng mới cho 4 nút lọc -->
                    <div class="row mb-3">
                        <div class="col-12 text-center">
                            <div class="btn-group" role="group">
                                <a href="<c:url value='/admin/registermonth/active'/>" class="btn btn-success mx-1">Đăng
                                    ký còn hiệu lực</a>
                                <a href="<c:url value='/admin/registermonth/expired'/>"
                                    class="btn btn-warning mx-1">Đăng ký đã hết hạn</a>
                                <a href="<c:url value='/admin/registermonth/expiring-soon'/>"
                                    class="btn btn-info mx-1">Đăng ký sắp hết hạn</a>
                                <a href="<c:url value='/admin/registermonth'/>" class="btn btn-secondary mx-1">Tất
                                    cả</a>
                            </div>
                        </div>
                    </div>

                    <!-- Bảng danh sách -->
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Mã đăng ký</th>
                                <th>Biển số xe</th>
                                <th>Thời gian đăng ký</th>
                                <th>Thời gian hết hạn</th>
                                <th>Nhân viên ghi nhận</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${registerMonthList}" var="registerMonth">
                                <tr>
                                    <td>${registerMonth.maDangKy}</td>
                                    <td>${registerMonth.bienSoXe.bienSoXe}</td>
                                    <td>${registerMonth.tGianDangKy}</td>
                                    <td>${registerMonth.tGianHetHan}</td>
                                    <td>${registerMonth.nvGhiNhan.maNV}</td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a href="<c:url value='/admin/registermonth/update/${registerMonth.maDangKy}'/>"
                                                class="btn btn-warning btn-sm">Sửa</a>
                                            <a href="<c:url value='/admin/registermonth/delete/${registerMonth.maDangKy}'/>"
                                                class="btn btn-danger btn-sm">Xóa</a>
                                            <a href="<c:url value='/admin/registermonth/extend/${registerMonth.maDangKy}'/>"
                                                class="btn btn-info btn-sm">Gia hạn</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!-- Phân trang -->
                    <!-- <c:if test="${registerMonthPage.totalPages > 0}"> -->
                    <nav>
                        <ul class="pagination justify-content-center">
                            <c:if test="${!registerMonthPage.first}">
                                <li class="page-item">
                                    <a class="page-link"
                                        href="?page=${registerMonthPage.number - 1}&size=${registerMonthPage.size}">Trước</a>
                                </li>
                            </c:if>
                            <c:forEach begin="0" end="${registerMonthPage.totalPages - 1}" var="i">
                                <li class="page-item ${i == registerMonthPage.number ? 'active' : ''}">
                                    <a class="page-link" href="?page=${i}&size=${registerMonthPage.size}">${i +
                                        1}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${!registerMonthPage.last}">
                                <li class="page-item">
                                    <a class="page-link"
                                        href="?page=${registerMonthPage.number + 1}&size=${registerMonthPage.size}">Sau</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                    <!-- </c:if> -->
                </div>

                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            </body>

            </html>