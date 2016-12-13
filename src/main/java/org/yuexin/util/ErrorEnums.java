package org.yuexin.util;
public enum ErrorEnums{
	SUCCESS(10000, "成功"),
	SERVER_ERROR(9999, "服务器异常"),
	PARAM_ERROR(99999, "参数错误"),
    USERORPASSWORD_ERROR(20001, "用户名或密码错误");

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
