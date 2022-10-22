package com.cos.photogramstart.util;

public class Script {
	
	//어떤 pg 에서든 back 버튼을 눌렀을 때 공통으로 사용
	public static String back(String msg) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<script>");
		sb.append("alert('"+ msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		
		return sb.toString();
	}

}
