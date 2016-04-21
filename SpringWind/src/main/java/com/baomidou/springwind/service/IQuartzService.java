package com.baomidou.springwind.service;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.springwind.entity.User;
import org.quartz.SchedulerException;

import java.text.ParseException;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IQuartzService  {

	/**
	 * 添加Demo Job
	 *
	 */
	boolean addDemoJob(String jobName) throws ParseException, SchedulerException;

}