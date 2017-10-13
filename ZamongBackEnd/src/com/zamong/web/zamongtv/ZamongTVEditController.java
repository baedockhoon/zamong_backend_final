package com.zamong.web.zamongtv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.zamong.tv.service.ZamongTVDTO;
import com.zamong.tv.service.impl.ZamongTVDAO;


public class ZamongTVEditController extends HttpServlet{
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청분석]
		if(req.getMethod().toUpperCase().equals("GET")){//수정폼으로 이동
			String mv_no = req.getParameter("mv_no");
			//모델 호출 및 결과 값 받기]
			ZamongTVDAO dao = new ZamongTVDAO(req.getServletContext());
			ZamongTVDTO dto= dao.selectOne(mv_no);
			dao.close();
			//리퀘스트 영역에 저장]
			req.setAttribute("dto", dto);
			//포워드]
			
			req.getRequestDispatcher("/bbs/zamongtv/ZamongTVEdit.jsp").forward(req, resp);
		}
	}/////////////////doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//3]요청분석- 수정처리 요청
		//4]모델호출 및 결과값 받기
		int sucorfail;
		String mv_no = req.getParameter("mv_no");
		String at_no = req.getParameter("at_no");
		String mv_title = req.getParameter("mv_title");
		String mv_content = req.getParameter("mv_content");
		String mv_link = req.getParameter("mv_link");
		
			//데이타베이스 CRUD작업과 관련된 모델 호출]
			ZamongTVDAO dao = new ZamongTVDAO(req.getServletContext());
			ZamongTVDTO dto = new ZamongTVDTO();
			dto.setTv_no(mv_no);
			dto.setAt_no(at_no);
			dto.setTv_title(mv_title);
			dto.setTv_contents(mv_content);
			dto.setTv_link(mv_link);
			
			sucorfail=dao.update(dto);
			//DB 업데이트 성공 및 파일 교체시 기존 업로드된 파일 삭제
			
			dao.close();
		
		//5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		//5-1]DB입력 성공 여부 및 파일용량 초과 판단용 속성 저장
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		req.setAttribute("WHERE","EDT");
		//6]포워드
		req.getRequestDispatcher("/ZAMONG/ZamongTVList.do").forward(req, resp);
		}

}
