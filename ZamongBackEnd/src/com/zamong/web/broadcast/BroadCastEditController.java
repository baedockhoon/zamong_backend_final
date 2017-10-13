package com.zamong.web.broadcast;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.zamong.mv.service.impl.MusicVideoDAO;
import com.zamong.br.service.BroadCastDAO;
import com.zamong.br.service.impl.BroadCastDTO;
import com.zamong.mv.service.MusicVideoDTO;


public class BroadCastEditController extends HttpServlet{
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청분석]
		if(req.getMethod().toUpperCase().equals("GET")){//수정폼으로 이동
			String br_no = req.getParameter("br_no");
			//모델 호출 및 결과 값 받기]
			BroadCastDAO dao = new BroadCastDAO(req.getServletContext());
			BroadCastDTO dto= dao.selectOne(br_no);
			dao.close();
			//리퀘스트 영역에 저장]
			req.setAttribute("dto", dto);
			//포워드]
			
			req.getRequestDispatcher("/bbs/zamongtv/Broadcast/BroadCastEdit.jsp").forward(req, resp);
		}
	}/////////////////doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//3]요청분석- 수정처리 요청
		//4]모델호출 및 결과값 받기
		int sucorfail;
		String br_no = req.getParameter("br_no");
		String at_no = req.getParameter("at_no");
		String br_title = req.getParameter("br_title");
		String br_contents = req.getParameter("br_contents");
		String br_link = req.getParameter("br_link");
		String br_programname = req.getParameter("br_programname");
		String br_endof = req.getParameter("br_endof");
		
		//데이타베이스 CRUD작업과 관련된 모델 호출]
			BroadCastDAO dao = new BroadCastDAO(req.getServletContext());
			BroadCastDTO dto = new BroadCastDTO();
			dto.setBr_no(br_no);
			dto.setAt_no(at_no);
			dto.setBr_title(br_title);
			dto.setBr_contents(br_contents);
			dto.setBr_link(br_link);
			dto.setBr_programname(br_programname);
			dto.setBr_endof(br_endof);
			
			sucorfail=dao.update(dto);

			dao.close();
		
		//5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		//5-1]DB입력 성공 여부 및 파일용량 초과 판단용 속성 저장
		req.setAttribute("br_no", br_no);
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		req.setAttribute("WHERE","EDT");
		//6]포워드
		req.getRequestDispatcher("/bbs/zamongtv/Broadcast/BroadCastMessage.jsp").forward(req, resp);
		}

}
