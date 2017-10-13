package com.zamong.web.musicaward;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.ma.service.impl.MusicAwardDAO;
import com.zamong.ma.service.MusicAwardDTO;


public class MusicAwardEditController extends HttpServlet{
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청분석]
		if(req.getMethod().toUpperCase().equals("GET")){//수정폼으로 이동
			String ma_no = req.getParameter("ma_no");
			//모델 호출 및 결과 값 받기]
			MusicAwardDAO dao = new MusicAwardDAO(req.getServletContext());
			MusicAwardDTO dto= dao.selectOne(ma_no);
			dao.close();
			//리퀘스트 영역에 저장]
			req.setAttribute("dto", dto);
			//포워드]
			
			req.getRequestDispatcher("/bbs/musicaward/MusicAwardEdit.jsp").forward(req, resp);
		}
	}/////////////////doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//3]요청분석- 수정처리 요청
		//4]모델호출 및 결과값 받기
		int sucorfail;
		String ma_no = req.getParameter("ma_no");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String endofday = req.getParameter("endofday");
		
		/*SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");

		//종료일 String을 날짜형식으로 변환
		Date dateEndofDay = null;
		try {
			dateEndofDay = (Date) transFormat.parse(endofday);
		} catch (ParseException e) {e.printStackTrace();}

		//util형 날짜를 sql형날짜로 형변환
		java.sql.Date sqlDate = new java.sql.Date(dateEndofDay.getTime());*/
		
		//데이타베이스 CRUD작업과 관련된 모델 호출]
			MusicAwardDAO dao = new MusicAwardDAO(req.getServletContext());
			MusicAwardDTO dto = new MusicAwardDTO();
			dto.setMa_title(title);
			dto.setMa_contents(contents);
			dto.setMa_endofday(endofday);
			
			sucorfail=dao.update(dto);

			dao.close();
		
		//5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		//5-1]DB입력 성공 여부 및 파일용량 초과 판단용 속성 저장
		req.setAttribute("ma_no", ma_no);
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		req.setAttribute("WHERE","EDT");
		//6]포워드
		req.getRequestDispatcher("/bbs/musicaward/MusicAwardMessage.jsp").forward(req, resp);
		}

}
