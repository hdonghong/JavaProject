package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

import team.webstore.utils.EmailUtils;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//请在这里配置您的基本信息

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = EmailUtils.getValue("app_id");

    // 商户私钥，您的PKCS8格式RSA2私钥
    //public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCOLQFzf9M1gDCuApelLADiIGBHOFUhbXp0TE+Av2UHqQAfTycxKd1UojhB7bxb1vPoapkdibHw4DEYbiROoud+1MJZFU/Fwcy7xWXSI5GlUC4kRNZjdWGLWYQOt60PU0nwO5MAEaiKibtfPKX6MjG+wT1aSHEI6viA6uEi5bsq2eeFJ6/qJERcYSN7ZJ4Z8mRCXpUCspDlibdEJkKOgRIp4lP2AKbrnXa0Zy3ed8VEZkvB6mh8F7PpsFFHLFSYHGVd3OhpEz1dt/yQQgSHyPZAt9E3iUhdu/pwERcUjZ6Su4/Pcgy3hHQu1dosNX9aoXegOc3Yy18LwngEbLpXkhYNAgMBAAECggEAec/JjIGaHh6ElrIV5yg6YYNHuf1jws4fuoCfJTnWf6emOS0B8TdjMF4EQUhEt5MFSr36DbqpwCz1I29KoC0mlh4HnXeK725J0R8hS9e5s59syRpJbNFnD5NUd5+bIKRFlxQTsF1wv7Gd3c0Cj8htonfB7xZd8jwPSbTzy4h0whK7atjyp/GnZX+fyAMzX3Uij5RIIE1bcWkbdqb+hXL0XuxyTM/CWthEP1SM7E3Z8MxzlLUU5z7FWuWgK3u0jGi8SV5cloCeolGO5cadTL3MywrpFesN0Tg0RY+wmTFZl15t2LEAOIesCT4hZbRkDbLNpeNbOsYHDsfukbQ6c8nV6QKBgQDzu3q4IivhZ3K/7WvtJoRn2YMOUI0/fRrtv29rbgD4+pOohCL+UFVSxuKeHU3Gay4acOIk2Tsdrn91cg0Dy3LIwWu9GSwjXHZwdTO8Ae5uzvudlKq5rayv6lvvb2Qtmzsibid3jUaWquFcly5MF4x7/Yj0X6ujb3CKGJuQkh8Y4wKBgQCVVPVC1wqnXfriaXBDly0XCpGvueYXifMZzFZhVUC+jFIaDgTMecl2S+PUa+vhLuv/TkaG0b2zMV/8rmDw2tOlAjppx0/uV3FN2Rq5nNO6lfIgpdm3eSX/keGD63AYsB9bI1iQ8f/pfi6sH5Vr6hmzPboZgI8R1wdz9rppdqV4TwKBgQDQR3HDUFA3OnOw127XJYcYR6Pa47MQghQ0/tZz83kZSuhLpT9SG8wOBK87dsczu/uV1MkrvAbsgmWhM2kzyPO54q/y1EQHo8gCqeUf0Ha0cmRKcPTD30omuhr+LS5qWrDZ51AKtcuQjU4wFXEWzTjb6/jQ+8Bs1ZpSKF0n2faBCQKBgQCGks2Dsq8TLQpOx544CyDDRu8LYYgNZnABq7ecd2ulTGcgu8lvS8FWLgXfBHLJt7NNnIjKLnG57qEF4U/TPiDE70Ec2lUaAQtGkIQZCU91MnPFIDEXusGXkyub6qxWU0GurCfAV5W8Ic6FpuDNI2aNtO7k1IoaFaHNy2cqa5Wq2QKBgQCarazuTYFSdoB+fk8dEOh8zjd998CUrcgYNLpdI4uVX978UoBFi4wCZ3NhWTmWwsEvXa4OIdqZr1t50ARdic7GtHwTptUu4agNykst/PzoFz8K9kzBYCGkLeAtJQm73hWikIMCWi1FgsNCF5is+KXQomli5Ab8EY5lwRRDfhvFow==";
    public static String merchant_private_key = EmailUtils.getValue("merchant_private_key");

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    //public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw+B5uZskQ9m5wOs18P3hxGLjUAR0R2fDkFsdB86wvdAbXWgz3F3TQQaVnpEfrZiOzfH8bMFydaeBfOKGJTjIU3ieuBbVgTpPL8N5rnQwPFvJxlu1Eatcl54qzTQAIEbfAEFUP7RiQy3IfNk44s2ExgxKMaHfmI+D5qwbQo/GE1fD/PJ9eLWbB2GaM2GebItbCyWGJYR4g1+xZIWlp+DfF9Kx9w2OL0FKXBIu1Gn291Ihzv/6OKqYF1X5SnHr/+P8/v7KC1EIhcZwtmvjQNjpxLhEJ6aEWcwzbLu6DFAz4bqv15D4/YjCHzQX/VSl3hrHPmqDiv8QJqm2TXuarmonZwIDAQAB";
    public static String alipay_public_key = EmailUtils.getValue("alipay_public_key");

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = EmailUtils.getValue("notify_url");

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = EmailUtils.getValue("return_url");

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = EmailUtils.getValue("gatewayUrl");

    // 支付宝网关
    public static String log_path = EmailUtils.getValue("log_path");


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    /*
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */
}

