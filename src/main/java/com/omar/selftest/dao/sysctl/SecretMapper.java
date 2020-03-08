package com.omar.selftest.dao.sysctl;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.omar.selftest.domain.sysctl.Secret;

@Repository
public interface SecretMapper {
    
    @Insert("insert into Secret(id,roleID,OrganizationID,secret) values(#{id},#{roleID},#{OrganizationID},#{secret})")
    int addSecret(Secret secret);
    
    @Select("select * from Secret where secret like CONCAT('%',CONCAT(#{secret},'%'))")
    List<Secret> getSecrets(Secret secret);
    
    @Update("update Secret set secret=#{secret} where id=#{id} ") 
    int updateSecret(Secret secret);
    
    @Delete(" delete from Secret where id= #{id} ") 
    int  deleteSecret(Secret secret);
    
    @Select("SELECT id,roleID,OrganizationID,secret FROM Secret")
    @Results({@Result(property = "secret",column = "secret")})
    List<Secret> getSecretsAll();
}
