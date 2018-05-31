package cn.bupt.zcc.dynamicquartz.service;

/**
 * @program: dynamicquartz
 * @description:
 * @author: Zhang Chengcheng
 * @create: 2018-05-31 14:47
 **/
public interface QuartzService {

     void executeTask(String beanName,String methodName);

     void executeTask(String beanName);
}
