package com.omar.selftest.dao.sysctl;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.omar.selftest.domain.User;
import com.omar.selftest.domain.sysctl.Organization;

@Repository
public interface OrganizationMapper {
    
    @Insert("insert into Organization(id,organizationName,organizationHeader,organizationParent) values(#{id},#{organizationName},#{organizationHeader},#{organizationParent})")
    int addOrganization(Organization organization);
    
    @Select("select * from Organization where organizationName like CONCAT('%',CONCAT(#{organizationName},'%'))")
    List<Organization> getOrganizations(Organization organization);
    
    @Update("update Organization set organizationName=#{organizationName},organizationHeader=#{organizationHeader},organizationParent=#{organizationParent} where id=#{id} ") 
    int updateOrganization(Organization organization);
    
    @Delete(" delete from Organization where id= #{id} ") 
    int  deleteOrganization(Organization organization);
    
    @Select("SELECT id,organizationName,organizationHeader,organizationParent FROM Organization")
    @Results({@Result(property = "organizationName",column = "organizationName"),@Result(property = "organizationHeader",column = "organizationHeader")})
    List<Organization> getOrganizationsAll();
}
