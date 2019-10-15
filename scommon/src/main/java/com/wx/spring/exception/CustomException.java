package com.wx.spring.exception;


/**
 * 自定义异常
 * @Description:
 * @Title: CustomException
 * @author wangxin
 * @date 2019年10月14日
 */
public class CustomException extends RuntimeException{

	/** serialVersionUID*/  
	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	private Integer code;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	/**
     * 自定义错误信息
     * @param message
     * @param code
     */
    public CustomException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    
    /**
     * 自定义错误信息(默认code为601)
     * @param message
     * @param code
     */
    public CustomException(String message) {
        super(message);
        this.code = 601;
    }
}
