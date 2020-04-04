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
<title>Tùy chọn nhóm lao động/tháng tính lương</title>
</head>
<body>
<%-- 	<a href="${url}/salary/"><button
			class="btn btn-lg btn-primary btn-sm">Thông tin	lương nhân viên</button></a> --%>
	<a href="${pageContext.request.contextPath}/salary/prepareSummarySalary">
		<button	class="btn btn-primary btn-sm">Quay lại thống kê lương nhân viên </button></a>		
	<form:form action="listSalarysByGroup"
		modelAttribute="salaryReportForm" method="POST">		
		<br />
		<table class="table table-bordered">			
			<tr>
				<td bgcolor="#FAFAFA">Chọn nhóm lao động cần tính lương:</td>
				<%-- <form:hidden path="department"/> --%>
				<td><form:select path="group"
						class="form-control animated">
						<%-- <form:option value="0" label="Cả năm" /> --%>
						<form:options items="${workGroupMap}" var="group" />
					</form:select></td>				
			</tr>	
			<tr>
				<td bgcolor="#FAFAFA">Chọn tháng:</td>
				<td><form:select path="monthReport"
						class="form-control animated">						
						<form:option value="1" label="Tháng 1" />
						<form:option value="2" label="Tháng 2" />
						<form:option value="3" label="Tháng 3" />
						<form:option value="4" label="Tháng 4" />
						<form:option value="5" label="Tháng 5" />
						<form:option value="6" label="Tháng 6" />
						<form:option value="7" label="Tháng 7" />
						<form:option value="8" label="Tháng 8" />
						<form:option value="9" label="Tháng 9" />
						<form:option value="10" label="Tháng 10" />
						<form:option value="11" label="Tháng 11" />
						<form:option value="12" label="Tháng 12" />
					</form:select></td>
			</tr>	
			<jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
			<tr>
				<td bgcolor="#FAFAFA" width="30%">Chọn năm:</td>
				<td><form:select path="yearReport"
						class="form-control animated">
						<form:option value="${salaryReportForm.yearReport + 1}" label="${salaryReportForm.yearReport + 1}" />
						<form:option value="${salaryReportForm.yearReport}" label="${salaryReportForm.yearReport}" />
						<form:option value="${salaryReportForm.yearReport -1 }" label="${salaryReportForm.yearReport - 1 }" />
					</form:select></td>
			</tr>				
		</table>
		<input class="btn btn-lg btn-primary btn-sm" type="submit"
			value="Xem thông tin lương" name="Xem thông tin lương" />
	</form:form>
</body>
</html>