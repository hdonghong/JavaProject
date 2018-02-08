package pers.hdh.management.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName	ContractProduct	
 * @Description	合同下的货物，CONTRACT_PRODUCT_C表实体
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/07 19:47:38
 */
public class ContractProduct implements Serializable {

	private static final long serialVersionUID = 4491189353598080387L;
	
	private Contract contract;     // 货物和合同，多对一
	private Factory factory;       // 货物和厂家，多对一
	private Set<ExtCproduct> extCproducts = new HashSet<>();// 货物和附件，一对多

	private String id;
	private String factoryName;    // 冗余
	private String productNo;      // 货号
	private String productImage;   // 图片路径
	private String productDesc;    // 货描
	
	private String loadingRate;    // 报运：装率    1/3
	private Integer boxNum;        // 报运：箱数    100
	
	private String packingUnit;    // 包装单位：PCS/SETS  支/箱
	private Integer cnumber;       // 数量
	private Integer outNumber;     // 报运：出货数量
	private Integer finished;      // 报运：是否完成
	
	private String productRequest; // 要求
	private Double price;          // 单价
	private Double amount;         // 总金额，冗余
	private Integer orderNo;       // 排序号
	
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	public Set<ExtCproduct> getExtCproducts() {
		return extCproducts;
	}
	public void setExtCproducts(Set<ExtCproduct> extCproducts) {
		this.extCproducts = extCproducts;
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
	public String getLoadingRate() {
		return loadingRate;
	}
	public void setLoadingRate(String loadingRate) {
		this.loadingRate = loadingRate;
	}
	public Integer getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
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
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public Integer getFinished() {
		return finished;
	}
	public void setFinished(Integer finished) {
		this.finished = finished;
	}
	public String getProductRequest() {
		return productRequest;
	}
	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
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
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
}
