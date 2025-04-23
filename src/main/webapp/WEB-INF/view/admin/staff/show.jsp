<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Staff Page</title>
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
                                <h3>Table Staffs</h3>
                                <a href="/admin/staff/create" class="btn btn-primary">Create a staff</a>
                            </div>

                            <hr />
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>MÃ NV</th>
                                        <th>HỌ TÊN</th>
                                        <th>SDT</th>
                                        <th>EMAIL</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="staff" items="${staffshow}">
                                        <tr>
                                            <td>${staff.maNV}</td>
                                            <td>${staff.hoTen}</td>
                                            <td>${staff.sdt}</td>
                                            <td>${staff.email}</td>
                                            <td>
                                                <a href="/admin/staff/${staff.maNV}" class="btn btn-success">View</a>
                                                <a href="/admin/staff/update/${staff.maNV}"
                                                    class="btn btn-warning mx-2">Update</a>
                                                <a href="/admin/staff/delete/${staff.maNV}"
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