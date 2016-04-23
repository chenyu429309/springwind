package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.enums.OptionType;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户管理相关操作
 * </p>
 *
 *
 * @Author Jack
 * @Date 2016/4/15 15:03
 */
@Controller
@RequestMapping("/perm/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	@Permission("2001")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/user/list";
	}

    @Permission("2001")
    @RequestMapping("/addUser/{op}")
    public String addUser(Model model,@PathVariable Integer op) {
        if(op == OptionType.OP_INSERT.getOp()){
            model.addAttribute("op","1");
        } else {
            model.addAttribute("op","2");
        }
        return "/user/add";
    }

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/getUserList")
	public String getUserList() {
		Page<User> page = getPage();
		return jsonPage(userService.selectPage(page, null));
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/delUser/{userId}")
	public String delUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
		return Boolean.TRUE.toString();
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.selectById(userId);
	}

}
