package com.l2jwalker.util.spring;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bootstraps Spring-managed beans into an application. How to use:
 * <ul>
 * <li>Create application context XML configuration files and put them where they can be loaded as class path resources.
 * The configuration must include the {@code <context:annotation-config/>} element to enable annotation-based
 * configuration, or the {@code <context:component-scan base-package="..."/>} element to also detect bean definitions
 * from annotated classes.
 * <li>Create a "main" class that will receive references to Spring-managed beans. Add the {@code @Autowired} annotation
 * to any properties you want to be injected with beans from the application context.
 * <li>In your application {@code main} method, create an {@link com.l2jwalker.util.spring.ApplicationContextLoader} instance, and call the
 * {@link #load} method with the "main" object and the configuration file locations as parameters.
 * </ul>
 */
public class ApplicationContextLoader {

    private final String applicationContextMainXml;

    protected ConfigurableApplicationContext applicationContext;

    /**
     * Default constructor.
     */
    public ApplicationContextLoader(String applicationContextMainXml) {
        if (null == applicationContextMainXml) {
            throw new NullPointerException("Main context can't be null!");
        }
        this.applicationContextMainXml = applicationContextMainXml;
    }

    public ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Loads application context. Override this method to change how the application context is loaded.
     *
     * @param configLocations configuration file locations
     */
    protected void loadApplicationContext(String... configLocations) {
        if (null == applicationContext) { // we need only ONE applicationContext
            applicationContext = new ClassPathXmlApplicationContext(configLocations);
            applicationContext.registerShutdownHook();
        }
    }

    /**
     * Injects dependencies into the object. Override this method if you need full control over how dependencies are
     * injected.
     *
     * @param main object to inject dependencies into
     */
    protected void injectDependencies(Object main) {
        getApplicationContext().getBeanFactory().autowireBeanProperties(main, AutowireCapableBeanFactory.AUTOWIRE_NO,
                false);
    }

    /**
     * Loads application context, then injects dependencies into the object.
     *
     * @param main            object to inject dependencies into
     * @param configLocations configuration file locations
     */
    public void load(Object main, String... configLocations) {
        loadApplicationContext(configLocations);
        injectDependencies(main);
    }

    /**
     * Loads application context, then injects dependencies into the object.
     *
     * @param main object to inject dependencies into
     */
    public void load(Object main) {
        loadApplicationContext(applicationContextMainXml);
        injectDependencies(main);
    }

}