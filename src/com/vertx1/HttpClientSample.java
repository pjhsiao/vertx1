package com.vertx1;

import java.net.URL;
import java.net.URLConnection;

public class HttpClientSample {
	public static void main(String[] args)  {
		try {
			
			URL url = new URL("http://127.0.0.1:8080");
			URLConnection connection = url.openConnection();
			System.out.println(connection.getContentType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
