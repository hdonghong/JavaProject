package pers.hdh.management.exception;

/**
 * @ClassName	SysException	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/05 16:08:59
 */
public class SysException extends Exception {

	private static final long serialVersionUID = 3810476904013622510L;
	private String message;
	
	public SysException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
