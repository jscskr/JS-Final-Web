package js.android.DAO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import js.user.model.Friend;
import js.user.model.Member;

@Repository
public class etcDAO_android {

   @Autowired
   private SqlSessionTemplate sqlSession;
   private String str = "MemberMapper";
   
   public List<Member> searchId(Member member){
      return sqlSession.selectList(str+".searchId",member);
   }
   
   // 친구 추가
   public int insertFriend(Friend friend) {
      return sqlSession.insert(str+".insertFriend",friend);
   }
   

   public List<Friend> waitFriendList(Friend friend){
      return sqlSession.selectList(str+".waitFriendList",friend);
   }
   
// 친구 수락
   public int insertFriendAccept(Friend friend) {
      return sqlSession.insert(str+".insertFriendAccept",friend);
   }
}