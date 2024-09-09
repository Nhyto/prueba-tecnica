package com.antoniomorenoarribas.ecommerce.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;

public class MDCFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	throw new UnsupportedOperationException("El método init no está implementado.");	
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            // Generar un requestId si no existe en la solicitud
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);

            // Continuar con el resto de la cadena de filtros
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
    	 throw new UnsupportedOperationException("El método destroy no está implementado.");
    }
}
