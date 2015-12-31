package com.vip.athena.job.spring.namespace;

import com.vip.athena.job.config.JobDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author waying.he
 */
public class AthenaJobBeanDefinitionParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(JobDefinition.class);
        String cron = element.getAttribute("cron");
        String interval = element.getAttribute("interval");
        String rate = element.getAttribute("rate");
        if (StringUtils.isEmpty(cron) && (StringUtils.isEmpty(interval) || StringUtils.isEmpty(rate))) {
            throw new BeanDefinitionValidationException("athena job must set cron or (interval and rate) attribute");
        }
        addPropertyValueIfNotEmpty("name", element, builder);
        addPropertyValueIfNotEmpty("group", element, builder);
        addPropertyValueIfNotEmpty("jobClass", element, builder);
        addPropertyValueIfNotEmpty("cron", element, builder);
        addPropertyValueIfNotEmpty("delay", element, builder);
        addPropertyValueIfNotEmpty("interval", element, builder);
        addPropertyValueIfNotEmpty("rate", element, builder);
        addPropertyValueIfNotEmpty("override", element, builder);
        addPropertyValueIfNotEmpty("lazyExecution", element, builder);
        addPropertyValueIfNotEmpty("description", element, builder);
        return builder.getBeanDefinition();
    }

    private void addPropertyValueIfNotEmpty(final String propertyName, final Element element, BeanDefinitionBuilder builder) {
        String propertyValue = element.getAttribute(propertyName);
        if (StringUtils.isEmpty(propertyValue)) {
            return;
        }
        builder.addPropertyValue(propertyName, propertyValue);
    }

    @Override
    protected boolean shouldGenerateId() {
        return true;
    }
}
