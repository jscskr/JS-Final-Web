package js.android.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import js.user.model.Member;
import js.user.model.Message;
import js.user.model.Report;

@Repository
public class messageDAO_android {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String str = "MessageMapper_android";
	
	// 보낸 메시지 추가
	public int sendMessage(Message message) {
		return sqlSession.insert(str + ".sendMessage", message);
	}

	// 받은메시지 추가
	public int receiveMessage(Message message) {
		return sqlSession.insert(str + ".receiveMessage", message);
	}

	// 받은 전체 메시지
	public List<Message> receiveMessageStore(Message message) {
		return sqlSession.selectList(str + ".receiveMessageStore", message);
	}
	
	// 보낸 전체 메시지
	public List<Message> sendMessageStore(Message message){
		return sqlSession.selectList(str + ".sendMessageStore",message);
	}

	// 받은 선택된 메시지
	public Message receiveMessageStoreSelected(Message message) {
		return sqlSession.selectOne(str + ".receiveMessageStoreSelected", message);
	}
	
	// 보낸 선택된 메시지
		public Message sendMessageStoreSelected(Message message) {
			return sqlSession.selectOne(str + ".sendMessageStoreSelected", message);
		}
		 
	
	// 받은 메시지 삭제
	public int deleteReceiveMessage(Message message) {
		return sqlSession.delete(str + ".deleteReceiveMessage",message);
	}
	
	// 보낸 메시지 삭제
		public int deleteSendMessage(Message message) {
			return sqlSession.delete(str + ".deleteSendMessage",message);
		}
		
		// 받은메시지 신고
		public int insertReportMessage(Report report) {
			return sqlSession.insert(str+".insertReportMessage",report);
		}
		
		// 메시지 중복 신고하는지 검사
		public Report alreadyReportMessage(Report report) {
			return sqlSession.selectOne(str+".alreadyReportMessage",report);
		}

	
}
