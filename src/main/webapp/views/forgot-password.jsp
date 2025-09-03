<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
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
				<!-- Form -->
				<form action="" method="post">
					<span>Nhập email của bạn:</span>
					<div class="mb-3 form-floating">
						<input id="email" name="email" type="email"
							class="form-control" placeholder="" required /> <label
							for="user-name">Email</label>
					</div>
					<c:if test="${alert != null}">
						<div class="alert alert-danger" role="alert">${alert}</div>
					</c:if>
					<c:if test="${info != null}">
						<div class="alert alert-primary" role="alert">${info}</div>
					</c:if>
					<div class="d-grid mb-2">
						<button type="submit" class="btn btn-lg btn-primary btn-block">Gửi</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script
		src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
		integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
		crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>