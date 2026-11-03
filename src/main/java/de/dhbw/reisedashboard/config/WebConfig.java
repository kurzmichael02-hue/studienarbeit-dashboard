package de.dhbw.reisedashboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                // Login-Seite selbst, Logout und die statischen Dateien muessen ohne Session gehen,
                // sonst kommt die Login-Seite ohne CSS und man kaeme nie rein.
                .excludePathPatterns("/login", "/logout", "/error", "/css/**", "/webjars/**");
    }
}
