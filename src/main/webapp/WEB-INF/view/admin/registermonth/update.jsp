<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Cập nhật đăng ký tháng</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            </head>

            <body>
                <div class="container mt-4">
                    <h2>Cập nhật đăng ký tháng</h2>
                    <form:form action="/admin/registermonth/update" method="post" modelAttribute="newRegisterMonth">
                        <div class="form-group">
                            <label for="maDangKy">Mã đăng ký</label>
                            <form:input path="maDangKy" class="form-control" readonly="true" />
                        </div>
                        <div class="form-group">
                            <label for="bienSoXe">Biển số xe</label>
                            <form:select path="bienSoXe" class="form-control" required="required">
                                <form:options items="${vehicles}" itemValue="bienSoXe" itemLabel="bienSoXe" />
                            </form:select>
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
                            <form:select path="nvGhiNhan" class="form-control" required="required">
                                <form:options items="${staffs}" itemValue="maNV" itemLabel="hoTen" />
                            </form:select>
                        </div>
                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                        <a href="<c:url value='/admin/registermonth'/>" class="btn btn-secondary">Hủy</a>
                    </form:form>
                </div>
            </body>

            </html>