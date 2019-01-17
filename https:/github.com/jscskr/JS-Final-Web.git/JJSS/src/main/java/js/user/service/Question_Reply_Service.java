package js.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import js.user.DAO.Question_Reply_DAO;
import js.user.model.Question_Reply;


@Service
public class Question_Reply_Service {
	@Autowired
	private Question_Reply_DAO dao;

	
	
	public Object insertReply(Question_Reply question_Reply) {
		return this.dao.insertReply(question_Reply);
	}
	
	public Object selectReplyList(Question_Reply question_Reply) {
		return this.dao.selectReplyList(question_Reply);
	}
	
	public Object deleteReply(Question_Reply question_Reply) {
		return this.dao.deleteReply(question_Reply);
	}
	
	public Object updateReply(Question_Reply question_Reply) {
		return this.dao.updateReply(question_Reply);
	}
}
