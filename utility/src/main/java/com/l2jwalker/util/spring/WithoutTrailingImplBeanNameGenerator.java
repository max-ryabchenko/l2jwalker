package com.l2jwalker.util.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

import static org.apache.commons.lang.StringUtils.substringBeforeLast;

/**
 * {@link org.springframework.context.annotation.AnnotationBeanNameGenerator} to remove the trailing <code>Impl</code> of the spring bean names that are picked by spring using the annotation scanning feature.
 * <p>
 * To enable in your applicationContext:
 * <pre>
 *  &lt;context:component-scan base-package="au.com.promedicus.smd" name-generator="au.com.promedicus.smd.util.WithoutTrailingImplBeanNameGenerator"&gt;
 * </pre>
 * @see org.springframework.context.annotation.AnnotationBeanNameGenerator
 */
public class WithoutTrailingImplBeanNameGenerator extends AnnotationBeanNameGenerator
{

    /**
     * Generate bean name.
     * @param definition
     * @param registry
     */
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry)
    {
        String beanName = super.generateBeanName(definition, registry);
        return beanName.endsWith("Impl") ? substringBeforeLast(beanName, "Impl") : beanName;
    }
}