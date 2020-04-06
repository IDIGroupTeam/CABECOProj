<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CABECO - Quản lý tiền lương</title>
<style>
.error-message {
	color: red;
	font-size: 90%;
	font-style: italic;
}
</style>
<!-- Initialize the plugin: -->
<script src="${url}/public/js/jquery.min.js"></script>
<script src="${url}/public/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		/* moneyConvert("value"); */
	});
</script>
</head>
<body>
	<a href="${url}/salary/salaryRe"><button class="btn btn-primary btn-sm">Quay lại danh sách điều tiết</button></a>
	<br/><br/>
	<form:form modelAttribute="salaryForm" method="POST"
		action="updateSalaryRe">		
			<table class="table table-bordered">
				<tbody>
					<form:hidden path="month"/>
					<form:hidden path="year"/>
					<form:hidden path="group"/>
					<tr>
						<td colspan="4" nowrap="nowrap" bgcolor="#E6E6E6">Thông tin	điều tiết</td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Tháng:(*)</td>
						<td><form:select path="month" class="form-control animated" disabled="true">
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
							</form:select>
						</td>					
						<td bgcolor="#FAFAFA">Năm:(*)</td>
						<td><form:select path="year" class="form-control animated" disabled="true">
								<form:option value="${salaryForm.year + 1}" label="${salaryForm.year + 1}" />
								<form:option value="${salaryForm.year}" label="${salaryForm.year}" />
								<form:option value="${salaryForm.year -1 }" label="${salaryForm.year - 1 }" />
							</form:select>
						</td>		
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Nhóm lao động:(*)</td>
						<td>
							<form:select path="group" class="form-control animated" disabled="true">
								<form:options items="${workGroupMap}" var="group" />
							</form:select>
						</td>								
						<td bgcolor="#FAFAFA">Điều tiết/công:(*)</td>
						<td><form:input path="value" maxlength="8" type="number" min='0' size="8" max='99999999'
								class="form-control animated"/></td>						
					</tr>	
					<tr>
						<td bgcolor="#FAFAFA">Ghi chú:</td>
						<td colspan="3"><form:textarea path="des" cols="64" class="form-control animated"/></td>
					</tr>
				</tbody>
			</table>
			<input class="btn btn-primary btn-sm" type="submit" value="Lưu" />
	</form:form>	
</body>
</html>