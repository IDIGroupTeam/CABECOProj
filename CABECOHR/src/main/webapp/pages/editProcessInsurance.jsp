<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CABECO - Sửa thông tin quá trình đóng BH</title>
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
<script src="${url}/public/js/bootstrap-datetimepicker.min.js"></script>
<script src="${url}/public/js/bootstrap-datetimepicker.vi.js"></script>

<script type="text/javascript">
	var $j = jQuery.noConflict();
	$j(function() {
		//alert($j.fn.jquery);
		moneyConvert("salarySocicalInsu");
		/* 		// Wait for window load
		 $(window).load(function(){
		 // Animate loader off screen
		 //$("button").click(function(){
		 $(".loader").fadeOut("slow");
		 //});	
		 }); */

		/* 		$j('#comment').editable({
		 type : 'text',
		 pk : 1,
		 name : 'comment',
		 url : '${url}/timekeeping/ghichu',
		 title : 'thêm ghi chú'
		 }); */

		$j(".datetime").datetimepicker({
			//language : 'vi',
			format : 'dd/mm/yyyy',
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			pickerPosition : "bottom-left"
		});

	//$j(function() {
		
	});
</script>

</head>
<body>
<a href="${pageContext.request.contextPath}/insurance/listProcessInsurance?socicalInsuNo=${socicalInsuNo}&employeeId=${employeeId}"><button class="btn btn-primary btn-sm">Quay	lại danh sách</button></a>
<br/><br/>
	<form:form modelAttribute="pInsuranceForm" method="POST"
		action="updateProcessInsurance?employeeId=${employeeId}">
			<table class="table">
			<form:hidden path="socicalInsuNo"/>
			<form:hidden path="fromDate"/>
			<c:if test="${not empty validate}">
				<div class="alert alert-warning">${validate}</div>
			</c:if>
				<tbody>
					<tr>
						<td colspan="4" nowrap="nowrap" bgcolor="#C4C4C4">Thông tin	bảo hiểm xã hội</td>
					</tr>
					<tr>
						<td bgcolor="#EEEEEE">Nhân viên:</td>
						<td ><c:out value="${name}" /></td>

						<td bgcolor="#EEEEEE">Số sổ BHXH:</td>
						<td>${pInsuranceForm.socicalInsuNo}</td>
					</tr>
					<tr>
						<td bgcolor="#EEEEEE">Mức lương đóng BH(*):</td>
						<td><form:input path="salarySocicalInsu" size="12"
								maxlength="12" required="required" class="form-control animated"/></td>
						<td bgcolor="#EEEEEE">Phụ cấp lương:</td>
						<td><form:input path="subSalary" class="form-control animated" size="12" maxlength="12"/></td> 						
					</tr>
					<tr>
						<td bgcolor="#EEEEEE">Hệ số lương:</td>
						<td><form:input path="constSalary" size="8"
								class="form-control animated" maxlength="8" /></td>
						<td bgcolor="#EEEEEE">Bậc lương:</td>
						<td><form:input path="salaryLevel" size="8"
								class="form-control animated" maxlength="8" /></td>	
					</tr>
					<tr>
						<td colspan="4" nowrap="nowrap" bgcolor="#C4C4C4">Thời gian	đóng</td>
					</tr>
					<tr>
						<td bgcolor="#EEEEEE">Từ ngày(*):</td>
						<td>${pInsuranceForm.fDate}</td>
						<td bgcolor="#EEEEEE">Đến ngày:</td>
						<td>
							<div class="input-group date datetime">
								<form:input path="toDate" class="form-control"
									placeholder="dd/mm/yyyy" autocomplete="off" />
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
						<%-- <form:input path="tDate" type="date" class="form-control animated"/> --%>
						</td>
					</tr>
					<tr>
						<td bgcolor="#EEEEEE">Ghi chú:</td>
						<td colspan="3"><form:textarea path="comment" cols="64" class="form-control animated"/></td>					
					</tr>
				</tbody>
			</table>
			<input type="submit" value="Lưu" class="btn btn-primary btn-sm"/>
	</form:form>
</body>
</html>