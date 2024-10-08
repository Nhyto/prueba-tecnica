package com.antoniomorenoarribas.ecommerce.infrasctructure.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.antoniomorenoarribas.ecommerce.application.dto.requestdto.PriceRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
  
    private PriceRequestDTO createRequestDTO(Long productId, Long brandId, LocalDateTime applicationDate) {
        return new PriceRequestDTO(productId, brandId, applicationDate);
    }
    
    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());  // Registrar el módulo JSR310 para manejar fechas y horas de Java 8

            // Usar una configuración personalizada para serializar BigDecimal sin notación científica
            SimpleModule module = new SimpleModule();
            module.addSerializer(BigDecimal.class, new ToStringSerializer());
            mapper.registerModule(module);

            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Prueba 1: Consulta a las 10:00 del día 14/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune14At10AM() throws Exception {
    	mockMvc.perform(get("/v1/prices") // Cambiamos a GET
                .with(httpBasic("prueba", "pruebame"))  // Autenticación básica
                .param("productId", "35455")  // Parámetros en la URL como query parameters
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T10:00:00"))  // Fecha y hora en formato ISO 8601
            .andExpect(status().isOk())  // Esperamos un estado 200 OK
            .andExpect(jsonPath("$.price", is(String.format(Locale.US, "%.2f", new BigDecimal("35.50")))));  // Verificamos el precio
    }

    // Prueba 2: Consulta a las 16:00 del día 14/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune14At4PM() throws Exception {
    	 mockMvc.perform(get("/v1/prices") // Cambiamos a GET
                 .with(httpBasic("prueba", "pruebame")) // Autenticación básica
                 .param("productId", "35455") // Parámetros en la URL como query parameters
                 .param("brandId", "1")
                 .param("applicationDate", "2020-06-14T16:00:00")) // Fecha y hora en formato ISO 8601
             .andExpect(status().isOk()) // Esperamos un estado 200 OK
             .andExpect(jsonPath("$.price", is(String.format(Locale.US, "%.2f", new BigDecimal("25.45"))))); // Verificamos el precio
    }

    // Prueba 3: Consulta a las 21:00 del día 14/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune14At9PM() throws Exception {
    	mockMvc.perform(get("/v1/prices")
                .with(httpBasic("prueba", "pruebame")) // Autenticación básica
                .param("productId", "35455") // Parámetros en la URL como query parameters
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T21:00:00")) // La fecha y hora en formato ISO 8601
            .andExpect(status().isOk()) // Esperamos un estado 200 OK
            .andExpect(jsonPath("$.price", is(String.format(Locale.US,"%.2f", new BigDecimal("35.50"))))); // Verificamos el precio
    }

    // Prueba 4: Consulta a las 10:00 del día 15/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnNotFoundForRequestOnJune15At10AM() throws Exception {
    	mockMvc.perform(get("/v1/prices")
                .with(httpBasic("prueba", "pruebame"))
                .param("productId", "35455") // Parámetros en la URL como query parameters
                .param("brandId", "1")
                .param("applicationDate", "2021-06-06T10:00:00")) // Formato ISO 8601
            .andExpect(status().isNotFound()) // Esperamos un estado 404 Not Found
            .andExpect(jsonPath("$.errorCode").value("PRICE_NOT_FOUND"))
            .andExpect(jsonPath("$.errorMessage").value("No se encontró un precio aplicable para los criterios proporcionados."));
    }

    // Prueba 5: Consulta a las 21:00 del día 15/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune15At10AM() throws Exception {
    	 mockMvc.perform(get("/v1/prices") // Cambiamos a GET
                 .with(httpBasic("prueba", "pruebame")) // Autenticación básica
                 .param("productId", "35455") // Parámetros en la URL como query parameters
                 .param("brandId", "1")
                 .param("applicationDate", "2020-06-15T10:00:00")) // La fecha en formato ISO 8601
             .andExpect(status().isOk()) // Esperamos un estado 200 OK
             .andExpect(jsonPath("$.price", is(String.format(Locale.US,"%.2f", new BigDecimal("30.50")))));
    }

    // Prueba 6: Consulta a las 21:00 del día 16/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune16At9PM() throws Exception {
    	 mockMvc.perform(get("/v1/prices") // Cambiamos a GET
                 .with(httpBasic("prueba", "pruebame")) // Autenticación básica
                 .param("productId", "35455") // Parámetros en la URL como query parameters
                 .param("brandId", "1")
                 .param("applicationDate", "2020-06-16T21:00:00")) // Fecha y hora en formato ISO 8601
             .andExpect(status().isOk()) // Esperamos un estado 200 OK
             .andExpect(jsonPath("$.price", is(String.format(Locale.US,"%.2f", new BigDecimal("38.95"))))); // Verificamos el precio
    }
}
