package team.webstore.web.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页模块
 * @author hdonghong 
 * @version 创建时间：2017年11月20日 上午9:26:02 
 */
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = -3764124846990655190L;

	/**
	 * 跳转到商品模块
	 * @return
	 */
	public String index() {
		return "index";
	}
	
}
