package cn.bupt.zcc.dynamicquartz.controller;

import cn.bupt.zcc.dynamicquartz.job.QuartzJobFactory;
import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 张城城 on 2018/5/31.
 */
@Controller
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private Scheduler scheduler;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){


        return "index";
    }


    @RequestMapping("/addJob")
    private void addjob(@ModelAttribute ScheduleJob scheduleJob){

        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup()).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup())
                .withSchedule(scheduleBuilder).build();
        jobDetail.getJobDataMap().put("scheduleJob",scheduleJob);
        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
