package model;

public class PagingUtil {
	
	public static String pagingText(int totalRecordCount,int pageSize,int blockPage,int nowPage,String page){
		/*
		 * totalRecordCount - 게시글 수
		 * pageSize - 한 페이지당 출력되는 게시글 수
		 * blockPage - 한 화면 페이지 수??  (  1 2 >>  /  << 3 4 >> ) 일 경우 2
		 * nowPage - 현재 페이지
		 * 
		 * intTemp - 각 블락?별 첫 페이지 ( [1] 2 3 >> / << [4] 5 6 >> / << [7] 8 9 ) 일 때 1,3,7
		 *  pagingStr - 처음 및 이전
		 *  String page - 쿼리문 
		 */
		
		
		String pagingStr="";
		
		//1.전체 페이지 구하기
		int totalPage = (int)(Math.ceil(((double)totalRecordCount/pageSize)));
		
		int intTemp = ((nowPage - 1) / blockPage) * blockPage + 1;

		//처음 및 이전을 위한 로직
		if(intTemp != 1){
               
			pagingStr+="<a href='"+page+"nowPage=1'>◀◀</a>&nbsp;<a href='"+page+"nowPage="+(intTemp -blockPage)+"'>◀</a>";
		}
		
		//페이지 표시 제어를 위한 변수
		int blockCount = 1;
		
		//페이지를 뿌려주는 로직
		//블락 페이지 수만큼 혹은 마지막 페이지가 될때까지 페이지를 표시한다1 
		while(blockCount <= blockPage && intTemp <= totalPage){  // 페이지 오버 를 체크
				//현재 페이지를 의미함
			if(intTemp == nowPage){  
				pagingStr+="<span style='Color:red'>"+intTemp+"</span>&nbsp;";
			}
		     else
		    	 pagingStr+="<a href='"+page+"nowPage="+intTemp+"'>"+intTemp+"</a>&nbsp;";
		       
			intTemp = intTemp + 1;
			blockCount = blockCount + 1;
		
		}

		//다음 및 마지막을 위한 로직
			
		if(intTemp <= totalPage){

			pagingStr+="<a href='"+page+"nowPage="+intTemp+"'>▶</a>&nbsp;<a href='"+page+"nowPage="+totalPage+"'>▶▶</a>"; 

				   
		}
		
		return pagingStr;
	}
	public static String pagingImage(){
		
		return null;
	}
	
}
