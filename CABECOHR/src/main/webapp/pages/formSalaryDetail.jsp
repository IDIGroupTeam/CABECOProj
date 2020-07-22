<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Cabeco - Quản lý tiền lương</title>
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
		moneyConvert("bounus");
		moneyConvert("advancePayed");
		moneyConvert("subsidize");
		moneyConvert("taxPersonal");
		moneyConvert("subInsurance");
		moneyConvert("other");
		moneyConvert("arrears");
		moneyConvert("subLunch");
		moneyConvert("subPhone");
		moneyConvert("subGas");
		moneyConvert("overWork");
		moneyConvert("subSkill");		
	});
</script>	
</head>
<body>
	<a href="${url}/salary/listSalarysByGroup?group=${salaryDetail.group}&month=${salaryDetail.month}&year=${salaryDetail.year}">
	<button	class="btn btn-lg btn-primary btn-sm">Quay lại danh sách lương tháng</button></a>
	<br/><br/>
	<form:form modelAttribute="salaryDetail" method="POST"
		action="insertSalaryDetail" enctype="multipart/form-data">
		<input class="btn btn-lg btn-primary btn-sm" type="submit" value="Lưu" name="Lưu" /><br/><br/>
		<div class="table table-bordered">
<%-- 			<c:if test="${not empty workDayDefine}">
				<div class="alert alert-warning">
			     	${workDayDefine}
			    </div>
		    </c:if> --%>
			<table class="table">
				<tbody>
 					<form:hidden path="employeeId"/>
					<form:hidden path="salary"/>
					<form:hidden path="fullName"/>
					<form:hidden path="month"/> 
					<form:hidden path="phoneNo"/>
					<form:hidden path="group"/>					
					<form:hidden path="department"/>
					<form:hidden path="jobTitle"/>
					<form:hidden path="year"/> 
					<form:hidden path="bankNo"/>
					<form:hidden path="bankName"/>
					<form:hidden path="bankBranch"/>
					<form:hidden path="salaryInsurance"/>
					<form:hidden path="salaryPerHour"/> 					
					<tr>
						<td colspan="6" nowrap="nowrap" bgcolor="#E6E6E6">Thông tin nhân viên</td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Họ tên:</td>
						<td>${salaryDetail.fullName}</td>
						
						<td bgcolor="#FAFAFA">Số điện thoại:</td>
						<td>${salaryDetail.phoneNo}</td>						
	
						<td bgcolor="#FAFAFA">Chức vụ:</td>
						<td>${salaryDetail.jobTitle}</td>					
								
					</tr>
					<tr>
						<td colspan="6" nowrap="nowrap" bgcolor="#E6E6E6">Thông tin tài khoản ngân hàng</td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Số TK:</td>
						<td>${salaryDetail.bankNo}</td>
						
						<td bgcolor="#FAFAFA">Tên NH:</td>
						<td>${salaryDetail.bankName}</td>
						
						<td bgcolor="#FAFAFA">Chi nhánh:</td>
						<td>${salaryDetail.bankBranch}</td>
					</tr>
					<tr>
						<td colspan="6" nowrap="nowrap" bgcolor="#E6E6E6">Thông tin lương chi tiết</td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA" nowrap="nowrap">Lương BHXH:</td>
						<c:if test="${not empty salaryDetail.salaryInsurance}">
							<td><fmt:formatNumber value="${salaryDetail.salaryInsurance}" type="number"/></td> 
							<td bgcolor="#FAFAFA" nowrap="nowrap">Phụ cấp BHXH:</td>
							<td><form:input path="subInsurance" class="form-control animated"  maxlength="12" /></td> 
						</c:if>

						<c:if test="${empty salaryDetail.salaryInsurance}">
							<td><i>Không tham gia BHXH</i></td> 
						</c:if>	
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Lương:(lương cơ bản x hệ số)</td>
						<td><fmt:formatNumber value="${salaryDetail.basicSalary}" type="number"/></td>						
											
						<td bgcolor="#FAFAFA" nowrap="nowrap" title="Bắt buộc phải > hoăc = 0. Mặc định là 100%">Hệ số hoàn thành/chuyên cần (%):</td>
						<td><form:input path="workComplete" class="form-control bfh-number" min="0" max="999" value="100" type="number" size="4" required="required"/></td>													
					</tr>
					<tr>						
						<td bgcolor="#FAFAFA" nowrap="nowrap" title="Chỉ nhập số ngày nếu tháng đó không làm đủ cả tháng">Số ngày công:</td>
						<td><form:input path="workedDay" class="form-control bfh-number" min="0.001" max="31" step="0.001" type="number" required="required" title="Chỉ nhập số ngày nếu tháng đó không làm đủ cả tháng. Và bắt buộc phải định nghĩa ngày công chuẩn trước để việc tính toán được chính sác"/></td>
						<td bgcolor="#FAFAFA">Ngày công bảo trì:</td>
						<td><form:input path="maintainDay" class="form-control animated" min="0" max="31" step="0.001" type="number" /></td>						
						<td colspan="3">Lương điều tiết: (điều tiết * ngày công): ${salaryDetail.rSalary}</td>
						
					</tr>				 
					<tr>
						<td colspan="3" nowrap="nowrap" bgcolor="#E6E6E6">Các khoản thưởng/phụ cấp/tăng ca/lễ/tết</td>
						<td colspan="3" nowrap="nowrap" bgcolor="#E6E6E6">Các khoản giảm trừ vào lương</td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Thưởng:</td>
						<td colspan="2"><form:input path="bounus" class="form-control animated"  maxlength="12" /></td>
												
						<td bgcolor="#FAFAFA">Tạm ứng:</td>
						<td colspan="2"><form:input path="advancePayed" class="form-control animated" maxlength="12" /></td>						
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Trợ cấp độc hại/trách nhiệm:</td>
						<td colspan="2"><form:input path="subsidize" class="form-control animated"  maxlength="12" /></td>
																
						<td bgcolor="#FAFAFA">Thuế TNCN:</td>
						<td colspan="2"><form:input path="taxPersonal" class="form-control animated"  maxlength="12" /></td>						
					</tr>				
					<tr>
						<td bgcolor="#FAFAFA">Phụ cấp tiền ăn trưa:</td>
						<td colspan="2"><form:input path="subLunch" class="form-control animated"  maxlength="12" /></td>
																
						<td bgcolor="#FAFAFA" nowrap="nowrap">Truy thu</td>
						<td colspan="2"><form:input path="arrears" class="form-control animated" maxlength="12" /></td>						
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Phụ cấp tiền điện thoại:</td>
						<td colspan="2"><form:input path="subPhone" class="form-control animated"  maxlength="12" /></td>
																
						<td bgcolor="#FAFAFA" nowrap="nowrap" title="10.5% trong đó gồm: 8% cho hưu trí, 1% cho thất nghiệp và 1.5% y tế">Đóng BHXH(10.5%):</td>						
						<c:if test="${not empty salaryDetail.salaryInsurance}">
							<td><fmt:formatNumber value="${(salaryDetail.salaryInsurance + salaryDetail.subInsurance)*10.5/100}" /> </td> 
						</c:if>
						<c:if test="${empty salaryDetail.salaryInsurance}">
							<td><i>Không tham gia BHXH</i></td> 
						</c:if>			
						<td></td>	
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Phụ cấp tiền xăng xe:</td>
						<td colspan="2"><form:input path="subGas" class="form-control animated"  maxlength="12" /></td>
						
						<td bgcolor="#FAFAFA">Phí công đoàn:</td>
						<c:if test="${not empty salaryDetail.salaryInsurance}">
							<td><fmt:formatNumber value="${(salaryDetail.salaryInsurance + salaryDetail.subInsurance)*1/100}" /></td> 
						</c:if>		
						
					</tr>				
					<tr>
						<td bgcolor="#FAFAFA">Phụ cấp chức vụ/chuyên môn/tay nghề:</td>
						<td colspan="2"><form:input path="subSkill" class="form-control animated"  maxlength="12" /></td>
						<td></td>
						<td colspan="2"></td>			
						
					</tr>	
					<tr>
						<td bgcolor="#FAFAFA">Tiền ca 3:</td>
						<td colspan="2"><form:input path="overWork" class="form-control animated"  maxlength="12" /></td>
						<td></td>
						<td colspan="2"></td>			
						
					</tr>
