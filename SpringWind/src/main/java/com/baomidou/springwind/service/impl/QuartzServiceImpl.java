package com.baomidou.springwind.service.impl;

import com.baomidou.framework.quartz.QuartzJobManager;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.jobs.DemoJob;
import com.baomidou.springwind.mapper.UserMapper;
import com.baomidou.springwind.service.IQuartzService;
import com.baomidou.springwind.service.IUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Quartz 测试业务类
 */
@Service
public class QuartzServiceImpl implements IQuartzService {
    private static final Log LOGGER = LogFactory.getLog(QuartzServiceImpl.class);

    @Autowired
    private QuartzJobManager quartzJobManager;

    @Override
    public boolean addDemoJob(String jobName) throws ParseException, SchedulerException {
        LOGGER.debug("---------- 添加测试Job处理开始 ---------");

        if(StringUtils.isEmpty(jobName)){
            jobName = "DemoJob";
        }

        // 如果已经添加过Job
        if (quartzJobManager.isJobAdded(jobName, "job_group")) {
            LOGGER.error("名称[" + jobName + "]的任务已存在]");
            return false;
        }

        // 添加一个每分钟执行的任务
        quartzJobManager.addJob(jobName, DemoJob.class, "0 0/1 * * * ?");

        LOGGER.debug("---------- 添加测试Job处理结束 ---------");
        return true;
    }
}