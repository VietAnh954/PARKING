<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<body>
    <%@ include file="/WEB-INF/view/client/layout/header.jsp" %>
    <div class="container py-5">
        <div class="card shadow-sm border-0">
            <div class="card-body p-5">
                <h3 class="card-title mb-4 text-primary fw-bold">Danh sách xe đã đăng ký</h3>
                <a href="/student/vehicle/create" class="btn btn-primary mb-4">
                    <i class="fas fa-plus me-2"></i> Đăng ký xe mới
                </a>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th>Biển số xe</th>
                                <th>Tên xe</th>
                                <th>Loại xe</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="vehicle" items="${vehicleList}">
                                <tr>
                                    <td>${vehicle.bienSoXe}</td>
                                    <td>${vehicle.tenXe}</td>
                                    <td>${vehicle.maLoaiXe.tenLoaiXe}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/view/client/layout/footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>