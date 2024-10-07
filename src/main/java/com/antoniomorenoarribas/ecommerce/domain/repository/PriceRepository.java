package com.antoniomorenoarribas.ecommerce.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.antoniomorenoarribas.ecommerce.domain.model.Price;

/**
 * Repositorio para gestionar las operaciones de consulta relacionadas con los precios.
 * 
 * Esta interfaz define los métodos necesarios para buscar precios aplicables según el producto, 
 * la marca y la fecha de aplicación.
 */
@Repository
public interface PriceRepository {
	
	/**
     * Busca los precios aplicables para un producto y marca específicos en una fecha de aplicación determinada.
     * 
     * @param productId El ID del producto.
     * @param brandId El ID de la marca.
     * @param applicationDate La fecha de aplicación(o consulta) en la que se requiere el precio.
     * @return Una lista de precios que coinciden con el producto, marca y fecha especificados.
     */
    Price findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate);
    
}
