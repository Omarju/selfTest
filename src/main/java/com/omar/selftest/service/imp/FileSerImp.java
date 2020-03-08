package com.omar.selftest.service.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.omar.selftest.dao.FileMapper;
import com.omar.selftest.domain.User;
import com.omar.selftest.domain.sysctl.Organization;
import com.omar.selftest.service.FileSev;
import com.omar.selftest.util.CompressUtils;
import com.omar.selftest.util.FileUtil;

import net.lingala.zip4j.exception.ZipException;

@Service
public class FileSerImp<E> implements FileSev {

	@Value("${upload_dir}")
	public String upload_dir;
	
	@Value("${isEnc}")
	public boolean isEnc;

	@Value("${isTrueDel}")
	public boolean isTrueDel;

	@Value("${numOfEncAndDec}")
	public int numOfEncAndDec; // 16进制加密解密秘钥
	// 静态字段时，value注解无法获取参数

	@Autowired
	private FileMapper fileMapper;
	String dirPath = System.getProperty("user.dir");

	@Transactional
	@Override
	public String uploadFile(MultipartFile file, com.omar.selftest.domain.File domainFile) throws IOException {

		UUID uuid = UUID.randomUUID();
		String newFileName = uuid.toString() + file.getOriginalFilename();
		// 该方法返回的为当前项目的工作目录，即在哪个地方启动的java线程
		String path = upload_dir + File.separator + newFileName;
		File destFile = new File(dirPath + path);
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}

		try {
			// 将文件信息存储至库表
			com.omar.selftest.domain.File fileSave = new com.omar.selftest.domain.File(uuid.toString(),
					file.getOriginalFilename(), destFile.getName(), path,domainFile.getTags());
			int uploadFile = fileMapper.addFile(fileSave);

			FileUtil.uploadFile(file, destFile, isEnc, numOfEncAndDec);

			if (uploadFile == 1) {
				return path;
			} else {
				throw new IOException("文件上传失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("文件上传错误");
		}

	}

	@Transactional
	@Override
	public String downloadFile(com.omar.selftest.domain.File file, HttpServletResponse response) throws IOException, ZipException {
		List<com.omar.selftest.domain.File> files = fileMapper.getFiles(file);

		int nextInt = new Random().nextInt(files.size());
		com.omar.selftest.domain.File file2 = files.get(nextInt);
		
		//多文件时压缩后下载（未解决加密问题）
		/*
		 * //创建压缩后文件夹 File tmpFloder = new File(dirPath+ upload_dir + File.separator +
		 * UUID.randomUUID()+".zip"); List<String> listSrc = new ArrayList<String>();
		 * for (int i = 0; i < files.size(); i++) { String filePath =
		 * dirPath+files.get(i).getFilePath(); listSrc.add(filePath); } int compressFile
		 * = CompressUtils.compressFile(listSrc, null, null, tmpFloder.getPath());
		 * String downloadSetName = FileUtil.downloadSetName(tmpFloder.getPath(),
		 * tmpFloder.getName(), response, isEnc, numOfEncAndDec);
		 */
		
		
		String downloadSetName = FileUtil.downloadSetName(dirPath + File.separator + file2.getFilePath(),
				file2.getFileOriginalName(), response, isEnc, numOfEncAndDec);

		return downloadSetName;

	}

	@Transactional
	@Override
	public String deleteFile(com.omar.selftest.domain.File file) {

		List<com.omar.selftest.domain.File> files = fileMapper.getFiles(file);

		int deleteFile = 1;
		for (int i = 0; i < files.size(); i++) {
			// 删除信息
			deleteFile = fileMapper.deleteFile(files.get(i));

			// 删除下载的文件
			String path = dirPath + File.separator + files.get(i).getFilePath();
			boolean b = FileUtil.deleteFile(path, isTrueDel);

			if (deleteFile == 0 || !b) {
				break;
			}
		}

		return "deleteFile success" + deleteFile;
	}

	@Transactional
	@Override
	public List<com.omar.selftest.domain.File> getFiles(com.omar.selftest.domain.File file) {

		List<com.omar.selftest.domain.File> files = fileMapper.getFiles(file);
		return files;
	}
	
	@Transactional
	@Override
	public String updateFile(com.omar.selftest.domain.File file) {
		
		List<com.omar.selftest.domain.File> files = fileMapper.getFiles(file);
		
		//编辑要怎么做呢
		int updateFile = fileMapper.updateFile(files.get(0));
		
		return "updateUser success " + updateFile;
	}

}
