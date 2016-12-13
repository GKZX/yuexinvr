package org.yuexin.util.yunxin;

import java.security.MessageDigest;

/**
 * Created by liurui on 15/12/31.
 */
public class CheckSumBuilder {

    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("SHA-1", appSecret + nonce + curTime);
    }

    public static String getMD5(String requestBody) {
        return encode("MD5", requestBody);
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());

            return getFormattedText(messageDigest.digest());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);

        for (int i = 0; i < len; i++) {
            buf.append(HEX_DIGETS[(bytes[i] >> 4) & 0x0f]);
            buf.append(HEX_DIGETS[bytes[i] & 0x0f]);

        }
        return buf.toString();
    }

    private static final char[] HEX_DIGETS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
}
