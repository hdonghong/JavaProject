package team.xg2.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 随机生成id
	 * @return
	 */
	public static String getID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
