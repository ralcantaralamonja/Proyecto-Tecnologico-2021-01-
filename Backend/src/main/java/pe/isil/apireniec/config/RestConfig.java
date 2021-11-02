package pe.isil.apireniec.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate createRestTemplate(RestTemplateBuilder builder){
        return builder
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Bearer 2ad4c7f85cc0c5b02da0b2084d7953401bd4c0ce2342e3e9451de55b7e540af8")
                .build();
    }
}
