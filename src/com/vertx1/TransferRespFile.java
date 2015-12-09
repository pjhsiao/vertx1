package com.vertx1;

import java.io.File;
import java.io.FileInputStream;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;

/**
 * using vert.x
 * response file with async
 * @author pjhsiao
 *
 */
public class TransferRespFile extends AbstractVerticle  {
	
	@Override
	public void start() throws Exception {
		HttpServer server = vertx.createHttpServer();
		final FileInputStream fis = new FileInputStream(new File("/Users/pjhsiao/Desktop/spring3restfulwebservi.pdf"));
		Buffer buff = Buffer.buffer();
		server.requestHandler(request -> {
											byte[] b = new byte[1024];
											try {
												while(fis.read(b)!=-1){
													buff.appendBytes(b);
												}
												request.response().setChunked(true);
												request.response().putHeader("Content-Type","application/pdf");
												request.response().write(buff);
												request.response().close();
											} catch (Exception e) {
												e.printStackTrace();
											}
								});
		server.listen(8080);
		
	}
}
