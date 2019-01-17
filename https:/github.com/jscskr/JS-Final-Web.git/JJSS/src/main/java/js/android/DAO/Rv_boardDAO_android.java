package js.android.DAO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import js.user.model.*;

@Repository
public class Rv_boardDAO_android {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String str = "js.user.model.rv_boardMapper";

	
	// 리뷰 게시글 작성
	public int writeRvBoard(Rv_board_android rv_board_android) {
		
		return sqlSession.insert(str+".writeRvBoard",rv_board_android);
		
	}
	
	// 모든 리뷰게시글 가져오기
	public List<Rv_board_android> selectAllRvBoard(){
		return sqlSession.selectList(str+".selectAllRvBoard");
	}
	
	// 선택된 리뷰게시글 가져오기
	public Rv_board_android rv_boardSelected(Rv_board_android rv_board_android) {
		return sqlSession.selectOne(str+".rv_boardSelected",rv_board_android);
	}
	
	// 해당 게시글에 댓글 추가하기
	public int rv_board_insertAlert(Alert alert) {
		return sqlSession.insert(str+".rv_board_insertAlert",alert);
	}
	
	// 해당 게시글의 댓글 모두 가져오기
	public List<Alert> rv_boardLoadAlert(Alert alert) {
		System.out.println("모두불러오는중");
		return sqlSession.selectList(str+".rv_boardLoadAlert",alert);
	}
	
	// 해당 게시글의 전체 댓글 수
	public int rv_boardCountAlert(Rv_board_android rv_board_android) {
		return sqlSession.selectOne(str+".rv_boardCountAlert",rv_board_android);
	}
	
	// 해당 게시글 좋아요 클릭
	public int rv_board_favorite(HashMap<String, String> map) {
		return sqlSession.insert(str+".rv_board_favorite",map);
	}
	
	// 해당 게시글 좋아요 중복검사
	public String rv_board_duplicated(HashMap<String, String> map) {
		return sqlSession.selectOne(str+".rv_board_duplicated",map);
	}
	
	// 해당 게시글 좋아요 삭제
	public int rv_board_favoriteDelete(HashMap<String, String> map) {
		return sqlSession.delete(str+".rv_board_favoriteDelete",map);
	}
	
	// 해당 게시글의 좋아요 갯수
	public int rv_board_favoriteCount(Rv_board_android rv_board_android) {
		return sqlSession.selectOne(str+".rv_board_favoriteCount",rv_board_android);
	}
	
	// 해당 게시글 조회수 up
	public int rv_board_countUp(Rv_board_android rv_board_android) {
		return sqlSession.update(str+".rv_board_countUp",rv_board_android);
	}
	
	// 게시글 삭제
	public int deleteRv_board(Rv_board_android rv_board_android) {
		return sqlSession.delete(str+".deleteRv_board",rv_board_android);
	}
	
	// 해당 게시글 신고
	public int insertReportRv_board(Report report) {
		return sqlSession.insert(str+".insertReportRv_board",report);
	}
	
	// 해당 게시글 신고한 유저가 중복 신고하려는지 검사
	public Report alreadyReportRv_board(Report report) {
		return sqlSession.selectOne(str+".alreadyReportRv_board",report);
	} 

	// 해당 게시글의 댓글 신고
	public int insertReportAlert(Report report) {
		return sqlSession.insert(str+".insertReportAlert",report);
	}
	
	// 해당 댓글 신고한 유저가 중복 신고하려는지 검사
	public Report alreadyReportAlert(Report report) {
		return sqlSession.selectOne(str+".alreadyReportAlert",report);
	}
	
	// 댓글 삭제
	public int deleteAlert(Alert alert) {
		return sqlSession.delete(str+".deleteAlert",alert);
	}
	
	// 댓글 클릭시 멤버 정보 찾아 뿌려주기
	   public Member findByAlert(Alert alert) {
	      return sqlSession.selectOne(str+".findByAlert",alert);
	   }
	   
	   
	   
	   // 게시글 멤버정보 클릭시 멤버 정보 찾아 뿌려주기
	   public Member findByBoard(Rv_board_android rv_board_android) {
	      return sqlSession.selectOne(str+".findByBoard",rv_board_android);
	   }
	   
	   // 내가쓴글
	   public List<Rv_board_android> memberWrote(Member member) {
	      return sqlSession.selectList(str+".memberWrote",member);
	   }
	
	
}
