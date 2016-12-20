package org.yuexin.util;
/**

* @Description: 管理后台错误代码枚举

* @author liuqin

* @date 2016-12-19 下午1:40:00
 */
public enum ErrorEnums{
	SUCCESS(10000, "成功"),
	SERVER_ERROR(9999, "服务器异常"),
	PARAM_ERROR(99999, "参数错误"),
    USERORPASSWORD_ERROR(20001, "用户名或密码错误"),
	NOT_LOGIN(20002, "未登录");

    private int code;
    private String msg;
    private ErrorEnums(int code, String msg) {
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
        for(ErrorEnums responseInfo:ErrorEnums.values()){
            if(code.equals(responseInfo.getCode())){
                return responseInfo.getMsg();
            }
        }
        return SERVER_ERROR.getMsg();
    }

}
