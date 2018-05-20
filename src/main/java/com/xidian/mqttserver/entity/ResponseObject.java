package com.xidian.mqttserver.entity;

import java.io.Serializable;

/**
 * 请求的返回结果。 如果:success=true resultObject=处理结果； 如果:success=false errorMessage=错误信息
 *@author Feng Gao
 *@date 2017年7月2日 上午10:07:59
 */
public class ResponseObject<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean success;

	private String errorMessage;

	private T resultObject;

	/**
	 * 构造一个错误响应对象
	 * 
	 * @param errorMessage
	 * @return
	 */
	public static ResponseObject newErrorResponseObject(String errorMessage) {
		ResponseObject res = new ResponseObject();
		res.setSuccess(false);
		res.setErrorMessage(errorMessage);
		return res;
	}

	/**
	 * 构造一个成功响应对象
	 * 
	 * @param resultObject
	 * @return
	 */
	public static ResponseObject newSuccessResponseObject(Object resultObject) {
		ResponseObject res = new ResponseObject();
		res.setSuccess(true);
		res.setResultObject(resultObject);
		return res;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getResultObject() {
		return resultObject;
	}

	public void setResultObject(T resultObject) {
		this.resultObject = resultObject;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
