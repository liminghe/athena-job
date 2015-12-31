package com.vip.athena.job.config;

import org.quartz.Job;
import org.quartz.Scheduler;

/**
 * 作业定义类
 * @author waying.he
 */
public class JobDefinition {
    /**
     * 作业名称，同一个群组中的作业名称必须唯一
     */
    private String name;
    /**
     * 作业群组名称
     */
    private String group = Scheduler.DEFAULT_GROUP;
    /**
     * 作业类
     */
    private Class<? extends Job> jobClass;
    /**
     *cron表达式
     */
    private String cron;
    /**
     *延迟多长时间执行，单位：s
     */
    private long delay;
    /**
     *作业执行间隔时间，单位：s
     */
    private long interval;
    /**
     *作业执行频率
     */
    private long rate;
    /**
     *系统系统时是否将配置文件中作业覆盖配置中心中作业配置
     *默认覆盖
     */
    private boolean override = true;
    /**
     *作业是否延迟执行，系统启动时作业将不纳入作业调度中，
     *需要在管理中心手动启动作业才能将作业纳入作业调度，
     * 默认系统启动时就将作业纳入调度中
     */
    private boolean lazyExecution = false;

    /**
     * 作业描述信息
     */
    private String description;

    @Override
    public String toString() {
        return String.format("(name=%s, group=%s,jobClass=%s,cron=%s, delay=%d, interval=%d, rate=%d)",
                name, group, jobClass, cron, delay, interval, rate);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isLazyExecution() {
        return lazyExecution;
    }

    public void setLazyExecution(boolean lazyExecution) {
        this.lazyExecution = lazyExecution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
