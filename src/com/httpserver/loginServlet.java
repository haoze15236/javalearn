package com.httpserver;

public class loginServlet implements Servlet{
	
	public ResponseEntity service(RequestEntity request) {
		StringBuffer response = new StringBuffer();
		response.append("<html>"); 
		response.append("<head>"); 
		response.append("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">" ); 
		response.append("<title>");
		response.append("第一个servlet");
		response.append("</title>");
		response.append("</head>");
		response.append("<body>");
		response.append("欢迎回来:"+request.getParam());
		response.append("</body>");
		response.append("</html>");
		return new ResponseEntity(response.toString());
	}

}
