package com.vip.athena.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by waying.he on 2015/12/31.
 */
public class HelloJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello athena job");
    }
}
