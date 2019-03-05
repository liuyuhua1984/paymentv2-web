package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.MerchantStatisticsDayMapper;
import cn.com.payment.v2.web.model.MerchantStatisticsDay;
import cn.com.payment.v2.web.service.MerchantStatisticsDayService;


/**
 * MerchantStatisticsDayServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class MerchantStatisticsDayServiceImpl extends BaseServiceImpl<MerchantStatisticsDay, Long> 
	implements MerchantStatisticsDayService{
	@Autowired
	private MerchantStatisticsDayMapper merchantStatisticsDayMapper;
	@Override
	public BaseMapper<MerchantStatisticsDay, Long> getBaseMapper() {
		return this.merchantStatisticsDayMapper;
	}
}