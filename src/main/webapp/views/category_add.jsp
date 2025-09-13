<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
</head>
<body>
	<div
		class="container d-flex align-items-center justify-content-center h-100">
		<div class="h-75 w-100 p-3">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<form method="post" class="card p-3 shadow-sm">
					<div class="mb-3">
						<label for="categoryName" class="form-label">Category Name</label>
						<input type="text" id="categoryName" name="categoryName"
							class="form-control" required>
					</div>

					<div class="mb-3">
						<label for="icon" class="form-label">Icon</label> <input
							type="text" id="icon" name="icon" class="form-control"
							placeholder="e.g. shoes.png">
					</div>

					<div class="mb-3">
						<label for="status" class="form-label">Status</label> <select
							id="status" name="status" class="form-select">
							<option value="1" selected>Active</option>
							<option value="0">Inactive</option>
						</select>
					</div>

					<div class="d-flex justify-content-end">
						<a href="${pageContext.request.contextPath}/category"
							class="btn btn-secondary me-2">Cancel</a>
						<button type="submit" class="btn btn-success">Save
							Category</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>