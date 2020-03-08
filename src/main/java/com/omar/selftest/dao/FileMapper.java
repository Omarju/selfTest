package com.omar.selftest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.omar.selftest.domain.File;
import com.omar.selftest.domain.User;

@Repository
public interface FileMapper {

	@Insert("insert into File(id,fileOriginalName,fileStorageName,filePath,tags) values(#{id},#{fileOriginalName},#{fileStorageName},#{filePath},#{tags})")
	int addFile(File file);

	@Select("<script>" + "SELECT * FROM File WHERE 1=1" + "<if test='id!=null'>" + "and id=#{id}" + "</if>"
			+ "<if test='fileOriginalName!=null'>"
			+ "and fileOriginalName like CONCAT('%',CONCAT(#{fileOriginalName},'%'))" + "</if>"
			+ "<if test='tags!=null'>"
			+ "and tags like CONCAT('%',CONCAT(#{tags},'%'))" + "</if>"
			+ "<if test='filePath!=null'>" + "and filePath like CONCAT('%',CONCAT(#{filePath},'%'))" + "</if>"
			+ "</script>")
	List<File> getFiles(File file);

	@Update("update File set fileOriginalName=#{fileOriginalName},fileStorageName=#{fileStorageName},filePath=#{filePath},tags=#{tags} where id=#{id} ")
	int updateFile(File file);

	@Delete(" delete from File where id= #{id} ")
	int deleteFile(File file);

	@Select("SELECT id,fileOriginalName,fileStorageName,filePath,tags FROM File")
	@Results({ @Result(property = "fileOriginalName", column = "fileOriginalName"), @Result(property = "filePath", column = "filePath") })
	List<File> getFilesAll();

}
