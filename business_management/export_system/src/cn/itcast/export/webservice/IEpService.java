package cn.itcast.export.webservice;

import javax.jws.WebService;

/**
 * @ClassName	EpService	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/16 17:59:55
 */
@WebService
public interface IEpService {
	public String exportE(String jsonStr) throws Exception;
}
