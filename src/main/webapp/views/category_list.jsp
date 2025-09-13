<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories</title>
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
</head>
<body>
	<div
		class="container d-flex align-items-center justify-content-center h-100">
		<div class="h-75 w-100 p-3">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<div class="input-group w-50">
					<input type="text" class="form-control" placeholder="Tìm category">
					<button class="btn btn-outline-secondary" type="button">Tìm</button>
				</div>

				<c:if test="${isUserAdmin}">
					<a href="category/add" class="btn btn-success"> <span
						class="material-symbols-outlined align-middle">add</span> <span
						class="align-middle"> Thêm Category </span>
					</a>
				</c:if>
			</div>
			<div class="table-responsive w-100 h-100">
				<table class="table table-striped table-hover align-middle">
					<thead class="table-primary">
						<tr>
							<th scope="col">STT</th>
							<th scope="col">Hình ảnh</th>
							<th scope="col">Tên danh mục</th>
							<c:if test="${isUserAdmin}">
								<th scope="col">Action</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="category" items="${categories}" varStatus="loop">
							<tr>
								<td>${loop.index + 1}</td>
								<td><c:choose>
										<c:when test="${not empty category.icon}">
											<img
												src="${pageContext.request.contextPath}/images/${category.icon}"
												alt="${category.categoryName}" class="img-thumbnail"
												style="max-width: 50px; height: auto;">
										</c:when>
										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/images/not-found.jpg"
												alt="No image" class="img-thumbnail"
												style="max-width: 50px; height: auto;">
										</c:otherwise>
									</c:choose></td>
								<td>${category.categoryName}</td>
								<c:if test="${isUserAdmin}">
									<td>
										<div class="d-flex gap-2">
											<a href="category/edit?id=${category.id}"
												class="btn btn-sm btn-outline-primary">
												<span class="material-symbols-outlined">
													edit
												</span> 
											</a> <a href="category/delete?id=${category.id}"
												class="btn btn-sm btn-outline-danger">
												<span
													class="material-symbols-outlined align-middle">delete</span> 
											</a>
										</div>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script
		src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
		integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
		crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>