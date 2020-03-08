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
import com.omar.selftest.service.SysCtlSev;

@RestController
@PropertySource("classpath:config.properties")
public class OrganizationCtl {
	@Value("${peizhi}")
	public String peizhi;

	@Autowired
	private SysCtlSev sysCtlSev;

	/* URL访问地址示例 */
	@GetMapping("/addOrganization")
	public String addOrganization(HttpServletRequest request) {
		UUID uuid = UUID.randomUUID();
		String organizationName=request.getParameter("organizationName");
        String organizationHeader=request.getParameter("organizationHeader");
        String organizationParent=request.getParameter("organizationParent");
        Organization organization=new Organization(uuid.toString(), organizationName, organizationHeader, organizationParent);
		String string = sysCtlSev.addOrganization(organization);
		return string;
	}

	/* URL访问地址示例 */
	@GetMapping("/getOrganization")
	public @ResponseBody List<Organization> getOrganization(Organization organization, HttpServletRequest request) {
		List<Organization> organizations = sysCtlSev.getOrganizations(organization);
		return organizations;
	}

	/* URL访问地址示例 */
	@RequestMapping(value = "/updateOrganization",method = RequestMethod.GET)
	public String updateOrganization(Organization organization) {
		String updateorganization = sysCtlSev.updateOrganization(organization);
		return updateorganization;
	}

	/* URL访问地址示例 */
	@GetMapping("/deleteOrganization")
	public String deleteOrganization(Organization organization) {
		String deleteOrganization = sysCtlSev.deleteOrganization(organization);
		return deleteOrganization;
	}

	/* 融入mybatis示例 */
	@GetMapping("/getOrganizationsAll")
	public List<Organization> getOrganizationsAll() {
		return sysCtlSev.getOrganizationsAll();
	}

	// ---------------------------------------------------------------------------

	/* 读取配置的样例 */
	@GetMapping("/OrganizationConfig")
	public String getOrganizationConfig() {
		return peizhi;
	}

}
