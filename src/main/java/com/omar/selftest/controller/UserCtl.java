package com.omar.selftest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omar.selftest.domain.User;
import com.omar.selftest.service.SysCtlSev;

@RestController
@PropertySource("classpath:config.properties")
public class UserCtl {
	@Value("${peizhi}")
	public String peizhi;

	@Autowired
	private SysCtlSev sysCtlSev;

	/* URL访问地址示例 */
	@GetMapping("/addUser")
	public String addUser(User user) {
		if(user.getId()==null) {
			UUID uuid = UUID.randomUUID();
			user.setId(uuid.toString());
		}
		String string = sysCtlSev.addUser(user);
		return string;
	}

	/* URL访问地址示例 */
	@GetMapping("/getUser")
	public List<User> getUser(User user) {
		List<User> users = sysCtlSev.getUsers(user);
		return users;
	}

	/* URL访问地址示例 */
	@GetMapping("/updateUser")
	public String updateUser(User user) {
		String updateUser = sysCtlSev.updateUser(user);
		return updateUser;
	}

	/* URL访问地址示例 */
	@GetMapping("/deleteUser")
	public String deleteUser(User user) {
		String deleteUser = sysCtlSev.deleteUser(user);
		return deleteUser;
	}

	/* 融入mybatis示例 */
	@GetMapping("/getUsersAll")
	public List<User> getUsersAll() {
		return sysCtlSev.getUsersAll();
	}

	// ---------------------------------------------------------------------------

	/* 读取配置的样例 */
	@GetMapping("/UserConfig")
	public String getUserConfig() {
		return peizhi;
	}

	/* 跳转jsp的样例 */
	@GetMapping("/UserJsp")
	public String getUserJsp() {
		return "HelloWorld";
	}

}
