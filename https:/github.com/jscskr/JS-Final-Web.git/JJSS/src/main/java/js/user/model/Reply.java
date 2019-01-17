package js.user.model;

import java.util.Date;

public class Reply {
	private int rv_board_index;
	private int reply_index;
	private String member_id;
	private String reply_content;
	private Date reply_post_date;
	private Date reply_modify_date;
	
	public int getRv_board_index() {
		return rv_board_index;
	}
	public void setRv_board_index(int rv_board_index) {
		this.rv_board_index = rv_board_index;
	}
	public int getReply_index() {
		return reply_index;
	}
	public void setReply_index(int reply_index) {
		this.reply_index = reply_index;
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
