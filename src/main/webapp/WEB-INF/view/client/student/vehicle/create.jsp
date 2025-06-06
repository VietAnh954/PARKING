<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký xe mới - PTIT Parking System</title>
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
        .card-title {
            color: #1a2d4e;
            font-size: 1.5rem;
        }
        .form-label {
            color: #1a2d4e;
            font-size: 0.9rem;
            margin-bottom: 0.5rem;
        }
        .form-control, .form-select {
            border: 1px solid #dee2e6;
            border-radius: 8px;
            padding: 0.75rem 1rem;
            font-size: 0.9rem;
        }
        .form-control:focus, .form-select:focus {
            border-color: #0d6efd;
            box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
        }
        .btn-primary {
            background: #0d6efd;
            border: none;
            padding: 0.75rem 1.5rem;
            font-weight: 500;
        }
        .btn-primary:hover {
            background: #0b5ed7;
        }
        .btn-secondary {
            background: #6c757d;
            border: none;
            padding: 0.75rem 1.5rem;
            font-weight: 500;
        }
        .btn-secondary:hover {
            background: #5c636a;
        }
        .text-danger {
            font-size: 0.85rem;
            margin-top: 0.25rem;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/view/client/layout/header.jsp" %>
    
    <div class="content">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <div class="card">
                        <div class="card-body p-4">
                            <h3 class="card-title text-center mb-4">Đăng ký xe mới</h3>
                            
                            <form:form method="post" action="/student/vehicle/create" modelAttribute="newVehicle">
                                <div class="mb-3">
                                    <label class="form-label">Biển số xe</label>
                                    <form:input type="text" class="form-control" path="bienSoXe" required="required" placeholder="Nhập biển số xe"/>
                                    <form:errors path="bienSoXe" cssClass="text-danger" />
                                </div>
                                
                                <div class="mb-3">
                                    <label class="form-label">Loại xe</label>
                                    <form:select class="form-select" path="maLoaiXe">
                                        <c:forEach var="type" items="${vehicleTypes}">
                                            <form:option value="${type}" label="${type.tenLoaiXe}" />
                                        </c:forEach>
                                    </form:select>
                                </div>
                                
                                <div class="mb-3">
                                    <label class="form-label">Tên xe</label>
                                    <form:input type="text" class="form-control" path="tenXe" required="required" placeholder="Nhập tên xe"/>
                                </div>
                                
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                                    <a href="/student/vehicle/list" class="btn btn-secondary me-2">
                                        <i class="fas fa-times me-2"></i>Hủy
                                    </a>
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-save me-2"></i>Đăng ký
                                    </button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/view/client/layout/footer.jsp" %>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>