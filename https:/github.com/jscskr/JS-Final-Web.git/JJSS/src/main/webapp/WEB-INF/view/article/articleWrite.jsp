<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/user/include/mainheader.jsp" />
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>


<form action="${pageContext.request.contextPath}/write" method="post" enctype="multipart/form-data">
	
	<div style="width: 27%; margin: 0 auto;">
		<div style="margin-bottom: 20px; margin-top: 195px;">
			<div>title</div>
			<div><input type="text" name="rv_board_title"></div>
		</div>
		<div style="margin-bottom: 20px;">
			<div>content</div>
			<div>
				<textarea rows="20" cols="40"  name="rv_board_content" style="resize: none; border: 1px solid #dfdfdf;"></textarea>
		
			</div>
		</div>
		<div style="margin-bottom: 20px;">
			<div>file</div>
			<div>
				<input type="file" name="files" multiple> 	
			</div>
		</div>
		
				
		<%-- 부트스트랩 회원 우편 번호 체크 --%>
		<div class="form-group  has-feedback" id="formMemberZip_Code">
		    <label class="control-label" for="inputSuccess4" >우편 번호</label>
		    <input type="text" class="form-control postcodify_postcode5 onlyNone" name="member_Zip_code" autofocus="autofocus" id="inputSuccess4" aria-describedby="inputSuccess4Status" readonly="readonly">
		    <br>
		    <button id="postcodify_search_button" type="button" >검색</button>
		    <span class="glyphicon  form-control-feedback" aria-hidden="true" id="formMemberZip_Code_Img"></span>
		    <span id="inputSuccess4Status" class="sr-only">(success)</span>
		</div>
		<br>
		<br>
		
		<%-- 부트스트랩 회원 도로명주소 체크 --%>
		<div class="form-group  has-feedback" id="formMemberStreet_Name">
		    <label class="control-label" for="inputSuccess4" >도로명주소</label>
		    <input type="text" class="form-control postcodify_address" name="rv_board_Street_name_address" autofocus="autofocus" id="inputSuccess4" aria-describedby="inputSuccess4Status" readonly="readonly" size="30">
		    <span class="glyphicon  form-control-feedback" aria-hidden="true" id="formMemberStreet_Name_Img"></span>
		    <span id="inputSuccess4Status" class="sr-only">(success)</span>
		</div>
		<br>
		<br>
		
		<%-- 부트스트랩 회원 상세주소 체크 --%>
		<div class="form-group  has-feedback" >
		    <label class="control-label" for="inputSuccess4" >상세주소</label>
		    <input type="text" class="form-control postcodify_details" name="member_Detailed_Address" autofocus="autofocus"  aria-describedby="inputSuccess4Status">
		    <span class="glyphicon  form-control-feedback" aria-hidden="true"></span>
		    <span id="inputSuccess4Status" class="sr-only">(success)</span>
		</div>
				
		
		
		<div style="margin-bottom: 50px;">
			<input type="submit" value="post" style="background:#ffffff; border-radius: 5px; padding: 4px 12px; color: #67b9ea;">
		</div>
	</div>
	<div style="margin-bottom: 200px;"></div>

	 
	<jsp:include page="/WEB-INF/view/user/include/mainfooter.jsp" />


	<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
	
	<script>
		$(function() {
		    $("#postcodify_search_button").postcodifyPopUp();
		});
	</script>


		