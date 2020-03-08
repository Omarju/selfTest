package com.omar.selftest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omar.selftest.domain.File;
import com.omar.selftest.domain.User;
import com.omar.selftest.service.FileSev;

import net.lingala.zip4j.exception.ZipException;

@RestController
public class FileCtl<E> {

	@Value("${imageType}")
	public String imageType;
	
	@Value("${mvType}")
	public String mvType;

	@Autowired
	private FileSev fileSev;

	/**
	 * @Description @param file
	 * @Description @param domainFile 设置文件标签
	 * @Description @return
	 * @Description @throws IOException
	 * 2020年2月28日
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public String uoloadFile(@RequestParam("file") MultipartFile file,com.omar.selftest.domain.File domainFile) throws IOException {
		// 获取文件名，带后缀
		String originalFilename = file.getOriginalFilename();
		// 获取文件的后缀格式
		String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		// 首先校验图片格式
		String path = null;
		if (imageType.contains(fileSuffix)||(mvType.contains(fileSuffix))) {
			// 只有当满足图片格式时才进来，重新赋图片名，防止出现名称重复的情况
			path = fileSev.uploadFile(file, domainFile);
		} else {
			throw new IOException("文件格式错误");
		}
		return path;
	}

	@RequestMapping(value = "/file/download", method = RequestMethod.POST)
	public String downloadFile(com.omar.selftest.domain.File file, HttpServletResponse response) throws IOException, ZipException {
		
		String downloadFiles = fileSev.downloadFile(file,response);
		return downloadFiles;
	}

	/* URL访问地址示例 */
	@GetMapping("/file/delete")
	public String deleteFile(com.omar.selftest.domain.File file) {
		String deleteFile = fileSev.deleteFile(file);
		return deleteFile;
	}
	
	/* 查询文件信息 */
	@GetMapping("/getFiles")
	public List<File> getFiles(com.omar.selftest.domain.File file) {
		List<File> files = fileSev.getFiles(file);
		return files;
	}
	
	/* URL访问地址示例 */
	@GetMapping("/updateFile")
	public String updateFile(com.omar.selftest.domain.File file) {
		String updateFile = fileSev.updateFile(file);
		return updateFile;
	}
	
}
