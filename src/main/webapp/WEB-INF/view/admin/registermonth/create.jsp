<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Thêm đăng ký tháng mới</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            </head>

            <body>
                <div class="container mt-4">
                    <h2>Thêm đăng ký tháng mới</h2>
                    <form:form action="/admin/registermonth/create" method="post" modelAttribute="newRegisterMonth">
                        <div class="form-group">
                            <label for="maDangKy">Mã đăng ký</label>
                            <form:input path="maDangKy" class="form-control" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="bienSoXe">Biển số xe</label>
                            <form:input path="bienSoXe" class="form-control" required="required" />

                        </div>
                        <div class="form-group">
                            <label for="tGianDangKy">Thời gian đăng ký</label>
                            <form:input type="date" path="tGianDangKy" class="form-control" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="tGianHetHan">Thời gian hết hạn</label>
                            <form:input type="date" path="tGianHetHan" class="form-control" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="nvGhiNhan">Nhân viên ghi nhận</label>
                            <form:input path="nvGhiNhan" class="form-control" required="required" />

                        </div>
                        <button type="submit" class="btn btn-primary">Lưu</button>
                        <a href="<c:url value='/admin/registermonth'/>" class="btn btn-secondary">Hủy</a>
                    </form:form>
                </div>
            </body>

            </html>