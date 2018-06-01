package cn.bupt.zcc.dynamicquartz.service;

import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;

/**
 * Created by 张城城 on 2018/6/1.
 */
public interface ScheduleJobInService {

    int insert(ScheduleJob scheduleJob);

    int insertSelective(ScheduleJob scheduleJob);

    ScheduleJob selectByJobNameAngJobGroup(String jobName, String groupName);

    ScheduleJob selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ScheduleJob scheduleJob);

    int updateByExample(ScheduleJob scheduleJob);

    int deleteByPrimaryKey(Integer id);

    int deleteByJobNameAndJobGroup(String jobName, String jobGroup);



}
