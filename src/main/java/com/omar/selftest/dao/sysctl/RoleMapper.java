package com.omar.selftest.dao.sysctl;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.omar.selftest.domain.sysctl.Role;

@Repository
public interface RoleMapper {
	
    @Insert("insert into Role(id,roleName) values(#{id},#{roleName}) ")
    int addRole(Role role);
    
    @Select("select * from Role where roleName like CONCAT('%',CONCAT(#{roleName},'%'))")
    List<Role> getRoles(Role role);
    
    @Update("update Role set roleName=#{roleName} where id=#{id} ") 
    int updateRole(Role role);
    
    @Delete(" delete from Role where id= #{id} ") 
    int  deleteRole(Role role);
    
    @Select("SELECT id,roleName FROM Role")
    @Results({@Result(property = "roleName",column = "roleName")})
    List<Role> getRolesAll();
}
