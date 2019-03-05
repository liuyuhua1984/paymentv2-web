package cn.com.payment.v2.web.dto;

import cn.com.payment.v2.web.common.RespCodeEnum;

public class ResponseBase {
	private String code;
	private String msg;
	private Object data;

	public ResponseBase() {
		super();
	}

	public ResponseBase(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseBase(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResponseBase(RespCodeEnum respCodeEnum) {
		super();
		this.code = respCodeEnum.getCode();
		this.msg = respCodeEnum.getMsg();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
