package cn.com.payment.v2.web.common;

public enum RespCodeEnum {

	SUCCESS("0000", "成功"), 
	SUCCESS_NODATA("0001", "成功但无数据"), 
	ERROR_EXPIRED("1000", "请求过期，需要重新登录"),
	INVALID_REQUEST("1001", "无效的请求"), 
	DATA_NOT_FONUD("1002", "数据不存在"),
	DATA_EXISTS("1003", "数据存在"),
	UNRECOGNIABLE_CALSS("1004", "无法识别的类型"),
	DATA_RESOLVE("1005", "数据解析错误"), 
	DATA_NULL("1006", "数据为空"),
	NO_ACCESS("2000", "无权限访问"),
	DISALLOW_OPERATION("2001", "操作不被允许"),
	ERROR_AUTH("2002", "认证异常"),
	ERROR_VERIFICATION("2003", "验证异常"),
	THIRD_API("3001", "接口异常"),
	SYSTEM_ERR("5000", "系统内部异常"),
	UNDEFINED("-1", "未知类型");

	private String code;
	private String msg;
	
	private RespCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
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

}
