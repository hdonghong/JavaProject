package pers.hdh.utils;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 随机生成id
     * @return
     */
    public static String setId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
