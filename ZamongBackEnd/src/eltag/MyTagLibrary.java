package eltag;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;



public class MyTagLibrary {
	
	
	
	
	
	//URL인코딩 메소드
		public static String urlEncoding(String name){
			String nameEncoding = null;
			try {
				nameEncoding = URLEncoder.encode(name, "UTF-8");
			}//try
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}//catch
			return nameEncoding;
		}//urlEncoding
	
	
}
