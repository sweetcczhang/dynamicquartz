package cn.bupt.zcc.dynamicquartz.model;

import java.io.Serializable;

/**
 * @program: dynamicquartz
 * @description: ${description}
 * @author: Zhang Chengcheng
 * @create: 2018-05-31 15:57
 **/
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jobId;   //任务Id

    private String jobName;  //任务的名称

    private String jobGroup;  //任务组

    private String jobStatus; //任务的状态

    private String cronExpression;  //cron表达式

    private String desc;    //任务描述

    private String beanName;    //要执行任务bean的名称

    private String methodName;  //要执行任务的方法


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
