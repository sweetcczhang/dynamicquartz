package cn.bupt.zcc.dynamicquartz.job;

import cn.bupt.zcc.dynamicquartz.service.QuartzService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @program: dynamicquartz
 * @description: ${description}
 * @author: Zhang Chengcheng
 * @create: 2018-05-31 14:38
 **/
public class QuartzJobFactory extends QuartzJobBean {

    @Autowired
    QuartzService quartzService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            Object object = jobExecutionContext.getMergedJobDataMap().get("");
    }
}
