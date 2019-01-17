package js.user.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import js.user.model.Alert;
import js.user.model.Member;
import js.user.model.Reply;
import js.user.model.Rv_Board;
import js.user.model.Best_Board;

@Repository
public class ArticleDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String strNameSpace = "model.ArticleMapper";
	
	public List<Rv_Board> selectList() {
		return sqlSession.selectList(strNameSpace + ".articleList");
	}
	
	public List<Rv_Board> selectMyList(Rv_Board rv_board) {
		return sqlSession.selectList(strNameSpace + ".myArticleList", rv_board);
	}
	
	public List<Reply> selectReplyList(Reply reply) {
		return sqlSession.selectList(strNameSpace + ".replyList", reply);
	}
	
	public int alertInsert(Alert alert) {
		return sqlSession.insert(strNameSpace + ".articleAlert", alert);
	}
	
	public int articleInsert(Rv_Board rv_Board) {
		return sqlSession.insert(strNameSpace + ".articleWrite", rv_Board);
	}
	
	public int articleDelete(Rv_Board article) {
		return sqlSession.delete(strNameSpace + ".articleDelete", article);
	}
	
	public int articleUpdate(Rv_Board article) {
		return sqlSession.update(strNameSpace + ".articleUpdate", article);
	}
	
	public int insertReply(Reply reply) {
		return sqlSession.insert(strNameSpace + ".insertReply", reply);
	}
	
	// 유저프로필 자기가 쓴
	public List <Rv_Board> User_RvBoard_List(String member_id) {
		return sqlSession.selectList(strNameSpace + ".User_RvBoard_List",member_id);
	}
	// 메인페이지 배스트3 
	public List<Best_Board> best_board() {
		return sqlSession.selectList(strNameSpace + ".best_board");
	}
	
	// 검색어에 따른 메인페이지 게시물 셀렛트
	public List<Object> board_search(String address) {
		return sqlSession.selectList(strNameSpace + ".board_search", address);
	}

	
	

	
	
}