<%-- 					<tr>
						<td bgcolor="#FAFAFA" nowrap="nowrap" title="Yêu cầu định nghĩa số ngày công chuẩn cho tháng này trước">Làm thêm ngày thường (h):</td>
						<td><form:input path="overTimeN" maxlength="12" class="form-control animated" type="number" min="0" step="0.5" max="432"/></td>
						<td nowrap="nowrap"> x <fmt:formatNumber value="${salaryPerHour}" type="number"/> x 1.5</td>
						<td>= <fmt:formatNumber value="${salaryDetail.overTimeN*salaryPerHour*1.5}" /> </td>
						
						<td bgcolor="#FAFAFA"></td>
						<td></td>														
					</tr>
					<tr>
						<td bgcolor="#FAFAFA" nowrap="nowrap" title="Yêu cầu định nghĩa số ngày công chuẩn cho tháng này trước">Làm thêm cuối tuần (h):</td>
						<td><form:input path="overTimeW" maxlength="12" class="form-control animated" type="number" min="0" step="0.5" max="240"/></td>
						<td nowrap="nowrap"> x <fmt:formatNumber value="${salaryPerHour}" type="number"/> x 2</td>
						<td>= <fmt:formatNumber value="${salaryDetail.overTimeW*salaryPerHour*2}" /> </td>
						<td bgcolor="#FAFAFA"></td>
						<td></td>																		
					</tr>
					<tr>
						<td bgcolor="#FAFAFA" nowrap="nowrap" title="Yêu cầu định nghĩa số ngày công chuẩn cho tháng này trước">Làm thêm ngày lễ (h):</td>
						<td><form:input path="overTimeH" maxlength="12" class="form-control animated" type="number" min="0" step="0.5" max="240"/></td>
						<td nowrap="nowrap"> x <fmt:formatNumber value="${salaryPerHour}" /> x 3</td>
						<td>= <fmt:formatNumber value="${salaryDetail.overTimeH*salaryPerHour*3}" /> </td>
						
						<td bgcolor="#FAFAFA" nowrap="nowrap"></td>
						<td></td> 											
					</tr> --%>
					<tr>
						<td bgcolor="#FAFAFA">Khác:</td>
						<td colspan="2"><form:input path="other" class="form-control animated" maxlength="12" /></td>
						<td></td>
						<td></td>
						<td></td>												
					</tr>
					<tr>
						<td nowrap="nowrap" bgcolor="#E6E6E6">Tổng thu nhập</td>
						<td nowrap="nowrap" bgcolor="#E6E6E6" colspan="2"><i><fmt:formatNumber value="${salaryDetail.totalIncome}"/> 				
