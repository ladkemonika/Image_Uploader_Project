package com.image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.image.helper.FileHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileHelper fileHelper;

	@PostMapping("/uploadfile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize());
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());
		
		try {			
			//validation   
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}

			if (!file.getContentType().equals("image/jpg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG FIle allowed");
			}

			// upload code

			boolean f= fileHelper.uploadFile(file);
			if(f) {
				//return ResponseEntity.ok("File is succesfully upload");
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong please try again!!!!!!");
	}
}
