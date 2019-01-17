<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/user/include/mainheader.jsp" />

		
	<div style="margin-top: 200px; margin-bottom: 100px;">
		<div style="margin-bottom: 20px;">
			<div style="width: 50%; margin: 0 auto;">
				<a href="${pageContext.request.contextPath }/update/privacyModify">[개인정보 수정]</a>
				<a href="${pageContext.request.contextPath }/update/password">[패스워드 변경]</a>
				<a href="#">[회원탈퇴]</a>
			
			</div>
		</div>
	</div>

<jsp:include page="/WEB-INF/view/user/include/mainfooter.jsp" />