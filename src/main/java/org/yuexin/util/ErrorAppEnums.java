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
	PARAM_ERROR(10001, "参数错误");

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
    
    public static JSONObject getResult(ErrorAppEnums error, Object data){
    	JSONObject result = new JSONObject();
    	result.put("code", error.getCode());
    	result.put("msg", error.getMsg());
    	result.put("data", data);
    	return result;
    }

}
