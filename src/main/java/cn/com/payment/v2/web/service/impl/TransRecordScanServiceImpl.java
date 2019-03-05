package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.TransRecordScanMapper;
import cn.com.payment.v2.web.model.TransRecordScan;
import cn.com.payment.v2.web.service.TransRecordScanService;


/**
 * TransRecordScanServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class TransRecordScanServiceImpl extends BaseServiceImpl<TransRecordScan, Long> 
	implements TransRecordScanService{
	@Autowired
	private TransRecordScanMapper transRecordScanMapper;
	@Override
	public BaseMapper<TransRecordScan, Long> getBaseMapper() {
		return this.transRecordScanMapper;
	}
}