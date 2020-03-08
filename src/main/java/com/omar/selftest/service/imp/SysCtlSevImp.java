package com.omar.selftest.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omar.selftest.dao.UserMapper;
import com.omar.selftest.dao.sysctl.OrganizationMapper;
import com.omar.selftest.dao.sysctl.RoleMapper;
import com.omar.selftest.dao.sysctl.SecretMapper;
import com.omar.selftest.domain.User;
import com.omar.selftest.domain.sysctl.Organization;
import com.omar.selftest.domain.sysctl.Role;
import com.omar.selftest.service.SysCtlSev;

@Service
public class SysCtlSevImp implements SysCtlSev {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private SecretMapper secretMapper;

	// ------------------------------------用户管理
	@Override
	public String addUser(User user) {
		int addUser = userMapper.addUser(user);
		return "addUser success " + addUser;
	}

	@Override
	public List<User> getUsers(User user) {
		List<User> users = userMapper.getUsers(user);
		return users;
	}

	@Override
	public String updateUser(User user) {
		List<User> users = userMapper.getUsers(user);
		int updateUser = userMapper.updateUser(users.get(0));
		return "updateUser success " + updateUser;
	}

	@Override
	public String deleteUser(User user) {
		List<User> users = getUsers(user);
		int deleteUser = 1;
		for (int i = 0; i < users.size(); i++) {
			deleteUser = userMapper.deleteUser(users.get(i));
			if (deleteUser == 0) {
				break;
			}
		}
		return "deleteUser success" + deleteUser;
	}

	@Override
	public List<User> getUsersAll() {
		List<User> all = userMapper.getUsersAll();
		return all;
	}

	// ------------------------------------组织管理
	@Override
	public String addOrganization(Organization organization) {
		int addOrganization = organizationMapper.addOrganization(organization);
		return "addUser success " + addOrganization;
	}

	@Override
	public List<Organization> getOrganizations(Organization organization) {
		List<Organization> organizations = organizationMapper.getOrganizations(organization);
		return organizations;
	}

	@Override
	public String updateOrganization(Organization organization) {
		List<Organization> organizations = organizationMapper.getOrganizations(organization);
		int updateOrganization = organizationMapper.updateOrganization(organizations.get(0));
		return "updateUser success " + updateOrganization;
	}

	@Override
	public String deleteOrganization(Organization organization) {

		List<Organization> organizations = getOrganizations(organization);
		int deleteOrganization = 1;
		for (int i = 0; i < organizations.size(); i++) {
			deleteOrganization = organizationMapper.deleteOrganization(organizations.get(i));
			if (deleteOrganization == 0) {
				break;
			}
		}
		return "deleteUser success" + deleteOrganization;
	}

	@Override
	public List<Organization> getOrganizationsAll() {
		List<Organization> all = organizationMapper.getOrganizationsAll();
		return all;
	}

	// ------------------------------------角色管理
	@Override
	public String addRole(Role role) {
		int addRole = roleMapper.addRole(role);
		return "addRole success " + addRole;
	}

	@Override
	public List<Role> getRoles(Role role) {
		List<Role> roles = roleMapper.getRoles(role);
		return roles;
	}

	@Override
	public String updateRole(Role role) {
		List<Role> roles = roleMapper.getRoles(role);
		int updateRole = roleMapper.updateRole(roles.get(0));
		return "updateRole success " + updateRole;
	}

	@Override
	public String deleteRole(Role role) {
		List<Role> roles = getRoles(role);
		int deleteRoles = 1;
		for (int i = 0; i < roles.size(); i++) {
			deleteRoles = roleMapper.deleteRole(roles.get(i));
			if (deleteRoles == 0) {
				break;
			}
		}
		return "deleteRoles success" + deleteRoles;
	}

	@Override
	public List<Role> getRolesAll() {
		List<Role> all = roleMapper.getRolesAll();
		return all;
	}

	
}
