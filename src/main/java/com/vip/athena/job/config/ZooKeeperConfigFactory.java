package com.vip.athena.job.config;

import com.vip.athena.job.exception.JobException;
import com.vip.athena.job.util.PropertiesHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author waying.he
 */
public class ZooKeeperConfigFactory {

    public final static String DEFAULT_ZK_CONFIG_FILE = "zookeeper.properties";

    public final static String CONNECTION_STRING = "zk.connectString";
    public final static String NAMESPACE = "zk.namespace";
    public final static String MAX_RETRY = "zk.maxRetryTimes";
    public final static String SESSION_TIMEOUT = "zk.sessionTimeoutMs";
    public final static String CONNECTION_TIMEOUT = "zk.connectionTimeoutMs";
    public final static String BASE_SLEEP_TIME = "zk.baseSleepTimeMs";
    public final static String MAX_SLEEP_TIME = "zk.maxSleepTimeMs";

    private static Map<String, ZooKeeperConfig> configs = new HashMap<>(3);


    public ZooKeeperConfig getZooKeeperConfig(String configFileName) {
        PropertiesHelper ph = new PropertiesHelper(loadPropertiesFromClasspath(configFileName));

    }

    public ZooKeeperConfig getZooKeeperConfig() {
        return getZooKeeperConfig(DEFAULT_ZK_CONFIG_FILE);
    }

    private Properties loadPropertiesFromClasspath(String configFileName) {
        Properties properties = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(configFileName);
        if (in == null) {
            throw new JobException("zookeeper.properties not fount from classpath");
        }
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new JobException(e);
        }
        return properties;
    }
}
