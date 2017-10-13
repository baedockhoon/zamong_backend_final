package com.zamong.web.problem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DownloadController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터 받기
		String filename = req.getParameter("filename");
		String no       = req.getParameter("mq_no");
		
		//다운로드 관련 모델 호출
		//1.파일 다운로드 로직 호출
		FileUtils.download(req,resp,"/Upload",filename);
		
		//2.테이블의 다운로드수 컬럼 증가용 데이터베이스관련 로직 호출
	
	}//doGet
	
}//class
