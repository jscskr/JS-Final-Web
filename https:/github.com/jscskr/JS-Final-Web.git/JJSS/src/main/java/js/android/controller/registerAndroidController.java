package js.android.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import js.user.model.Member;
import js.user.service.registService;

@RestController
public class registerAndroidController {		
	
	@Autowired
	private registService registservice;
	
	@RequestMapping(value = "/android/register", method = RequestMethod.GET)
	public void restful_Android_register_get() {		
		
		String id = "test";	
	}
	
	
	@RequestMapping(value = "/android/register", method = RequestMethod.POST)
	public void restful_Android_register_post(Member member, MultipartFile image, HttpServletRequest request) throws IllegalStateException, IOException {						
		String member_Street_name_address = null;
		String member_Detailed_Address = null;
		// 한글깨짐 방지
				try {
					member_Street_name_address = URLDecoder.decode(member.getMember_Street_name_address(), "UTF-8");
					member_Detailed_Address = URLDecoder.decode(member.getMember_Detailed_Address(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(member_Street_name_address);

				// 멤버 도로명주소
				member.setMember_Street_name_address(member_Street_name_address);

				// 멤버 상세주소
				member.setMember_Detailed_Address(member_Detailed_Address);
				
										
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = dayTime.format(new Date(time));
		
		member.setMember_register_date(str);		
		
		// 프로필 사진 추가안하는 경우도 있으므로
		// 초기 프로필 사진 처리
				if(member.getMember_profile_pic() == null) {
					member.setMember_profile_pic("/JS/android/Member/Profile/profile_images/originalProfile.jpg");
				}
		if(image != null) {
		String originalFileName = image.getOriginalFilename();
		System.out.println("OriginalFileName :" + originalFileName);		
		
		String saveFileName = member.getMember_id()+ "_" + originalFileName;
		String realpath = request.getSession().getServletContext().getRealPath("/WEB-INF/android/Member/Profile/profile_images");
		String imagePath = request.getContextPath() + "/android/Member/Profile/profile_images/"+ member.getMember_id()+ "_" + originalFileName ;
		
		member.setMember_profile_pic(imagePath);
		
		File dir = new File(realpath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File imageFile = new File(dir, saveFileName);
		
		image.transferTo(imageFile);
		}
		
		int check;
		check = registservice.insertAndroidMember(member);
						
		if(check==1){
			System.out.println("SQL에 쿼리가 성공적");
		} else {
			System.out.println("SQL에 쿼리가 실패적");
		}
		
	}

}
