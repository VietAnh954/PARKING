<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Create new account</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Create new account</h3>
                            <hr />

                            <form:form method="post" action="/admin/account/create" modelAttribute="newAccount">
                                <div class="mb-3 ">
                                    <label class="form-label">Mã TK:</label>
                                    <form:input type="text" class="form-control" path="maTK" />
                                </div>
                                <div class="mb-3 ">
                                    <label class="form-label">Password:</label>
                                    <form:input type="text" class="form-control" path="password" />
                                </div>
                                <div class="mb-3  ">
                                    <label class="form-label">Loại TK:</label>
                                    <form:select class="form-select" path="loaiTK">
                                        <form:option value="Quanly" label="Quản lý" />
                                        <form:option value="BaoVe" label="Bảo vệ" />
                                        <form:option value="SinhVien" label="Sinh viên" />
                                        <form:option value="GiaoVien" label="Giáo Viên" />
                                    </form:select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">MÃ NV:</label>
                                    <form:input type="text" class="form-control" path="maNV" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Mã SV:</label>
                                    <form:input type="text" class="form-control" path="maSV" />
                                </div>
                                <button type="submit" class="btn btn-primary">Create</button>
                                <a href="/admin/account" class="btn btn-secondary">Cancel</a>
                            </form:form>
                        </div>

                    </div>

                </div>
            </body>

            </html>