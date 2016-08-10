package com.napkinstudio.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by User1 on 19.07.2016.
 */
public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer
         {

    /**
     * Creates root application context
     */
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return super.createRootApplicationContext();
    }

    /**
     * Creates serlvet application context
     */
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        return super.createServletApplicationContext();
    }

    /**
     * Application context config class
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    /**
     * Dispatcher-servlet config class
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    /**
     * Adds mapping
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/"};
    }

    /**
     * Specifies name of dispatcher-servlet
     */
    @Override
    protected String getServletName() {
        return "dispatcher";
    }

}