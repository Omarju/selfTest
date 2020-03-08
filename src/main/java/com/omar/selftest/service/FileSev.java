package com.omar.selftest.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.omar.selftest.domain.File;

import net.lingala.zip4j.exception.ZipException;

@Service
public interface FileSev {

	public String uploadFile(MultipartFile file, com.omar.selftest.domain.File domainFile) throws IOException;

	public String downloadFile(com.omar.selftest.domain.File file, HttpServletResponse response) throws IOException, ZipException;

	public String deleteFile(File file);

	public List<File> getFiles(File file);

	public String updateFile(File file);

}
