package js.android.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import js.android.DAO.etcDAO_android;
import js.user.model.Friend;
import js.user.model.Member;

@Service
public class etcService_android {
   @Autowired
   private etcDAO_android dao;
   
   public List<Member> searchId(Member member){
      return this.dao.searchId(member);
   }
   
   public int insertFriend(Friend friend) {
      return this.dao.insertFriend(friend);
   }
   
   public List<Friend> waitFriendList(Friend friend) {
      return this.dao.waitFriendList(friend);
   }
   
   public int insertFriendAccept(Friend friend) {
      return this.dao.insertFriendAccept(friend);
   }
}