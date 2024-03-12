package com.example.demoqrgenerationby.using.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoqrgenerationby.using.springboot.services.QrCodeGeneratorService;

@RestController
@RequestMapping("/qr")
public class QrCodeController {

	@Autowired
	private QrCodeGeneratorService qrServices; 
	
	@GetMapping(value = "/qrcode/{content}",produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] generateQrCode(@PathVariable String content) {
		int height=200;
		int width=200;
		return qrServices.generateQrCodeImage(content,height,width);
	}
}
