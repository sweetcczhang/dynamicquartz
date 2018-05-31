package cn.bupt.zcc.dynamicquartz.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @program: dynamicquartz
 * @description: ${description}
 * @author: Zhang Chengcheng
 * @create: 2018-05-31 15:07
 **/
@Component("ApplicationContextUtil")
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }


    public ApplicationContext getApplicationContext(){
        checkApplicationContext();
        return applicationContext;
    }

    public void checkApplicationContext(){
        if (applicationContext==null){
            throw new IllegalStateException("applicationContext 未注入,请在applicationContext.xml中定义SpringContextUtil");
        }
    }

    /**
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){

        return (T) applicationContext.getBean(name);
    }

    /**
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return (T) applicationContext.getBean(name,clazz);
    }

}
