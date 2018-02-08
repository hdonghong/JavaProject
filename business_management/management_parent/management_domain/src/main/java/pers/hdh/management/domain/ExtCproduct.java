package pers.hdh.management.domain;

import java.io.Serializable;

/**
 * @ClassName	ExtCproduct	
 * @Description	合同下附件，EXT_CPRODUCT_C表实体
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/07 19:48:33
 */
public class ExtCproduct implements Serializable {

	private static final long serialVersionUID = -7755401048289771043L;
	
	private ContractProduct contractProduct;// 附件和货物，多对一
	private Factory factory;                // 附件和厂家，多对一
	
	private String id;
	private String factoryName;   // 厂家名，冗余
	private String productNo;     // 产品号
	private String productImage;  // 图片
	private String productDesc;   // 产品描述
	private String packingUnit;   // 包装单位，PCS/SETS
	private Integer cnumber;      // 数量
	private Double price;         // 单价
	private Double amount;        // 总金额 = 数量*单价，冗余
	private String productRequest;// 产品要求
	private Integer orderNo;      // 排序号
	
	public ContractProduct getContractProduct() {
		return contractProduct;
	}
	public void setContractProduct(ContractProduct contractProduct) {
		this.contractProduct = contractProduct;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Integer getCnumber() {
		return cnumber;
	}
	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getProductRequest() {
		return productRequest;
	}
	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
