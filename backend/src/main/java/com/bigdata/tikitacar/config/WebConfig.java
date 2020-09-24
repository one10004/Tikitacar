package com.bigdata.tikitacar.config;

import com.bigdata.tikitacar.util.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] INCLUDE_PATHS = {
    };
    private static final String[] EXCLUDE_PATHS = {
            "/deal/view/**",
            "/**"
    };

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns(INCLUDE_PATHS)
                .excludePathPatterns(EXCLUDE_PATHS);
    }
}
