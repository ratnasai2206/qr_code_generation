package com.example.demoqrgenerationby.using.springboot.services;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QrCodeGeneratorService {

	public byte[] generateQrCodeImage(String content, int height, int width) {
		QRCodeWriter qrCodeWriter=new QRCodeWriter();
		
		Map<EncodeHintType, Object> hintsMap=new HashMap<EncodeHintType, Object>();
		hintsMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix;
		try {
			bitMatrix=qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to generate the Qr Code image",e);
		}
		ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
		BufferedImage image=toBufferedImage(bitMatrix); 
		try {
			ImageIO.write(image, "png", arrayOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to write QR code image to text ",e);
		}
		
		
		return arrayOutputStream.toByteArray();
	}

	private BufferedImage toBufferedImage(BitMatrix bitMatrix) {
	
		int width=bitMatrix.getWidth();
		int heigh=bitMatrix.getHeight();
		
		BufferedImage image=new BufferedImage(width, heigh, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics=(Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, heigh);
		graphics.setColor(Color.BLACK);
		for(int x=0;x<width;x++) {
			for(int y=0;y<heigh;y++) {
				if(bitMatrix.get(x, y)) {
					graphics.fillRect(x, y, 1, 1);
				}
			}
		}
		return image;
	}

}
