<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/user/include/mainheader.jsp" />


	<div style="margin: 0 auto; width: 45%;">
		<div style="margin-top: 200px; padding: 200px;">
			<%-- 회원정보 변경하기전 사용자의 비밀번호 체크--%>
			<span style="color: red">
				<c:if test="${passwordF }">비밀번호가 일치하지 않습니다</c:if>
			</span>
			<form action="${pageContext.request.contextPath }/checkPw" method="post">
				비밀번호 : 
				<input type="password" name="member_password">
				<input type="submit" value="확인">
			</form>
		</div>
	</div>
<jsp:include page="/WEB-INF/view/user/include/mainfooter.jsp" />