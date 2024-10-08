package com.antoniomorenoarribas.ecommerce.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de filtros para la aplicación.
 * Este filtro añade valores al MDC (Mapped Diagnostic Context) para que cada solicitud pueda
 * tener un identificador único y se puedan seguir los logs con más precisión.
 */
@Configuration
public class FilterConfig {
	
	 @Bean
	    public FilterRegistrationBean<MDCFilter> loggingFilter() {
	        FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
	        registrationBean.setFilter(new MDCFilter());
	     
	        registrationBean.addUrlPatterns("/v1/*");
	        return registrationBean;
	    }

}
