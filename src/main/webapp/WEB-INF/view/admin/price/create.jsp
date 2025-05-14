<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Create new price table</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h1>Thêm Bảng Giá</h1>

                            <form:form action="/admin/price/create" method="post" modelAttribute="newBangGia">
                                <div class="mb-3 ">
                                    <label class="form-label"> Mã bảng giá:</label>
                                    <form:input type="text" class="form-control" path="maBangGia" />
                                </div>
                                <div class="mb-3 ">
                                    <label class="form-label">MÃ LOẠI XE:</label>

                                    <form:select class="form-select" path="maLoaiXe">
                                        <form:option value="LX001" label="Xe máy" />
                                        <form:option value="LX002" label="Ô tô" />

                                    </form:select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label"> Hình Thức Gửi:</label>
                                    <form:select class="form-select" path="maHinhThuc">
                                        <form:option value="HT001" label="Gửi vãng lai" />
                                        <form:option value="HT002" label="Gửi tháng" />

                                    </form:select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label"> Giá: </label>
                                    <form:input type="text" class="form-control" path="gia" />
                                </div>


                                <button type="submit" class="btn btn-primary">Create</button>
                            </form:form>

                        </div>

                    </div>

                </div>
            </body>

            </html>