<%-- 						<c:if test="${not empty salaryDetail.salaryForWorkedDay}">			
							<c:if test="${not empty salaryDetail.basicSalary}">
								<fmt:formatNumber value="${salaryDetail.bounus.replaceAll(',', '') + salaryDetail.subsidize.replaceAll(',', '') + salaryDetail.overTimeH*salaryPerHour*3 + salaryDetail.overTimeW*salaryPerHour*2 + salaryDetail.overTimeN*salaryPerHour*1.5 + salaryDetail.other.replaceAll(',', '') + salaryDetail.salaryForWorkedDay.replaceAll(',', '')*(salaryDetail.workComplete/100)}" /> đ
							</c:if>
							<c:if test="${empty salaryDetail.basicSalary}">
								<fmt:formatNumber value="${salaryDetail.bounus.replaceAll(',', '') + salaryDetail.subsidize.replaceAll(',', '') + salaryDetail.overTimeH*salaryPerHour*3 + salaryDetail.overTimeW*salaryPerHour*2 + salaryDetail.overTimeN*salaryPerHour*1.5 + salaryDetail.other.replaceAll(',', '') + salaryDetail.salaryForWorkedDay.replaceAll(',', '')*(salaryDetail.workComplete/100)}" /> đ
							</c:if>	
						</c:if>
						<c:if test="${empty salaryDetail.salaryForWorkedDay}">	
							<c:if test="${not empty salaryDetail.basicSalary}">
								<fmt:formatNumber value="${salaryDetail.bounus.replaceAll(',', '') + salaryDetail.subsidize.replaceAll(',', '') + salaryDetail.overTimeH*salaryPerHour*3 + salaryDetail.overTimeW*salaryPerHour*2 + salaryDetail.overTimeN*salaryPerHour*1.5 + salaryDetail.other.replaceAll(',', '') + salaryDetail.basicSalary.replaceAll(',', '')*(salaryDetail.workComplete/100)}" /> đ
							</c:if>
							<c:if test="${empty salaryDetail.basicSalary}">
								<fmt:formatNumber value="${salaryDetail.bounus.replaceAll(',', '') + salaryDetail.subsidize.replaceAll(',', '') + salaryDetail.overTimeH*salaryPerHour*3 + salaryDetail.overTimeW*salaryPerHour*2 + salaryDetail.overTimeN*salaryPerHour*1.5 + salaryDetail.other.replaceAll(',', '') + salaryDetail.salary.replaceAll(',', '')*(salaryDetail.workComplete/100)}" /> đ
							</c:if>						
						</c:if> --%>
						</i></td>
						<td nowrap="nowrap" bgcolor="#E6E6E6">Tổng giảm trừ</td>
						<td nowrap="nowrap" bgcolor="#E6E6E6" colspan="2"> <i><fmt:formatNumber value="${salaryDetail.totalReduce}"/></i>
