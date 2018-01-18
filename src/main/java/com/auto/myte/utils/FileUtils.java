package com.auto.myte.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.util.FileCopyUtils;

public class FileUtils {

	// 判断文件是否存在
	public static boolean judeFileExists(File file) {

		if (file.exists()) {
			return true;
		} 
		return false;
	}

	// 判断文件夹是否存在
	public static boolean judeDirExists(File file) {
		if (file == null) {
			return false;
		}

		// 判断是否是目录
		if (file.isDirectory()) {
			if (!file.exists()) {
				file.mkdirs();
			}
		} else {
			File fileParent = file.getParentFile();
			if (!fileParent.exists()) {
				fileParent.mkdirs();
			}
		}
		return true;
	}

	public static void download(String urlString, String filename) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 输入流
		InputStream is = con.getInputStream();
		OutputStream os = new FileOutputStream(filename);
		
		int i = FileCopyUtils.copy(is, os);
	}
	
	public static void main(String[] args) {
		try {
			download("http://localhost:8080/images/myte.zip", "C:\\test\\myte.zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
