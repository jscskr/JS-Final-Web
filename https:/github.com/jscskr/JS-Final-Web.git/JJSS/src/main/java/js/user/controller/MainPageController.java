package js.user.controller;

import java.lang.ProcessBuilder.Redirect;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import ch.qos.logback.core.net.SyslogOutputStream;
import js.user.model.*;
import js.user.service.ArticleListService;
import js.user.service.FileDownloadService;
import js.user.service.FileUploadService;
import js.user.service.findService;
import js.user.service.loginService;
import js.user.service.registService;
import nl.captcha.Captcha;
import js.admin.model.FileImage;
import js.admin.model.Location;

@SessionAttributes({ "Member", "LoginMember", "loginMemberId" })
@Controller
public class MainPageController {

	// 팔로우 관련 서비스
	@Autowired
	private registService registservice;

	// 로그인 관련 서비스
	@Autowired
	private loginService loginservice;

	// 아이디 , 비밀번호 찾기
	@Autowired
	private findService findservice;

	// 비밀번호 찾기할 때 메일 보내기
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ArticleListService rv_boardService;
	@Autowired
	private FileUploadService fileUploadService;	
	@Autowired
	private FileDownloadService downloadService;
	
	private String PictureAbsolutePath;
	private String PictureAbsolutePath_name;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(Member member, Model model, HttpSession session) {
		// 멤버가 로그인 했을 경우
		String address =  "부산";
		
		if (session.getAttribute("Member") != null) {
			Gson gson = new Gson();
			Member loginMember = (Member) session.getAttribute("Member");
			//  스토리에서 사진을 불러오기 위한 배열
			List<FollowPic> list = new ArrayList<>();
			list = loginservice.followedIdPic(loginMember);
			System.out.println("list아이디 픽쳐에 관한 리스트?" + list);
			
			List<Follow> fList = new ArrayList<>();
			fList = loginservice.followLoginSelect(loginMember);
			List<String> sList = new ArrayList<>();
			for (Follow follow : fList) {
				sList.add(follow.getFollowed_id());
			}
			String gList = gson.toJson(fList);
			model.addAttribute("sList", gList);
			model.addAttribute("follow", list);
		
			Object articles;
			
			articles = rv_boardService.getArticleList();
			model.addAttribute("articleList", articles);	
			
			
			// best 게시물 
			Object best_boards = rv_boardService.best_board();
			model.addAttribute("best_boards", best_boards);	
			System.out.println("베스트");
		
			System.out.println("best_boards1"+best_boards);
			
			return "/user/page/main";
			
		} else {
			model.addAttribute("first","뭐야");
			
			Object articles = rv_boardService.getArticleList();
			model.addAttribute("articleList", articles);
			
			
			
			// best 게시물 
			Object best_boards = rv_boardService.best_board();
			model.addAttribute("best_boards", best_boards);	
			System.out.println("best_boards2"+best_boards);
			return "/user/page/main";
		}

	}
	
	// 로그인 페이지로(GET)
	// 로그인 실패 누적을 위한 쿠키값 저장하기위해 response 사용 // 쿠키의 생성주기 // 여기서는 GET 으로 클라이언트에게 입력을
	// 받을 시 response로 쿠키와 함께 return login/login.jsp 파일로 이동 // 이러한 과정으로
	// login/login.jsp 에서 쿠키가 생성되어진다
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goLogin(HttpServletResponse response, Model model) {

		String intCookie = "0";
		// response 해줄 쿠키 생성
		Cookie cookie1 = new Cookie("cookie", intCookie);
		// return 되는순간 쿠키 보내기
		response.addCookie(cookie1);
		model.addAttribute(cookie1);

		return "/login/login";
	}

	// 아이디찾기 페이지로(GET)
	@RequestMapping(value = "/MyId", method = RequestMethod.GET)
	public String MyId() {

		return "find/myId";
	}

	// 패스워드 찾기 페이지로(GET)
	@RequestMapping(value = "/MyPassword", method = RequestMethod.GET)
	public String MyPassword() {

		
		return "find/myPassword";
	}

	// 패스워드 찾기 성공(GET)
	@RequestMapping(value = "/find/myPasswordSuc", method = RequestMethod.GET)
	public String MyPasswordSuc() {

		return "find/myPasswordSuc";
	}

	// 패스워드 찾기 실패(GET)
	@RequestMapping(value = "find/myPasswordFail", method = RequestMethod.GET)
	public String MyPasswordFail() {

		return "find/myPasswordFail";
	}

