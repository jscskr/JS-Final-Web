package js.android.controller;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import js.android.Service.etcService_android;
import js.user.model.Friend;
import js.user.model.Member;
import js.user.model.Rv_board_android;

@Controller
public class etcAndroidController {
   Gson gson = new Gson();

   @Autowired
   private etcService_android service;
   
   // 회원 이름 검색
         @ResponseBody
         @RequestMapping(value = "/android/rv_board/searchId", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
         public String searchId(Member member) {
            List<Member> memberList = service.searchId(member);
            String JsonMember = gson.toJson(memberList);
            System.out.println(JsonMember);
   return JsonMember;
         }
         
         
         // 친구 추가 
         
         @ResponseBody
         @RequestMapping(value = "/android/insertFriend", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
         public void insertFriend(Friend friend) {
            
            
            service.insertFriend(friend);
         }
         
         // 친구 대기
         @ResponseBody
         @RequestMapping(value = "/android/waitFriendList", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
         public String waitFriendList(Friend friend) {
            
            List<Friend> friendList = service.waitFriendList(friend);
            String JsonFriendList = gson.toJson(friendList);
            System.out.println(JsonFriendList);
            return JsonFriendList;
         }
         
         // 친구 수락
         
                  @ResponseBody
                  @RequestMapping(value = "/android/insertFriendAccept", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
                  public void insertFriendAccept(Friend friend) {
                     
                     service.insertFriendAccept(friend);
                     
                  }
   
}
