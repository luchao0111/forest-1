package com.thebeastshop.forest.spring.schema;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.thebeastshop.forest.spring.beans.ClientFactoryBean;
import com.thebeastshop.forest.spring.utils.ClientFactoryBeanUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-04-24 19:49
 */
public class ForestClientBeanDefinitionParser implements BeanDefinitionParser {
    private static Log log = LogFactory.getLog(ForestConfigurationBeanDefinitionParser.class);

    private final Class factoryBeanClass = ClientFactoryBean.class;

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();

        beanDefinition.setLazyInit(false);
        String id = element.getAttribute("id");
        if (id == null || id.length() == 0) {
            String generatedBeanName = factoryBeanClass.getName();
            id = generatedBeanName;
            int counter = 2;
            while(parserContext.getRegistry().containsBeanDefinition(id)) {
                id = generatedBeanName + (counter ++);
            }
        }
        if (id != null && id.length() > 0) {
            if (parserContext.getRegistry().containsBeanDefinition(id))  {
                throw new IllegalStateException("Duplicate spring bean id " + id);
            }
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
//            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }

        String configurationId = element.getAttribute("configuration");
        String clientClassName = element.getAttribute("class");

        ClientFactoryBeanUtils.setupClientFactoryBean(beanDefinition, configurationId, clientClassName);
        log.info("[Forest] Created Forest Client Bean with name '" + id
                + "' and Proxy of '" + clientClassName + "' client interface");

        return beanDefinition;
    }
}
