<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Delete Staff</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>

                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>Delete staff with MÃ NV = ${maNV}</h3>
                            </div>
                            <hr />
                            <div class="alert alert-danger" role="alert">
                                Are you sure you want to delete this staff? This action cannot be undone.
                            </div>
                            <form:form method="post" action="/admin/staff/delete" modelAttribute="newStaff">
                                <div class="mb-3" style=display:none>
                                    <label class="form-label">MÃ NV:</label>
                                    <form:input value="${maNV}" type="text" class="form-control" path="maNV" />
                                </div>
                                <button class="btn btn-danger">Confirm</button>
                            </form:form>
                        </div>

                    </div>

                </div>


            </body>

            </html>