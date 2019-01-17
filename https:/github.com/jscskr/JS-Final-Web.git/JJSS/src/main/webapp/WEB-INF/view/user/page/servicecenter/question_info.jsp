<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=lz0e8vpjrz5ApswJPUqL&submodules=geocoder"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<LINK REL="StyleSheet"
	HREF="<%=request.getContextPath()%>/resources/css/style.css"
	TYPE="text/css">


<jsp:include page="/WEB-INF/view/user/include/mainheader.jsp" />
<style>
body {
	background-color: #fafafa;
}

header {
	padding-top: 0px;
	border: 0px solid #fafafa;
}

ul {
	list-style: none;
}
</style>

<section id="servicecenter" style=margin-top:170px; "> 
	<div id="wrap" style="color: #272829;">
		<div class="container row">
		  <div class="login_div">
			<div class="main_left col-md-3" 
				style="margin-bottom: -83px;
		    	position: relative;
		    	background: #fff;
		    	border: 0.1px solid white;
		    	box-shadow: 0 12px 15px 0 rgba(0, 0, 5, 0.15);
		    	margin-left: 0px;
		    	margin-top: 25px; 
		    	border-radius: 15px;">
    
	    
				<div class="login_div" >
					<ul style=" margin-bottom: -72px;">
						<h2 style="color: #387cb8; font-size: 20px; ">정보</h2>
						<li><a target="asd" style="font-size: 20px; background-color: ; color: black; " href="${pageContext.request.contextPath}/company_info">회사소개</a></li>
						<h2 style="color: #387cb8; font-size: 20px; ">법무</h2>
						<li><a target="asd" style="font-size: 20px; background-color: ; color: black; " href="${pageContext.request.contextPath}/policy">개인정보정책</a></li>
						<li><a target="asd" style="font-size: 20px; background-color: ; color: black; " href="${pageContext.request.contextPath}/tos">이용약관</a></li>
						<h2 style="color: #387cb8; font-size: 20px; ">고객센터</h2>	
						<li><a target="asd" style="font-size: 20px; background-color: ; color: black; " href="${pageContext.request.contextPath}/notice">공지·건의사항</a>
						</li><br><br><br><br><br><br><br><br><br>
						<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
					</ul>
				</div>
			</div>





			
			
		 
			<div class="main_right col-md-9">
				<div class="items" style="margin-top: 25px; ">
				
					<div class="item" style="padding: 85px;
					    margin-bottom: 22px;
					    position: relative;
					    background: #fff;
					    border: 0.1px solid white;
					    box-shadow: 0 12px 15px 0 rgba(0, 0, 5, 0.15);
					    margin-left: 11px;
					    margin-top: 19px;
					    border-radius: 15px;">
			    	
					   <div class="noti_section question_info" style="font-size: 25px; color: #3f80ba; ">
	    	     		  <div>
	    	       			문의내용
	    	    		   </div>
	    	    		   <div>
	    	       				<legend class="screen_out"></legend>
	    	       			</div>
	    	       			<div style="margin-top: 50px;">
			    	       		${question_info.question_title}
			    	      	 </div>
			    	      	 <hr class="animated-width" />
			    	      	 <div class="content">
			    	       		${question_info.question_content}
							</div>
							<hr class="animated-width" />
							<!-- 댓글 시작 -->
							<c:forEach items="${q_Reply}" var="list" varStatus="status">
								<div class="reply row" style="margin-bottom: 5px;">
								
									<div class="reply_line id">${list.member_id} : </div>
									<div class="reply_line content">${list.reply_content}</div>
									<div class="reply_line btns">
									
					 					<form action="${pageContext.request.contextPath}/q_Reply_delete" method="post" style="margin: 0px;">
											<input type="hidden" name="reply_index" value="${list.reply_index}">
											<input type="hidden" name="question_id" value="${list.question_id}">									
											<input type="submit" name="reply_delete" value="삭제">
										</form>	
									</div> 
									<div class="reply_line btns">
										<input id="updatereply${status.index}" type="button" name="reply_update" value="수정">
									</div>
									<div class="reply_line time">${list.reply_post_date}</div> 
								</div>
							
								<form action="${pageContext.request.contextPath}/q_Reply_Update" method="post" style="margin: 0px;">
									<div class="updatereply${status.index}" style="display: none; font-size: 10px;">
										<i class="fas fa-long-arrow-alt-right" style="margin-left: 20px;"></i>
										<input type="hidden" name="reply_index" value="${list.reply_index}">
										<input type="hidden" name="question_id" value="${list.question_id}">	
										<input type="text" name="reply_content" style="width: 525px; border-width: 0px 0px 1px 0px; padding: 10px; margin-right: 13px;">								
										<input type="submit" name="reply_update" value="작성">
									</div>
								</form>	
							
							</c:forEach> 
							<!-- 댓글 끝 -->
	    	       			 
	    	       			<form action="${pageContext.request.contextPath}/q_Reply/${question_id}" method="post">
	    	       				<div style="margin-top: 10px; font-size: 20px;">
	    	       					
	    	       					<input type="text" name="content" style="width: 552px; border-width: 0px 0px 1px 0px; padding: 10px; margin-right: 13px;">
	    	       					<input type="submit" name="submit" value="작성" style="background:#ffffff; border-radius: 5px;">
	    	       				</div>
    	       				</form>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/view/user/include/mainfooter.jsp" />

<script>

 $('[id^="updatereply"]').on('click', function(e) {
	    if( $('.'+ this.id).css("display")=="none"){
	        $('.'+ this.id).show();
	        $('.'+ this.id).css("display")="block";
	        
	    }else{
	       $('.'+ this.id).hide();
	    }
	    
	});
 
 </script>
 