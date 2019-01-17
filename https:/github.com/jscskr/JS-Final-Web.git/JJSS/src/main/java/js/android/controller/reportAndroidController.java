package js.android.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import js.android.Service.Rv_boardService_android;
import js.android.Service.messageService_android;
import js.user.model.Alert;
import js.user.model.Report;

@Controller
public class reportAndroidController {

	@Autowired
	private Rv_boardService_android rv_boardService;

	@Autowired
	private messageService_android messageService;

	// 신고
	// Report model 안에 게시글,댓글,메시지 신고처리 다해준다
	@ResponseBody
	@RequestMapping(value = "/android/report", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String report(Report report) {

		String report_reason = null;
		try {
			report_reason = URLDecoder.decode(report.getReport_reason(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		report.setReport_reason(report_reason);
		// 게시글 신고 처리
		if (report.getRv_board_index() != null) {
			// 신고한 유저가 중복 신고하는지 검사
			Report isAlreadyReport = rv_boardService.alreadyReportRv_board(report);

			// 날짜처리
			Date date = new Date();
			SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			String currentTime = smdf.format(date);
			report.setReport_date(currentTime);
			if (isAlreadyReport == null) {
				rv_boardService.insertReportRv_board(report);
			} else {
				return "{\"msg\":\"alreadyReport\"}";
			}

		}

		// 댓글 신고 처리
		if (report.getAlert_index() != null) {
			Report isAlreadyReport = rv_boardService.alreadyReportAlert(report);
			// 날짜처리
			Date date = new Date();
			SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			String currentTime = smdf.format(date);
			report.setReport_date(currentTime);
			if (isAlreadyReport == null) {
			rv_boardService.insertReportAlert(report);
			} else {
				return "{\"msg\":\"alreadyReport\"}";
			}
		}

		// 메시지 신고 처리
		if (report.getMessage_id() != null) {
			Report isAlreadyReport = messageService.alreadyReportMessage(report);
			// 날짜처리
			Date date = new Date();
			SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			String currentTime = smdf.format(date);
			report.setReport_date(currentTime);
			if (isAlreadyReport == null) {
			messageService.insertReportMessage(report);
			} else {
				return "{\"msg\":\"alreadyReport\"}";
			}
		}
return "{\"msg\":\"reportSuc\"}";
	}
}
