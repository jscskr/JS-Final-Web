package js.android.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import js.user.model.*;
import js.android.Service.Rv_boardService_android;

import com.google.gson.Gson;

@Controller
public class rv_boardAndroidController {
	
	@Autowired
	private Rv_boardService_android service;
	
	Gson gson = new Gson();
	// 우편번호 검색
		@RequestMapping(value = "/android/address", method = RequestMethod.GET)
		public String android2() {
			
			return "addressApi";

		}
		
		// 리뷰 게시글 작성
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/write", method = RequestMethod.POST)
		public String writeRvBoard(Rv_board_android rv_board_android, MultipartFile file, HttpServletRequest request) {
			String rv_board_location = null;
			try {
				rv_board_location = URLDecoder.decode(rv_board_android.getRv_board_location(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 한글로 변환된 값 set
			rv_board_android.setRv_board_location(rv_board_location);
			String msg;
			String path = null;
			String fileName = null;
			String DBpath;
			FileImage fileimage = new FileImage();

			// 게시글 작성시 첨부파일이 없는경우도 있으므로
			if (file != null) {
				fileimage.setFile(file);
				path = request.getSession().getServletContext().getRealPath("/WEB-INF/android/Board/Board_images");
				fileName = rv_board_android.getMember_id() + "_" + file.getOriginalFilename();
				DBpath = request.getContextPath() + "/android/Board/Board_images/"
						+ rv_board_android.getMember_id() + "_" + file.getOriginalFilename();
				rv_board_android.setRv_board_picture(DBpath);
			}

			// 날짜처리
			Date date = new Date();
			SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			String currentTime = smdf.format(date);
			rv_board_android.setRv_board_post_date(currentTime);

			int result = service.writeRvBoard(rv_board_android);
			if (result == 0) {
				msg = "fail";
			} else {
				msg = "success";
				if (file != null) {
					fileimage.fileImage(path, fileName);

				}
			}
			return "{\"msg\":\"" + msg + "\"}";

		}
		
		// 리뷰 게시글 삭제
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/delete", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public void deleteRv_board(Rv_board_android rv_board_android) {

			service.deleteRv_board(rv_board_android);

		}
		
		// 댓글 삭제
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/alert/delete", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public void deleteAlert(Alert alert) {

	service.deleteAlert(alert);

		}
		
		

		// 모든 리뷰게시글 가져오기
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/All", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public String All_RvBoard() {

			String JsonRv_board;
			List<Rv_board_android> rv_board_android = new ArrayList<>();
			rv_board_android = service.selectAllRvBoard();
			Gson gson = new Gson();
			JsonRv_board = gson.toJson(rv_board_android);
			System.out.println(JsonRv_board);

			return JsonRv_board;

		}
		
		// 리뷰 게시글 좋아요  // 좋아요 // 좋아요 삭제
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/favorite", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public String rv_board_favorite(String rv_board_index , String member_id) {
			
			// 해당 게시글에 이미 좋아요 한 상태인지 확인
			HashMap<String,String> isDuplicated = new HashMap<>();
			isDuplicated.put("rv_board_index", rv_board_index);
			isDuplicated.put("member_id", member_id);
			String duplicatedMember = service.rv_board_duplicated(isDuplicated);
			
