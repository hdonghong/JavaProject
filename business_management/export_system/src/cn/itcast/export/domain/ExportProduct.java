package cn.itcast.export.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description:	ExportProductService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-7 0:05:42
 */

public class ExportProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String exportProductId;		
	@JSONField(serialize=false)
	private Export export ;
	//private String exportId;			
	private String factoryId;			
	private String productNo;			
	private String packingUnit;			//PCS/SETS
	private Integer cnumber;			
	private Integer boxNum;			
	private Double grossWeight;			
	private Double netWeight;			
	private Double sizeLength;			
	private Double sizeWidth;			
	private Double sizeHeight;			
	private Double exPrice;			//sales confirmation 中的价格（手填）
	private Double price;			
	private Double tax;			//收购单价=合同单价
	private Integer orderNo;			

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getExportProductId() {
		return this.exportProductId;
	}
	public void setExportProductId(String exportProductId) {
		this.exportProductId = exportProductId;
	}	
	
	/*public String getExportId() {
		return this.exportId;
	}
	public void setExportId(String exportId) {
		this.exportId = exportId;
	}	*/
	
	public String getFactoryId() {
		return this.factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}	
	
	public String getProductNo() {
		return this.productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}	
	
	public String getPackingUnit() {
		return this.packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}	
	
	public Integer getCnumber() {
		return this.cnumber;
	}
	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}	
	
	public Integer getBoxNum() {
		return this.boxNum;
	}
	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
	}	
	
	public Double getGrossWeight() {
		return this.grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}	
	
	public Double getNetWeight() {
		return this.netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}	
	
	public Double getSizeLength() {
		return this.sizeLength;
	}
	public void setSizeLength(Double sizeLength) {
		this.sizeLength = sizeLength;
	}	
	
	public Double getSizeWidth() {
		return this.sizeWidth;
	}
	public void setSizeWidth(Double sizeWidth) {
		this.sizeWidth = sizeWidth;
	}	
	
	public Double getSizeHeight() {
		return this.sizeHeight;
	}
	public void setSizeHeight(Double sizeHeight) {
		this.sizeHeight = sizeHeight;
	}	
	
	public Double getExPrice() {
		return this.exPrice;
	}
	public void setExPrice(Double exPrice) {
		this.exPrice = exPrice;
	}	
	
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}	
	
	public Double getTax() {
		return this.tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}	
	
	public Integer getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Export getExport() {
		return export;
	}
	public void setExport(Export export) {
		this.export = export;
	}	
	
	
}
