package cn.bupt.zcc.dynamicquartz.service.impl;

import cn.bupt.zcc.dynamicquartz.dao.ScheduleJobMapper;
import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;
import cn.bupt.zcc.dynamicquartz.model.ScheduleJobExample;
import cn.bupt.zcc.dynamicquartz.service.ScheduleJobInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by 张城城 on 2018/6/1.
 */
@Service("scheduleJobInService")
public class ScheduleJobInServiceImpl implements ScheduleJobInService {

    @Autowired
    ScheduleJobMapper scheduleJobMapper;

    @Override
    public int insert(ScheduleJob scheduleJob) {
        int id = scheduleJobMapper.insertSelective(scheduleJob);
        return id;
    }

    @Override
    public int insertSelective(ScheduleJob scheduleJob) {
        int id = scheduleJobMapper.insertSelective(scheduleJob);
        return id;
    }

    @Override
    public ScheduleJob selectByJobNameAngJobGroup(String jobName, String groupName) {
        ScheduleJobExample scheduleJobExample = new ScheduleJobExample();
        scheduleJobExample.createCriteria().andJobGroupEqualTo(groupName).andJobNameEqualTo(jobName);
        List<ScheduleJob> list = scheduleJobMapper.selectByExample(scheduleJobExample);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ScheduleJob selectByPrimaryKey(Integer id) {
        return scheduleJobMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(ScheduleJob scheduleJob) {
        return scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
    }

    @Override
    public int updateByExample(ScheduleJob scheduleJob) {
        ScheduleJobExample scheduleJobExample = new ScheduleJobExample();
        scheduleJobExample.createCriteria().andJobNameEqualTo(scheduleJob.getJobName()).andJobGroupEqualTo(scheduleJob.getJobGroup());
        return scheduleJobMapper.updateByExample(scheduleJob,scheduleJobExample);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return scheduleJobMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByJobNameAndJobGroup(String jobName, String jobGroup) {
        ScheduleJobExample scheduleJobExample = new ScheduleJobExample();
        scheduleJobExample.createCriteria().andJobGroupEqualTo(jobGroup).andJobNameEqualTo(jobName);
        return scheduleJobMapper.deleteByExample(scheduleJobExample);
    }
}
