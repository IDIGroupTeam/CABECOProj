<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<script type="text/javascript">
	function ConfirmDelete() {
	  return confirm("Bạn có chắc chắn muốn xóa không?");
	}
</script>
<title>Danh sách các sản phẩm đã bán trong tháng</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #E8E3E3;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #E8E3E3;
}
</style>
</head>
<body>
	<div class="table-responsive">
		<a href="${url}/salary/listSalarysByGroup?group=${group}&month=${monthR}&year=${yearR}"><button
			class="btn btn-lg btn-primary btn-sm">Thông tin
			lương nhân viên</button></a>
		<a href="${url}/salary/prepareProductSold"><button
			class="btn btn-lg btn-primary btn-sm">Chọn tháng/nhóm lao động khác</button></a>		
		<a href="${url}/salary/insertProductSold?month=${month}&group=${group}"><button
				class="btn btn-primary btn-sm">Thêm sản phẩm đã bán tháng này</button></a> <br />
		<br />
		<table class="table table-bordered">
			<tr>
				<th>Mã sản phẩm</th>
				<th>Tên sản phẩm</th>
				<th>Số lượng</th>
				<th>Đơn giá</th>
				<th>Hệ số %</th>
				<th>Thành tiền</th>
				<th>Xóa</th>
				<th>Ghi chú</th>
			</tr>
			<c:forEach var="productSold" items="${productSold}">
				<tr>
					<td>${productSold.code}</td>
					<td>${productSold.name}</td>
					<td>${productSold.amount}</td>
					<td><fmt:formatNumber value="${productSold.price}" type="number"/></td>
					<td>${productSold.scale} %</td>
					<td><fmt:formatNumber value="${productSold.moneyIncome}" type="number"/></td>
					<td><a style="color: red;"
						href="deleteProductSold?month=${productSold.month}&group=${group}&productCode=${productSold.code}&price=${productSold.price}&scale=${productSold.scale}" Onclick="return ConfirmDelete()">Xóa</a>
					</td>
					<td>${productSold.comment}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="right"><b>Tổng tiền thu được trong tháng</b></td>
				<td colspan="3"><b><fmt:formatNumber value="${totalMoneyIncome}" type="number"/></b></td>
			</tr>
		</table>	
		
		<c:if test="${not empty message1}">
			<div class="alert ">${message1}</div>
		</c:if>	
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
	</div>
</body>
</html>