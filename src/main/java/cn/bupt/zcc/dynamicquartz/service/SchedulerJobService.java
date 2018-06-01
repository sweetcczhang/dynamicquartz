package cn.bupt.zcc.dynamicquartz.service;

import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @program: dynamicquartz
 * @description: ${description}
 * @author: Zhang Chengcheng
 * @create: 2018-05-31 16:12
 **/
public interface SchedulerJobService {

    List<ScheduleJob> getAllScheduleJob();

    void checkNotNull(ScheduleJob scheduleJob);

    List<ScheduleJob> getAllRunningJob() throws SchedulerException;

    void saveOrUpdate(ScheduleJob scheduleJob) throws Exception;



}
