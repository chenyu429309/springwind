package com.baomidou.springwind.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.IUserService;
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
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/toUserPage")
    public String toUserPage(Model model){
        return "/user/user";
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public Page getUserList(HttpServletRequest request, HttpServletResponse response){
        Page<User> page = getPage();
        page = userService.selectPage(page, null);
        return page;
    }


}
