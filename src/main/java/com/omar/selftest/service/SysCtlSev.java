package com.omar.selftest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.omar.selftest.domain.User;
import com.omar.selftest.domain.sysctl.Organization;
import com.omar.selftest.domain.sysctl.Role;

@Service
public interface SysCtlSev {
	//-----------------------用户管理
    public String addUser(User user);
    public List<User> getUsers(User user);
    public String updateUser(User user);
    public String deleteUser(User user);
    public List<User> getUsersAll();
    
  //-----------------------组织管理
    public String addOrganization(Organization organization);
    public List<Organization> getOrganizations(Organization organization);
    public String updateOrganization(Organization organization);
    public String deleteOrganization(Organization organization);
    public List<Organization> getOrganizationsAll();
    
    //-----------------------角色管理
    public String addRole(Role role);
    public List<Role> getRoles(Role role);
    public String updateRole(Role role);
    public String deleteRole(Role role);
    public List<Role> getRolesAll();
    
    
    
}