			if(duplicatedMember == null) {
			
				// 좋아요 한 상태가 아니라면 좋아요 insert
			service.rv_board_favorite(isDuplicated);
			
			return "{\"msg\":\"good\"}";
			} else {
				
				// 좋아요 한 상태라면 해당 게시글의 좋아요 delete
				service.rv_board_favoriteDelete(isDuplicated);
				return "{\"msg\":\"bad\"}";
			}
		}
		
		// 리뷰 게시글 좋아요  // 좋아요 상태인지 check
				@ResponseBody
				@RequestMapping(value = "/android/rv_board/favorite/check", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
				public String rv_board_favorite_check(String rv_board_index , String member_id) {
					
					// 해당 게시글에 이미 좋아요 한 상태인지 확인
					HashMap<String,String> isDuplicated = new HashMap<>();
					isDuplicated.put("rv_board_index", rv_board_index);
					isDuplicated.put("member_id", member_id);
					String duplicatedMember = service.rv_board_duplicated(isDuplicated);
					
					
					if(duplicatedMember == null) {
					
				//좋아요 아닌 상태
					return "{\"favorite\":\"false\"}";
					
					} else {
						
						// 좋아요인 상태
						return "{\"favorite\":\"true\"}";
					}
				}
		
		
		
		// 리뷰 게시글 좋아요 갯수 불러오기
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/favoriteCount", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public String rv_board_favoriteCount(Rv_board_android rv_board_android) {
			
			int count = service.rv_board_favoriteCount(rv_board_android);
			
			return "{\"rv_board_heart\":\""+count+"\"}";
		}
		
		
		// 선택된 리뷰게시글 가져오기
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/selected", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public String selected_rvBoard(Rv_board_android rv_board_android) {
			
			// 게시글의 index 번호로 검색
			Rv_board_android rv_board_android1 = service.rv_boardSelected(rv_board_android);
			Gson gson = new Gson();
			String JsonRv_board = gson.toJson(rv_board_android1);
			
			// 해당 게시글의 조회수 +1
			service.rv_board_countUp(rv_board_android1);

			return JsonRv_board;

		}
		
		
		
		// 게시글 댓글 입력하기
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/insertAlert", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public void rv_board_insertAlert(Alert alert) {
			// 게시글 넘버는 int auto_increment unique 때문에 1부터 // 댓글 DB 안에 게시글 넘버는 int auto_increment 이 아니므로 0부터 시작해 -1 해주는 작업
					alert.setRv_board_index(alert.getRv_board_index()-1);
			// 날짜처리
			Date date = new Date();
			SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			String currentTime = smdf.format(date);
			alert.setAlert_date(currentTime);

			int result = service.rv_board_insertAlert(alert);

		}

		// 게시글 댓글 가져오기
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/loadAlert", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public String rv_boardLoadAlert(Alert alert) {
			System.out.println("alert.getRv_board_index():"+alert.getRv_board_index());
			// 게시글 넘버는 int auto_increment unique 때문에 1부터 // 댓글 DB 안에 게시글 넘버는 int auto_increment 이 아니므로 0부터 시작해 -1 해주는 작업
			alert.setRv_board_index(alert.getRv_board_index()-1);
			List<Alert> alertList = service.rv_boardLoadAlert(alert);
			Gson gson = new Gson();
			String JsonAlert = gson.toJson(alertList);

			return JsonAlert;

		}

		// 각 게시판의 댓글 갯수 불러오기
		@ResponseBody
		@RequestMapping(value = "/android/rv_board/count", method = RequestMethod.POST)
		public String rv_boardCountAlert(Rv_board_android rv_board_android) {
			// 게시글 넘버는 int auto_increment unique 때문에 1부터 // 댓글 DB 안에 게시글 넘버는 int auto_increment 이 아니므로 0부터 시작해 -1 해주는 작업
			rv_board_android.setRv_board_index(rv_board_android.getRv_board_index()-1);
			System.out.println(service.rv_boardCountAlert(rv_board_android));
			int comments = service.rv_boardCountAlert(rv_board_android);
			System.out.println(comments);
			return "{\"rv_board_comments\":\""+comments+"\"}";

		}
		// 각 게시글 댓글 클릭시 멤버정보 보여주도록
	      @ResponseBody
	      @RequestMapping(value = "/android/rv_board/findByAlert", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	      public String findByAlert(Alert alert) {
	         Member member = service.findByAlert(alert);
	         String JsonMember = gson.toJson(member);
	         
	return JsonMember;
	      }
	      
	      // 각 게시글 작성자 아이디 클릭시 멤버정보 보여주도록
	            @ResponseBody
	            @RequestMapping(value = "/android/rv_board/findByRv_board", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	            public String findByAlert(Rv_board_android rv_board_android) {
	               Member member = service.findByBoard(rv_board_android);
	               String JsonMember = gson.toJson(member);
	               System.out.println(JsonMember);
	      return JsonMember;
	            }
	      
	      // 각 게시글 회원정보 클릭해도 멤버정보 보여주도록
	      
	      // 내가 쓴 게시글 
	      @ResponseBody
	      @RequestMapping(value = "/android/rv_board/memberWrote", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	      public String memberWrote(Member member) {
	         List<Rv_board_android> memberWrote = service.memberWrote(member);
	         String JsonBoard = gson.toJson(memberWrote);
	         System.out.println(JsonBoard);
	return JsonBoard;
	      }
		
}
