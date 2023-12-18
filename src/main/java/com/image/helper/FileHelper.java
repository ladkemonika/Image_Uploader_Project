package com.image.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FileHelper {
	//public final String UPLOAD_DIR="C:\\Users\\Dell\\Desktop\\JAVA\\SpringToolSuit\\programms\\ImageUpload-1\\src\\main\\resources\\static\\image";
	
	//dynamically upload a file
	public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();
public FileHelper()throws IOException
{
	
}
public boolean uploadFile(MultipartFile multipartFile)
{
	boolean f=false;
	try {
		Files.copy(multipartFile.getInputStream(),
				Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()),
				StandardCopyOption.REPLACE_EXISTING);
		f=true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return f;
	
}



}
