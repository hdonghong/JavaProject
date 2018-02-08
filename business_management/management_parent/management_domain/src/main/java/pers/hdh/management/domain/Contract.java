package pers.hdh.management.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName	Contract	
 * @Description	购销合同，CONTRACT_C表实体
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/07 19:45:43
 */
public class Contract extends BaseEntity {
	private static final long serialVersionUID = -7324231023761479797L;

	private Set<ContractProduct> contractProducts = new HashSet<>();// 合同和货物，一对多
	
	private String id; 
	private String offeror;     // 收购方
	private String contractNo;  // 合同号
	private Date signingDate;   // 签单日期
	private String inputBy;     // 签单人
	private String checkBy;     // 审单员
	private String inspector;   // 验货员
	private Double totalAmount; // 总金额 = 货物总金额 + 附件总金额，冗余字段，方便分散计算
	private String crequest;    // 要求
	private String customName;  // 客户名称
	private Date shipTime;      // 船期
	private Integer importNum;  // 重要程度
	private Date deliveryPeriod;// 交货期限
	private Integer oldState;   // 旧的状态，报运
	private Integer outState;   // 出货状态，报运
	private String tradeTerms;  // 贸易条款
	private String printStyle;  // 打印版式，1=打印一个货物；2=打印两个货物
	private String remark;      // 备注
	private Integer state;      // 状态，0=草稿；1=已上报待报运
	
	public Set<ContractProduct> getContractProducts() {
		return contractProducts;
	}
	public void setContractProducts(Set<ContractProduct> contractProducts) {
		this.contractProducts = contractProducts;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOfferor() {
		return offeror;
	}
	public void setOfferor(String offeror) {
		this.offeror = offeror;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	public String getInputBy() {
		return inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}
	public String getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCrequest() {
		return crequest;
	}
	public void setCrequest(String crequest) {
		this.crequest = crequest;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public Date getShipTime() {
		return shipTime;
	}
	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}
	public Integer getImportNum() {
		return importNum;
	}
	public void setImportNum(Integer importNum) {
		this.importNum = importNum;
	}
	public Date getDeliveryPeriod() {
		return deliveryPeriod;
	}
	public void setDeliveryPeriod(Date deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}
	public Integer getOldState() {
		return oldState;
	}
	public void setOldState(Integer oldState) {
		this.oldState = oldState;
	}
	public Integer getOutState() {
		return outState;
	}
	public void setOutState(Integer outState) {
		this.outState = outState;
	}
	public String getTradeTerms() {
		return tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}
	public String getPrintStyle() {
		return printStyle;
	}
	public void setPrintStyle(String printStyle) {
		this.printStyle = printStyle;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
