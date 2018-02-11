package pers.hdh.management.domain;

import java.io.Serializable;

public class ExtEproduct extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ExportProduct exportProduct;		//附件和货物，多对一
	private Factory factory;					//附件和厂家，多对一

	private String id;	  	
	private String productNo;			
	private String productImage;			
	private String productDesc;			
	private Integer cnumber;			
	private String packingUnit;			//PCS/SETS
	private Double price;			
	private Double amount;			//自动计算: 数量x单价
	private String productRequest;			
	private Integer orderNo;			

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getProductNo() {
		return this.productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}	
	
	public String getProductImage() {
		return this.productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}	
	
	public String getProductDesc() {
		return this.productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}	
	
	public Integer getCnumber() {
		return this.cnumber;
	}
	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}	
	
	public String getPackingUnit() {
		return this.packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}	
	
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}	
	
	public Double getAmount() {
		return this.amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}	
	
	public String getProductRequest() {
		return this.productRequest;
	}
	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
	}	
	
	public Integer getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public ExportProduct getExportProduct() {
		return exportProduct;
	}
	public void setExportProduct(ExportProduct exportProduct) {
		this.exportProduct = exportProduct;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	
}
