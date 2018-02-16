package cn.itcast.export.webservice;

import java.util.Set;

import javax.jws.WebMethod;

import com.alibaba.fastjson.JSON;

import cn.itcast.export.domain.Export;
import cn.itcast.export.domain.ExportProduct;
import cn.itcast.export.service.ExportService;


public class EpService {
  private ExportService exportService;
	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}


@WebMethod
public String exportE(String jsonStr) throws Exception{
	  System.out.println(jsonStr);
	  
	  Export export = JSON.parseObject(jsonStr, Export.class);
	  
	  System.out.println(export.getDestinationPort()+","+export.getProducts().iterator().next().getCnumber());
	  
	  exportService.saveOrUpdate(export);
	  
	  //海关人员根据自己的审核标准，决定是否通过，最终以json数据作为响应的结果
	  /**
	   * {
			 exportId:"",
			 state:"",
			 remark:"",
			 products:[
			            {
			               exportProductId:"",
			               tax:""
			            },
				   {
			               exportProductId:"",
			               tax:""
			            }
			          ]
			}
	   * 
	   */
/*	  StringBuffer sb = new StringBuffer();
	  sb.append("{exportId:\"").append(export.getExportId()).append("\",");
	  sb.append("state:\"").append(2).append("\",");
	  sb.append("remark:\"").append("申报成功").append("\",");
	  sb.append("products:[");
	  Set<ExportProduct> exportSet = export.getProducts();
	  double i=1;
	  for(ExportProduct ep :exportSet){
		  sb.append("{exportProductId:\"").append(ep.getExportProductId()).append("\",");
		  sb.append("tax:\"").append(10+(i++)*0.4).append("\"},");
	  }
	  sb.delete(sb.length()-1, sb.length());
	  sb.append("]}");
	  System.out.println(sb.toString());
	  
	  return sb.toString();*/
	  
	  return "success";
  }
}
