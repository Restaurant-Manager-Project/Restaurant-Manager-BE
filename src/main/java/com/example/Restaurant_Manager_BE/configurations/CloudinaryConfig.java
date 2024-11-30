package com.example.Restaurant_Manager_BE.configurations;
import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary configKey(){
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "dxemmfi5t");
        config.put("api_key", "855496453531336");
        config.put("api_secret", "GW2B2F0QIsTO3oTNX23dZkywebc");
        return new Cloudinary(config);
    }
}
