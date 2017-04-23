package com.template.springMVCtemplate.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by ud on 23/4/17.
 *
 * @EnableWebMvc : Adding this annotation to an @Configuration class imports the Spring MVC configuration
 * from WebMvcConfigurationSupport.
 *
 * This class configures the Dispatcher or Front Controller.
 * The Spring Dispatcher is actually the AppInit file which uses this class to configure itself.
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.template.springMVCtemplate")
public class AppConfig extends WebMvcConfigurerAdapter {

    /**
     * Configure View Resolver and set where to find the views for the application
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /*
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /*
     * Configure MessageSource to provide internationalized messages and provide custom messages from
     * messages.properties
     *
     */

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
}