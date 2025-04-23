<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Update Student</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>
                <div class=" container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Update student</h3>
                            <hr />
                            <form:form method="post" action="/admin/student/update" modelAttribute="newStudent">
                                <div class="mb-3 " style=display:none>
                                    <label class="form-label">Họ và tên:</label>
                                    <form:input type="text" class="form-control" path="hoTen" />
                                </div>
                                <div class="mb-3 ">
                                    <label class="form-label">Mã SV:</label>
                                    <form:input type="text" class="form-control" path="maSV" />
                                </div>
                                <div class="mb-3  ">
                                    <label class="form-label">Email:</label>
                                    <form:input type="email" class="form-control" path="email" disabled="true" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">SĐT:</label>
                                    <form:input type="text" class="form-control" path="sdt" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Địa chỉ:</label>
                                    <form:input type="text" class="form-control" path="diaChi" />
                                </div>
                                <div class="mb-3 ">
                                    <label class="form-label">Quê quán:</label>
                                    <form:input type="text" class="form-control" path="queQuan" />
                                </div>
                                <div class="mb-3 ">
                                    <label class="form-label">Ngày sinh:</label>
                                    <form:input type="text" class="form-control" path="ngaySinh" />
                                </div>


                                <button type="submit" class="btn btn-warning">Update</button>
                            </form:form>
                        </div>

                    </div>

                </div>
            </body>

            </html>