package org.yuexin.util;

import com.alibaba.fastjson.JSONObject;

/**

* @Description: APP接口错误代码枚举

* @author liuqin

* @date 2016-12-19 下午1:39:17

*
 */
public enum ErrorAppEnums{
	SUCCESS(0, "成功"),
	SERVER_ERROR(-1, "服务器异常"),
	PARAM_ERROR(10001, "参数错误"),
	PHONE_ISNULL(10002, "手机号不能为空"),
	MESSAGE_SEND_FAIL(10003, "短信发送失败"),
	USER_EXISTS(10004, "用户已存在"),
	NAMEORPASW_ISNULL(10005, "用户名或密码不能为空"),
	NAMEORPASW_ERROR(10006, "用户名或密码错误"),
	PHONE_FORMAT_ERROR(10007,"手机号码格式不正确");

    private int code;
    private String msg;
    private ErrorAppEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

    public static String getResponseMsg(String code){
        for(ErrorAppEnums responseInfo:ErrorAppEnums.values()){
            if(code.equals(responseInfo.getCode())){
                return responseInfo.getMsg();
            }
        }
        return SERVER_ERROR.getMsg();
    }
    
    public static JSONObject getResult(ErrorAppEnums error, String additionalMsg, Object data){
    	String msg = (additionalMsg == null ? "" : additionalMsg) + error.getMsg();
    	JSONObject result = new JSONObject();
    	result.put("code", error.getCode());
    	result.put("msg", msg);
    	if(data == null){
    		data = new JSONObject();
    	}
    	result.put("data", data);
    	return result;
    }

}
