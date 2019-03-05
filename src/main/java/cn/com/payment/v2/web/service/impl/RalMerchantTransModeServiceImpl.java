package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.RalMerchantTransModeMapper;
import cn.com.payment.v2.web.model.RalMerchantTransMode;
import cn.com.payment.v2.web.service.RalMerchantTransModeService;


/**
 * RalMerchantTransModeServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class RalMerchantTransModeServiceImpl extends BaseServiceImpl<RalMerchantTransMode, Long> 
	implements RalMerchantTransModeService{
	@Autowired
	private RalMerchantTransModeMapper ralMerchantTransModeMapper;
	@Override
	public BaseMapper<RalMerchantTransMode, Long> getBaseMapper() {
		return this.ralMerchantTransModeMapper;
	}
}