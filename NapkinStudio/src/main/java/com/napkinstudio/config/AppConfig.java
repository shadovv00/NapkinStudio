package com.napkinstudio.config;

import com.napkinstudio.config.security.SecurityConfig;
import com.napkinstudio.util.FTPCommunicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

/**
 * Created by User1 on 18.07.2016.
 */
@EnableWebMvc
@Configuration
@ComponentScan( { "com.napkinstudio.*" })
@Import(value = { SecurityConfig.class })
public class AppConfig{

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("khomenko");
        mailSender.setPassword("10");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
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

}
