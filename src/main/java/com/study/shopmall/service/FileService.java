package com.study.shopmall.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class FileService {

	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String saveFileName = uuid.toString() + extension;
		String fileUploadFullUrl = uploadPath + "/" + saveFileName;
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
		fos.write(fileData);
		fos.close();
		return saveFileName;
	}

	public void deleteFile(String filePath) throws Exception {
		File deleteFile = new File(filePath);

		log.info(deleteFileToGetMessage(deleteFile));
	}

	private String deleteFileToGetMessage(File deleteFile) {
		if (deleteFile.exists()) {
			deleteFile.delete();
			return "파일을 삭제하였습니다.";
		}
		return "파일이 존재하지않습니다.";
	}
}
