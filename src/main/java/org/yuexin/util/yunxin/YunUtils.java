package org.yuexin.util.yunxin;

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
import java.util.Random;

/**
 * Created by liurui on 15/12/31.
 * 云信短信处理
 */
public final class YunUtils {

    private static final String APP_KEY = "1719bb50b6bb43e08187c9f99a330dec";
    private static final String APP_SECRET = "3ab4445fe76d";
    private static final String ERROR_MSG = "{code: 500, msg: '云信服务错误'}";
    private static final int TEMPLATEID = 3029638; //短信模版

    private static Logger logger = LoggerFactory.getLogger(YunUtils.class);


    /**
     * 发送模板短信到指定的手机列表
     *
     * @param mobiles    手机号
     * @param content    内容
     * @return 状态
     * @throws IOException io异常
     */
    public static String sendTemplateMessage(String[] mobiles, String[] content) {
        String url = "https://api.netease.im/sms/sendtemplate.action";

        Gson gson = new Gson();

        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        formEncodingBuilder.add("mobiles", gson.toJson(mobiles));
        formEncodingBuilder.add("params", gson.toJson(content));
        formEncodingBuilder.add("templateid", String.valueOf(TEMPLATEID));

        logger.info("发送短信到：" + mobiles[0] + "，内容为：" + content[0]);

        try {
            return request(url, formEncodingBuilder.build()).body().string();
        } catch (IOException e) {
            return ERROR_MSG;
        }

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
