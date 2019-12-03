package com.httpserver;

public interface Servlet {
	public ResponseEntity service(RequestEntity request);
}
