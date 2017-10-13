package com.zamong.web.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.me.service.AddressDTO;
import com.zamong.me.service.MemberDTO;
import com.zamong.me.service.impl.AddressDAO;
import com.zamong.me.service.impl.MemberDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;

public class MemberEditController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getMethod().toUpperCase().equals("GET")) {// 수정폼으로 이동
			String me_no = req.getParameter("me_no");
			String nowPage = req.getParameter("nowPage");
			// 모델 호출 및 결과 값 받기]
			MemberDAO dao = new MemberDAO(req.getServletContext());
			MemberDTO dto = dao.selectOne(me_no);
			AddressDAO ad_dao = new AddressDAO(req.getServletContext());
			AddressDTO ad_dto = ad_dao.selectOne(me_no);
			if(ad_dto!=null) {
			String me_addr1 = ad_dto.getAd_zip();
			String me_addr2 = ad_dto.getAd_basic_addr();
			String me_addr3 = ad_dto.getAd_detail_addr();
			req.setAttribute("me_addr1", me_addr1);
			req.setAttribute("me_addr2", me_addr2);
			req.setAttribute("me_addr3", me_addr3);
			}
			String me_birth = dto.getMe_birth();
			String me_email = dto.getMe_email();
			String me_tel = dto.getMe_tel();
			String me_gender = dto.getMe_gender();
			String tel[] = me_tel.split("-");
			String email[] = me_email.split("@");

			
			String me_email1 = email[0];
			String me_email2 = email[1];
			String me_tel1 = tel[0];
			String me_tel2 = tel[1];
			String me_tel3 = tel[2];
			
			
			req.setAttribute("me_tel1", me_tel1);
			req.setAttribute("me_tel2", me_tel2);
			req.setAttribute("me_tel3", me_tel3);
			req.setAttribute("me_email1", me_email1);
			req.setAttribute("me_email2", me_email2);
			
			dao.close();
			// 리퀘스트 영역에 저장]
			req.setAttribute("dto", dto);
			// 포워드]
			req.getRequestDispatcher("/bbs/membermgmt/MemberEdit.jsp").forward(req, resp);

		} else {// 수정처리-POST방식
				// 한글처리]
			int sucorfail;
			
			req.setCharacterEncoding("UTF-8");
			// 3]요청분석- 수정처리 요청
			// 4]모델호출 및 결과값 받기
			// 파일 업로드와 관련된 모델(비지니스 로직) 호출
			String nowPage = req.getParameter("nowPage");
			// 수정성공:1,실패:0,용량초과:-1
			String me_no = req.getParameter("me_no");

			req.setAttribute("me_no", me_no);
			req.setAttribute("nowPage", nowPage);

			String me_id = req.getParameter("me_id");
			String me_pass = req.getParameter("me_pass");
			String me_check_pass = req.getParameter("me_check_pass");
			String me_name = req.getParameter("me_name");
			String me_nickname = req.getParameter("me_nickname");
			String me_gender = req.getParameter("me_gender");
			String me_birth = req.getParameter("me_birth");
			String me_email1 = req.getParameter("me_email1");
			String me_email2 = req.getParameter("me_email2");
			String me_email = me_email1 + "@" + me_email2;
			String me_tel1 = req.getParameter("me_tel1");
			String me_tel2 = req.getParameter("me_tel2");
			String me_tel3 = req.getParameter("me_tel3");
			String me_tel = me_tel1 + "-" + me_tel2 + "-" + me_tel3;
			
			
			AddressDAO ad_dao = new AddressDAO(req.getServletContext());
			int affected;
			if (!req.getParameter("me_addr1").equals("")) {
				String me_addr1 = req.getParameter("me_addr1");
				String me_addr2 = req.getParameter("me_addr2");
				String me_addr3 = req.getParameter("me_addr3");
				if(ad_dao.selectOne(me_no)!=null){
					AddressDTO ad_dto = new AddressDTO();
					ad_dto.setMe_no(me_no);
					ad_dto.setAd_zip(me_addr1);
					ad_dto.setAd_basic_addr(me_addr2);
					ad_dto.setAd_detail_addr(me_addr3);
					ad_dao.update(ad_dto);
					req.setAttribute("ad_dto", ad_dto);
				} else {
					AddressDTO ad_dto = new AddressDTO();
					ad_dto.setMe_no(me_no);
					ad_dto.setAd_zip(me_addr1);
					ad_dto.setAd_basic_addr(me_addr2);
					ad_dto.setAd_detail_addr(me_addr3);
					affected = ad_dao.insert(ad_dto);
					req.setAttribute("ad_dto", ad_dto);
				}
			}
			

			
			
			String me_photo = req.getParameter("me_photo");

			MemberDTO dto = new MemberDTO();
			dto.setMe_no(me_no);
			dto.setMe_birth(me_birth);
			dto.setMe_email(me_email);
			dto.setMe_gender(me_gender);
			dto.setMe_id(me_id);
			dto.setMe_name(me_name);
			dto.setMe_nickname(me_nickname);
			dto.setMe_pass(me_pass);
			dto.setMe_photo(me_photo);
			dto.setMe_tel(me_tel);

			req.setAttribute("dto", dto);
			// 데이타베이스 CRUD작업과 관련된 모델 호출]
			
			MemberDAO dao = new MemberDAO(req.getServletContext());
			
			sucorfail = dao.update(dto);
			
			dao.close();
			
			req.setAttribute("SUC_FAIL", sucorfail);
			// 5-2]어느 컨트롤러에서 포워드 됬는지 판단용
			req.setAttribute("WHERE", "EDT");
			// 6]포워드
			req.getRequestDispatcher("/bbs/membermgmt/MemberMessage.jsp").forward(req, resp);
		} ///////// else
	}////////////////////

}//////////////
