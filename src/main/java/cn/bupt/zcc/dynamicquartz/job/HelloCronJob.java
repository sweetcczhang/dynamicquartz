package cn.bupt.zcc.dynamicquartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 张城城 on 2018/6/1.
 */
@Service("helloCronJob")
public class HelloCronJob {
    private static final Logger logger = LoggerFactory.getLogger(HelloCronJob.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-ss");
    public void execute(){
        logger.info("[HelloCronJob]测试这个任务调度框架是否管用！");
        logger.info("----------------------------------:{}",sdf.format(new Date()));
    }
}
