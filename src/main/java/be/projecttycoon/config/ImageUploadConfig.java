package be.projecttycoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
/**
 * Created by kiwi on 15/02/2016.
 */

@Configuration
public class ImageUploadConfig {

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(20000000);
        factory.setMaxRequestSize(20000000);
        //factory.setFileSizeThreshold();
        //factory.setLocation();

        return factory.createMultipartConfig();
    }
}
