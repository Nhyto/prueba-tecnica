package com.antoniomorenoarribas.ecommerce.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.antoniomorenoarribas.ecommerce.application.dto.responseDTO.PriceResponseDTO;
import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "product.id", target = "productId")
    Price toDomain(PriceEntity entity);

    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(source = "productId", target = "product.id")
    PriceEntity toEntity(Price price);

    PriceResponseDTO toDTO(Price domain);
}
