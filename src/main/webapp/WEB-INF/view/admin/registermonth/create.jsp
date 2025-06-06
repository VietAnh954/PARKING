<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Thêm đăng ký tháng mới</title>
            </head>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
            <link href="/css/styles.css" rel="stylesheet" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">

                                <h2 class="mt-4">Thêm đăng ký tháng mới</h2>
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-12 mx-auto">
                                            <form:form action="/admin/registermonth/create" method="post"
                                                modelAttribute="newRegisterMonth">
                                                <div class="form-group">
                                                    <label for="maDangKy">Mã đăng ký</label>
                                                    <form:input path="maDangKy" class="form-control"
                                                        required="required" />
                                                </div>
                                                <div class="form-group">
                                                    <label for="bienSoXe">Biển số xe</label>
                                                    <form:input path="bienSoXe" class="form-control"
                                                        required="required" />

                                                </div>
                                                <div class="form-group">
                                                    <label for="tGianDangKy">Thời gian đăng ký</label>
                                                    <form:input type="date" path="tGianDangKy" class="form-control"
                                                        required="required" />
                                                </div>
                                                <div class="form-group">
                                                    <label for="tGianHetHan">Thời gian hết hạn</label>
                                                    <form:input type="date" path="tGianHetHan" class="form-control"
                                                        required="required" />
                                                </div>
                                                <div class="form-group">
                                                    <label for="nvGhiNhan">Nhân viên ghi nhận</label>
                                                    <form:input path="nvGhiNhan" class="form-control"
                                                        required="required" />

                                                </div>
                                                <button type="submit" class="btn btn-primary">Lưu</button>
                                                <a href="<c:url value='/admin/registermonth'/>"
                                                    class="btn btn-secondary">Hủy</a>
                                            </form:form>

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
            </body>

            </html>