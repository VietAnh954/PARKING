<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
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
                                <h3>Table students</h3>
                                <a href="/admin/student/create" class="btn btn-primary">Create a student</a>
                            </div>

                            <hr />
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>MÃ SV</th>
                                        <th>HỌ TÊN</th>
                                        <th>SDT</th>
                                        <th>EMAIL</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="student" items="${studentshow}">
                                        <tr>
                                            <td>${student.maSV}</td>
                                            <td>${student.hoTen}</td>
                                            <td>${student.sdt}</td>
                                            <td>${student.email}</td>
                                            <td>
                                                <a href="/admin/student/${student.maSV}"
                                                    class="btn btn-success">View</a>
                                                <a href="/admin/student/update/${student.maSV}"
                                                    class="btn btn-warning mx-2">Update</a>
                                                <a href="/admin/student/delete/${student.maSV}"
                                                    class="btn btn-danger">Delete</a>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>

                    </div>

                </div>
            </body>

            </html>