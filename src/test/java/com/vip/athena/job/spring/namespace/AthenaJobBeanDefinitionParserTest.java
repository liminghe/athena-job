package com.vip.athena.job.spring.namespace;

import com.vip.athena.job.config.JobDefinition;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by waying.he on 2015/12/31.
 */
public class AthenaJobBeanDefinitionParserTest {


    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Map<String, JobDefinition> jobs = applicationContext.getBeansOfType(JobDefinition.class);
        System.out.println(jobs);

    }
}
