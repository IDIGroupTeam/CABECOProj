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
		//moneyConvert("salary");
	});
</script>
</head>
<body>
	
	<a href="${url}/salary/listProductSold?month=${productSoldForm.month}&department=${productSoldForm.department}"><button class="btn btn-primary btn-sm">Danh sách sản phẩm đã bán trong tháng</button></a>
	<br/><br/>
	<form:form modelAttribute="productSoldForm" method="POST"
		action="updateProductSold">		
			<table class="table table-bordered">				
				<tbody>
					<form:hidden path="month"/>
					<form:hidden path="department"/>
					<tr>
						<td colspan="4" nowrap="nowrap" bgcolor="#E6E6E6">Thông tin
							sản phẩm</td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Chọn sản phẩm:</td>
						<td><form:select path="code" class="form-control animated">
								<form:options items="${productMap}" />
							</form:select></td>		
						<td bgcolor="#FAFAFA">Số lương:(*)</td>
						<td><form:input path="amount" maxlength="12" type="number" min="1" step="1" 
								required="required" class="form-control animated"/></td>					
					</tr>
					<tr>		
						<td bgcolor="#FAFAFA">Đơn giá:(*) </td>
						<td><form:input path="price" maxlength="12" type="number" min="0.1" step="0.01"
								required="required" class="form-control animated"/></td>
						<td bgcolor="#FAFAFA">Hệ số %:(*)</td>
						<td><form:input path="scale" maxlength="9" type="number" default="100"
								required="required" class="form-control animated"/></td>											
					</tr>				
					<tr>
						<td bgcolor="#FAFAFA">Ghi chú:</td>
						<td colspan="3"><form:textarea path="comment" cols="64" class="form-control animated"/></td>
					</tr>
				</tbody>
			</table>
			<input class="btn btn-primary btn-sm" type="submit" value="Lưu" />
	</form:form>
</body>
</html>