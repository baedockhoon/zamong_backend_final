package com.zamong.web.magazine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.zamong.mg.service.MagazineDTO;
import com.zamong.mg.service.impl.MagazineDAO;

import model.FileUtils;


public class ZamongMagazineWriteController extends HttpServlet {
	String getStringValue(String value){
	      switch(value){
	         case "1":return "금주의 신보";
	         case "2":return "금주의 차트";
	         case "3":return "아티스트 갤러리";
	         case "4":return "이슈 포커스";
	         case "5":return "멜론 스테이지";
	         case "6":return "HIPHOPLE";
	         case "7":return "원더풀 재즈";
	         case "8":return "스쿨 오브 록";
	         case "9":return "클래식 AtoZ";
	         case "10":return "EDM Floor";
	         case "11":return "인디 스트리트";
	         case "12":return "TV별책부록";
	         case "13":return "아이돌 탐구생활";
	         case "14":return "STATION H";
	         case "15":return "SM STATION";
	         case "16":return "Hi-Fi 가이드";
	         default : return "뮤직툰";
	      }
	   }
	
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//한글처리
			req.setCharacterEncoding("UTF-8");
			//오류시 입력값 보존을 위한 변수 선언
			String title;
			String contents;
			String division;
			//3]요청분석 -입력처리 요청
			//4]모델호출 및 결과값 받기
			
			int sucorfail;
			
			//기타 파라미터 받아서 테이블에 입력처리
			
					division = getStringValue(req.getParameter("Magazine_category"));
					title = req.getParameter("title");
					contents = req.getParameter("content");
					//데이터베이스 CRUD작업과 관련된 모델 호출
					MagazineDAO dao = new MagazineDAO(req.getServletContext());
					MagazineDTO dto = new MagazineDTO();
					dto.setMg_title(title);
					dto.setMg_contents(contents);
					dto.setMg_division(division);
					
					sucorfail = dao.insert(dto);
					dao.close();
					
					req.setAttribute("SUC_FAIL", sucorfail);
					//5-2]컨트롤러 구분용-입력:INS,수정:EDT,삭제:DEL
					req.setAttribute("WHERE", "INS");
					
			req.getRequestDispatcher("/bbs/magazine/Magazine_Message.jsp").forward(req, resp);
			
		}//doPost
	
	
	
}
