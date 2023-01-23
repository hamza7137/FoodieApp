package com.niit.springcloudapigateway.AppConfiguration;

import com.niit.springcloudapigateway.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
//@CrossOrigin(origins = "*")
public class AppConfig
{
    @Bean
    public RouteLocator locateRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p.path("/api/v1/**")
                        .uri("http://localhost:8081/"))
                .route(p->p.path("/api/v2/**")
                        .uri("http://localhost:8082/"))
                .route(p->p.path("/api/v3/**")
                        .uri("http://localhost:8083/"))
                .route(p->p.path("/api/v4/**")
                        .uri("http://localhost:8084/"))
                .build();
    }

    @Bean
    public FilterRegistrationBean jwtFilterBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v1/admin/**");
        filterRegistrationBean.addUrlPatterns("/api/v3/customer/**");
        filterRegistrationBean.addUrlPatterns("/api/v4/restaurant/**");

        return filterRegistrationBean;
    }

}
