<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<!-- Include Twitter Bootstrap and jQuery: -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<!-- Include the plugin's CSS and JS: -->
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />

<!-- Initialize the plugin: -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript">
</script>
<title>Tùy chọn tháng cần tính sản lượng, cho tính lương sản phẩm</title>
</head>
<body>
	<a href="${url}/salary/"><button
			class="btn btn-lg btn-primary btn-sm">Thông tin	lương nhân viên</button></a>
	<form:form action="listProductSold"
		modelAttribute="salaryReportForm" method="POST">		
		<br />
		<table class="table table-bordered">
			<jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
			<tr>
				<td bgcolor="#FAFAFA">Bộ phận:</td>
				<td><form:select path="department"
						class="form-control animated">
						<%-- <form:option value="0" label="Cả năm" /> --%>
						<form:options items="${departmentMap}" var="department" />
					</form:select></td>
				<td bgcolor="#FAFAFA">Chọn năm:</td>
				<td><form:select path="yearReport"
						class="form-control animated">
						<c:forEach begin="0" end="3" varStatus="loop">
							<c:set var="currentYear" value="${year - loop.index}" />
							<option value="${currentYear}"
								${form.yearReport == currentYear ? 'selected="selected"' : ''}>${currentYear}</option>
						</c:forEach>
					</form:select></td>
				<td bgcolor="#FAFAFA">Chọn tháng:</td>
				<td><form:select path="monthReport"
						class="form-control animated">
						<%-- <form:option value="0" label="Cả năm" /> --%>
						<form:option value="01" label="Tháng 1" />
						<form:option value="02" label="Tháng 2" />
						<form:option value="03" label="Tháng 3" />
						<form:option value="04" label="Tháng 4" />
						<form:option value="05" label="Tháng 5" />
						<form:option value="06" label="Tháng 6" />
						<form:option value="07" label="Tháng 7" />
						<form:option value="08" label="Tháng 8" />
						<form:option value="09" label="Tháng 9" />
						<form:option value="10" label="Tháng 10" />
						<form:option value="11" label="Tháng 11" />
						<form:option value="12" label="Tháng 12" />
					</form:select></td>
			</tr>			
		</table>
		<input class="btn btn-lg btn-primary btn-sm" type="submit"
			value="Xem sản lượng" name="Xem sản lượng" />
	</form:form>
</body>
</html>