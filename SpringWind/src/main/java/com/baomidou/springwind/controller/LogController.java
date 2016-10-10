package com.baomidou.springwind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.SysLog;
import com.baomidou.springwind.service.ISysLogService;

/**
 * <p>
 * 操作日志
 * </p>
 *
 *
 * @Author hubin
 * @Date 2016-05-09
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController {

	@Autowired
	private ISysLogService sysLogService;

	@Permission("3000")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/log/list";
	}

	@ResponseBody
	@Permission("3000")
	@RequestMapping("/jsonData")
	public String jsonData(SysLog sysLog) {
		Page<SysLog> page = getPage();
		return jsonPage(sysLogService.selectPage(page, sysLog));
	}

	@ResponseBody
	@Permission("3000")
	@RequestMapping("/delete/{id}")
	public String delUser(@PathVariable Long id) {
		sysLogService.deleteById(id);
		return Boolean.TRUE.toString();
	}

	/**
	 * 根据 ID 删除日志
	 */
	@ResponseBody
	@Permission("3000")
	@RequestMapping("/delLog")
	public String delLog(@RequestParam("ids[]") List<Long> logIds) {
		boolean rlt = false;
		if (logIds != null) {
			rlt = sysLogService.deleteBatchIds(logIds);
		}
		return callbackResult(rlt);
	}

}
