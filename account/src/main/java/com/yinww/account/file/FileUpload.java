package com.yinww.account.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.yinww.util.CommonUtil;
import com.yinww.util.DateUtil;
import com.yinww.util.RandomGenerator;
import com.yinww.util.io.IOUtil;

public class FileUpload {
	private static Logger log = LoggerFactory.getLogger(FileUpload.class);
	
	public static String getExt(String fileName) {
		if(!CommonUtil.isEmpty(fileName)) {
			int index = fileName.lastIndexOf(".");
			if(index > 0) {
				return fileName.substring(index);
			}
		}
		
		return null;
	}
	
	/**
	 * 保存文件
	 * 
	 * @param file
	 * @return
	 */
	public static File saveFile(MultipartFile file, String uploadBaseDir, String appName) {
		Date timestamp = new Date();
		StringBuilder sb = new StringBuilder();
		
		sb.append(uploadBaseDir).append(appName).append(File.separator)
				.append(DateUtil.formatDate(timestamp, "yyyyMM")).append(File.separator)
				.append(DateUtil.formatDate(timestamp, "dd")).append(File.separator)
				.append(DateUtil.formatDate(timestamp, "HH")).append(File.separator)
				.append(DateUtil.formatDate(timestamp, "yyyyMMddHHmmssSSS")).append(RandomGenerator.getString(6))
				.append(getExt(file.getOriginalFilename()));
		File destFile = new File(sb.toString());
		try {
			File parentFile = destFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs(); // 如果目录不存在则创建
			}

			FileOutputStream os = new FileOutputStream(destFile);
			InputStream is = file.getInputStream();
			byte[] buffer = new byte[2048];
			int length = 0;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
				os.flush();
			}
			IOUtil.close(os);
			IOUtil.close(is);
			/*
			destFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(destFile);
			fos.write(file.getBytes());
			fos.close();
			*/
		} catch (Exception e) {
			log.error("文件保存出错", e);
		}
		return destFile;
	}

}
