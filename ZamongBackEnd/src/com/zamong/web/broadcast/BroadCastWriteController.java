package com.zamong.web.broadcast;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.oreilly.servlet.MultipartRequest;
import com.zamong.br.service.BroadCastDAO;
import com.zamong.br.service.impl.BroadCastDTO;

//1]HttpServlet상속-컨트롤러가 됨 즉 서블릿이 됨
public class BroadCastWriteController extends HttpServlet {
	//[입력 폼으로 이동]
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석- 입력폼 요청
		//4]모델호출 및 결과값 받기
		//5]결과값이 있으면 ,리퀘스트 영역에 저장
		//6]뷰 선택
		/*
		 *  절대경로 지정시
		 * 	포워딩:컨텍스트 루트 경로 미 포함
		 *  리다이렉트:컨텍스트 루트 포함.
		 *  단, server.xml에 Context태그의 path속성 값을
		 *  지울시에는 신경 쓸 필요없다.
		 */
		//포워드]
		req.getRequestDispatcher("/bbs/zamongtv/Broadcast/BroadCastWrite.jsp").forward(req, resp);
		
		//리다이렉트]
		//resp.sendRedirect(req.getContextPath()+"/DATAROOM_13/Write.jsp");
		
	}//////////////////doGet()
	//[입력 처리 즉 파일 업로드 및 데이타베이스 입력]
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1]한글처리
		req.setCharacterEncoding("UTF-8");
		//2]파라미터 받기
		String artist = req.getParameter("artist");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String link = req.getParameter("link");
		String programname = req.getParameter("programname");
		String endof = req.getParameter("endof");
		String image="";
		
		if(link.contains("<iframe") && link.contains("https://www.youtube.com/embed/")) {
			int startNum=link.indexOf("https://www.youtube.com/embed/")+30;
			image ="https://img.youtube.com/vi/"+link.substring(startNum,startNum+11)+"/0.jpg";
		}
		else {
			image ="https://img.youtube.com/vi/0/0.jpg";
		}
		
		//3]데이타를 전달할 DTO객체 생성및 데이타 설정
		BroadCastDAO dao = new BroadCastDAO(req.getServletContext());
		BroadCastDTO dto = new BroadCastDTO();
		dto.setAt_no(artist);
		dto.setBr_title(title);
		dto.setBr_contents(contents);
		dto.setBr_link(link);
		dto.setBr_programname(programname);
		dto.setBr_endof(endof);
		dto.setBr_image(image);
		//4]CRUD작업용 DAO계열 객체 생성
		int sucorfail;//DB입력 성공시에는 1,실패시 0
		sucorfail = dao.insert(dto);
		dao.close();
		
		
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]컨트롤러 구분용-입력:INS,수정:EDT,삭제:DEL
		req.setAttribute("WHERE", "INS");
		
		req.getRequestDispatcher("/bbs/zamongtv/Broadcast/BroadCastMessage.jsp").forward(req, resp);
	}//////////////////doPost()
}
