package js.user.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import js.user.model.Question_Reply;
import js.admin.model.Question;

@Repository
public class Question_Reply_DAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String strNameSpace = "QuestionReplyMapper";
	

	
	public List<Question_Reply> selectReplyList(Question_Reply question_Reply) {
		return sqlSession.selectList(strNameSpace + ".replyList", question_Reply);
	}
	

	public int insertReply(Question_Reply question_Reply) {
		return sqlSession.insert(strNameSpace + ".insertReply", question_Reply);
	}
	
	public int deleteReply(Question_Reply question_Reply) {
		return sqlSession.delete(strNameSpace + ".deleteReply", question_Reply);
	}
	
	public int updateReply(Question_Reply question_Reply) {
		return sqlSession.update(strNameSpace + ".updateReply", question_Reply);
	}
	
	
}
