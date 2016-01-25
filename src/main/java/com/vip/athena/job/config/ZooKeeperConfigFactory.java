package com.vip.athena.job.config;

import com.vip.athena.job.exception.JobException;
import com.vip.athena.job.util.PropertiesHelper;
import org.slf4j.Logger;

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

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ZooKeeperConfigFactory.class);

    private static Map<String, ZooKeeperConfig> configs = new HashMap<>(3);

    private static volatile ZooKeeperConfigFactory instance = new ZooKeeperConfigFactory();

    private ZooKeeperConfigFactory() {}

    public static ZooKeeperConfigFactory getInstance() {
        return instance;
    }

    public ZooKeeperConfig getZooKeeperConfig(String configFileName) {
        if (logger.isDebugEnabled()) {
            logger.debug("load zookeeper config from file: " + configFileName);
        }
        PropertiesHelper ph = new PropertiesHelper(loadPropertiesFromClasspath(configFileName));
        ZooKeeperConfig config = configs.get(configFileName);
        if (config == null) {
            synchronized (configs) {
                config = configs.get(configFileName);
                if (config == null) {
                    config = newZooKeeperConfig(ph);
                    configs.put(configFileName, config);
                }
            }
        }
        return copyZooKeeperConfig(config);
    }

    public ZooKeeperConfig getZooKeeperConfig() {
        return getZooKeeperConfig(DEFAULT_ZK_CONFIG_FILE);
    }


    private ZooKeeperConfig newZooKeeperConfig(PropertiesHelper zkPropertiesHelper) {
        String connectString = zkPropertiesHelper.getString(CONNECTION_STRING);
        String namespace = zkPropertiesHelper.getString(NAMESPACE);
        int sessionTimeoutMs = zkPropertiesHelper.getInt(SESSION_TIMEOUT, 3000);
        int connectionTimeoutMs = zkPropertiesHelper.getInt(CONNECTION_TIMEOUT, 5000);
        int maxRetryTimes = zkPropertiesHelper.getInt(MAX_RETRY, 5);
        int baseSleepTimeMs = zkPropertiesHelper.getInt(BASE_SLEEP_TIME, 2000);
        int maxSleepTimeMs = zkPropertiesHelper.getInt(MAX_SLEEP_TIME, 5000);
        if (connectString == null) {
            throw new JobException("connect string not set");
        }
        if (namespace == null) {
            throw new JobException("namespace not set");
        }
        ZooKeeperConfig config = new ZooKeeperConfig(connectString, namespace);
        config.setConnectionTimeoutMs(connectionTimeoutMs);
        config.setSessionTimeoutMs(sessionTimeoutMs);
        config.setMaxRetryTimes(maxRetryTimes);
        config.setBaseSleepTimeMs(baseSleepTimeMs);
        config.setMaxSleepTimeMs(maxSleepTimeMs);
        return config;
    }

    private ZooKeeperConfig copyZooKeeperConfig(ZooKeeperConfig config) {
        ZooKeeperConfig copied = new ZooKeeperConfig(config.getConnectString(), config.getNamespace());
        copied.setConnectionTimeoutMs(config.getConnectionTimeoutMs());
        copied.setSessionTimeoutMs(config.getSessionTimeoutMs());
        copied.setMaxRetryTimes(config.getMaxRetryTimes());
        copied.setBaseSleepTimeMs(config.getBaseSleepTimeMs());
        copied.setMaxSleepTimeMs(config.getMaxSleepTimeMs());
        return copied;
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
