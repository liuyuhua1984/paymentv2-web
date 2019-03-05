package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.ProviderAccountMapper;
import cn.com.payment.v2.web.model.ProviderAccount;
import cn.com.payment.v2.web.service.ProviderAccountService;


/**
 * ProviderAccountServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class ProviderAccountServiceImpl extends BaseServiceImpl<ProviderAccount, Long> 
	implements ProviderAccountService{
	@Autowired
	private ProviderAccountMapper providerAccountMapper;
	@Override
	public BaseMapper<ProviderAccount, Long> getBaseMapper() {
		return this.providerAccountMapper;
	}
}