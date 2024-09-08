package com.antoniomorenoarribas.ecommerce.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	
	 @Bean
	    public FilterRegistrationBean<MDCFilter> loggingFilter() {
	        FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
	        registrationBean.setFilter(new MDCFilter());
	        registrationBean.addUrlPatterns("/api/*");
	        return registrationBean;
	    }

}
