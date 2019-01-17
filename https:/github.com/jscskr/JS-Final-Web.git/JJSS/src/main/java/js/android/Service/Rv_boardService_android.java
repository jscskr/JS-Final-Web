package js.android.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import js.user.model.Alert;
import js.user.model.Member;
import js.user.model.Message;
import js.user.model.Report;
import js.user.model.Rv_board_android;
import js.android.DAO.Rv_boardDAO_android;

@Service
public class Rv_boardService_android {

	@Autowired
	private Rv_boardDAO_android dao;
	
	
	
	public int writeRvBoard(Rv_board_android rv_board_android) {
		
		return this.dao.writeRvBoard(rv_board_android);
		
	}
	
	public List<Rv_board_android> selectAllRvBoard(){
		return this.dao.selectAllRvBoard();
	}
	
	public Rv_board_android rv_boardSelected(Rv_board_android rv_android_board) {
		return this.dao.rv_boardSelected(rv_android_board);
	}
	
	public int rv_board_insertAlert(Alert alert) {
		return this.dao.rv_board_insertAlert(alert);
	}
	
	public List<Alert> rv_boardLoadAlert(Alert alert){
		return this.dao.rv_boardLoadAlert(alert);
	}
	
	public int rv_boardCountAlert(Rv_board_android rv_board_android) {
		return this.dao.rv_boardCountAlert(rv_board_android);
	}
	
	public int rv_board_favorite(HashMap<String, String> map) {
		return this.dao.rv_board_favorite(map);
	}
	
	public String rv_board_duplicated(HashMap<String,String> map) {
		return this.dao.rv_board_duplicated(map);
	}
	
	public int rv_board_favoriteDelete(HashMap<String, String> map) {
		return this.dao.rv_board_favoriteDelete(map);
	}
	
	public int rv_board_favoriteCount(Rv_board_android rv_board_android) {
		return this.dao.rv_board_favoriteCount(rv_board_android);
	}
	
	public int rv_board_countUp(Rv_board_android rv_board_android) {
		return this.dao.rv_board_countUp(rv_board_android);
	}
	
	public int deleteRv_board(Rv_board_android rv_board_android) {
		return this.dao.deleteRv_board(rv_board_android);
	}
	
		public int insertReportRv_board(Report report) {
			return this.dao.insertReportRv_board(report);
		}
		
		public Report alreadyReportRv_board(Report report) {
			return this.dao.alreadyReportRv_board(report);
		}
		
		public int insertReportAlert(Report report) {
			return this.dao.insertReportAlert(report);
		}
		
		public Report alreadyReportAlert(Report report) {
			return this.dao.alreadyReportAlert(report);
		}
		
		public int deleteAlert(Alert alert) {
			return this.dao.deleteAlert(alert);
		}
		
		public Member findByAlert(Alert alert) {
	         return this.dao.findByAlert(alert);
	      }
	      
	      public Member findByBoard(Rv_board_android rv_board_android) {
	         return this.dao.findByBoard(rv_board_android);
	      }
	      
	      
	      
	      public List<Rv_board_android> memberWrote(Member member){
	         return this.dao.memberWrote(member);
	      }
}
