package com.wx.spring.base;

/**
 * 返回状态码对应状态
 * @Description:
 * @Title: StatusEnum
 * @author wangxin
 * @date 2019年10月15日
 */
public enum StatusEnum {

	/**
	 * 正确信息 200开始
	 */
	 SUCCESS(200,"success"),
	 
	 
	 /**
	  * 错误信息  600开始
	  */
	 NO_HAVE_ROLE(600,"没有访问权限"),
	 CUSTOM_INFO(601,"自定义错误信息"),
	 TOKEN_INVALID(602,"token过期或无效"), 
	 ;
	
	private Integer code;
    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
