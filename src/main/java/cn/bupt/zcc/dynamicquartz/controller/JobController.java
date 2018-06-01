package cn.bupt.zcc.dynamicquartz.controller;

import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;
import cn.bupt.zcc.dynamicquartz.service.SchedulerJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 张城城 on 2018/5/31.
 */
@Controller
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SchedulerJobService schedulerJobService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        logger.info("[JobController] the method index is start......");
        List<ScheduleJob> jobList = schedulerJobService.getAllScheduleJob();
        request.setAttribute("jobs",jobList);
        logger.info("[JobController] the method index is end......");
        return "index";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/getAllJobs")
    public Object getAllJobs(){
        List<ScheduleJob> jobList = schedulerJobService.getAllScheduleJob();
        return jobList;
    }

    /**
     * 获取正在执行的任务列表
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/getRunJob")
    public Object getAllRunningJob() throws SchedulerException{
        List<ScheduleJob> jobList = schedulerJobService.getAllRunningJob();
        return jobList;
    }


    @RequestMapping("/addJob")
    public void addOrUpdateJob(@ModelAttribute ScheduleJob scheduleJob){
        logger.info("[JobController] the method addOrUpdateJob is start path:/addJob, the param:{}", scheduleJob);
        try {
            schedulerJobService.saveOrUpdate(scheduleJob);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
