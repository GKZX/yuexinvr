package org.yuexin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by XIAOYAO on 2016/12/12 17:45.
 * 网易云信接口
 */
public final class YunxinAPIUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(YunxinAPIUtil.class);

    private static final int TEMPLATEID = 3029638; //短信模版id

    /**
     * 发送模板短信到指定的手机
     * @param phone 手机号
     * @param content 验证码
     * @return 接口返回信息
     */
    public static Map<String, Object> sendTemplateMessage(String phone, String content) {
        String url = "https://api.netease.im/sms/sendtemplate.action";
        return null;
    }
}
