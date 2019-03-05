package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.TransRecordDpayMapper;
import cn.com.payment.v2.web.model.TransRecordDpay;
import cn.com.payment.v2.web.service.TransRecordDpayService;


/**
 * TransRecordDpayServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class TransRecordDpayServiceImpl extends BaseServiceImpl<TransRecordDpay, Long> 
	implements TransRecordDpayService{
	@Autowired
	private TransRecordDpayMapper transRecordDpayMapper;
	@Override
	public BaseMapper<TransRecordDpay, Long> getBaseMapper() {
		return this.transRecordDpayMapper;
	}
}