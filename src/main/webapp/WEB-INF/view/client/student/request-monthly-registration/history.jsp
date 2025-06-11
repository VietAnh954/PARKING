<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch Sử Yêu Cầu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #4361ee;
            --secondary-color: #3f37c9;
            --accent-color: #4895ef;
            --light-color: #f8f9fa;
            --dark-color: #212529;
            --success-color: #4cc9f0;
            --danger-color: #f72585;
            --warning-color: #f8961e;
        }
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f5f7ff;
            color: #2b2d42;
            line-height: 1.6;
            padding-top: 80px;
            margin: 0;
        }
        .main-content {
            width: 1500px;
            height: 800px;
            margin: 0 auto;
            padding: 2rem 0;
            display: flex;
            align-items: flex-start;
            justify-content: center;
        }
        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
            overflow: hidden;
            width: 100%;
            max-width: 1400px;
        }
        .card-header {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            padding: 1.5rem;
            border-bottom: none;
        }
        .card-body {
            padding: 2.5rem;
            overflow-y: auto;
            max-height: calc(800px - 150px);
        }
        h1 {
            font-weight: 700;
            color: var(--primary-color);
            margin-bottom: 1.5rem;
            font-size: 1.8rem;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #dee2e6;
        }
        th {
            background-color: #f8f9fa;
            font-weight: 600;
        }
        tr:hover {
            background-color: #e9ecef;
        }
        .btn-edit {
            background-color: var(--accent-color);
            border: none;
            border-radius: 8px;
            padding: 0.5rem 1rem;
            color: white;
            text-decoration: none;
            transition: all 0.3s ease;
        }
        .btn-edit:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(72, 149, 239, 0.3);
        }
        @media (max-width: 1500px) {
            .main-content {
                width: 100%;
                height: auto;
                padding: 1rem;
            }
            .card-body {
                max-height: none;
            }
        }
        @media (max-width: 768px) {
            .card-body {
                padding: 1.5rem;
            }
            h1 {
                font-size: 1.5rem;
            }
            th, td {
                padding: 0.75rem;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="../../layout/header.jsp"/>
    <div class="main-content">
        <div class="card shadow-sm">
            <div class="card-header text-center">
                <h1 class="mb-0"><i class="fas fa-history me-2"></i>Lịch Sử Yêu Cầu</h1>
            </div>
            <div class="card-body">
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="fas fa-check-circle me-2"></i>
                        <c:out value="${successMessage}"/>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="fas fa-exclamation-circle me-2"></i>
                        <c:out value="${errorMessage}"/>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Mã Yêu Cầu</th>
                            <th>Biển Số Xe</th>
                            <th>Ngày Bắt Đầu</th>
                            <th>Ngày Hết Hạn</th>
                            <th>Giá (VNĐ)</th>
                            <th>Trạng Thái</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="request" items="${requests}">
                            <tr>
                                <td><c:out value="${request.maYeuCau.trim()}"/></td>
                                <td><c:out value="${request.vehicle.bienSoXe}"/></td>
                                <td><fmt:formatDate value="${request.ngayBatDauAsDate}" pattern="dd/MM/yyyy"/></td>
                                <td><fmt:formatDate value="${request.ngayHetHanAsDate}" pattern="dd/MM/yyyy"/></td>
                                <td><fmt:formatNumber value="${request.gia != null ? request.gia : 0}" pattern="#,###"/></td>
                                <td><c:out value="${request.trangThai}"/></td>
                                <td>
                                    <c:if test="${request.trangThai == 'Chờ duyệt'}">
                                        <a href="${pageContext.request.contextPath}/student/request-monthly-registration/edit?maYeuCau=${request.maYeuCau.trim()}" class="btn-edit">Chỉnh Sửa</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty requests}">
                            <tr>
                                <td colspan="7" class="text-center">Không tìm thấy yêu cầu nào.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <jsp:include page="../../layout/footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>