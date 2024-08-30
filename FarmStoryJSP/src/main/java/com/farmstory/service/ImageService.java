package com.farmstory.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public enum ImageService {
	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<String> imageUpload(HttpServletRequest req) {
		List<String> files = new ArrayList<>();
	    ServletContext ctx = req.getServletContext();
	    String uploadPath = ctx.getRealPath("/thumbUploads");
	    logger.debug("uploadPath: " + uploadPath);
	    
		try {
			
			Collection<Part> parts = req.getParts(); // 첨부파일 정보 객체 가져오기
	        logger.debug("Parts size: " + parts.size());
			
			for(Part part : parts) {
				// 파일명 추출
				String oFileName = part.getSubmittedFileName();
				
				// 첨부된 파일이 있으면
				if(oFileName != null && !oFileName.isEmpty()) {
					logger.debug("oFileName : " + oFileName);
					
					// 고유 파일명 생성
					int idx = oFileName.lastIndexOf(".");
					String ext = oFileName.substring(idx);
					String sFileName = UUID.randomUUID().toString()+ext;

					logger.debug("sFileName : " + sFileName);
					// 파일 저장
					part.write(uploadPath + File.separator + sFileName);
					files.add("/FarmStoryJSP/thumbUploads/"+sFileName);
				}
			}

		} catch (Exception e) {
			logger.error("Error uploading file: " + e.getMessage(), e);
		}
		return files;
	}
}
