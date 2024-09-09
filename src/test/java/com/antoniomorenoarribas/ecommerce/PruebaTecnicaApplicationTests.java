package com.antoniomorenoarribas.ecommerce;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.antoniomorenoarribas.ecommerce.application.service.PriceService;
import com.antoniomorenoarribas.ecommerce.infrastructure.rest.PriceController;

@SpringBootTest
class PruebaTecnicaApplicationTests {

	@Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }
    
    @Test
    void priceServiceBeanIsLoaded() {
        boolean beanExists = applicationContext.containsBean("priceService");
        assertThat(beanExists).isTrue();
        
        PriceService priceService = applicationContext.getBean(PriceService.class);
        assertThat(priceService).isNotNull();
    }
    
    @Test
    void priceControllerBeanIsLoaded() {
        
        boolean beanExists = applicationContext.containsBean("priceController");
        assertThat(beanExists).isTrue();
        
       
        PriceController priceController = applicationContext.getBean(PriceController.class);
        assertThat(priceController).isNotNull();
    }

}
