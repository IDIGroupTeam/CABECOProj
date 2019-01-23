<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${url}"><img height="80px"
				src="${url}/public/images/CabecoLogo.png" /></a>
		</div>
	    <ul class="nav navbar-nav navbar-right">
			<li><a href="/CABECOHome">Trang chủ</a></li>
			<li><a href="/CABECOHome/tintuc">Tin tức</a></li>
			<li><a href="/CABECOHome/lienhe">Liên hệ</a></li>
			<li><a href="/CABECOHome/vechungtoi">Về chúng tôi</a></li>
			<!--<c:set var="username" scope="session"  value="${pageContext.request.userPrincipal.name}"></c:set> -->
			 <c:choose>
		 		 <c:when test="${username != null}" >
		 		       <li><a href="${url}/userInfo" class="login"><b>Đăng nhập bởi: ${username} </b></a></li>
				       <li><a href="${url}/logout" class="login">Đăng Xuất</a></li>
				   </c:when>
				   <c:otherwise>
				       <li><a href="${url}/login" class="login"><span	class="glyphicon glyphicon-log-out"></span>Đăng Nhập</a></li>
				   </c:otherwise>
		     </c:choose>
		</ul>
		<div class="topnav navbar-right" id="myTopnav"></div>
       
	</div>
  <form class="form-inline navbar-right" action="#">
    <input class="form-control mr-sm-2" type="text" placeholder="Xin điền thông tin " >
    <button class="btn btn-success" type="submit">Tìm kiếm</button>
  </form>
</nav>