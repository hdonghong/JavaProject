package team.webstore.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 类描述：简单加密解密工具类  其中key的长度必须16位
 * 
 */
public class SecretUtil {
	//密钥字符串 必须为16位
	private static String key="itcastanditheima";
	//加密算法
	private static String Algorithm ="DES";
	//提供setter 
	public void setKey(String key) {
		this.key = key;
	}
	public void setAlgorithm(String algorithm) {
		Algorithm = algorithm;
	}
	
	/**
	* 方法描述：将指定的数据根据提供的密钥进行加密
	* @param input 需要加密的数据
	* @param key 密钥
	* @return byte[] 加密后的数据
	* @throws Exception
	*/
	private static byte [] encryptData (byte [] input ,byte [] key ) throws Exception{
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec (key ,Algorithm );
		Cipher c1 = Cipher.getInstance (Algorithm );
		c1.init (Cipher.ENCRYPT_MODE ,deskey );
		byte [] cipherByte =c1.doFinal (input );
		return cipherByte ;

	} 
	/**
	* 方法描述：将给定的已加密的数据通过指定的密钥进行解密
	* @param input 待解密的数据
	* @param key 密钥
	* @return byte[] 解密后的数据
	* @throws Exception
	*/
	private static byte [] decryptData (byte [] input ,byte [] key ) throws Exception{
		SecretKey deskey = new SecretKeySpec (key ,Algorithm );
		//提供解密功能
		Cipher c = Cipher.getInstance (Algorithm );
		c.init (Cipher.DECRYPT_MODE ,deskey );
		byte [] clearByte =c.doFinal (input );
		return clearByte ;

	} 
	
	/**
	* 方法描述：字符串转成字节数组.
	* @param hex 要转化的字符串.
	* @return byte[] 返回转化后的字符串.
	*/
	private static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
		}
	
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	* 方法描述：将字节数组转成字符串.
	* @param String 要转化的字符串.
	* @return 返回转化后的字节数组.
	*/
	private static final String bytesToHexString(byte[] byteArray) {
		StringBuffer sb = new StringBuffer(byteArray.length);
		String sTemp;
		for (int i = 0; i < byteArray.length; i++) {
		sTemp = Integer.toHexString(0xFF & byteArray[i]);
		if (sTemp.length() < 2)
		sb.append(0);
		sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	* 方法描述：获取密钥.
	* @return 要返回的字节数组.
	* @throws Exception 可能抛出的异常.
	*/
	public static byte[] getSecretKey() throws Exception {
		return hexStringToByte(key);
	}
	/**
	 * 方法描述：加密字符串
	 * @param toDoEncrypt 需要加密的字符串
	 * @return String 加密后的字符串
	 * @throws Exception
	 */
	public static String encrypt(String toDoEncrypt) throws Exception{
		//获得钥匙（字节数组）
		byte[] keys= getSecretKey(); 
		//传入密码和钥匙，获得加密后的字节数组的密码
		byte[] tmp = encryptData(toDoEncrypt.getBytes(), keys); 
		//将字节数组转化为字符串，获得加密后的字符串密码
		String encryptStr=SecretUtil.bytesToHexString(tmp);
		return encryptStr; 
	}
	
	/**
	 * 方法描述：解密
	 * @param encryptedStr 加密后的字符串
	 * @return String 解密后的字符串
	 * @throws Exception
	 */
	public static  String decode(String encryptedStr) throws Exception{
		//拿到key的字节数组
		byte[] keybytes=getSecretKey();
		//获得16进制字符串的byte数组
		byte[] input = hexStringToByte(encryptedStr);
		byte[] decodebytes =decryptData(input, keybytes);
		String decodeStr= new String(decodebytes);
		return decodeStr;
	}
	
	
	public static void main(String[] args) throws Exception {
		//加密	
		String s =	SecretUtil.encrypt("你会");
		System.out.println(s);
		//解密
		String ss = SecretUtil.decode(s);
		System.out.println(ss+"<<jiem");
	}
}
