package com.dzg.springboot.config;

import com.dzg.springboot.component.LoginInterceptor;
import com.dzg.springboot.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurer来扩展springMVC功能
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/dzg").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        SpringBoot已经做好了静态资源映射
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html", "/", "/user/login");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //        SpringBoot2.0没有做好静态资源映射
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/user/login",
                                "/asserts/**", "/webjars/**", "/hello", "/error/**");
            }
        };
        return configurer;
    }
}
