<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Create new vehicle</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Create new vehicle</h3>
                            <hr />

                            <form:form method="post" action="/admin/vehicle/create" modelAttribute="newVehicle">
                                <div class="mb-3 ">
                                    <label class="form-label">BIỂN SỐ XE:</label>
                                    <form:input type="text" class="form-control" path="bienSoXe" />
                                </div>
                                <div class="mb-3 ">
                                    <label class="form-label">MÃ LOẠI XE:</label>
                                    <!-- <form:input type="text" class="form-control" path="maLoaiXe" /> -->
                                    <form:select class="form-select" path="maLoaiXe">
                                        <form:option value="LX001" label="LX001 - Xe máy" />
                                        <form:option value="LX002" label="LX002 - Ô tô" />

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
                            </form:form>
                        </div>

                    </div>

                </div>
            </body>

            </html>