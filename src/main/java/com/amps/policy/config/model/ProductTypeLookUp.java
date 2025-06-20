package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_type_lkp", schema = "policy")
public class ProductTypeLookUp {

	@Id
	@Column(name = "product_key")
	private Integer productKey;

	@Column(name = "product_title")
	private String productTitle;

	public Integer getProductKey() {
		return productKey;
	}

	public void setProductKey(Integer productKey) {
		this.productKey = productKey;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

}
