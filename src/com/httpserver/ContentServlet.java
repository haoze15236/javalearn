package com.httpserver;

import java.net.URL;
import java.net.URLDecoder;

public class ContentServlet implements Servlet{

	public ResponseEntity service(RequestEntity request) {
		StringBuffer response = new StringBuffer();
		response.append("<html>"); 
		response.append("<head>"); 
		response.append("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">" ); 
		response.append("<title>");
		response.append("你在看什么？");
		response.append("</title>");
		response.append("</head>");
		response.append("<body>");
		response.append("你对我说："+URLDecoder.decode(request.getParam())+"我都听到了");
		response.append("</body>");
		response.append("</html>");
		return new ResponseEntity(response.toString());
	}


}
