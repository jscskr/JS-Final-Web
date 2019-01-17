
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=lz0e8vpjrz5ApswJPUqL&submodules=geocoder"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

<jsp:include page="/WEB-INF/view/user/include/mainheader.jsp" />

<section id="main">
	<div id="wrap">
		<div class="container row" style="margin-top: 150px;">
			<div class="main_left col-md-8"> 
				<div class="best_items">
					<div class="bxslider">
						<c:forEach var="best_boards" items="${best_boards}" varStatus="status"> 
							<a href="${pageContext.request.contextPath}/userprofile/${Member.member_id}">
								<div class="best_item"> 
									 <div class="best_user row"style="position: absolute;">
									 	<div class="best_user_l">
										 	<div class="user_profile_img">
										 		<img src="${best_boards.member_profile_pic}" style="width: 50px; height: 50px; border-radius: 50px; ">
										 	</div>
										 	<div id="cover${status.index}" style=" position: absolute; top:5px; left: 6px;"> 
												<img class="cover${status.index}" src="<%=request.getContextPath()%>/resources/img/11.png" style="width: 75px; height: 75px; ">
											</div>
										</div>
							 	
								 <%-- 	<div class="best_user_r"> 
										 	<div class="user_id">									
												${best_boards.member_id}
											</div>
											<div class="user_introduction">
												${best_boards.introduction}
											</div>
										</div> 
								--%>
									</div>
									
									 <div class="best_img">
										<img src="${best_boards.rv_board_picture}" style="width: 100%; height: 250px;">
									 </div>
										
								</div>
							</a>
						</c:forEach>
					</div>
				</div>
				<div>
					<input class="test01" name="test01" value="" style="display: none;">
					<div class="123123"></div>
					<script type="text/javascript">
						$(document).ready(function(){	
							$("#mapwrap").click(function(){
								
								var	article_address =  $(".article_address").val();
								$(".test01").val(article_address);
								
								 // 출력된 게시물 삭제
								 $(".123123").empty();	
								 
								$.ajax({
									type : "POST",
								    url : "${pageContext.request.contextPath}/main_rv_board_address/"+article_address,
								    dataType: "json",
								    success : function(result) {
								        console.log("result.rv_board_list : " + result.rv_board_list);
								        $.each(result.rv_board_list, function(index, i){
								        	var contents = '';
								        		contents += '<div class="posts" style="margin-bottom: 50px;  background-color: rgba( 179, 220, 244, 0.1 ); " > ';
								        		contents += '<a href="${pageContext.request.contextPath}/userprofile/'+i.member_id+'"} class="alink">';
								        		contents += '<div class="sub_title" style="padding: 20px 50px; ">';
								        		contents += '<img src="<%=request.getContextPath()%>/resources/img/sub_title.gif"/>';	
								        		contents += i.member_id;
								        		contents += '</div>';
								        		contents += '<div class="content" style="padding: 10px 50px; ">';
								        		contents += '<span style="font-size:15px; ">';
								        		contents += i.rv_board_content;
								        		contents += '</span>  <!-- 글쓴이 내용   -->';
								        		contents += '</div>';
								        	
								        		contents += '<div class="imgbox" style="padding: 10px 50px; margin-bottom: 20px; ">';
								        		contents += '<img src='+i.rv_board_picture+'> <!-- 이미지 화면 대문짝! -->';
								        		contents += '</div>';
								        		contents += '</a>';
								        	
								        		
								        		
								        		// 이미지하단 버튼
								        		contents += '<div class="bot" style="padding: 10px 50px 40px 50px;">';
								        		contents += '<div class="icons row">';
								        		contents += '<div class="icon icon_l">';
								        		contents += '<a href="#" class="alink"> <i class="far fa-heart"></i><!-- 좋아요! -->';
								        		contents += '</a></div><div class="icon icon_l">';
								        		contents += '<a href="#" class="alink"> <i class="far fa-comment"></i> <!-- 이게뭐지? -->';
								        		contents += '</a></div><div class="icon icon_l">';
								        		contents += '<a href="#" class="alink"> <i class="fas fa-share-alt"></i> <!-- 공유래요 -->';
								        		contents += '</a></div>';
								        		contents += '<!-- 즐겨찾기 온오프 -->';
								        		contents += '<div class="icon icon_r">';
								        		contents += '<a href="#" class="alink"> <i class="far fa-star"></i> <!-- 꽉찬 별 -->';
								        		contents += '</a></div><!-- 즐겨찾기 온오프 --><div class="icon icon_r">';
								        		contents += '<a href="#" class="alink"> <i class="fas fa-star"></i> <!-- 빈 별 -->';
								        		contents += '</a></div></div><div class="like">';
								        		contents += '<a href="#" class="alink">좋아요<span>0개</span></a> <!-- 좋아요 숫자 -->';
								        		contents += '</div><hr class="animated-width" style="margin: 5px 0px;"/>';
								        		contents += '<div class="ripples"><!-- 댓글 몇개만 먼저 뿌려주기 --><div class="ripple  row"><div class="id">';
								        		contents += '<a href="#" class="alink"> ${ reply.member_id } </a> <!-- 댓글 유저 아이디 -->';
								        		contents += '</div><div class="Posts_ripple">';
								        		contents += '<a href="#" class="alink"> 댓글 내용... </a> <!--  댓글 유저 내용 -->';
								        		contents += '</div>';
								        		contents += '</a>';
								        		// 댓글 작성
											
								        		
								        		
								        		// 왼쪽 상단 미니맵 클릿이 div 겹쳐서 이벤트 두번발생
							        			$(".123123").append(contents);	
							        			
								        		
								        });
								   },
								    error : function(jqXHR, textStatus, errorThrown) {
								        alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
								    }
								});
						
							});
						
						
						
						});
					
					</script>
				</div>
					
								
						
								<%-- <div class="contents">
									
									<form action="${pageContext.request.contextPath}/write">
										<input type="submit" value="write">
									</form>
										
									<c:if test="${empty articleList}">
										<div>
											No article
										</div>
									</c:if>
										
									<!-- c:foreach 포이치 시작 부분  -->
									<c:forEach var="article" items="${articleList}">
										<div class="posts" style="margin-bottom: 50px;  background-color: rgba( 179, 220, 244, 0.1 ); " > <!-- 포스트즈 하나의 게시물 이위에 포이치 하면됨!! -->
											<div class="sub_title" style="padding: 20px 50px; ">
												<a href="${pageContext.request.contextPath}/userprofile/${Member.member_id}">
													<!-- <img src="${Member.member_profile_pic}"	style="width: 50px; height: 50px; border-radius: 50px;" /> -->
													<img src="<%=request.getContextPath()%>/resources/img/sub_title.gif" />
													<span class="user_id">${article.member_id}</span>
												</a> <!-- 유저아이디 클릭시 유저프로필 넘어감 -->
											</div>
											<div class="content" style="padding: 10px 50px; ">
												
													<span style="font-size:15px; ">
														${article.rv_board_content}
													</span>  <!-- 글쓴이 내용   -->
									
												간략한 글내용...
												<a href="#" class="alink"> <span> 내용더보기</span> <!-- 댓글 더보기 <!!이거는 없어도 될거같아요!!>  -->
											
											</div>
											
											
											<div class="imgbox" style="padding: 10px 50px;">
												<a href="">
													<img src="${article.rv_board_picture}"> <!-- 이미지 화면 대문짝! -->
												</a>
											</div>
											<div class="bot" style="padding: 5px 50px;">
												<div class="icons row">
													<div class="icon icon_l">
														<a href="#" class="alink"> <i class="far fa-heart"></i> <!-- 좋아요! -->
														</a>
													</div>
													<div class="icon icon_l">
														<a href="#" class="alink"> <i class="far fa-comment"></i> <!-- 이게뭐지? -->
														</a>
													</div>
													<div class="icon icon_l">
														<a href="#" class="alink"> <i class="fas fa-share-alt"></i> <!-- 공유래요 -->
														</a>
													</div>
													<!-- 즐겨찾기 온오프 -->
													<div class="icon icon_r">
														<a href="#" class="alink"> <i class="far fa-star"></i> <!-- 꽉찬 별 -->
														</a>
													</div>
													<!-- 즐겨찾기 온오프 -->
													<div class="icon icon_r">
														<a href="#" class="alink"> <i class="fas fa-star"></i> <!-- 빈 별 -->
														</a>
													</div>
												</div>
												<div class="like">
													<a href="#" class="alink">좋아요<span>99999개</span></a> <!-- 좋아요 숫자 -->
												</div>
											
												<hr class="animated-width" style="margin: 5px 0px;"/>
												<div class="ripples"> <!-- 댓글 몇개만 먼저 뿌려주기 -->
													<div class="ripple  row">
														<div class="id">
															<a href="#" class="alink"> ${ reply.member_id } </a> <!-- 댓글 유저 아이디 -->
														</div>
														<div class="Posts_ripple">
															<a href="#" class="alink"> 댓글 내용... </a> <!--  댓글 유저 내용 -->
														</div>
														
													</div>
													<div class="more_ripple">
														<!--<a href="#" class="alink"> 댓글 보기 </a> <!--댓글 전체보기 -->
														<!-- <button type="button" id="button_replyList_ajax">댓글 보기</button> -->
														<div id="append_replyList_ajax" class="row"> 
														
															<script type="text/javascript">
																$(function () {
																	var rv_board_index = $("#rv_board_index_ajax").val();
																	$.ajax({
																		type: "POST",
																		url: "${pageContext.request.contextPath}/replyList/"+rv_board_index,
																		dataType: "json",
																		cache : false,
																		async : false,
																		success: function (result) {
																			var contents ="";
																			$("#append_replyList_ajax").html("");
															
																			$.each(result, function (index, reply) {
																				//alert(index + ", " + reply.member_id + ", " + reply.reply_content + ", " + reply.reply_post_date);	
																				contents += "<div class="+"row"+">";
																				contents += "<div class="+"reply_id"+">" + reply.member_id + " : </div>";
																				contents += "<div class="+"reply_content"+">" + reply.reply_content + "</div>";
																				contents += "<div class="+"reply_date"+">" + reply.reply_post_date + "</div>";
																				contents += "</div>";
																					
																			}); 
																			
																			$("#append_replyList_ajax").append(contents);	
																		}, 
																		error: function () {
																			alert("Error");
																		}
																	});
																});
															</script>
														</div> 
													</div>
												</div>
												
												<hr class="animated-width" />
												<div class="posts_bottom row">
													 <!-- 로그인유저 아니면 아래 댓글불가 로그인 상태면 글쓰기 가능 -->
													<div class="l_text col-md-11">
													
														<form>
															<input type="text" id="reply_content" style="border-bottom: 1px solid #eeeeee;"> <!-- onfocus="this.value=''" 클릭시 인풋 내용물 사라짐 -->
															<button type="button" id="reply_submit_ajax" style="background:#ffffff; border-radius: 5px; padding: 4px 12px;">등록</button>
															<input type="hidden" id="rv_board_index_ajax" value="${article.rv_board_index}">
														</form>
														<script type="text/javascript">
															$("#reply_submit_ajax").click(function () {
																var rv_board_index = $("#rv_board_index_ajax").val();	
																var reply_content = $("#reply_content").val();	
																
																$.ajax({
																	type: "POST",
																	url: "${pageContext.request.contextPath}/reply/"+rv_board_index+"/"+reply_content,
																	dataType: "json",
																	cache : false,
																	async : false,
																	success: function (result) {
																		alert(result.value);
																	}, 
																	error: function () {
																		alert("Error");
																	}
																});
															});
														</script>
														
													</div>
													<div class="r_btn col-md-1" style="padding: 5px;">
														<button type="button" class="btn" data-toggle="modal"
															data-target=".bs-example-modal-sm" style="width: 50px; height: 25px; padding: 0px;">...</button> <!-- 메뉴 더보기 버튼 -->
														<div class="modal fade bs-example-modal-sm" tabindex="-1"
															role="dialog" aria-labelledby="mySmallModalLabel"
															aria-hidden="true">
															<div class="modal-dialog modal-sm">
																<div class="modal-content" style="top: 200px;">
																	<div>
																		<a
																			href="${pageContext.request.contextPath}/alert/${article.rv_board_index}"
																				class="alink" style="color: #ed4f5c"> 부적절한 콘텐츠 신고 </a> <!-- 신고하기 버튼 -->
																		<div>
																			<a href="#" class="alink"> 게시물로 이동 </a>  <!-- 유저 프로필로 넘어가기 -->
																		</div>
																		<div>
																			<a
																				href="${pageContext.request.contextPath}/file/download">file
																				download</a> <!-- 올 다운로드 -->
																		</div>
																		<div>
																			<a href="#" class="alink"> 링크 복사하기 </a>  <!-- 게시물 주소 복사 ( 이거는 공부해야할꺼같아요 이대로 두세요~ ) -->
																		</div>
																		<div>
																			<a href="#" class="alink"> 취소 </a>  <!-- 메인화면! 또는 다이얼로그 닫기 -->
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach> 
								</div> --%>
			</div>
				
			<!-- 로그인 이후 -->
			<div class="main_right col-md-4">
				<c:if test="${LoginMember}" var="login">
					<div>
						<div class="user_info row">
							<!-- 로그인 후 로그인된 아이디의 사진 및 아이디를 보여주는 섹션 -->
							<!-- 로그인 된 아이디의 사진을 보여주는 Div -->
							<div class="user_profile_photo col-md-4">
								<a href="${pageContext.request.contextPath}/userprofile/${Member.member_id}">
									<img src="${Member.member_profile_pic}" style="width: 100px; height: 100px;" />
								</a>
							</div>
							<!-- 로그아웃 기능을 해주는 Div -->
							<form action="${pageContext.request.contextPath}/logout"
								method="get">
								<div class="id_name col-md-8">
									<div class="user">
										<a href="${pageContext.request.contextPath}/userprofile/${Member.member_id}">
											<strong>
												<span style="font-size: 20px;">${Member.member_id}</span>
											</strong>
										</a>
									</div>
									<div>
										<button type="submit" name="logout" value="logout">Logout</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- 팔로잉 해주는 사람들의 목록을 보여주는 섹션 -->
					<div class="storys">
						<div class="top row">
							<div class="title">스토리</div>
							<div class="more">
								<a href="#myModalFollowed" data-toggle="modal"
									data-target="#myModalFollowed" style="text-decoration: none;"
									class="alink"> 모두 보기 </a>
							</div>
						</div>
						<c:forEach items="${follow}" var="follow" begin="0" end="2">
							<div class="story">
								<div class="body">
									<div class="user row">
										<div class="user_profile_photo  col-md-4">
											<a
												href="${pageContext.request.contextPath}/userprofile/${follow.followed_id}"
												style="text-decoration: none;"><img
												src="${follow.pic2}" style="width: 75px; height: 75px;" /></a>
										</div>
										<div class="id_name col-md-8">
											<div class="user">
												<a	href="${pageContext.request.contextPath}/userprofile/${follow.followed_id}"
													style="text-decoration: none;">${follow.followed_id}</a>
											</div>
											<div class="time">몇시간전</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="maps" style="margin-bottom: 20px;">
						<div class="top row">
							<div class="title">지도</div>
						</div>
						<div class="map" id="dmap_click">
							<jsp:include page="dmap.jsp" />
						</div>
					</div>
					
					<div>
						<jsp:include page="/WEB-INF/view/chat/websocket-echo.jsp" />
					</div>
				</c:if>

				<!-- 오른쪽 메인 유저 화면 끝나는 부분 -->


				<!-- 메인 유저 프로필 화면 구성 시작 부분 -->
				<!-- 로그인 이전 -->
				<c:if test="${not login}">
					<div class="loginentrance">
						<div class="id">
							<a href=" ${pageContext.request.contextPath}/login"><button
									type="button" class="btn btn-light btn-lg btn-block">LogIn</button></a>
						</div>
						<div class="search">
							<a href=" ${pageContext.request.contextPath}/MyId"><button
									type="button" class="btn btn-light btn-sm" style="width: 49%;">Did
									you forget you Id?</button></a> <a
								href=" ${pageContext.request.contextPath}/regist"><button
									type="button" class="btn btn-light btn-sm" style="width: 49%;">Sign
									Up</button></a>
						</div>
					</div>
				</c:if>
			
		
	
				<!-- 팔로잉를 보여주는 모달 -->
				<div class="modal fade" id="myModalFollowed" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
					style="margin-top: 150px;">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">내가 팔로잉 하는 사람들</h4>
							</div>
							<div class="modal-body">
								<c:forEach items="${follow}" var="follow">
									<div class="story">
										<div class="body">
											<div class="user row">
												<div class="user_profile_photo  col-md-4"
													style="width: 100px;">
													<a
														href="${pageContext.request.contextPath}/userprofile/${follow.followed_id}"
														style="text-decoration: none;"><img src="${follow.pic2}"
														style="width: 75px; height: 75px;" /></a>
												</div>
												<div class="id_name col-md-8">
													<div class="user">
														<a
															href="${pageContext.request.contextPath}/userprofile/${follow.followed_id}"
															style="text-decoration: none;">${follow.followed_id}</a>
													</div>
													<div class="time">몇시간전</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/view/user/include/mainfooter.jsp" />

<script>
	$(".cover0").attr("src", "<%=request.getContextPath()%>/resources/img/11.png");
	$(".cover1").attr("src", "<%=request.getContextPath()%>/resources/img/22.png");
	$(".cover2").attr("src", "<%=request.getContextPath()%>/resources/img/33.png");
 


 </script>



