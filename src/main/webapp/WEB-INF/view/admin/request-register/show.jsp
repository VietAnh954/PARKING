<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Quản Lý Yêu Cầu - Danh Sách Yêu Cầu</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp" />
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Quản Lý Yêu Cầu</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                        <li class="breadcrumb-item active">Danh Sách Yêu Cầu</li>
                    </ol>
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h3>Danh Sách Yêu Cầu</h3>
                    </div>
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success" role="alert">
                            <c:out value="${successMessage}"/>
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <!-- Optional filter form (commented out as per original) -->
                    </div>
                    <c:choose>
                        <c:when test="${requestPage.totalElements == 0}">
                            <div class="alert alert-info">Không có yêu cầu nào.</div>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-bordered table-hover">
                                <thead class="table-light">
                                    <tr>
                                        <th>Mã Yêu Cầu</th>
                                        <th>Mã Sinh Viên</th>
                                        <th>Biển Số Xe</th>
                                        <th>Ngày Gửi</th>
                                        <th>Ngày Bắt Đầu</th>
                                        <th>Ngày Kết Thúc</th>
                                        <th>Giá (VNĐ)</th>
                                        <th>Trạng Thái</th>
                                        <th>Hành Động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="request" items="${requestPage.content}">
                                        <tr>
                                            <td><c:out value="${request.maYeuCau.trim()}"/></td>
                                            <td><c:out value="${request.student.maSV}"/></td>
                                            <td><c:out value="${request.vehicle.bienSoXe}"/></td>
                                            <td><fmt:formatDate value="${request.ngayGuiYeuCauAsDate}" pattern="dd/MM/yyyy"/></td>
                                            <td><fmt:formatDate value="${request.ngayBatDauAsDate}" pattern="dd/MM/yyyy"/></td>
                                            <td><fmt:formatDate value="${request.ngayHetHanAsDate}" pattern="dd/MM/yyyy"/></td>
                                            <td><fmt:formatNumber value="${request.gia != null ? request.gia : 0}" pattern="#,###"/></td>
                                            <td><c:out value="${request.trangThai}"/></td>
                                            <td>
                                                <c:if test="${request.trangThai != 'Đã duyệt'}">
                                                    <a href="${pageContext.request.contextPath}/admin/request/approve/${request.maYeuCau.trim()}" class="btn btn-success btn-sm">Duyệt</a>
                                                    <a href="#" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#rejectModal${request.maYeuCau.trim()}">Từ Chối</a>
                                                    <div class="modal fade" id="rejectModal${request.maYeuCau.trim()}" tabindex="-1" aria-labelledby="rejectModalLabel${request.maYeuCau.trim()}" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="rejectModalLabel${request.maYeuCau.trim()}">Từ Chối Yêu Cầu</h5>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form action="${pageContext.request.contextPath}/admin/request/reject" method="post">
                                                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                                        <input type="hidden" name="maYeuCau" value="${request.maYeuCau.trim()}"/>
                                                                        <div class="mb-3">
                                                                            <label for="ghiChu${request.maYeuCau.trim()}" class="form-label">Lý Do Từ Chối</label>
                                                                            <textarea class="form-control" id="ghiChu${request.maYeuCau.trim()}" name="ghiChu" rows="3" required></textarea>
                                                                            <div class="invalid-feedback">Vui lòng nhập lý do từ chối.</div>
                                                                        </div>
                                                                        <button type="submit" class="btn btn-danger">Xác Nhận Từ Chối</button>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <a href="${pageContext.request.contextPath}/admin/request/view/${request.maYeuCau.trim()}" class="btn btn-info btn-sm mx-1">Xem</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <nav>
                                <ul class="pagination justify-content-center">
                                    <c:if test="${!requestPage.first}">
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${requestPage.number - 1}&size=${requestPage.size}&sortByStatus=${currentSort}">Trước</a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="0" end="${requestPage.totalPages - 1}" var="i">
                                        <li class="page-item ${i == requestPage.number ? 'active' : ''}">
                                            <a class="page-link" href="?page=${i}&size=${requestPage.size}&sortByStatus=${currentSort}">${i + 1}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${!requestPage.last}">
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${requestPage.number + 1}&size=${requestPage.size}&sortByStatus=${currentSort}">Sau</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </c:otherwise>
                    </c:choose>
                </div>
            </main>
            <jsp:include page="../layout/footer.jsp" />
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        const table = document.querySelector('table');
        if (table) new simpleDatatables.DataTable(table);
    </script>
</body>
</html>