<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Request Approval Dashboard">
    <meta name="author" content="">
    <title>Quản lý yêu cầu - Duyệt yêu cầu</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp" />
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Quản lý yêu cầu</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                        <li class="breadcrumb-item active">Duyệt yêu cầu</li>
                    </ol>
                    <div class="container mt-4">
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <h3>Duyệt yêu cầu cho mã yêu cầu ${request.requestId.trim()}</h3>
                                <hr />
                                <c:if test="${not empty errorMessage}">
                                    <div class="alert alert-danger" role="alert">
                                        ${errorMessage}
                                    </div>
                                </c:if>
                                <form method="post" action="${pageContext.request.contextPath}/admin/request/approve" id="approveForm">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <input type="hidden" name="requestId" value="${request.requestId.trim()}" />
                                    <div class="mb-3">
                                        <label class="form-label">Mã yêu cầu:</label>
                                        <input type="text" class="form-control" value="${request.requestId.trim()}" readonly />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Mã sinh viên:</label>
                                        <input type="text" class="form-control" value="${request.student.maSV}" readonly />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Biển số xe:</label>
                                        <input type="text" class="form-control" value="${request.vehicle.bienSoXe}" readonly />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ngày bắt đầu:</label>
                                        <input type="text" class="form-control" value="${request.startDate}" readonly />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ngày kết thúc:</label>
                                        <input type="text" class="form-control" value="${request.endDate}" readonly />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ghi chú (Tùy chọn):</label>
                                        <textarea class="form-control" name="note" rows="3"></textarea>
                                    </div>
                                    <div class="d-grid gap-2">
                                        <button type="submit" class="btn btn-primary">Duyệt</button>
                                        <a href="${pageContext.request.contextPath}/admin/request/reject?requestId=${request.requestId.trim()}" class="btn btn-danger">Từ chối</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <jsp:include page="../layout/footer.jsp" />
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        document.getElementById('approveForm').addEventListener('submit', function(event) {
            // No strict validation needed since note is optional
        });
    </script>
</body>
</html>