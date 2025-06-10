<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Request View Dashboard">
    <meta name="author" content="">
    <title>Quản lý yêu cầu - Xem chi tiết</title>
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
                        <li class="breadcrumb-item active">Xem chi tiết</li>
                    </ol>
                    <div class="container mt-4">
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <h3>Chi tiết yêu cầu ${request.requestId.trim()}</h3>
                                <hr />
                                <c:if test="${not empty errorMessage}">
                                    <div class="alert alert-danger" role="alert">
                                        ${errorMessage}
                                    </div>
                                </c:if>
                                <table class="table table-bordered">
                                    <tbody>
                                        <tr>
                                            <th>Mã yêu cầu:</th>
                                            <td><c:out value="${request.requestId.trim()}"/></td>
                                        </tr>
                                        <tr>
                                            <th>Mã sinh viên:</th>
                                            <td><c:out value="${request.student.maSV}"/></td>
                                        </tr>
                                        <tr>
                                            <th>Biển số xe:</th>
                                            <td><c:out value="${request.vehicle.bienSoXe}"/></td>
                                        </tr>
                                        <tr>
                                            <th>Ngày gửi yêu cầu:</th>
                                            <td><c:out value="${request.requestDate}"/></td>
                                        </tr>
                                        <tr>
                                            <th>Ngày bắt đầu:</th>
                                            <td><c:out value="${request.startDate}"/></td>
                                        </tr>
                                        <tr>
                                            <th>Ngày kết thúc:</th>
                                            <td><c:out value="${request.endDate}"/></td>
                                        </tr>
                                        <tr>
                                            <th>Trạng thái:</th>
                                            <td><c:out value="${request.status}"/></td>
                                        </tr>
                                        <c:if test="${not empty request.processedBy}">
                                            <tr>
                                                <th>Được xử lý bởi:</th>
                                                <td><c:out value="${request.processedBy.maNV}"/></td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty request.note}">
                                            <tr>
                                                <th>Ghi chú:</th>
                                                <td><c:out value="${request.note}"/></td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                                <div class="d-grid gap-2 mt-3">
                                    <a href="${pageContext.request.contextPath}/admin/request" class="btn btn-secondary">Quay lại</a>
                                </div>
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
</body>
</html>