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
                // Login-Seite selbst, das Anlegen eines neuen Profils, Logout und die statischen
                // Dateien muessen ohne Session gehen, sonst kommt die Login-Seite ohne CSS und man
                // kaeme nie rein (und koennte sich nie das erste Profil anlegen).
                .excludePathPatterns("/login", "/benutzer/neu", "/logout", "/error", "/css/**", "/webjars/**");
    }
}