	// 로그아웃(GET)
	@RequestMapping(value = "/logout", method = RequestMethod.GET) // 적어두기만해도 redirect 시 세션값 넘어가지 않음
	public String logout(Model model, SessionStatus status, @CookieValue("cookie") String cookie,
			RedirectAttributes redirectAttributes) {

		// 세션 지운 상태
		status.setComplete();
		// 쿠키 제거방법
		// 쿠키 생명주기 0으로 준다
		// 여기서 "cookie" 는 로그인 실패 누적에서 사용한 쿠키
		Cookie cookie1 = new Cookie("cookie", null);
		cookie1.setMaxAge(0);
		return "redirect:/";

	}

	// 로그인 강화 (GET)
	@RequestMapping(value = "/loginUp", method = RequestMethod.GET)
	public String loginUp(HttpServletResponse response, HttpServletRequest request) {

		return "login/loginUp";

	}

	// 검색기능(POST)
	@ResponseBody
	@RequestMapping(value = "/a/{search}", method = RequestMethod.POST)
	public Map<String, List<Object>> search(Model model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable("search") String search, HttpSession session) throws Exception {
		
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		
		// 웹페이지 검색에서 검색하는 String 값 
		String searchNew = search + "%";		
		System.out.println(searchNew);
		// 로그인 하지 않았을 경우
		if(session.getAttribute("Member") == null) {
		
		// 소개글 가져오기 위한 리스트 배열
		List<Object> list = new ArrayList<>();
		
		// 문자열을 지닌 아이디값을 지닌 멤버의 정보를 불러옴
		try {
			list = loginservice.memberIntroAll(searchNew);						
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		System.out.println(list);
		map.put("member", list);		

		return map; 
		
		// 로그인 했을 경우
		} else {
			
			List<Object> list = new ArrayList<>();
			List<Object> fList = new ArrayList<>();			
			Member member = new Member();			
			member = (Member) session.getAttribute("Member");
			try {
				// 아이디 값에 해당하는 멤버 정보의 소개가 있는 테이블의 리스트
				list = loginservice.memberIntroAll(searchNew);
				// 아이디 값에 해당하는 멤버가 팔로우하는 아이디의 리스트
				fList = loginservice.followedLoginSelect(member);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			map.put("member", list);
			map.put("followed", fList);
											
			return map;
		}
		
	}

	// 로그인(POST)
	@RequestMapping(value = "/login", method = RequestMethod.POST) // 쿠키를 보내기위한 response 추가
	// 변경된 쿠키값을 저장하기위해서는 기존에 Session 설정하는 방법
	// model.addAttribute("",~) 와는 다르게
	// Cookie ~ = new Cookie("",~) 형태로 해줘야한다
	// session 과 마찬가지로 key 값은 같게
	public String login(HttpServletResponse response, Member member, Model model,
			@CookieValue(value = "cookie") String cookieValue, HttpSession session, HttpServletRequest request, RedirectAttributes variable) {
		// 입력한 정보의 아이디가 존재하는지 확인
		Member isMember = loginservice.selectById(member);
		// 존재한다면
		if (isMember != null) {
			// 패스워드 일치 로그인 성공
			if (member.getMember_password().equals(isMember.getMember_password())) {
				// main 페이지에서 c:if 로그인,로그아웃문 출력하기위해
				model.addAttribute("LoginMember", true);
				// session에 LGmember 저장
				model.addAttribute("Member", isMember);
				// 웹소켓 아이디값
				session.setAttribute("loginMemberId", member.getMember_id());

				System.out.println("소켓 모델의 session 값 : " + member.getMember_id());
				Map<String, Object> modelMap = model.asMap();
				System.out.println("모델의 session 값 : " + modelMap.get("Member"));

				List<Follow> list = new ArrayList<>();
				list = loginservice.followLoginSelect(isMember);
						
				System.out.println("로그인멤버 아이디: " + isMember.getMember_id());

				return "redirect:/";
			}
			// 로그인 실패 횟수를 누적하기 위한 코드 (model.addAttribute("cookie",cookieValue) 까지)
			int loginCount = Integer.parseInt(cookieValue);
			loginCount++;
			cookieValue = Integer.toString((loginCount));
			System.out.println("cookieValue : " + cookieValue);
			Cookie cookie1 = new Cookie("cookie", cookieValue);
			response.addCookie(cookie1);
			model.addAttribute("cookie", cookieValue);
			// 5번 로그인 실패 시
			if (loginCount >= 5) {
				// loginUp.jsp 에 로그인 5회 입력 실패 입력되도록 model 에 저장 후 loginUp.jsp 파일에 c:if 문으로 출력
				model.addAttribute("loginUp", true);
				// 아이디 , 비밀번호 , 이메일 3가지 입력하는 곳으로 이동
				return "login/loginUp";

			}
			// 패스워드가 틀림
			model.addAttribute("pwNotM", true);
			return "login/login";
		}
		// 로그인 실패 횟수를 누적하기 위한 코드 (model.addAttribute("cookie",cookieValue) 까지)
		// 위와 동일
		int loginCount = Integer.parseInt(cookieValue);
		loginCount++;
		cookieValue = Integer.toString((loginCount));
		System.out.println("cookieValue : " + cookieValue);
		Cookie cookie1 = new Cookie("cookie", cookieValue);
		response.addCookie(cookie1);
		model.addAttribute("cookie", cookieValue);
		if (loginCount >= 5) {
			// loginUp.jsp 에 로그인 5회 입력 실패 입력되도록 model 에 저장 후 loginUp.jsp 파일에 c:if 문으로 출력
			model.addAttribute("loginUp", true);

			return "login/loginUp";

		}
		// 아이디와 비밀번호중 하나 틀릴경우
		model.addAttribute("pwNotM", true);
		return "login/login";

	}

	// 로그인 강화 (POST)(자동입력방지문자 사용)
	@RequestMapping(value = "/loginUp", method = RequestMethod.POST) // 자동입력방지문자 입력값 저장되는 곳
	public String loginUp(Member member, Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "answer") String answer, HttpSession session) {
		// 자동입력방지문자 // js.user.model.CaptCha 에서 setAttribute 해준 NAME //
		// NAME =="simpleCaptcha";
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);

		// 입력한 문자와 출력된 문자가 일치
		if (captcha.isCorrect(answer)) {
			System.out.println("문자 일치 완료?");
			try {
				
				// 아이디,비밀번호,이메일 일치하는 회원정보가 있는지 확인
				Member member1 = loginservice.loginUp(member);
				if (member1.getMember_id() != null) {
					// 세션에 저장하기위한 attribute
					model.addAttribute("Member", member1);
					System.out.println("로그인 되니?");
					return "redirect:/";
				}
			} catch (Exception e) {
				// 로그인 실패 시
				model.addAttribute("loginF", true);
				return "login/loginUp";

			}

		} else {
			// 입력한 문자와 출력된 문자가 불일치
			model.addAttribute("loginF", true);
			return "login/loginUp";
		}

		return null;

	}

