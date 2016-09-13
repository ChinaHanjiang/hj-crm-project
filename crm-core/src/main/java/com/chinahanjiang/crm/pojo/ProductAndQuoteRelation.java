package com.chinahanjiang.crm.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProductAndQuoteRelation")
public class ProductAndQuoteRelation {

	private int id;

	private Product product;

	private ProductQuoteDetails productQuoteDetails;

	private double defindPrice;

	private int isDelete;

	private int quantity;
	
	private Timestamp createTime;
	
	private Timestamp updateTime;

	private String remarks;

	public ProductAndQuoteRelation() {

		isDelete = 1;
	}

	public ProductAndQuoteRelation(int id, Product product, int quantity,
			ProductQuoteDetails productQuoteDetails, double defindPrice,
			int isDelete,Timestamp createTime, Timestamp updateTime, String remarks) {
		super();
		this.id = id;
		this.product = product;
		this.productQuoteDetails = productQuoteDetails;
		this.defindPrice = defindPrice;
		this.isDelete = isDelete;
		this.quantity = quantity;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paqr_id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paqr_pid", referencedColumnName = "p_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paqr_pqdid", referencedColumnName = "pqd_id")
	public ProductQuoteDetails getProductQuoteDetails() {
		return productQuoteDetails;
	}

	public void setProductQuoteDetails(ProductQuoteDetails productQuoteDetails) {
		this.productQuoteDetails = productQuoteDetails;
	}

	@Column(name = "paqr_defindPrice")
	public double getDefindPrice() {
		return defindPrice;
	}

	public void setDefindPrice(double defindPrice) {
		this.defindPrice = defindPrice;
	}

	@Column(name = "paqr_isDelete")
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "paqr_remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "paqr_quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "paqr_createtime")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "paqr_updatetime")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
