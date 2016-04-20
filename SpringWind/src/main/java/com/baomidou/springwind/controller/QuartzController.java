package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.jobs.DemoJob;
import com.baomidou.springwind.service.IQuartzService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

/**
 * <p>
 * Quartz管理
 * </p>
 *
 * @author yyn_0210@sina.com
 * @Date 2016-04-19
 */
@Controller
@RequestMapping("/quartz")
public class QuartzController extends BaseController {
    private static final Log LOGGER = LogFactory.getLog(DemoJob.class);

    @Autowired
    private IQuartzService quartzService;

    /**
     * 发送
     */
    @Permission("1001")
    @RequestMapping("/add")
    public String addJob(Model model, String jobName) throws ParseException, SchedulerException {
		if (isPost()) {
			model.addAttribute("jobName", jobName);
			model.addAttribute("loginName", getSSOToken().getData());
			boolean rlt = quartzService.addDemoJob(jobName);
			String tipMsg = "名称为【" + jobName + "】的Job 已存在！！";
			if(rlt){
				tipMsg = "添加JOb成功！！";
			}
			model.addAttribute("tipMsg", tipMsg);
		}

        return "/quartz/add";

    }

}
