<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Đăng ký sắp hết hạn</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        </head>

        <body>
            <div class="container mt-4">
                <h2>Danh sách đăng ký sắp hết hạn (trước 5 ngày)</h2>
                <div class="mb-3">
                    <a href="<c:url value='/admin/registermonth/expiring-soon/send-all-emails'/>"
                        class="btn btn-primary">Gửi email cho tất cả</a>
                    <a href="<c:url value='/admin/registermonth'/>" class="btn btn-secondary">Quay lại</a>
                </div>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Mã đăng ký</th>
                            <th>Biển số xe</th>
                            <th>Ngày hết hạn</th>
                            <th>Chủ xe</th>
                            <th>Email</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${registerMonths}" var="registerMonth">
                            <tr>
                                <td>${registerMonth.maDangKy}</td>
                                <td>${registerMonth.bienSoXe.bienSoXe}</td>
                                <td>${registerMonth.tGianHetHan}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${registerMonth.bienSoXe.maNV != null}">
                                            ${registerMonth.bienSoXe.maNV.hoTen}
                                        </c:when>
                                        <c:when test="${registerMonth.bienSoXe.maSV != null}">
                                            ${registerMonth.bienSoXe.maSV.hoTen}
                                        </c:when>
                                        <c:otherwise>Không xác định</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${registerMonth.bienSoXe.maNV != null}">
                                            ${registerMonth.bienSoXe.maNV.email}
                                        </c:when>
                                        <c:when test="${registerMonth.bienSoXe.maSV != null}">
                                            ${registerMonth.bienSoXe.maSV.email}
                                        </c:when>
                                        <c:otherwise>Không xác định</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="<c:url value='/admin/registermonth/expiring-soon/send-email/${registerMonth.maDangKy}'/>"
                                        class="btn btn-info btn-sm">Gửi email</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        </body>

        </html>