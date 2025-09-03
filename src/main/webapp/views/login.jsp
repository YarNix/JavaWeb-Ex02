<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
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
		class="container d-flex align-items-center justify-content-center h-100">
		<div class="w-50">
			<div class="shadow border rounded-bottom border-top-0 p-4 mb-4">
				<!-- Form -->
				<form action="" method="post">
					<svg class="mx-auto d-block fcl" xmlns="http://www.w3.org/2000/svg"
						height="48" viewBox="0 -960 960 960" width="48">
						<path
							d="M479.385-166.157 212.079-312.464v-216.921L81.541-600l397.844-216.921L878.458-600v285.459h-45.383v-258.921l-86.384 44.077v216.921L479.385-166.157Zm0-268.382L782.845-600l-303.46-163.23L177.155-600l302.23 165.461Zm0 216.921 221.922-121.999v-162.231L479.385-383.15 257.462-503.463v163.846l221.923 121.999ZM480-434.539Zm-.615 60.538Zm0 0Z" /></svg>
					<div class="mt-3 mb-1 form-floating">
						<input id="user-name" name="user-name" type="text"
							class="form-control" placeholder="" required /> <label
							for="user-name">Tên đăng nhập</label>
					</div>
					<div class="mb-4 form-floating">
						<input id="user-pass" name="user-pass" type="password"
							class="form-control" placeholder="" required /> <label
							for="user-pass">Mật khẩu</label>
					</div>
					<c:if test="${alert != null}">
						<div class="alert alert-danger" role="alert">${alert}</div>
					</c:if>
					<div class="d-grid mb-2">
						<button type="submit" class="btn btn-lg btn-primary btn-block">Đăng
							nhập</button>
					</div>
					<div class="d-grid mb-1">
						<div class="row g-2">
							<div class="col-12 col-lg-6">
								<a href="${pageContext.request.contextPath}/register"
									class="btn btn-lg btn-secondary btn-block w-100">Đăng ký</a>
							</div>
							<div class="col-12 col-lg-6">
								<a href="${pageContext.request.contextPath}/forgot-password"
									class="btn btn-lg btn-secondary btn-block w-100">Quên mật khẩu</a>
							</div>
						</div>
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