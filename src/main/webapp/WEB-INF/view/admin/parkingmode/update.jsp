<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Cập nhật Hình Thức Gửi Xe</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            </head>

            <body>

                <div class=" container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h1>Cập nhật Hình Thức Gửi Xe</h1>
                            <hr />
                            <form:form method="post" action="/admin/parkingmode/update" modelAttribute="newHinhThuc">

                                <div class="mb-3 ">
                                    <label class="form-label"> Mã hình thức:</label>
                                    <form:input type="text" class="form-control" path="maHinhThuc" />
                                </div>
                                <div class="mb-3 ">
                                    <label class="form-label"> Tên hình thức:</label>
                                    <form:input type="text" class="form-control" path="tenHinhThuc" />
                                </div>




                                <button type="submit" class="btn btn-warning">Update</button>
                            </form:form>
                        </div>

                    </div>

                </div>




            </body>

            </html>