	// 아이디 찾기(POST)
	@ResponseBody
	@RequestMapping(value = "/findMyId/{member_email}/{member_phone}", method = RequestMethod.POST)
	public String MyId1(Member member) {

		String msg;
		// 전화번호와 이메일로 아이디 찾는 서비스
		Member member1 = findservice.findMyId(member);

		// findservice 해서 해당되는 정보 찾지 못한 경우
		if (member1.getMember_phone() == null) {
			msg = "해당되는 아이디가 없습니다.";
		} else {
			msg = member1.getMember_id();
		}

		return "{\"msg\" : \"" + msg + "\"}";

	}

	// 패스워드 찾기 첫번째(버튼눌러 이메일 전송)(form 에서 입력한 아이디와 이메일이 일치하는 정보가 있는지 DB 에서 확인 후 있으면 메일
	// 보내도록 설정)(POST)
	@ResponseBody
	@RequestMapping(value = "/findMyPw/{member_id}/{email}/{title}/{content}", method = RequestMethod.POST)
	public String MyId(@PathVariable("member_id") String member_id, @PathVariable("email") String toemail,
			@PathVariable("title") String title, @PathVariable("content") String content) {
		// 異뷀썑�뿉 愿�由ъ옄 硫붿씪濡� 蹂�寃�
		String setfrom = "zxcasdqwe155555@gmail.com";

		Member member = new Member();
		member.setMember_id(member_id);
		member.setMember_email(toemail);
		// form �뿉�꽌 �엯�젰諛쏆� �븘�씠�뵒�� 硫붿씪�씠 �씪移섑븯�뒗 �쑀��媛� �엳�뒗吏� 寃��깋�빐�꽌 �엳�떎硫� sendmail �릺�룄濡�
		Member member1 = findservice.findMyPw(member);
		String msg = null;
		try {
			// form �뿉�꽌 �엯�젰諛쏆� �븘�씠�뵒�� 硫붿씪�씠 �씪移섑븯�뒗 �쑀��媛� �엳�뒗寃쎌슦 硫붿씪蹂대궡湲�
			if (member1.getMember_id() != null) {

				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom(setfrom); // 蹂대궡�뒗�궗�엺 �깮�왂�븯嫄곕굹 �븯硫� �젙�긽�옉�룞�쓣 �븞�븿
				messageHelper.setTo(toemail); // 諛쏅뒗�궗�엺 �씠硫붿씪
				messageHelper.setSubject(title); // 硫붿씪�젣紐⑹� �깮�왂�씠 媛��뒫�븯�떎
				messageHelper.setText(content); // 硫붿씪 �궡�슜

				mailSender.send(message);
				msg = "전송완료";

			}
		} catch (Exception e) {
			// form �뿉�꽌 �엯�젰諛쏆� �븘�씠�뵒�� 硫붿씪�씠 �씪移섑븯�뒗 �쑀��媛� �뾾�뒗寃쎌슦
			msg = "전송실패";
			System.out.println(e);
		}

		return "{\"msg\" : \"" + msg + "\"}";
	}

