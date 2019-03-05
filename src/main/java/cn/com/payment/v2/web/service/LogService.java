package cn.com.payment.v2.web.service;

import cn.com.payment.v2.web.model.Log;

public interface LogService extends BaseService<Log, Long> {
	// 增删改
	public Long createLog(Log log);

	public void updateLog(Log log);
}