package js.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import js.user.DAO.ArticleDAO;
import js.user.model.Alert;
import js.user.model.Member;
import js.user.model.Reply;
import js.user.model.Rv_Board;

@Service
public class ArticleListService {
	@Autowired
	private ArticleDAO dao;

	public Object getArticleList() {
		return this.dao.selectList();
	}
	
	public Object getMyArticleList(Rv_Board rv_board) {
		return this.dao.selectMyList(rv_board);
	} 
	
	public Object insertAlert(Alert alert) {
		return this.dao.alertInsert(alert);
	}
	
	public Object insertArticle(Rv_Board rv_Board) {
		return this.dao.articleInsert(rv_Board);
	}
	
	public Object deleteArticle(Rv_Board article) {
		return this.dao.articleDelete(article); 
	}
	
	public Object modifyArticle(Rv_Board article) {
		return this.dao.articleUpdate(article);
	}
	
	public Object insertReply(Reply reply) {
		return this.dao.insertReply(reply);
	}
	
	public Object selectReplyList(Reply reply) {
		return this.dao.selectReplyList(reply);
	}
	
	
	// 유저프로필 자기가 쓴
	public List <Rv_Board> getUser_RvBoard_List(String userid) {
		return this.dao.User_RvBoard_List(userid);
	}
	//메인페이지 베스트 3
	public Object best_board() {
		return this.dao.best_board();
	}
	
	// 메인페이지 게시물 검색
	// 검색 시 멤버 클래스의 정보와 소개를 합한 테이블의 클래스인 memberIntroAll 클래스를 셀렉트 하는 메서드
	public List<Object> getBoard_search(String address) {
		return this.dao.board_search(address);
	}

	
		
}
