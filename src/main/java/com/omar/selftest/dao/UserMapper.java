package com.omar.selftest.dao;

import com.omar.selftest.domain.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    
    @Insert("insert into user(id,userName,userSex,userAge) values(#{id},#{userName},#{userSex},#{userAge})")
    int addUser(User user);
    
    @Select("<script>" +
            "SELECT * FROM user WHERE 1=1" +
            "<if test='id!=null'>" +
            "and id=#{id}" +
            "</if>" +
            "<if test='userName!=null'>" +
            "and userName like CONCAT('%',CONCAT(#{userName},'%'))" +
            "</if>" +
            "<if test='userSex!=null'>" +
            "and userSex=#{userSex}" +
            "</if>" +
            "<if test='userAge!=null'>" +
            "and userAge=#{userAge}" +
            "</if>" +
            "</script>")
    List<User> getUsers(User user);
    
    @Update("update user set userName=#{userName},userSex=#{userSex},userAge=#{userAge} where id=#{id} ") 
    int updateUser(User user);
    
    @Delete(" delete from user where id= #{id} ") 
    int  deleteUser(User user);
    
    @Select("SELECT id,userName,userSex,userAge FROM user")
    @Results({@Result(property = "userSex",column = "userSex"),@Result(property = "userAge",column = "userAge")})
    List<User> getUsersAll();
}
