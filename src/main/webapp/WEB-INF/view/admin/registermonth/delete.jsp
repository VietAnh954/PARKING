<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Xóa đăng ký tháng</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            </head>

            <body>
                <div class="container mt-4">
                    <h2>Xóa đăng ký tháng</h2>
                    <div class="alert alert-warning">
                        <p>Bạn có chắc chắn muốn xóa đăng ký tháng này không?</p>
                        <p>Mã đăng ký: ${maDangKy}</p>
                    </div>
                    <form:form action="/admin/registermonth/delete" method="post" modelAttribute="newRegisterMonth">
                        <input type="hidden" name="maDangKy" value="${maDangKy}" />
                        <button type="submit" class="btn btn-danger">Xóa</button>
                        <a href="<c:url value='/admin/registermonth'/>" class="btn btn-secondary">Hủy</a>
                    </form:form>
                </div>
            </body>

            </html>