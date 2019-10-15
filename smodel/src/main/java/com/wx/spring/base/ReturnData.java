package com.wx.spring.base;

import java.io.Serializable;


/**
 * 统一返回格式
 * @Description:
 * @Title: ReturnData
 * @author wangxin
 * @date 2019年10月15日
 */
public class ReturnData<T> implements Serializable{

	/** serialVersionUID*/  
	private static final long serialVersionUID = 1L;

	public ReturnData(){
		
	}
	
	public ReturnData(Integer code,String msg,String alert){
		this.code = code;
		this.msg = msg;
		this.alert = alert;
	}
	
	public ReturnData(Integer code,String msg,String alert,T data){
		this.code = code;
		this.msg = msg;
		this.alert = alert;
		this.data = data;
	}
	
	/**
	 * code 码
	 */
	private Integer code;
	
	/**
	 * 返回信息
	 */
	private String msg;
	
	/**
	 * 提示信息
	 */
	private String alert;
	
	/**
	 * 返回数据
	 */
	private T data;

	/**
	 * 设置成功信息
	 * @Description:  
	 * @author wangxin
	 * @date 2019年10月15日  
	 * @param 
	 * @return
	 */
	public static ReturnData<?> success(Object data){
		ReturnData<Object> result = new ReturnData<Object>();
        result.setCode(StatusEnum.SUCCESS.getCode());
        result.setMsg(StatusEnum.SUCCESS.getMsg());
        result.setAlert(StatusEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
		
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	

    @Override
    public String toString() {
        return "{" +
        " code=" + code +
        ", msg=" + msg +
        ", alert=" + alert +
        ", data=" + data +
        "}";
    }
}
