<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ - PTIT Parking System</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="../view/admin/layout/header.jsp" />
    <div class="container mt-5">
        <h1>Chào mừng đến với hệ thống quản lý bãi gửi xe</h1>
        <p>Xin chào, <c:out value="${sessionScope.fullName}" default="Khách"/>!</p>
        <a href="/admin" class="btn btn-primary">Quản lý sinh viên</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
</body>
</html>