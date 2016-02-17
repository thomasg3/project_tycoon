package be.projecttycoon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by kiwi on 16/02/2016.
 */
@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/hosted_images/**").addResourceLocations("file:D:\\");
        registry.addResourceHandler("/hosted_resources/**").addResourceLocations("file:../images/");
    }
}