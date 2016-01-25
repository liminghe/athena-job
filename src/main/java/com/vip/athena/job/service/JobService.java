package com.vip.athena.job.service;

import org.quartz.Scheduler;

/**
 * Job服务类
 * @author waying.he
 */
public interface JobService {


    void scheduleJob(String group, String name);

    void rescheduleJob(String group, String name, String cron);

    void rescheduleJob(String group, String name, long delay, long rate, long interval);

    void pauseJob(String group, String name);

    void resumeJob(String group, String name);

    void stopJob(String group, String name);

    boolean isJobExists(String group, String name);

    
}