<%-- 							<c:if test="${not empty salaryDetail.salaryInsurance}">
								<i><fmt:formatNumber value="${salaryDetail.salaryInsurance*10.5/100 + salaryDetail.taxPersonal.replaceAll(',', '') + salaryDetail.advancePayed.replaceAll(',', '') + salaryDetail.arrears.replaceAll(',', '')}" /> đ </i>
							</c:if>
							<c:if test="${empty salaryDetail.salaryInsurance}">
								<i><fmt:formatNumber value="${salaryDetail.taxPersonal.replaceAll(',', '') + salaryDetail.advancePayed.replaceAll(',', '') + salaryDetail.arrears.replaceAll(',', '')}" /> đ</i>
							</c:if>	 --%>						
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" bgcolor="#FAFAFA"><b>Lương thực nhận:</b></td>
						<c:if test="${not empty salaryDetail.finalSalary}">
							<td><b><fmt:formatNumber value="${salaryDetail.finalSalary}" /> </b>vnđ</td>								
							<%-- <td nowrap="nowrap"><b>Trạng thái thanh toán:</b></td>
							<td>
								<form:select path="payStatus" class="form-control animated">
									<form:option value="" label="-Chọn trạng thái-"/>
									<form:option value="Đã trả lương" label="Đã trả lương"></form:option>
									<form:option value="Chưa trả lương" label="Chưa trả lương"></form:option> 
								</form:select>
							</td> --%>
						</c:if>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA">Ghi chú:</td>
						<td colspan="5"><form:textarea path="desc" cols="100" class="form-control animated"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<input class="btn btn-lg btn-primary btn-sm" type="submit" value="Lưu" name="Lưu" /><br/>
	</form:form>
	<br/>
	<a href="${url}/salary/listSalarysByGroup?group=${salaryDetail.group}&month=${salaryDetail.month}&year=${salaryDetail.year}">
	<button	class="btn btn-lg btn-primary btn-sm">Quay lại danh sách lương tháng</button></a>
</body>
</html>