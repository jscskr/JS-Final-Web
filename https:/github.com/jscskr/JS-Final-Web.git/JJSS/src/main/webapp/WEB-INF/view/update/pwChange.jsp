<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/view/user/include/mainheader.jsp" />


<%--
private String member_id;
	private String member_password;
	private String member_name;
	private String member_birthday;
	private String member_gender;
	private String member_phone;
	private String member_address;
	private int member_trip_exp;
	private String member_register_date;
	private String member_profile_pic;
	// 0 이면 사용자 1이면 어드민
	private int member_type;
 --%>
		
	<div style="margin-top: 200px; margin-bottom: 100px;">
		<div style="margin-bottom: 20px;">
			<div style="width: 50%; margin: 0 auto;">
				<a href="${pageContext.request.contextPath }/update/privacyModify">[개인정보 수정]</a>
				<a href="${pageContext.request.contextPath }/update/password">[패스워드 변경]</a>
				<a href="#">[회원탈퇴]</a>
			
			</div>
			
			<form action="${pageContext.request.contextPath }/update/password" method="post">
		
				<div id="loginUp" style="width: 50%; margin-top: 20px;">
				<fieldset>
					<legend>비밀번호 변경</legend>
						<input type="password" class="form-control FC_B" name="current_member_password" id="current_member_password" placeholder="현재 비밀번호"><c:if test="${notMatch }"><span style="color: red">비밀번호를 다시 확인해주세요</span></c:if><br>
						<input type="password" class="form-control FC_B" name="new_member_password" id="new_member_password" pattern="\d{7,11}" title="비밀번호를 7~11글자로 작성해주세요" placeholder="새로운 비밀번호"><br>
						<input type="password" class="form-control FC_B" name="new_member_password_chk" id="new_member_password_chk" placeholder="새로운 비밀번호 확인"> <span id="passwordText" style="color: red"></span><br>
						<input type="submit" value="수정완료" id="submit"><br><br>
						<a href="${pageContext.request.contextPath }/">[메인 페이지]</a>
					</fieldset>
				</div>
			</form>
		</div>
	</div>
		

<script type="text/javascript">
<%-- 새로운 비밀번호, 새로운 비밀번호 확인 ajax
$("#match").click(function(){
	var current_member_password = $("#current_member_password").val();
	var new_member_password = $("#new_member_password").val();
	var new_member_password_chk = $("#new_member_password_chk").val();
	
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/updatePwChk/"+current_member_password+"/"+new_member_password_chk,
		dataType:"json",
		success : function(check){
			$("#passwordText").text(check.msg);

		},
		error : function(){
			$("#passwordText").text("입력해주세요");
		}
	});
	
});
--%>

$("#submit").click (function(){
	$("#passwordText").text("");
	var member_password = $("#new_member_password").val();
	var member_password_chk = $("#new_member_password_chk").val();
	if(member_password != member_password_chk){
		$("#passwordText").text("비밀번호 불일치");
		return false;
	}
	if(member_password == "" || member_password_chk == ""){
		$("#passwordText").text("입력해주세요");
		return false;
	}
	
	$("#inputsearch").on('keyup', function() {
        $('#memberListTable').html('');
        var   search =  $("#inputsearch").val();         

           $.ajax({
                 type : "POST",
                 url : "${pageContext.request.contextPath}/"+search,
                 dataType : "json",               
                 success : function(result) {                  
                    $.each(result, function(index, i){
                       
                       var contents = "<tr class = 'memberContens'>";
                       contents +="<td><div class=\"user_profile_picture col-md-4\"><img src = \""+ i.member_profile_pic + "\"  style = \"width:100px; height:100px;\"/></div></td>";
                       contents +="<td>" + i.member_id + "<td>";
                       contents +-"</tr>";                              
                       
                       $("#memberListTable").append(contents);                        
                    });                        
                 },
                 error : function() {
                    alert("ajax 실패");
                 }
              });
                 //end
           });
});

</script>



<jsp:include page="/WEB-INF/view/user/include/mainfooter.jsp" />