package ${ package }.action.xxx;

import java.util.List;

import com.itheima.jk.action.BaseAction;
import com.itheima.jk.domain.${ className };
import com.itheima.jk.utils.Page;
import com.itheima.jk.service.${ className }Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

<#import "CopyRight.ftl" as my>
<@my.CopyRight/>

public class ${ className }Action extends BaseAction implements ModelDriven<${ className }> {
	//注入service
	private ${ className }Service ${ className?uncap_first }Service;
	public void set${ className }Service(${ className }Service ${ className?uncap_first }Service) {
		this.${ className?uncap_first }Service = ${ className?uncap_first }Service;
	}
	
	//model驱动
	private ${ className } model = new ${ className }();
	public ${ className } getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	//列表展示
	public String list(){
		String hql = "from ${ className } o";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("${ className?uncap_first }Action_list");		//配置分页按钮的转向链接
		page = ${ className?uncap_first }Service.findPage(hql, page, ${ className }.class, null);
		super.put("page", page);
		
		return "plist";						//page list
	}
	
	//转向新增页面
	public String tocreate(){
		//准备数据
		List<${ className }> ${ className?uncap_first }List = ${ className?uncap_first }Service.${ className?uncap_first }List();
		super.put("${ className?uncap_first }List", ${ className?uncap_first }List);		//页面就可以访问${ className?uncap_first }List
		
		return "pcreate";
	}
	
	//新增保存
	public String insert(){
		${ className?uncap_first }Service.saveOrUpdate(model);
		
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
		//准备数据
		List<${ className }> ${ className?uncap_first }List = ${ className?uncap_first }Service.${ className?uncap_first }List();
		super.put("${ className?uncap_first }List", ${ className?uncap_first }List);		//页面就可以访问${ className?uncap_first }List
				
		//准备修改的数据
		${ className } obj = ${ className?uncap_first }Service.get(${ className }.class, model.getId());
		super.getValueStack().push(obj);
		
		return "pupdate";
	}
	
	//修改保存
	public String update(){
		${ className } ${ className?uncap_first } = ${ className?uncap_first }Service.get(${ className }.class, model.getId());
		
		//设置修改的属性，根据业务去掉自动生成多余的属性
		<#list properties as pro>  
		${ className?uncap_first }.set${ pro.proName?cap_first }(model.get${ pro.proName?cap_first }());
		</#list>
		
		${ className?uncap_first }Service.saveOrUpdate(${ className?uncap_first });
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		${ className?uncap_first }Service.deleteById(${ className }.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		${ className?uncap_first }Service.delete(${ className }.class, model.getId().split(", "));
		
		return "alist";
	}
	
	//查看
	public String toview(){
		${ className } obj = ${ className?uncap_first }Service.get(${ className }.class, model.getId());
		super.push(obj);
		
		return "pview";			//转向查看页面
	}
}
