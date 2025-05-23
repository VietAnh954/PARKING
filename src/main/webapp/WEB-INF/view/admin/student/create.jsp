
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Create Student - Student Management Dashboard">
    <meta name="author" content="">
    <title>Create Student - Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    <!-- Header -->
    <jsp:include page="../layout/header.jsp"/>

    <div id="layoutSidenav">
        <!-- Sidebar -->
        <jsp:include page="../layout/sidebar.jsp"/>

        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Student Management</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                        <li class="breadcrumb-item"><a href="/admin/student">Students</a></li>
                        <li class="breadcrumb-item active">Create Student</li>
                    </ol>

                    <div class="container mt-4">
                        <h3>Thêm sinh viên mới</h3>
                        <div class="card">
                            <div class="card-body">
                                <form:form action="/admin/student/create" method="post" modelAttribute="student" class="row g-3">
                                    <div class="col-md-6">
                                        <label for="maSV" class="form-label">Mã SV</label>
                                        <form:input path="maSV" class="form-control" id="maSV" required="true"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="hoTen" class="form-label">Họ tên</label>
                                        <form:input path="hoTen" class="form-control" id="hoTen" required="true"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="diaChi" class="form-label">Địa chỉ</label>
                                        <form:input path="diaChi" class="form-control" id="diaChi" required="true"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="ngaySinh" class="form-label">Ngày sinh</label>
                                        <form:input path="ngaySinh" type="date" class="form-control" id="ngaySinh"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="queQuan" class="form-label">Quê quán</label>
                                        <form:input path="queQuan" class="form-control" id="queQuan"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="sdt" class="form-label">SĐT</label>
                                        <form:input path="sdt" class="form-control" id="sdt" required="true"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="email" class="form-label">Email</label>
                                        <form:input path="email" class="form-control" id="email"/>
                                    </div>
                                    <div class="col-12">
                                        <button type="submit" class="btn btn-primary">Lưu</button>
                                        <a href="/admin/student" class="btn btn-secondary">Hủy</a>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </main>

                <jsp:include page="../layout/footer.jsp" />
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>
