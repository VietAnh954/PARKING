<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý xe - PTIT Parking System</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"/>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f5f7fa;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .content {
            flex: 1;
            padding: 2rem 0;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            background: #fff;
        }
        .table {
            margin-bottom: 0;
        }
        .table th {
            background-color: #f8f9fa;
            font-weight: 600;
            border-bottom: 2px solid #dee2e6;
        }
        .table td {
            vertical-align: middle;
            padding: 1rem;
        }
        .btn-primary {
            background: #0d6efd;
            border: none;
            padding: 8px 20px;
            border-radius: 5px;
        }
        .btn-primary:hover {
            background: #0b5ed7;
        }
        .page-title {
            color: #1a2d4e;
            font-weight: 600;
            margin-bottom: 1.5rem;
        }
        .action-buttons {
            display: flex;
            gap: 0.5rem;
        }
        .btn-sm {
            padding: 0.4rem 0.8rem;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/view/client/layout/header.jsp" %>
    
    <div class="content">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-10">
                    <h2 class="page-title">Danh sách xe đã đăng ký</h2>
                    
                    <div class="card">
                        <div class="card-body p-4">
                            <div class="d-flex justify-content-end mb-4">
                                <a href="/student/vehicle/create" class="btn btn-primary">
                                    <i class="fas fa-plus me-2"></i>Đăng ký xe 
                </a>
                            </div>
                            
                <div class="table-responsive">
                                <table class="table">
                                    <thead>
                            <tr>
                                            <th style="width: 25%">Biển số xe</th>
                                            <th style="width: 30%">Tên xe</th>
                                            <th style="width: 25%">Loại xe</th>
                                            <th style="width: 20%">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="vehicle" items="${vehicleList}">
                                <tr>
                                    <td>${vehicle.bienSoXe}</td>
                                    <td>${vehicle.tenXe}</td>
                                    <td>${vehicle.maLoaiXe.tenLoaiXe}</td>
                                                <td>
                                                    <div class="action-buttons">
                                                        <a href="/student/vehicle/edit/${vehicle.bienSoXe}" 
                                                           class="btn btn-sm btn-primary"
                                                           title="Chỉnh sửa">
                                                            <i class="fas fa-edit"></i>
                                                        </a>
                                                        <button class="btn btn-sm btn-danger" 
                                                                onclick="deleteVehicle('${vehicle.bienSoXe}')"
                                                                title="Xóa">
                                                            <i class="fas fa-trash"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/view/client/layout/footer.jsp" %>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function deleteVehicle(bienSoXe) {
            if (confirm('Bạn có chắc chắn muốn xóa xe này?')) {
                window.location.href = '/student/vehicle/delete/' + bienSoXe;
            }
        }
    </script>
</body>
</html>