package com.omar.selftest.util.unuse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * @Auther: Administrator
 * @Date: 2019/2/19 19:49
 * @Description: 压缩/解压缩工具包
 */
public class ZipUtil {

	/**
	 * 使用GBK编码可以避免压缩中文文件名乱码
	 */
	private static final String CHINESE_CHARSET = "GBK";

	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 1024;

	/**
	 * <p>
	 * 压缩文件
	 * </p>
	 *
	 * @param sourceFolder 压缩文件夹
	 * @param zipFilePath  压缩文件输出路径
	 * @throws Exception
	 */
	public static void zip(String sourceFolder, String zipFilePath) throws Exception {
		OutputStream out = new FileOutputStream(zipFilePath);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		ZipOutputStream zos = new ZipOutputStream(bos);
		// 解决中文文件名乱码
		zos.setEncoding(CHINESE_CHARSET);
		File file = new File(sourceFolder);
		String basePath = null;
		if (file.isDirectory()) {
			basePath = file.getPath();
		} else {
			basePath = file.getParent();
		}
		zipFile(file, basePath, zos);
		zos.closeEntry();
		zos.close();
		bos.close();
		out.close();
	}

	/**
	 * <p>
	 * 递归压缩文件夹
	 * </p>
	 *
	 * @param parentFile
	 * @param basePath
	 * @param zos
	 * @throws Exception
	 */
	private static void zipFile(File parentFile, String basePath, ZipOutputStream zos) throws Exception {
		File[] files = new File[0];
		if (parentFile.isDirectory()) {
			files = parentFile.listFiles();
		} else {
			files = new File[1];
			files[0] = parentFile;
		}
		String pathName;
		InputStream is;
		BufferedInputStream bis;
		byte[] cache = new byte[CACHE_SIZE];
		for (File file : files) {
			if (file.isDirectory()) {
				zipFile(file, basePath, zos);
			} else {
				pathName = file.getPath().substring(basePath.length() + 1);
				is = new FileInputStream(file);
				bis = new BufferedInputStream(is);
				zos.putNextEntry(new ZipEntry(pathName));
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					zos.write(cache, 0, nRead);
				}
				bis.close();
				is.close();
			}
		}
	}

	/**
	 * <p>
	 * 解压压缩包
	 * </p>
	 *
	 * @param zipFilePath 压缩文件路径
	 * @param destDir     压缩包释放目录
	 * @throws Exception
	 */
	public static void unZip(String zipFilePath, String destDir) throws Exception {
		ZipFile zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
		Enumeration<?> emu = zipFile.getEntries();
		BufferedInputStream bis;
		FileOutputStream fos;
		BufferedOutputStream bos;
		File file, parentFile;
		ZipEntry entry;
		byte[] cache = new byte[CACHE_SIZE];
		while (emu.hasMoreElements()) {
			entry = (ZipEntry) emu.nextElement();
			if (entry.isDirectory()) {
				new File(destDir + entry.getName()).mkdirs();
				continue;
			}
			bis = new BufferedInputStream(zipFile.getInputStream(entry));
			file = new File(destDir.endsWith("\\") ? destDir : (destDir + "\\") + entry.getName());
			parentFile = file.getParentFile();
			if (parentFile != null && (!parentFile.exists())) {
				parentFile.mkdirs();
			}
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos, CACHE_SIZE);
			int nRead = 0;
			while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
				fos.write(cache, 0, nRead);
			}
			bos.flush();
			bos.close();
			fos.close();
			bis.close();
		}
		zipFile.close();
	}

	/**
	 * 解压Zip文件
	 *
	 * @param path 文件目录
	 */
	public static void unZip(String path) {
		int count = -1;
		String savepath = "";

		File file = null;
		InputStream is = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; // 保存解压文件目录
		new File(savepath).mkdir(); // 创建保存目录
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(path, "gbk"); // 解决中文乱码问题
			Enumeration<?> entries = zipFile.getEntries();

			while (entries.hasMoreElements()) {
				byte buf[] = new byte[CACHE_SIZE];

				ZipEntry entry = (ZipEntry) entries.nextElement();

				String filename = entry.getName();
				boolean ismkdir = false;
				if (filename.lastIndexOf("/") != -1) { // 检查此文件是否带有文件夹
					ismkdir = true;
				}
				filename = savepath + filename;

				if (entry.isDirectory()) { // 如果是文件夹先创建
					file = new File(filename);
					file.mkdirs();
					continue;
				}
				file = new File(filename);
				if (!file.exists()) { // 如果是目录先创建
					if (ismkdir) {
						new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); // 目录先创建
					}
				}
				file.createNewFile(); // 创建文件

				is = zipFile.getInputStream(entry);
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, CACHE_SIZE);

				while ((count = is.read(buf)) > -1) {
					bos.write(buf, 0, count);
				}
				bos.flush();
				bos.close();
				fos.close();

				is.close();
			}

			zipFile.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (is != null) {
					is.close();
				}
				if (zipFile != null) {
					zipFile.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}