package com.omar.selftest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static int uploadFile(MultipartFile file, File destFile, Boolean isEnc, int numOfEncAndDec)
			throws IOException {

		if (isEnc) {
			if (!destFile.exists()) {
				destFile.createNewFile();
			}
			// 加密存储
			InputStream inputStream = file.getInputStream();
			OutputStream outputStream = new FileOutputStream(destFile);

			EncFile(inputStream, outputStream, numOfEncAndDec);

			inputStream.close();
			outputStream.close();
		} else {
			// 普通存储
			file.transferTo(destFile);
		}

		return 1;
	}

	public static String downloadSetName(String path, String fileName, HttpServletResponse response, Boolean isEnc,
			int numOfEncAndDec) throws IOException {
		File filePic = new File(path);
		String name = filePic.getName();
		if (filePic.exists()) {
			// 配置文件下载
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("*"); // 设置返回的文件类型
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
			FileInputStream is = new FileInputStream(filePic);
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象

			if (isEnc) {

				DecFile(is, toClient, numOfEncAndDec);

			} else {
				int i = is.available(); // 得到文件大小
				byte data[] = new byte[i];
				is.read(data); // 读数据
				toClient.write(data); // 输出数据
			}

			is.close();
			toClient.close();
		} else {
			System.out.println("路径不存在");
		}
		return "success";
	}

	public static String download(String path, HttpServletResponse response) throws IOException {
		File filePic = new File(path);
		String name = filePic.getName();
		if (filePic.exists()) {
			// 配置文件下载
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("*"); // 设置返回的文件类型
			// 下载文件能正常显示中文
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
			FileInputStream is = new FileInputStream(filePic);
			int i = is.available(); // 得到文件大小
			byte data[] = new byte[i];
			is.read(data); // 读数据
			is.close();
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.close();
		} else {
			System.out.println("路径不存在");
		}
		return "success";
	}

	public static boolean deleteFile(String path, boolean isTrue) {

		boolean delete = false;
		if (isTrue) {
			File file = new File(path);
			if (file.exists()) {
				delete = file.delete();
			}
		} else {
			delete = true;
		}

		return delete;
	}

	/**
	 * 文件加密
	 * 
	 * @param numOfEncAndDec
	 */
	private static void EncFile(InputStream inputStream, OutputStream outputStream, int numOfEncAndDec)
			throws IOException {

		byte[] buffer = new byte[1024];
		byte[] buffer2 = new byte[1024];
		int numRead = 0;

		while ((numRead = inputStream.read(buffer)) > -1) {

			for (int i = 0; i < numRead; i++) {
				byte b = buffer[i];
				// 每个字节加2加密
				b += 2;
				buffer2[i] = b;
			}
			outputStream.write(buffer2, 0, numRead);
		}
		outputStream.flush();
	}

	/**
	 * 文件解密
	 * 
	 * @param numOfEncAndDec
	 */
	private static void DecFile(InputStream fis, OutputStream fos, int numOfEncAndDec) throws IOException {

		byte[] buffer = new byte[1024];
		int numRead = 0;
		byte[] buffer2 = new byte[1024];

		while ((numRead = fis.read(buffer)) > -1) {

			for (int i = 0; i < numRead; i++) {
				byte b = buffer[i];
				// buffer2[i]=b==0?bMax:--b;
				// 每个字节减2解码
				b -= 2;
				buffer2[i] = b;
			}
			fos.write(buffer2, 0, numRead);
		}
		fos.flush();
	}

}
