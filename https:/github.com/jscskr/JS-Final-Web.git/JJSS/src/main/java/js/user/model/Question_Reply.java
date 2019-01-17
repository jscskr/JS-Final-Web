package js.user.model;

import java.util.Date;

public class Question_Reply {
	private int reply_index;
	private int question_id;
	private String member_id;
	private String reply_content;
	private Date reply_post_date;
	private Date reply_modify_date;
	
	public int getReply_index() {
		return reply_index;
	}
	public void setReply_index(int reply_index) {
		this.reply_index = reply_index;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReply_post_date() {
		return reply_post_date;
	}
	public void setReply_post_date(Date reply_post_date) {
		this.reply_post_date = reply_post_date;
	}
	public Date getReply_modify_date() {
		return reply_modify_date;
	}
	public void setReply_modify_date(Date reply_modify_date) {
		this.reply_modify_date = reply_modify_date;
	}
	
	
	
}
