package com.omar.selftest.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omar.selftest.domain.sysctl.Organization;
import com.omar.selftest.domain.sysctl.Role;
import com.omar.selftest.service.SysCtlSev;

@RestController
@PropertySource("classpath:config.properties")
public class RoleCtl {
	@Value("${peizhi}")
	public String peizhi;

	@Autowired
	private SysCtlSev sysCtlSev;

	/* URL访问地址示例 */
	@GetMapping("/addRole")
	public String addOrganization(HttpServletRequest request) {
		UUID uuid = UUID.randomUUID();
		String roleName=request.getParameter("roleName");
		Role role = new Role(uuid.toString(),roleName );
		String string = sysCtlSev.addRole(role);
		return string;
	}

	/* URL访问地址示例 ,无法封装对象*/
	@GetMapping("/getRole")
	public @ResponseBody List<Role> getRole(Role role, HttpServletRequest request) {
		List<Role> roles = sysCtlSev.getRoles(role);
		return roles;
	}

	/* URL访问地址示例 */
	@RequestMapping(value = "/updateRole",method = RequestMethod.GET)
	public String updateRole(Role role) {
		String updateRole = sysCtlSev.updateRole(role);
		return updateRole;
	}

	/* URL访问地址示例 */
	@GetMapping("/deleteRole")
	public String deleteRole(Role role) {
		String deleteRole = sysCtlSev.deleteRole(role);
		return deleteRole;
	}

	/* 融入mybatis示例 */
	@GetMapping("/getRolesAll")
	public List<Role> getRolesAll() {
		return sysCtlSev.getRolesAll();
	}

	// ---------------------------------------------------------------------------

	/* 读取配置的样例 */
	@GetMapping("/RoleConfig")
	public String getRoleConfig() {
		return peizhi;
	}

}
