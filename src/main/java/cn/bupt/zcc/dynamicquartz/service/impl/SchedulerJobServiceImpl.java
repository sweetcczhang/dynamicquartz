package cn.bupt.zcc.dynamicquartz.service.impl;

import cn.bupt.zcc.dynamicquartz.job.QuartzJobFactory;
import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;
import cn.bupt.zcc.dynamicquartz.service.SchedulerJobService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 张城城 on 2018/6/1.
 */
@Service
public class SchedulerJobServiceImpl implements SchedulerJobService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerJobServiceImpl.class);
    @Autowired
    private Scheduler scheduler;

    /**
     * 获取所有的任务
     * @return
     */
    @Override
    public List<ScheduleJob> getAllScheduleJob() {
        List<ScheduleJob> jobList = new ArrayList<>();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyGroup();
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey key : jobKeys){
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(key);
                for (Trigger trigger: triggers){
                    ScheduleJob scheduleJob = new ScheduleJob();
                    getScheduleJob(scheduleJob,scheduler,key,trigger);
                    jobList.add(scheduleJob);
                }
            }
        } catch (SchedulerException e) {
            logger.error("[SchedulerJobServiceImpl] get the jobKeys is error:{}",e);
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有运行中的任务
     * @return
     * @throws SchedulerException
     */
    @Override
    public List<ScheduleJob> getAllRunningJob() throws SchedulerException {

        List<JobExecutionContext> executionJobList = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList<>();
        for (JobExecutionContext jobExecutionContext: executionJobList){
            JobDetail jobDetail = jobExecutionContext.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = jobExecutionContext.getTrigger();
            ScheduleJob scheduleJob = new ScheduleJob();
            getScheduleJob(scheduleJob,scheduler,jobKey,trigger);
            jobList.add(scheduleJob);
        }
        return jobList;
    }

    /**
     * 更新新的任务或者添加一个新的任务
     * @param scheduleJob
     * @throws Exception
     */
    @Override
    public void  saveOrUpdate(ScheduleJob scheduleJob) throws Exception{
        if (StringUtils.isBlank(scheduleJob.getJobId())){
            addJob(scheduleJob);
        }else {
            updateJobCronSchedule(scheduleJob);
        }
    }

    /**
     * 停止运行任务
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    public void pauseJob(String jobName, String jobGroup) throws SchedulerException{
        JobKey jobKey = JobKey.jobKey(jobName,jobGroup);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 删除一个任务
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    public void deleteJob(String jobName,String jobGroup) throws SchedulerException{
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 运行一个任务
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    public void runOneJob(String jobName, String jobGroup) throws SchedulerException{
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.triggerJob(jobKey);
    }


    /**
     * 添加任务
     * @param scheduleJob
     * @throws Exception
     */
    private void addJob(ScheduleJob scheduleJob) throws Exception{
        checkNotNull(scheduleJob);
        if (StringUtils.isBlank(scheduleJob.getCronExpression())){
            throw new Exception("[SchedulerJobServiceImpl] CronExpression不能为空");
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (cronTrigger!=null){
            throw new Exception("job is already exist!");
        }
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup())
                .build();
        jobDetail.getJobDataMap().put("scheduleJob",scheduleJob);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        cronTrigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup())
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    /**
     * 更新一个任务
     * @param scheduleJob
     * @throws Exception
     */
    private void updateJobCronSchedule(ScheduleJob scheduleJob) throws Exception{
        checkNotNull(scheduleJob);
        if (StringUtils.isBlank(scheduleJob.getCronExpression())){
            throw new Exception("[SchedulerJobServiceImpl] CronExpression不能为空");
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
        jobDetail.getJobDataMap().put("scheduleJob",scheduleJob);
        scheduler.rescheduleJob(triggerKey,cronTrigger);

    }


    /**
     * 判断一个任务是否为空
     * @param scheduleJob
     */
    @Override
    public void checkNotNull(ScheduleJob scheduleJob) {
        if (scheduleJob==null){
            throw new IllegalStateException("scheduleJob is null,Please check it");
        }
        if (scheduleJob.getJobName()==null || scheduleJob.getJobName().equals("")){
            throw new IllegalStateException("the jobName of scheduleJob is null,Please check it");
        }
        if (scheduleJob.getJobGroup()==null || scheduleJob.getJobGroup().equals("")){
            throw new IllegalStateException("the jobGroup of scheduleJob is null,Please check it");
        }
        if (scheduleJob.getBeanName()==null || scheduleJob.getBeanName().equals("")){
            throw new IllegalStateException("the BeanName of scheduleJob is null,Please check it");
        }


    }



    private void getScheduleJob(ScheduleJob scheduleJob, Scheduler scheduler, JobKey jobKey, Trigger trigger){
        try {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            scheduleJob = (ScheduleJob)jobDetail.getJobDataMap().get("scheduleJob");
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            scheduleJob.setJobStatus(triggerState.name());
            scheduleJob.setJobName(jobKey.getName());
            scheduleJob.setJobGroup(jobKey.getGroup());
            if (trigger instanceof CronTrigger){
                CronTrigger cronTrigger = (CronTrigger) trigger;
                scheduleJob.setCronExpression(cronTrigger.getCronExpression());
            }

        } catch (Exception e) {
            logger.error("[SchedulerJobServiceImpl] method getScheduleJob get JobDetail error:{}",e);
        }
    }

}
