package js.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import js.admin.model.*;
import js.admin.service.*;
import js.user.model.Member;


@Controller
public class AdminMainPageController {
	
	@Autowired
	private UserListService service;
	Gson gson = new Gson();

	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String admin_mainpage() {
		return "admin/page/adminmain";
	}
	

	@RequestMapping(value="/admin_warning_list", method=RequestMethod.GET)
	public String admin_warning_list() {
			return "admin/page/admin_warning_list";
	}
	

	@RequestMapping(value="/game", method=RequestMethod.GET)
	public String game() {
			return "game/index";
	}
	
	// 안드로이드
	   
	   // 게시글 신고 내역
	   @ResponseBody
	   @RequestMapping(value = "/android/reportList/Rv_board", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	   public String reportRv_board(Report report) {
	      
	      List<Report> reportList= service.reportRv_board(report);
	      String JsonReportList = gson.toJson(reportList);
	      System.out.println(JsonReportList);
	      return JsonReportList;
	   }
	   
	   // 댓글 신고 내역
	      @ResponseBody
	      @RequestMapping(value = "/android/reportList/Alert", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	      public String reportAlert(Report report) {
	         
	         List<Report> AlertList= service.reportAlert(report);
	         String JsonAlertList = gson.toJson(AlertList);
	         System.out.println(JsonAlertList);
	         return JsonAlertList;
	      }
	      
	      // 메시지 신고 내역
	      @ResponseBody
	      @RequestMapping(value = "/android/reportList/Message", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	      public String reportMessage(Report report) {
	         
	         List<Report> reportList= service.reportMessage(report);
	         String JsonReportList = gson.toJson(reportList);
	         System.out.println(JsonReportList);
	         return JsonReportList;
	      }
	   
	   
	

}