	// 패스워드 찾기 두번째 ( 인증이 완료되면 새로운 비밀번호 설정)(POST)
	@RequestMapping(value = "/newPassword", method = RequestMethod.POST)
																		// 적어두기만해도 redirect 시 세션값 넘어가지 않음
	public String newPassword(memberUpdate memberUpdate, Model model, RedirectAttributes redirectAttributes) {

		
		
		Member member = new Member();
		member.setMember_id(memberUpdate.getMember_id());
		member.setMember_email(memberUpdate.getMember_email());
		
			// 재차확인 (이메일 전송 후 아이디 수정하는 경우가 있을 수 있으므로)
			Member member1 = findservice.findMyPw(member);
			// form 에서 입력받은 아이디와 메일이 일치하는 유저가 있는경우 메일보내기
			if (member1 != null) {
				// 패스워드 수정 날짜 설정
				Date date = new Date();
				SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
				String member_updatePassword_date = smdf.format(date);
				memberUpdate.setMember_updatePassword_date(member_updatePassword_date);
				// 새로운 비밀번호 설정
				findservice.newPassword(memberUpdate);
				return "redirect:/find/myPasswordSuc";

			}
		
		// form 에서 입력받은 아이디와 메일이 일치하는 유저가 없는경우 ( 메일만 전송하고 아이디를 바꿔 새로운 비밀번호 수정하려 하는 경우 )
		model.addAttribute("IdEmailNotMatch", true);
		return "/find/myPassword";

	}
	
	@RequestMapping("/alert/{rv_board_index}")
	public String alert(Model model, Alert alert, 	
			@ModelAttribute("Member")Member sessionMember,
			@PathVariable("rv_board_index") int rv_board_index) {

		alert.setMember_id(sessionMember.getMember_id());
		alert.setRv_board_index(rv_board_index);

		Object result = rv_boardService.insertAlert(alert);
		model.addAttribute("alertResult", result);
		return "article/alert";
	}
	
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String write() {
		return "article/articleWrite";
	} 
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(Model model, Rv_Board rv_Board, @ModelAttribute("Member") Member sessionMember,
			HttpServletRequest request, @RequestParam("files") ArrayList<MultipartFile> multipartFile,
										@RequestParam("rv_board_title") String rv_board_title,
										@RequestParam("rv_board_content") String rv_board_content,
										@RequestParam("rv_board_Street_name_address") String rv_board_Street_name_address
			) {
		
//		to upload file
//		fileUploadService.saveFile(realPath, file);
//    	
//        String fileName = file.getFile().getOriginalFilename(); // 원본 파일명
//        long fileSize = file.getFile().getSize(); // 원본 파일 크기
//        
//        HashMap< String, Object > map = new HashMap< String, Object>();
//        map.put("fileName", fileName);
//        map.put("fileSize", fileSize);
//        model.addAttribute("uploadFile", map);
//        
//        //DB 
//		article.setMember_id(sessionMember.getMember_id());
//		article.setRv_board_picture(fileName);
//		Object result = rv_boardService.insertArticle(article);
//		model.addAttribute("writeArticle", result);
		
		
		
		Random random = new Random();
		
		System.out.println("M:"+multipartFile);
		  for( MultipartFile mf : multipartFile ) {
		         FileImage fileimage = new FileImage();
		         fileimage.setFile(mf);
		         String fileName = mf.getOriginalFilename();
		         String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/image");
		         System.out.println("realPath:" +realPath);
		       
		         
		         System.out.println("realPath: " + realPath);
		         System.out.println("fileName: " + fileName);
		         
		      // multipartFile.getOriginalFilename() 이 있을때 작동하도록 if문 (앞에 ! 붙어있음) // 이미지 첨부 하지않을 시 substring 에서 오류발생하므로
		 	if(!fileName.equals("")) {
		         // 데이터베이스에 파일 저장할때는 /WEB-INF/ 경로 빼고 저장 // ${} 표현식으로 바로 불러오게 하기위해서 
		         // 파일 데이터베이스에 저장하기위한 절대경로	// 절대경로 가져오기 // WEB-INF 접근위한 mvc:resource해준 /message/image/ // 파일 고유의 이름만 출력하기위한 substring (확장자만 제거) 		// 랜덤값 			// 뒤에 .jpg,.png ... (확장자) 만 선택하기 위한 코드 // substring과 indexOf 찾아보기
		     
		       PictureAbsolutePath_name = fileName.substring(0, mf.getOriginalFilename().indexOf("."))+"_"+random.nextLong()+fileName.substring(mf.getOriginalFilename().indexOf("."));
		      
		       PictureAbsolutePath = request.getContextPath()+"/image/" + PictureAbsolutePath_name;

		       // 디비저장
		       rv_Board.setRv_board_picture(PictureAbsolutePath);
		       
		       // 파일 저장
		       fileimage.fileImage(realPath, PictureAbsolutePath_name);
		       System.out.println("메인 게시물 파일이름 : " + PictureAbsolutePath_name);
		       System.out.println("메인 게시물 파일경로 + 이름 : " + PictureAbsolutePath);
		       
		 		}
		  }
		
		rv_Board.setMember_id(sessionMember.getMember_id());
		rv_Board.setRv_board_title(rv_board_title);
		rv_Board.setRv_board_content(rv_board_content);
		
		// 주소
		rv_Board.setRv_board_Street_name_address(rv_board_Street_name_address);
		// 인설트
		model.addAttribute("writeArticle", rv_boardService.insertArticle(rv_Board));
		
		return "redirect:/";

	}
	
