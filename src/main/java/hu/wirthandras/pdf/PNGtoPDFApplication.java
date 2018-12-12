package hu.wirthandras.pdf;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PNGtoPDFApplication implements CommandLineRunner {
	
	private final Logger logger = LoggerFactory.getLogger(PNGtoPDFApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PNGtoPDFApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Started");
		
		PDDocument doc = new PDDocument();
		for(int i = 1; i < 11; i++) {
			File img = new File(i + ".png");
			logger.info("Image: " + img.getAbsolutePath());
			PDPage page = new PDPage(PDRectangle.A4);
			PDImageXObject pdImage = PDImageXObject.createFromFileByContent(img, doc);
			
			
			logger.info("Image Loaded");
			PDPageContentStream contentStream = new PDPageContentStream(doc, page);
			contentStream.drawImage(pdImage, 0,0, PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());
			logger.info("Image Drawn");
			contentStream.close();
			
			doc.addPage(page);
		}
		doc.save(new File("out.pdf"));
		logger.info("PDF saved");
		doc.close();
	}
	
	
}
