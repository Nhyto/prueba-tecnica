package com.antoniomorenoarribas.ecommerce.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class PriceDTO {
	private Long id;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
    
    
    
	public PriceDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
		return Objects.hash(brandId, currency, id, priceList, priority, productId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceDTO other = (PriceDTO) obj;
		return Objects.equals(brandId, other.brandId) && Objects.equals(currency, other.currency)
				&& Objects.equals(id, other.id) && Objects.equals(priceList, other.priceList)
				&& Objects.equals(priority, other.priority) && Objects.equals(productId, other.productId);
	}
	@Override
	public String toString() {
		return "PriceDTO [id=" + id + ", brandId=" + brandId + ", priceList=" + priceList + ", productId=" + productId
				+ ", priority=" + priority + ", currency=" + currency + "]";
	}
    
    
}