	@ModelAttribute("realPath")
	public String getRealPath(HttpServletRequest request) { 	
		String realPath = request.getRealPath("/WEB-INF/resources/upload/");
		return realPath;
	}
	
    @RequestMapping(value="/file/download", method=RequestMethod.GET)
    public String filesSubmit(Model model, @ModelAttribute("realPath") String realPath) {
    	
        model.addAttribute("downloadFiles", downloadService.downloadFiles(realPath));
         
        return "file/download";
    }
    
	@RequestMapping(value="reply", method=RequestMethod.GET)
	public String reply() {
		return "reply";
	}
	@ResponseBody
	@RequestMapping(value="/reply/{rv_board_index}/{reply_content}", method=RequestMethod.POST)
	public String reply(
			@ModelAttribute("Member")Member sessionMember,
			@PathVariable("rv_board_index") int rv_board_index,
			@PathVariable("reply_content") String reply_content,
			Reply reply) {
		
		System.out.println(rv_board_index);
		System.out.println(reply_content);
		reply.setMember_id(sessionMember.getMember_id());
		reply.setRv_board_index(rv_board_index);
		reply.setReply_content(reply_content);
		
		Object result = rv_boardService.insertReply(reply);
		
		String msg;
		if( result == null )
			msg = "실패"; 
		else
			msg = "등록";

		return "{\"value\" : \"" + msg + "\"}";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/replyList/{rv_board_index}", method=RequestMethod.POST)
	public String replyList(@PathVariable("rv_board_index") int rv_board_index, Reply reply) {
		reply.setRv_board_index(rv_board_index);
		Object result = rv_boardService.selectReplyList(reply);
		
		Gson gson = new Gson();
		String json = gson.toJson(result); 
		System.out.println(json);
		
		return json;
	}
	
	
	// 검색기능(POST)
		@ResponseBody
		@RequestMapping(value = "/main_rv_board_address/{article_address}", method = RequestMethod.POST)
		public Map<String, List<Object>> rv_board_search(Model model, HttpServletRequest request, HttpServletResponse response,
				HttpSession session, @PathVariable("article_address") String article_address ) throws Exception {
			
			Map<String, List<Object>> map = new HashMap<String, List<Object>>();
			String search_a;
			
			
				switch (article_address) {
					case "전국":
						search_a = "";
						break;
					default:
						search_a = article_address;
						break;
				}
			
			
			// 웹페이지 검색에서 검색하는 String 값 
			String searchNew = search_a + "%";		
			System.out.println(searchNew);
			// 로그인 하지 않았을 경우
			if(session.getAttribute("rv_board_list") == null) {
			
			// 소개글 가져오기 위한 리스트 배열
			List<Object> list = new ArrayList<>();
			
			// 문자열을 지닌 아이디값을 지닌 멤버의 정보를 불러옴
			try {
				list = rv_boardService.getBoard_search(search_a);				
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			System.out.println(list);
			map.put("rv_board_list", list);		

		}
			return map;
	}
	
	
	
}