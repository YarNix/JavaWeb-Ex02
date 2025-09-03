<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div
		class="container d-flex align-items-center justify-content-center h-100 flex-column">
		<div class="w-50">
			<div class="shadow border rounded-bottom border-top-0 p-4 mb-4">
				<c:if test="${token == null}">
				<div class="alert alert-danger" role="alert">Đã có lỗi! Vui lòng dùng link hợp lệ</div>
				</c:if>
				<c:if test="${token != null}">
				<!-- Form -->
				<form action="" method="post">
					<span>Nhập mật khẩu mới của bạn:</span>
					<div class="mb-3 mt-1 form-floating">
						<input id="user-pass" name="user-pass" type="password"
							class="form-control" placeholder="" required /> <label
							for="user-pass">Mật khẩu</label>
						<input type="hidden" name="reset-token" value="${token}"/>
					</div>
					<c:if test="${alert != null}">
						<div class="alert alert-danger" role="alert">${alert}</div>
					</c:if>
					<c:if test="${info != null}">
						<div class="alert alert-primary" role="alert">${info}</div>
					</c:if>
					<div class="d-grid mb-2">
						<button type="submit" class="btn btn-lg btn-primary btn-block">Đặt lại</button>
					</div>
				</form>
				</c:if>
			</div>
		</div>
	</div>
	
	<script
		src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
		integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
		crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>