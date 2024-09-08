package com.antoniomorenoarribas.ecommerce.application.dto.requestDTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class PriceRequestDTO {

	private Long productId;
    private Long brandId;
    private LocalDateTime applicationDate;
    
	public PriceRequestDTO(Long productId, Long brandId, LocalDateTime applicationDate) {
		super();
		this.productId = productId;
		this.brandId = brandId;
		this.applicationDate = applicationDate;
	}

	public PriceRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicationDate, brandId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceRequestDTO other = (PriceRequestDTO) obj;
		return Objects.equals(applicationDate, other.applicationDate) && Objects.equals(brandId, other.brandId)
				&& Objects.equals(productId, other.productId);
	}
	
    
}
