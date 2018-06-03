package cn.bupt.zcc.dynamicquartz.model;

import java.io.Serializable;

public class ScheduleJob implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String jobId;

    private String jobName;

    private String jobStatus;

    private String jobGroup;

    private String cronExpression;

    private String description;

    private String beanName;

    private String methodName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "ScheduleJob{" +
                "id=" + id +
                ", jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}