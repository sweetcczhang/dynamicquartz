package cn.bupt.zcc.dynamicquartz.controller;

import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;
import cn.bupt.zcc.dynamicquartz.service.SchedulerJobService;
import cn.bupt.zcc.dynamicquartz.util.Message;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 张城城 on 2018/5/31.
 */
@RequestMapping("/api")
@Controller
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SchedulerJobService schedulerJobService;

//    @RequestMapping("/index")
//    public String index(HttpServletRequest request){
//        logger.info("[JobController] the url path:------------/index----------------");
//        logger.info("[JobController] the method index is start......");
//        List<ScheduleJob> jobList = schedulerJobService.getAllScheduleJob();
//        request.setAttribute("jobs",jobList);
//        logger.info("[JobController] the method index is end......");
//        return "index";
//    }

    /**
     *获取所有的任务
     * @return
     */
    @RequestMapping("/getAllJobs")
    @ResponseBody
    public Object getAllJobs(){
        logger.info("[JobController] the method:getAllJobs! the url path:------------/getAllJobs----------------");
        List<ScheduleJob> jobList = schedulerJobService.getAllScheduleJob();
        logger.info("[JobController] the method:getAllJobs is execution over ");
        return jobList;
    }

    /**
     * 获取正在执行的任务列表
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/getRunJob")
    @ResponseBody
    public Object getAllRunningJob() throws SchedulerException{
        logger.info("[JobController] the method:getAllRunningJob! the url path:------------/getRunJob----------------");
        List<ScheduleJob> jobList = schedulerJobService.getAllRunningJob();
        logger.info("[JobController] the method:getAllRunningJob is execution over ");
        return jobList;
    }

    /**
     *更新或者添加一个任务
     * @param scheduleJob
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object addOrUpdateJob(@ModelAttribute ScheduleJob scheduleJob){
        logger.info("[JobController] the method addOrUpdateJob is start URL path:/addJob, the param:{}", scheduleJob);
        Message message = Message.failure();
        try {
            schedulerJobService.saveOrUpdate(scheduleJob);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] addOrUpdateJob is failure in method:addOrUpdateJob！");
        }
        return message;
    }

    /**
     *运行一个任务
     * @param jobName
     * @param jobGroup
     */
    @RequestMapping("/runOneJob")
    @ResponseBody
    public Object runJob(String jobName, String jobGroup){
        logger.info("[JobController] the url path:------------/runOneJob----------------");
        Message message  = Message.failure();
        try {
            schedulerJobService.runOneJob(jobName,jobGroup);
            message = Message.success();
        } catch (SchedulerException e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] runOnejob is failure in method:runJob");
        }
        return message;
    }

    /**
     *停止一个定时任务
     * @param jobName
     * @param jobGroup
     */
    @RequestMapping("/pauseJob")
    @ResponseBody
    public Object pauseJob(String jobName, String jobGroup){
        logger.info("[JobController] the url path:------------/runOneJob----------------");
        Message message = Message.failure();
        try {
            schedulerJobService.pauseJob(jobName,jobGroup);
            message = Message.success();
        } catch (SchedulerException e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] pauseJob is failure in method:pauseJob");
        }
        return message;
    }

    /**
     * 删除一个定时任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping("/deleteJob")
    @ResponseBody
    public Object deleteJob(String jobName,String jobGroup){
        logger.info("[JobController] the url path:------------/runOneJob----------------");
        Message message = Message.failure();
        try {
            schedulerJobService.deleteJob(jobName,jobGroup);
            message = Message.success();
        } catch (SchedulerException e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] deleteJob is failre in method: deleteJob!");
        }
        return message;
    }

    /**
     * 重启一个定时任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @ResponseBody
    @RequestMapping("/resumeJob")
    public Object resumeJob(String jobName, String jobGroup){
        logger.info("[JobController] the url path:------------/resumeJob----------------");
        Message message = Message.failure();
        try {
            schedulerJobService.resumeJob(jobName,jobGroup);
            message = Message.success();
        } catch (SchedulerException e) {
            message.setMsg(e.getMessage());
            logger.error("[JobController] resumeJob is failre in method: resumeJob!");
        }
        return message;
    }

}
