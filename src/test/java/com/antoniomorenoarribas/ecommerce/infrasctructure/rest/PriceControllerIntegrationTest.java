package com.antoniomorenoarribas.ecommerce.infrasctructure.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
        mockMvc.perform(post("/api/prices/find")
                .with(httpBasic("prueba", "pruebame"))  // Credenciales de prueba
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createRequestDTO(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0)))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(String.format(Locale.US,"%.2f", new BigDecimal("35.50")))));
    }

    // Prueba 2: Consulta a las 16:00 del día 14/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune14At4PM() throws Exception {
        mockMvc.perform(post("/api/prices/find")
                .with(httpBasic("prueba", "pruebame"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createRequestDTO(35455L, 1L, LocalDateTime.of(2020, 6, 14, 16, 0)))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(String.format(Locale.US,"%.2f", new BigDecimal("25.45")))));
    }

    // Prueba 3: Consulta a las 21:00 del día 14/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune14At9PM() throws Exception {
        mockMvc.perform(post("/api/prices/find")
                .with(httpBasic("prueba", "pruebame"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createRequestDTO(35455L, 1L, LocalDateTime.of(2020, 6, 14, 21, 0)))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(String.format(Locale.US,"%.2f", new BigDecimal("35.50")))));
    }

    // Prueba 4: Consulta a las 10:00 del día 15/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnNotFoundForRequestOnJune15At10AM() throws Exception {
        mockMvc.perform(post("/api/prices/find")
                .with(httpBasic("prueba", "pruebame"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createRequestDTO(35455L, 1L, LocalDateTime.of(2021, 6, 6, 10, 0)))))
        		.andExpect(jsonPath("$.errorCode").value("PRICE_NOT_FOUND"))
        		.andExpect(jsonPath("$.errorMessage").value("No se encontraron precios para el producto y la marca especificados."));
    }

    // Prueba 5: Consulta a las 21:00 del día 15/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune15At10AM() throws Exception {
        mockMvc.perform(post("/api/prices/find")
                .with(httpBasic("prueba", "pruebame"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createRequestDTO(35455L, 1L, LocalDateTime.of(2020, 6, 15, 10, 0)))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(String.format(Locale.US,"%.2f", new BigDecimal("30.50")))));
    }

    // Prueba 6: Consulta a las 21:00 del día 16/06/2020 para el producto 35455 y la marca 1
    @Test
    void shouldReturnPriceForRequestOnJune16At9PM() throws Exception {
        mockMvc.perform(post("/api/prices/find")
                .with(httpBasic("prueba", "pruebame"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createRequestDTO(35455L, 1L, LocalDateTime.of(2020, 6, 16, 21, 0)))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(String.format(Locale.US,"%.2f", new BigDecimal("38.95")))));
    }
}
