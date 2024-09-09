package com.antoniomorenoarribas.ecommerce.application.dto.responsedto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PriceResponseDTO {
	private Long productId;
    private Long brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private BigDecimal price;
    private String currency;
    
    
    
	public PriceResponseDTO(Long productId, Long brandId, Integer priceList, LocalDateTime startDate,
			LocalDateTime endDate, BigDecimal price, String currency) {
		super();
		this.productId = productId;
		this.brandId = brandId;
		this.priceList = priceList;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.currency = currency;
	}
	public PriceResponseDTO() {
		super();
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public Integer getPriceList() {
		return priceList;
	}
	public void setPriceList(Integer priceList) {
		this.priceList = priceList;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public int hashCode() {
		return Objects.hash(brandId, currency, endDate, price, priceList, productId, startDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceResponseDTO other = (PriceResponseDTO) obj;
		return Objects.equals(brandId, other.brandId) && Objects.equals(currency, other.currency)
				&& Objects.equals(endDate, other.endDate) && Objects.equals(price, other.price)
				&& Objects.equals(priceList, other.priceList) && Objects.equals(productId, other.productId)
				&& Objects.equals(startDate, other.startDate);
	}
	@Override
	public String toString() {
		return "PriceResponseDTO [productId=" + productId + ", brandId=" + brandId + ", priceList=" + priceList
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", price=" + price + ", currency=" + currency
				+ "]";
	}
	
	
    
}
