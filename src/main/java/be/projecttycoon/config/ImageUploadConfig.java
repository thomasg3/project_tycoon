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


    /*todo kies logische filegrootte*/
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(999999999);
        factory.setMaxRequestSize(999999999);
        //factory.setFileSizeThreshold();
        //factory.setLocation();

        return factory.createMultipartConfig();
    }
}
