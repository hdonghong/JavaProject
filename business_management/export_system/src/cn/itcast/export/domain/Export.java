package cn.itcast.export.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Export implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String exportId;	
	
	private Set<ExportProduct> products = new HashSet<ExportProduct>();
	private Date inputDate;			
	private String shipmentPort;			
	private String destinationPort;			
	private String transportMode;			//SEA/AIR
	private String priceCondition;			//FBO/CIF
	private Integer boxNums;			//冗余，为委托服务，一个报运的总箱数
	private Double grossWeights;			//冗余，为委托服务，一个报运的总毛重
	private Double measurements;			//冗余，为委托服务，一个报运的总体积
	private Integer state;			
	private String reason;			
	private Integer orderNo;			
	private Date exportDate;			//申批时间

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getExportId() {
		return this.exportId;
	}
	public void setExportId(String exportId) {
		this.exportId = exportId;
	}	
	
	public Date getInputDate() {
		return this.inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}	
	
	public String getShipmentPort() {
		return this.shipmentPort;
	}
	public void setShipmentPort(String shipmentPort) {
		this.shipmentPort = shipmentPort;
	}	
	
	public String getDestinationPort() {
		return this.destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}	
	
	public String getTransportMode() {
		return this.transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}	
	
	public String getPriceCondition() {
		return this.priceCondition;
	}
	public void setPriceCondition(String priceCondition) {
		this.priceCondition = priceCondition;
	}	
	
	public Integer getBoxNums() {
		return this.boxNums;
	}
	public void setBoxNums(Integer boxNums) {
		this.boxNums = boxNums;
	}	
	
	public Double getGrossWeights() {
		return this.grossWeights;
	}
	public void setGrossWeights(Double grossWeights) {
		this.grossWeights = grossWeights;
	}	
	
	public Double getMeasurements() {
		return this.measurements;
	}
	public void setMeasurements(Double measurements) {
		this.measurements = measurements;
	}	
	
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}	
	
	public String getReason() {
		return this.reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}	
	
	public Integer getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}	
	
	public Date getExportDate() {
		return this.exportDate;
	}
	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}
	public Set<ExportProduct> getProducts() {
		return products;
	}
	public void setProducts(Set<ExportProduct> products) {
		this.products = products;
	}	
	
}
