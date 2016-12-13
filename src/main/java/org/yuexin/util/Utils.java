package org.yuexin.util;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by liurui on 15/12/31.
 */
public class Utils {

    public static final String APP_KEY = "1719bb50b6bb43e08187c9f99a330dec";
    public static final String APP_SECRET = "3ab4445fe76d";
    public static final String ERROR_MSG = "{code: 500, msg: '云信服务错误'}";

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    // 创建云信用户
    public static String createAccid(String accid, String name, String token) {
        String url = "https://api.netease.im/nimserver/user/create.action";

        FormEncodingBuilder formBuilder = new FormEncodingBuilder();
        formBuilder.add("accid", accid);
        formBuilder.add("name", name);
        formBuilder.add("token", token);

        try {
            return request(url, formBuilder.build()).body().string();
        } catch (IOException e) {
            return ERROR_MSG;
        }
    }

    /**
     * 修改云信密码
     *
     * @param accid 云信帐号
     * @param token 密码
     * @return 状态
     */
    public static String udpateAccidPassword(String accid, String token) {
        String url = " https://api.netease.im/nimserver/user/update.action";

        FormEncodingBuilder formBuilder = new FormEncodingBuilder();
        formBuilder.add("accid", accid);
        formBuilder.add("token", token);

        try {
            return request(url, formBuilder.build()).body().string();
        } catch (IOException e) {
            return ERROR_MSG;
        }
    }

    /**
     * 发送自定义系统通知
     *
     * @param to     发送到云信帐号
     * @param attach 消息内容
     * @return 状态
     */
    public static String sendAttachMsg(String to, String attach) {
        String url = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";

        FormEncodingBuilder formBuilder = new FormEncodingBuilder();
        //发送者accid，用户帐号，最大32字符，APP内唯一
        formBuilder.add("from", "root");

        //0：点对点自定义通知，1：群消息自定义通知，其他返回414
        formBuilder.add("msgtype", "0");

        //msgtype==0是表示accid即用户id，msgtype==1表示tid即群id
        formBuilder.add("to", to);

        //自定义通知内容，第三方组装的字符串，建议是JSON串，最大长度4096字符
        formBuilder.add("attach", attach);

        try {
            return request(url, formBuilder.build()).body().string();
        } catch (IOException e) {
            return ERROR_MSG;
        }
    }

    // 向指定的手机发送短信验证码
    public static String sendCode(String mobile) {
        String url = "https://api.netease.im/sms/sendcode.action";

        FormEncodingBuilder formBuilder = new FormEncodingBuilder();
        formBuilder.add("mobile", mobile);

        try {
            return request(url, formBuilder.build()).body().string();
        } catch (IOException e) {
            return ERROR_MSG;
        }
    }

    // 校验验证码
    public static String verifyCode(String mobile, String code) {
        String url = "https://api.netease.im/sms/verifycode.action";

        FormEncodingBuilder formBuilder = new FormEncodingBuilder();
        formBuilder.add("mobile", mobile);
        formBuilder.add("code", code);

        try {
            return request(url, formBuilder.build()).body().string();
        } catch (IOException e) {
            return ERROR_MSG;
        }

    }

    /**
     * 发送模板短信到指定的手机列表
     *
     * @param mobiles    手机号
     * @param templateid 模版id
     * @param content    内容
     * @return 状态
     * @throws IOException io异常
     */
    public static String sendTemplateMessage(String[] mobiles, int templateid, String[] content) {
        String url = "https://api.netease.im/sms/sendtemplate.action";

        Gson gson = new Gson();

        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        formEncodingBuilder.add("mobiles", gson.toJson(mobiles));
        formEncodingBuilder.add("params", gson.toJson(content));
        formEncodingBuilder.add("templateid", String.valueOf(templateid));

        logger.info("发送短信到：" + mobiles[0] + "，内容为：" + content);

        try {
            return request(url, formEncodingBuilder.build()).body().string();
        } catch (IOException e) {
            return ERROR_MSG;
        }

    }

    /**
     * 发送系统消息到指定手机或群
     *
     * @param from    发送端手机号
     * @param to      接收端手机号
     * @param msgType 类型
     * @param map     参数
     * @return 状态
     */
    public static String sendNotification(String from, String to, String msgType, HashMap<String, Object> map) {
//		String url = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";
        return map.toString();

        // Feedback fb = new Feedback(map.get("name").toString(), (Boolean)
        // map.get("is_read"), map.get("apply_date").toString(),
        // map.get("meeting_date").toString(), (Integer)map.get("type_id"),
        // map.get("result").toString());
        // System.out.println(fb);
        //
        // Gson gson = new Gson();
        //
        // FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        // formEncodingBuilder.add("from", from);
        // formEncodingBuilder.add("to", to);
        // formEncodingBuilder.add("msgtype", msgType);
        // formEncodingBuilder.add("attach", gson.toJson(fb));
        //
        // try {
        // return request(url, formEncodingBuilder.build()).body().string();
        // } catch (IOException e) {
        // return ERROR_MSG;
        // }

    }

    /**
     * Request helper method
     *
     * @param url  地址
     * @param body 内容
     * @return Response 结果
     * @throws IOException io异常
     */
    public static final Response request(String url, RequestBody body) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Headers headers = createHeader();
        Request request = new Request.Builder().url(url).headers(headers).post(body).build();
        return client.newCall(request).execute();
    }

    /**
     * Create Headers helper
     *
     * @return Headers
     */
    public static final Headers createHeader() {

        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String nonce = String.valueOf(new Random(10).nextInt(100));

        return new Headers.Builder().add("Appkey", APP_KEY).add("Nonce", nonce).add("CurTime", curTime)
                .add("CheckSum", CheckSumBuilder.getCheckSum(APP_SECRET, nonce, curTime))
                .add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8").build();

    }

}
