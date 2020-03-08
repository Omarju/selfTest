package com.omar.selftest.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * 对文件进行压缩和加密 对文件进行解压和解密
 * 
 * 
 */
public class CompressUtils {

	/**
	 * 压缩文件的方法
	 * 
	 * @param files       要压缩文件的地址集合
	 * @param folders     要压缩的文件夹的地址集合
	 * @param pwd         压缩密码
	 * @param appointFile 压缩到指定文件夹的路径
	 * @return
	 * @throws ZipException 
	 * @throws Exception
	 */
	public static int compressFile(List<String> files, List<String> folders, String pwd, String appointFile)
			throws IOException, ZipException {
		int flag = 0;
		if (!appointFile.isEmpty()) {
			File oldFile = new File(appointFile);
			if (oldFile.exists()) {
				return flag;
			}
			ZipFile zipFile = new ZipFile(appointFile);
			ZipParameters parameters = new ZipParameters(); // 设置参数
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			if (pwd!=null) {// 有密码，用密码压缩
//				if (!(pwd==null)||(!pwd.isEmpty())) {// 有密码，用密码压缩
				parameters.setEncryptFiles(true);
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
				parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
				parameters.setPassword(pwd);
			}

			if (files!=null) {
				ArrayList<File> toAddFile = new ArrayList<File>();
				for (String fileString : files) {
					File file = new File(fileString);
					if (file.exists()) {
						toAddFile.add(file);// 添加文件
					}
				}
				if (!toAddFile.isEmpty()) {
					zipFile.addFiles(toAddFile, parameters);
				}
			}
			if (folders!=null) {
				for (String folder : folders) {
					File file = new File(folder);
					if (file.exists()) {
						zipFile.addFolder(folder, parameters);// 添加文件夹
					}
				}
			}
			flag = 1;
		}
		return flag;
	}

	/**
	 * 解压文件的方法
	 * 
	 * @param srcFile 要解压的文件
	 * @param path    解压到的路径
	 * @param pwd     解压密码
	 * @return
	 * @throws Exception
	 */
	public static int unzip(String srcFile, String path, String pwd) throws Exception {
		int flag = 0;
		if (!srcFile.isEmpty()) {
			ZipFile zipFile = new ZipFile(srcFile);// 获取解压的文件
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(pwd);// 解压密码
			}
			zipFile.extractAll(path);// 解压到指定路径
			flag = 1;
		}
		return flag;
	}

}