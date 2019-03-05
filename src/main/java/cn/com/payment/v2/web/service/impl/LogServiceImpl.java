package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.LogMapper;
import cn.com.payment.v2.web.model.Log;
import cn.com.payment.v2.web.service.LogService;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log, Long>  implements LogService {

	@Autowired
	private LogMapper logMapper;
	@Override
	public BaseMapper<Log, Long> getBaseMapper() {
		return this.logMapper;
	}
	@Async("taskAsyncPool") // 异步处理创建日志记录表
	@Override
	public Long createLog(Log log) {
		System.out.println("日志入库" + log);
		return this.logMapper.save(log);
	}

	@Async("taskAsyncPool") // 异步处理更新日志记录表
	@Override
	public void updateLog(Log log) {
		System.out.println("日志更新" + log);
		this.logMapper.update(log);
	}
}