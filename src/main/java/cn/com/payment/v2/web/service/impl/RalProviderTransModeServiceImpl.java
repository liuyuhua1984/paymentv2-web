package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.RalProviderTransModeMapper;
import cn.com.payment.v2.web.model.RalProviderTransMode;
import cn.com.payment.v2.web.service.RalProviderTransModeService;


/**
 * RalProviderTransModeServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class RalProviderTransModeServiceImpl extends BaseServiceImpl<RalProviderTransMode, Long> 
	implements RalProviderTransModeService{
	@Autowired
	private RalProviderTransModeMapper ralProviderTransModeMapper;
	@Override
	public BaseMapper<RalProviderTransMode, Long> getBaseMapper() {
		return this.ralProviderTransModeMapper;
	}
}