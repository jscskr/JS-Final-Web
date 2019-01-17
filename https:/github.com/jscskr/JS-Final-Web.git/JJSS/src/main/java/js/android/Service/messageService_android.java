package js.android.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import js.user.model.Member;
import js.user.model.Message;
import js.user.model.Report;
import js.android.DAO.messageDAO_android;

@Service
public class messageService_android {


	@Autowired
	private messageDAO_android dao;
	
	public int sendMessage(Message message) {
		return this.dao.sendMessage(message);
	}
	
	public int receiveMessage(Message message) {
		return this.dao.receiveMessage(message);
	}
	
	public List<Message> receiveMessageStore(Message message){
		return this.dao.receiveMessageStore(message);
		
	}
	
	public List<Message> sendMessageStore(Message message){
		return this.dao.sendMessageStore(message);
	}
	
	public Message receiveMessageStoreSelected(Message message) {
		return this.dao.receiveMessageStoreSelected(message);
	}
	
	public Message sendMessageStoreSelected(Message message) {
		return this.dao.sendMessageStoreSelected(message);
	}
	
	public int deleteReceiveMessage(Message message) {
		return this.dao.deleteReceiveMessage(message);
	}
	
	public int deleteSendMessage(Message message) {
		return this.dao.deleteSendMessage(message);
	}
	
	public int insertReportMessage(Report report) {
		return this.dao.insertReportMessage(report);
	}
	
	public Report alreadyReportMessage(Report report) {
		return this.dao.alreadyReportMessage(report);
	}
	
}
