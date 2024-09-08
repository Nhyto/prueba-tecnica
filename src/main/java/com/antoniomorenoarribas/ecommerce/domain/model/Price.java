package com.antoniomorenoarribas.ecommerce.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Price {
	private Long id;
    private Long brandId;
    private Long productId;
    private Integer priceList;
    private Integer priority;
    private BigDecimal price;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//Metodo que calcula si un precio es aplicable
    public boolean isApplicable(LocalDateTime date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getPriceList() {
		return priceList;
	}

	public void setPriceList(Integer priceList) {
		this.priceList = priceList;
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

	@Override
	public int hashCode() {
		return Objects.hash(brandId, currency, endDate, id, price, priceList, priority, productId, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		return Objects.equals(brandId, other.brandId) && Objects.equals(currency, other.currency)
				&& Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
				&& Objects.equals(price, other.price) && Objects.equals(priceList, other.priceList)
				&& Objects.equals(priority, other.priority) && Objects.equals(productId, other.productId)
				&& Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", brandId=" + brandId + ", productId=" + productId + ", priceList=" + priceList
				+ ", priority=" + priority + ", price=" + price + ", currency=" + currency + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	
    
    
    
}
