package edu.td3.models;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("io.github.jeemv.springboot.vuejs")
public class AppConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
       return builder.build();
    }


}