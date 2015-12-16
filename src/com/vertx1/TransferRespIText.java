package com.vertx1;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;

/**
 * using vert.x
 * response pdf with iText
 * @author pjhsiao
 *
 */
public class TransferRespIText extends AbstractVerticle  {
	
	@Override
	public void start() throws Exception {
		HttpServer server = vertx.createHttpServer();
		Buffer buff = Buffer.buffer();
		server.requestHandler(request -> {
											try {
												ByteArrayOutputStream baos = new ByteArrayOutputStream();
											 	Document document = new Document();
										        PdfWriter.getInstance(document, baos);
										        document.open();
										        Font f = new Font(FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD, BaseColor.YELLOW);
										        Chunk c = new Chunk("Hello World, First iText with Vert.x", f);
										        c.setBackground(BaseColor.RED);
										        Paragraph p = new Paragraph(c);
										        document.add(p);
										        document.close();
										        baos.flush();
										        buff.appendBytes(baos.toByteArray());
												request.response().setChunked(true);
												request.response().putHeader("Content-Type","application/pdf");
												request.response().write(buff);
												request.response().close();
												baos.close();
											} catch (Exception e) {
												e.printStackTrace();
											}
								});
		server.listen(8080);
		
	}
}
