<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Tạo Đăng Ký Tháng Mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp" />
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Tạo Đăng Ký Tháng Mới</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                        <li class="breadcrumb-item"><a href="/admin/request">Danh Sách Yêu Cầu</a></li>
                        <li class="breadcrumb-item active">Tạo Đăng Ký Mới</li>
                    </ol>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${errorMessage}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success" role="alert">
                            <c:out value="${successMessage}"/>
                        </div>
                    </c:if>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-file-alt me-1"></i>
                            Thông Tin Đăng Ký
                        </div>
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/admin/request/create" method="post">
                                <sec:csrfInput/>
                                <div class="mb-3">
                                    <label for="bienSoXe" class="form-label">Biển Số Xe</label>
                                    <input type="text" class="form-control" id="bienSoXe" name="bienSoXe" required>
                                </div>
                                <div class="mb-3">
                                    <label for="soThang" class="form-label">Số Tháng Đăng Ký</label>
                                    <select class="form-control" id="soThang" name="soThang" required>
                                        <option value="1">1 Tháng</option>
                                        <option value="6">3 Tháng</option>
                                        <option value="12">6 Tháng</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="ngayBatDau" class="form-label">Ngày Bắt Đầu</label>
                                    <input type="date" class="form-control" id="ngayBatDau" name="ngayBatDau" required>
                                </div>
                                <div class="mb-3">
                                    <label for="ghiChu" class="form-label">Ghi Chú</label>
                                    <textarea class="form-control" id="ghiChu" name="ghiChu" rows="3"></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">Tạo Đăng Ký</button>
                                <a href="${pageContext.request.contextPath}/admin/request" class="btn btn-secondary">Hủy</a>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
            <jsp:include page="../layout/footer.jsp" />
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/js/scripts.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>