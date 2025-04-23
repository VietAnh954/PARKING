<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>VehicleType Page</title>
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
                                <h3>Table VehicleTypes</h3>
                                <a href="/admin/vehicletype/create" class="btn btn-primary">Create new vehicletype</a>
                            </div>

                            <hr />
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>MÃ LOẠI XE</th>
                                        <th>TÊN LOẠI XE</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="vehicletype" items="${vehicletypeshow}">
                                        <tr>
                                            <td>${vehicletype.maLoaiXe}</td>
                                            <td>${vehicletype.tenLoaiXe}</td>
                                            <td>
                                                <a href="/admin/vehicletype/${vehicletype.maLoaiXe}"
                                                    class="btn btn-success">View</a>
                                                <a href="/admin/vehicletype/update/${vehivehicletypecle.maLoaiXe}"
                                                    class="btn btn-warning mx-2">Update</a>
                                                <a href="/admin/vehicletype/delete/${vehicletype.maLoaiXe}"
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