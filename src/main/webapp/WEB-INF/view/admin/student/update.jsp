
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Update Student - Student Management Dashboard">
    <meta name="author" content="">
    <title>Update Student - Admin Dashboard</title>
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
                        <li class="breadcrumb-item active">Update Student</li>
                    </ol>

                    <div class="container mt-4">
                        <h3>Cập nhật sinh viên</h3>
                        <div class="card">
                            <div class="card-body">
                                <form:form method="post" action="/admin/student/update" modelAttribute="student" class="row g-3">
                                    <form:hidden path="maSV"/>
                                    <div class="mb-3">
                                        <label for="hoTen" class="form-label">Họ và tên:</label>
                                        <form:input type="text" class="form-control" path="hoTen" required="true"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email:</label>
                                        <form:input type="email" class="form-control" path="email"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="sdt" class="form-label">SĐT:</label>
                                        <form:input type="text" class="form-control" path="sdt" required="true"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="diaChi" class="form-label">Địa chỉ:</label>
                                        <form:input type="text" class="form-control" path="diaChi" required="true"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="queQuan" class="form-label">Quê quán:</label>
                                        <form:input type="text" class="form-control" path="queQuan"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="ngaySinh" class="form-label">Ngày sinh:</label>
                                        <form:input type="date" class="form-control" path="ngaySinh"/>
                                    </div>
                                    <div class="col-12">
                                        <button type="submit" class="btn btn-warning">Cập nhật</button>
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