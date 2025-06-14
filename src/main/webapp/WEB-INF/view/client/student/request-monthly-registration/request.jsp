<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Gửi Yêu Cầu Đăng Ký Tháng</title>
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
           align-items: center;
           justify-content: center;
       }
       .card {
           border: none;
           border-radius: 12px;
           box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
           overflow: hidden;
           transition: transform 0.3s ease, box-shadow 0.3s ease;
           width: 100%;
           max-width: 1000px;
       }
       .card:hover {
           transform: translateY(-5px);
           box-shadow: 0 15px 35px rgba(0, 0, 0, 0.12);
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
       .form-label {
           font-weight: 600;
           color: #495057;
           margin-bottom: 0.5rem;
       }
       .form-control, .form-select {
           border: 1px solid #ced4da;
           border-radius: 8px;
           padding: 0.75rem 1rem;
           transition: border-color 0.3s ease, box-shadow 0.3s ease;
       }
       .form-control:focus, .form-select:focus {
           border-color: var(--accent-color);
           box-shadow: 0 0 0 0.25rem rgba(67, 97, 238, 0.25);
       }
       .btn-primary {
           background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
           border: none;
           border-radius: 8px;
           padding: 0.75rem 1.5rem;
           font-weight: 600;
           letter-spacing: 0.5px;
           transition: all 0.3s ease;
       }
       .btn-primary:hover {
           transform: translateY(-2px);
           box-shadow: 0 5px 15px rgba(67, 97, 238, 0.3);
       }
       .alert {
           border-radius: 8px;
           padding: 1rem;
       }
       .invalid-feedback {
           color: var(--danger-color);
           font-size: 0.85rem;
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
       }
       ::-webkit-scrollbar {
           width: 8px;
           height: 8px;
       }
       ::-webkit-scrollbar-track {
           background: #f1f1f1;
           border-radius: 10px;
       }
       ::-webkit-scrollbar-thumb {
           background: var(--accent-color);
           border-radius: 10px;
       }
       ::-webkit-scrollbar-thumb:hover {
           background: var(--secondary-color);
       }
   </style>
</head>
<body>
   <jsp:include page="../../layout/header.jsp"/>
   <div class="main-content">
       <div class="card shadow-sm">
           <div class="card-header text-center">
               <h1 class="mb-0"><i class="fas fa-calendar-alt me-2"></i>Gửi Yêu Cầu Đăng Ký Tháng</h1>
           </div>
           <div class="card-body">
               <c:if test="${not empty message}">
                   <div class="alert alert-success alert-dismissible fade show" role="alert">
                       <i class="fas fa-check-circle me-2"></i>
                       <c:out value="${message}"/>
                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                   </div>
               </c:if>
               <c:if test="${not empty error}">
                   <div class="alert alert-danger alert-dismissible fade show" role="alert">
                       <i class="fas fa-exclamation-circle me-2"></i>
                       <c:out value="${error}"/>
                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                   </div>
               </c:if>
               <form action="${pageContext.request.contextPath}/student/request-monthly-registration" method="post" class="needs-validation" novalidate>
                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                   <div class="mb-4">
                       <label for="maSV" class="form-label">
                           <i class="fas fa-id-card me-2"></i>Mã Sinh Viên
                       </label>
                       <input type="text" id="maSV" value="${maSV}" class="form-control" readonly>
                   </div>
                   <div class="mb-4">
                       <label for="bienSoXe" class="form-label">
                           <i class="fas fa-motorcycle me-2"></i>Biển Số Xe
                       </label>
                       <select id="bienSoXe" name="bienSoXe" class="form-select" required onchange="updatePrice()">
                           <option value="" disabled selected>Chọn xe</option>
                           <c:forEach var="vehicle" items="${vehicles}">
                               <option value="${vehicle.bienSoXe}" data-maLoaiXe="${vehicle.maLoaiXe.maLoaiXe}">
                                   <c:out value="${vehicle.bienSoXe}"/>
                               </option>
                           </c:forEach>
                       </select>
                       <div class="invalid-feedback">Vui lòng chọn xe.</div>
                       <c:if test="${empty vehicles}">
                           <div class="mt-2 text-danger">
                               <i class="fas fa-info-circle me-2"></i>Chưa có xe nào được đăng ký. Vui lòng thêm xe trước.
                           </div>
                       </c:if>
                   </div>
                   <div class="mb-4">
                       <label for="soThang" class="form-label">
                           <i class="fas fa-calendar-check me-2"></i>Số Tháng
                       </label>
                       <select id="soThang" name="soThang" class="form-select" required onchange="updatePrice()">
                           <option value="" disabled selected>Chọn số tháng</option>
                           <option value="1">1 Tháng</option>
                           <option value="3">3 Tháng</option>
                           <option value="6">6 Tháng</option>
                       </select>
                       <div class="invalid-feedback">Vui lòng chọn số tháng.</div>
                   </div>
                   <div class="mb-4">
                       <label for="gia" class="form-label">
                           <i class="fas fa-money-bill me-2"></i>Giá Tiền (VNĐ)
                       </label>
                       <input type="text" id="gia" class="form-control" readonly value="Đang tính...">
                   </div>
                   <div class="mb-4">
                       <label for="ngayBatDau" class="form-label">
                           <i class="far fa-calendar-alt me-2"></i>Ngày Bắt Đầu
                       </label>
                       <input type="date" id="ngayBatDau" name="ngayBatDau" class="form-control" required
                              min="<%= java.time.LocalDate.now().plusDays(1) %>">
                       <div class="invalid-feedback">Vui lòng chọn ngày trong tương lai.</div>
                   </div>
                   <div class="d-grid mt-4">
                       <button type="submit" class="btn btn-primary btn-lg">
                           <i class="fas fa-paper-plane me-2"></i>Gửi Yêu Cầu
                       </button>
                   </div>
               </form>
           </div>
       </div>
   </div>
   <jsp:include page="../../layout/footer.jsp"/>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
   <script>
       const priceMap = {
           'LX001': 120000, // Xe Máy, HT002
           'LX002': 400000  // Ô tô, HT002
       };

       function updatePrice() {
           const vehicleSelect = document.getElementById('bienSoXe');
           const soThangSelect = document.getElementById('soThang');
           const giaInput = document.getElementById('gia');
           const selectedVehicle = vehicleSelect.options[vehicleSelect.selectedIndex];
           const soThang = soThangSelect.value;

           if (!selectedVehicle || !selectedVehicle.getAttribute('data-maLoaiXe') || !soThang) {
               console.log('Vehicle or months not selected');
               giaInput.value = 'Đang tính...';
               return;
           }

           const maLoaiXe = selectedVehicle.getAttribute('data-maLoaiXe').trim();
           const pricePerMonth = priceMap[maLoaiXe] || 0;
           const totalPrice = pricePerMonth * parseInt(soThang);

           if (pricePerMonth === 0) {
               console.log('No price found for maLoaiXe:', maLoaiXe);
               giaInput.value = 'Đang tính...';
           } else {
               giaInput.value = totalPrice.toLocaleString('vi-VN');
           }
       }

       // Initialize price on page load
       document.addEventListener('DOMContentLoaded', updatePrice);

       (function () {
           'use strict';
           const forms = document.querySelectorAll('.needs-validation');
           Array.from(forms).forEach(form => {
               form.addEventListener('submit', event => {
                   if (!form.checkValidity()) {
                       event.preventDefault();
                       event.stopPropagation();
                   }
                   form.classList.add('was-validated');
               }, false);
           });
       })();

       document.getElementById('ngayBatDau').addEventListener('change', function () {
           const selectedDate = new Date(this.value);
           const today = new Date();
           today.setHours(0, 0, 0, 0);
           if (selectedDate <= today) {
               this.setCustomValidity('Vui lòng chọn ngày trong tương lai.');
               this.reportValidity();
               this.value = '';
           } else {
               this.setCustomValidity('');
           }
       });
   </script>
</body>
</html>