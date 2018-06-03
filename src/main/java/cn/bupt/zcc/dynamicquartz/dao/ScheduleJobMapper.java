package cn.bupt.zcc.dynamicquartz.dao;

import cn.bupt.zcc.dynamicquartz.model.ScheduleJob;
import cn.bupt.zcc.dynamicquartz.model.ScheduleJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleJobMapper {
    int countByExample(ScheduleJobExample example);

    int deleteByExample(ScheduleJobExample example);

    int deleteByPrimaryKey(Integer id);

    Integer insert(ScheduleJob record);

    Integer insertSelective(ScheduleJob record);

    List<ScheduleJob> selectByExample(ScheduleJobExample example);

    ScheduleJob selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScheduleJob record, @Param("example") ScheduleJobExample example);

    int updateByExample(@Param("record") ScheduleJob record, @Param("example") ScheduleJobExample example);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);
}