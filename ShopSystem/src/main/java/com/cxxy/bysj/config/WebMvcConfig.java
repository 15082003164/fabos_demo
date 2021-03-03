package com.cxxy.bysj.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        String pathPatterns="/pictures/**";
        String pathAbsolute="file:D:/upload/";
        if (os.toLowerCase().startsWith("linux")){
            pathAbsolute="file:/usr/upload/";
        }
        registry.addResourceHandler(pathPatterns).addResourceLocations(pathAbsolute);

    }

}