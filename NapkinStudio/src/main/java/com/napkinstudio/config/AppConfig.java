package com.napkinstudio.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.napkinstudio.config.security.SecurityConfig;
import com.napkinstudio.sapcommunicationmodels.DataTransferFromSAP;
import com.napkinstudio.sapcommunicationmodels.DataTransferToSAP;
import com.napkinstudio.util.FTPCommunicator;
import com.thoughtworks.xstream.XStream;
/**
 * Created by User1 on 18.07.2016.
 */
@EnableWebMvc
@Configuration
@ComponentScan( { "com.napkinstudio.*" })
@Import(value = { WebSocketConfig.class, SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter{

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }    
    
    
    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("khomenkotest1");
        mailSender.setPassword("napkinstudio2016");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/fmtemplates/");
        return bean;
    }


    @Bean
    public FTPCommunicator getHandler(){
        FTPCommunicator handler= new FTPCommunicator();
        handler.setMarshaller(getCastorMarshaller());
        handler.setUnmarshaller(getCastorMarshaller());
        return handler;
    }

    @Bean
    public CastorMarshaller getCastorMarshaller() {
        CastorMarshaller castorMarshaller = new CastorMarshaller();
        Resource resource = new ClassPathResource("mapping.xml");
        castorMarshaller.setMappingLocation(resource);
        return castorMarshaller;
    }
    
    @Bean
    public XStream  getXStream() {
    	XStream xstream = new XStream();
    	xstream.alias("DataTransferFromSAP", DataTransferFromSAP.class);
    	xstream.alias("DataTransferToSAP", DataTransferToSAP.class);
    	return xstream;
    }


//    @Bean(name = "multipartResolver")
//    public StandardServletMultipartResolver resolver() {
//        return new StandardServletMultipartResolver();
//    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver resolver() {
        return new CommonsMultipartResolver();
    }



}
