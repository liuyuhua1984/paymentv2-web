package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.MerchantInfoMapper;
import cn.com.payment.v2.web.model.MerchantInfo;
import cn.com.payment.v2.web.service.MerchantInfoService;


/**
 * MerchantInfoServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class MerchantInfoServiceImpl extends BaseServiceImpl<MerchantInfo, Long> 
	implements MerchantInfoService{
	@Autowired
	private MerchantInfoMapper merchantInfoMapper;
	@Override
	public BaseMapper<MerchantInfo, Long> getBaseMapper() {
		return this.merchantInfoMapper;
	}
}