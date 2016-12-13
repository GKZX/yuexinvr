package org.yuexin.util;

import java.util.Random;

/**
 * Created by XIAOYAO on 2016/12/13 15:28.
 * 随机数
 */
public final class RandomNumeric {

    /**
     * 随机获取六位整数
     * @return 六位整数字符串
     */
    public static String random() {
        int count = 6;
        char start = '0';
        char end = '9';

        Random rnd = new Random();

        char[] result = new char[count];
        int len = end - start + 1;

        while (count-- > 0) {
            result[count] = (char) (rnd.nextInt(len) + start);
        }

        return new String(result);
    }
}
