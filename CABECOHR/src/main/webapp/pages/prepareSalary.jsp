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
<title>Tùy chọn bộ phận tính lương</title>
</head>
<body>
	<a href="${url}/salary/"><button
			class="btn btn-lg btn-primary btn-sm">Thông tin	lương nhân viên</button></a>
	<form:form action="listSalarysByDepartment"
		modelAttribute="salaryReportForm" method="POST">		
		<br />
		<table class="table table-bordered">			
			<tr>
				<td bgcolor="#FAFAFA">Chọn bộ phận cần tính lương:</td>
				<form:hidden path="department"/>
				<td><form:select path="department"
						class="form-control animated">
						<%-- <form:option value="0" label="Cả năm" /> --%>
						<form:options items="${departmentMap}" var="department" />
					</form:select></td>				
			</tr>			
		</table>
		<input class="btn btn-lg btn-primary btn-sm" type="submit"
			value="Xem thông tin lương" name="Xem thông tin lương" />
	</form:form>
</body>
</html>