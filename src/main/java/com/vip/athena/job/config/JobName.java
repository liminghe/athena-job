package com.vip.athena.job.config;

import com.google.common.base.Preconditions;
import org.quartz.Scheduler;

/**
 * @author waying.he
 */
public class JobName {
    private static final String DEFAULT_GROUP = Scheduler.DEFAULT_GROUP;
    private String group;
    private String name;

    public JobName(String name) {
        this(DEFAULT_GROUP, name);
    }

    public JobName(String group, String name) {
        Preconditions.checkArgument(name != null, "Job name must not be null");
        if (group == null) {
            group = DEFAULT_GROUP;
        }
        this.group = group;
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

}
