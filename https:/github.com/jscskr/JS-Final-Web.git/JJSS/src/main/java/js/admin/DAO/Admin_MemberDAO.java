package js.admin.DAO;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import js.admin.model.*;



@Repository
public class Admin_MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	private String strNameSpace = "ADMemberMapper";
	

	//맴버리스트
	public List<Member> selectList(){
		return sqlSession.selectList(strNameSpace + ".userList");
	}
	
	//맴버 카운트
	public int count() {
		return sqlSession.selectOne(strNameSpace + ".count");
	}
	//리스트
	public List <Member> page(HashMap<String, Integer> map){
		
		return sqlSession.selectList(strNameSpace + ".page", map);
	}
	
	// 게시글 신고 리스트
	   public List<Report> reportRv_board(Report report){
	      return sqlSession.selectList(strNameSpace+".reportRv_board",report);
	   }
	   
	   // 댓글 신고 리스트
	      public List<Report> reportAlert(Report report){
	         return sqlSession.selectList(strNameSpace+".reportAlert",report);
	      }
	      
	      // 메시지 신고 리스트
	      public List<Report> reportMessage(Report report){
	         return sqlSession.selectList(strNameSpace+".reportMessage",report);
	      }
	
	

}
