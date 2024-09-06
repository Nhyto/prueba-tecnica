package com.antoniomorenoarribas.ecommerce.application.mappers;

import org.mapstruct.Mapper;

import com.antoniomorenoarribas.ecommerce.domain.model.Price;
import com.antoniomorenoarribas.ecommerce.infrastructure.database.entities.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    Price toDomain(PriceEntity entity);

    PriceEntity toEntity(Price price);
}
