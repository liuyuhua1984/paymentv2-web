package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.ProviderStatisticsDaysMapper;
import cn.com.payment.v2.web.model.ProviderStatisticsDays;
import cn.com.payment.v2.web.service.ProviderStatisticsDaysService;


/**
 * ProviderStatisticsDaysServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class ProviderStatisticsDaysServiceImpl extends BaseServiceImpl<ProviderStatisticsDays, Long> 
	implements ProviderStatisticsDaysService{
	@Autowired
	private ProviderStatisticsDaysMapper providerStatisticsDaysMapper;
	@Override
	public BaseMapper<ProviderStatisticsDays, Long> getBaseMapper() {
		return this.providerStatisticsDaysMapper;
	}
}