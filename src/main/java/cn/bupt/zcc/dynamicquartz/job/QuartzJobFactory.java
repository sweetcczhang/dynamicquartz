package cn.bupt.zcc.dynamicquartz.job;

import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;
import cn.bupt.zcc.dynamicquartz.service.QuartzService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author: Zhang Chengcheng
 * @create: 2018-05-31 14:38
 **/
@Service("quartzJobFactory")
public class QuartzJobFactory extends QuartzJobBean {

    @Autowired
    QuartzService quartzService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob object = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        if(object.getMethodName()==null || object.getMethodName().equals("")){
            quartzService.executeTask(object.getBeanName());
        }else {
            quartzService.executeTask(object.getBeanName(),object.getMethodName());
        }
    }
}
