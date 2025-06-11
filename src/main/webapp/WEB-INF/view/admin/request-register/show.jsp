<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Request Management Dashboard">
    <meta name="author" content="">
    <title>Quản lý yêu cầu - Danh sách yêu cầu</title>
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
                        <li class="breadcrumb-item active">Danh sách yêu cầu</li>
                    </ol>
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h3>Danh sách yêu cầu</h3>
                        <!-- Placeholder for Create Request (if needed in future) -->
                        <!-- <a href="#" class="btn btn-primary">+ Thêm yêu cầu</a> -->
                    </div>
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success" role="alert">
                            ${successMessage}
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <!-- Optional filter form -->
                        <!-- <form action="/admin/request" method="get" class="d-flex align-items-center">
                            <label for="statusFilter" class="form-label me-2">Lọc theo trạng thái:</label>
                            <select id="statusFilter" name="sortByStatus" class="form-control me-2" style="width: 200px;">
                                <option value="">-- Tất cả trạng thái --</option>
                                <option value="asc" ${currentSort == 'asc' ? 'selected' : ''}>Chờ duyệt (Tăng)</option>
                                <option value="desc" ${currentSort == 'desc' ? 'selected' : ''}>Chờ duyệt (Giảm)</option>
                            </select>
                            <button type="submit" class="btn btn-primary">Lọc</button>
                        </form> -->
                    </div>
                    <c:choose>
                        <c:when test="${requestPage.totalElements == 0}">
                            <div class="alert alert-info">Không có yêu cầu nào.</div>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-bordered table-hover">
                                <thead class="table-light">
                                    <tr>
                                        <th>Mã yêu cầu</th>
                                        <th>Mã sinh viên</th>
                                        <th>Biển số xe</th>
                                        <th>Ngày gửi yêu cầu</th>
                                        <th>Ngày bắt đầu</th>
                                        <th>Ngày kết thúc</th>
                                        <th>Trạng thái</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="request" items="${requestPage.content}">
                                        <tr>
                                            <td><c:out value="${request.requestId.trim()}"/></td>
                                            <td><c:out value="${request.student.maSV}"/></td>
                                            <td><c:out value="${request.vehicle.bienSoXe}"/></td>
                                            <td><c:out value="${request.requestDate}"/></td>
                                            <td><c:out value="${request.startDate}"/></td>
                                            <td><c:out value="${request.endDate}"/></td>
                                            <td><c:out value="${request.status}"/></td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/admin/request/approve/${request.requestId.trim()}" class="btn btn-success btn-sm">Duyệt</a>
                                                <a href="${pageContext.request.contextPath}/admin/request/view/${request.requestId.trim()}" class="btn btn-info btn-sm mx-1">Xem</a>
                                                <a href="#" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#rejectModal${request.requestId.trim()}">Từ chối</a>

                                                <!-- Reject Modal -->
                                                <div class="modal fade" id="rejectModal${request.requestId.trim()}" tabindex="-1" aria-labelledby="rejectModalLabel${request.requestId.trim()}" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="rejectModalLabel${request.requestId.trim()}">Từ chối yêu cầu</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form action="${pageContext.request.contextPath}/admin/request/reject" method="post">
                                                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                                    <input type="hidden" name="requestId" value="${request.requestId.trim()}"/>
                                                                    <div class="mb-3">
                                                                        <label for="note${request.requestId.trim()}" class="form-label">Lý do từ chối</label>
                                                                        <textarea class="form-control" id="note${request.requestId.trim()}" name="note" rows="3" required></textarea>
                                                                    </div>
                                                                    <button type="submit" class="btn btn-danger">Xác nhận từ chối</button>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        const table = document.querySelector('table');
        if (table) new simpleDatatables.DataTable(table);
    </script>
</body>
</html>