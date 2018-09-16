package com.cmcc.andedu.hdsyn.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * Created by LiYangpan on 2018/7/17  4:06 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述:
 */
@Component("SchedulingManager")
@ConfigurationProperties(prefix = "cron")
public class SchedulingManager implements SchedulingConfigurer {

    private String hd_gen_expression;

    @Autowired
    private HbbCvsGeneratorTask hbbCvsGeneratorTask;

    @Autowired
    private EduHdFilesGeneratorTask eduHdFilesGeneratorTask;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

//        scheduledTaskRegistrar.addTriggerTask(hbbCvsGeneratorTask, (triggerContext) -> {
//            //@Override
//            //public Date nextExecutionTime(TriggerContext triggerContext) {
////                CronTrigger cronTrigger = new CronTrigger(expression);
////                return cronTrigger.nextExecutionTime(triggerContext);
//            //}
//
//            CronTrigger cronTrigger_hdfile = new CronTrigger(expression);
//            return cronTrigger_hdfile.nextExecutionTime(triggerContext);
//        });

        scheduledTaskRegistrar.addTriggerTask(eduHdFilesGeneratorTask, (triggerContext) -> {
            CronTrigger cronTrigger_hdfile = new CronTrigger(hd_gen_expression);
            return cronTrigger_hdfile.nextExecutionTime(triggerContext);
        });

    }

    public String getHd_gen_expression() {
        return hd_gen_expression;
    }

    public void setHd_gen_expression(String hd_gen_expression) {
        this.hd_gen_expression = hd_gen_expression;
    }
}
