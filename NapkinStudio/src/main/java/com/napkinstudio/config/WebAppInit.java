package com.napkinstudio.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

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

             @Override
             protected void customizeRegistration(ServletRegistration.Dynamic registration) {
                 registration.setMultipartConfig(getMultipartConfigElement());
             }

             private MultipartConfigElement getMultipartConfigElement() {
                 MultipartConfigElement multipartConfigElement = new MultipartConfigElement( LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
                 return multipartConfigElement;
             }

             private static final String LOCATION = "D:/temp/"; // Temporary location where files will be stored

             private static final long MAX_FILE_SIZE = 209715200; // 200MB : Max file size.
             // Beyond that size spring will throw exception.
             private static final long MAX_REQUEST_SIZE = 209715200; // 200MB : Total request size containing Multi part.

             private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk


}