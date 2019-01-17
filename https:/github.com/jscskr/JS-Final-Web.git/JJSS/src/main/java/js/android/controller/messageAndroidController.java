package js.android.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import js.android.Service.messageService_android;
import js.user.model.FileImage;
import js.user.model.Member;
import js.user.model.Message;
import com.google.gson.Gson;

@Controller
public class messageAndroidController {
	
	@Autowired
	private messageService_android service;
	// 메시지
		@ResponseBody
		@RequestMapping(value = "/android/sendMessage", method = RequestMethod.POST)
		public String android5(MultipartFile file, Message message, HttpServletRequest request, Member member) {
			System.out.println(message.getMessage_content());

			message.setMember_id(member.getMember_id());
			// 안드로이드에서 해결 message.setMember_receiver(member_receiver);
			// 안드로이드에서 해결 message.setMessage_title(message_title);
			// 안드로이드에서 해결 message.setMessage_content(message_content);
			// DB 에서 해결 message.setMessage_id(message_id);
			// 파일처리
			FileImage fileimage = null;
			String path = null;
			String fileName = null;

			if (file != null) {
				fileimage = new FileImage();
				fileimage.setFile(file);

				// C\data~ 절대경로
				path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/android/Member/Message/message_images");

				fileName = member.getMember_id() + "_" + file.getOriginalFilename();

				String DBpath =  request.getContextPath()
						+ "/android/Member/Message/message_images/" + member.getMember_id() + "_"
						+ file.getOriginalFilename();
				message.setMessage_picture(DBpath);

			}

			// 프로필처리
			message.setMessage_profil_pic(member.getMember_profile_pic());

			// 날짜처리
			Date date = new Date();
			SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			String currentTime = smdf.format(date);
			message.setMessage_send_date(currentTime);

			String msg;
			int result = service.sendMessage(message);
			if (result == 0) {
				msg = "fail";
			} else {
				msg = "success";
				if (file != null) {
					fileimage.fileImage(path, fileName);
				}
				service.receiveMessage(message);
			}
			return "{\"msg\":\"" + msg + "\"}";
		}

		// MessageActivity 에서 사용
		// 화면에 받은 전체 메시지 출력
		@ResponseBody
		@RequestMapping(value = "/android/receiveMessageStore", method = RequestMethod.POST)
		public String receiveMessageStore(Message message) {
			
			List<Message> messageList = service.receiveMessageStore(message);
			System.out.println(messageList);
			Gson gson = new Gson();
			String messageListToJson = gson.toJson(messageList);

			return messageListToJson;

		}
		
		// 화면에 보낸 전체 메시지 출력
				@ResponseBody
				@RequestMapping(value = "/android/sendMessageStore", method = RequestMethod.POST)
				public String sendMessageStore(Message message) {
					
					List<Message> messageList = service.sendMessageStore(message);
					System.out.println(messageList);
					Gson gson = new Gson();
					String messageListToJson = gson.toJson(messageList);

					return messageListToJson;

				}
		

		// MessageReceiveSelected 에서 사용
		// MessageActivity 에서 선택한 메시지의 아이디값으로
		// 해당 메시지가 출력
		@ResponseBody
		@RequestMapping(value = "/android/receiveMessageStore/selected", method = RequestMethod.POST)
		public String receiveMessageStoreSelected(Message message) {
			System.out.println("message_id:" + message.getMessage_id());
			Message selectedMessage = service.receiveMessageStoreSelected(message);

			Gson gson = new Gson();
			String selectedMessageJson = gson.toJson(selectedMessage);

			return selectedMessageJson;

			
		}
		
		@ResponseBody
		@RequestMapping(value = "/android/sendMessageStore/selected", method = RequestMethod.POST)
		public String sendMessageStoreSelected(Message message) {
			System.out.println("message_id:" + message.getMessage_id());
			Message selectedMessage = service.sendMessageStoreSelected(message);

			Gson gson = new Gson();
			String selectedMessageJson = gson.toJson(selectedMessage);

			return selectedMessageJson;

			
		}
		
		// 받은 메시지 삭제
		@ResponseBody
		@RequestMapping(value = "/android/receiveMessageStore/selected/deleteMessage", method = RequestMethod.POST)
		public void deleteReceiveMessage(Message message) {
			System.out.println("받은 메시지 삭제");
			service.deleteReceiveMessage(message);
			
		}
		
		
		// 보낸 메시지 삭제
		@ResponseBody
		@RequestMapping(value = "/android/sendMessageStore/selected/deleteMessage", method = RequestMethod.POST)
		public void deleteSendMessage(Message message) {
			System.out.println("보낸 메시지 삭제");
			
			service.deleteSendMessage(message);
			
		}
		
		
}
