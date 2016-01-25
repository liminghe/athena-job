package com.vip.athena.job.config;

/**
 * @author waying.he
 */
public class ZooKeeperConfig {
    /**
     * zookeeper链接字符串
     */
    private final String connectString;

    /**
     * 命名空间，zookeeper中所有节点都在挂载在这个命名空间下
     */
    private final String namespace;

    /**
     * 链接超时时间
     * 单位：毫秒
     */
    private int connectionTimeoutMs;

    /**
     * session超时时间
     * 单位：毫秒
     */
    private int sessionTimeoutMs;

    /**
     * 最大重试次数
     */
    private int maxRetryTimes;

    /**
     * 重连最大间隔时间
     */
    private int maxSleepTimeMs;

    /**
     * 重连初始间隔时间
     */
    private int baseSleepTimeMs;

    public ZooKeeperConfig(String connectString, String namespace) {
        this.connectString = connectString;
        this.namespace = namespace;
    }

    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public int getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

    public int getMaxSleepTimeMs() {
        return maxSleepTimeMs;
    }

    public void setMaxSleepTimeMs(int maxSleepTimeMs) {
        this.maxSleepTimeMs = maxSleepTimeMs;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }
}
