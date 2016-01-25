package com.vip.athena.job.spring.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author waying.he
 */
public class AthenaNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("job", new AthenaJobBeanDefinitionParser());
    }
}
