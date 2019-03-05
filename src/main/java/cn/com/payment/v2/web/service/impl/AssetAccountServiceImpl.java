package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.AssetAccountMapper;
import cn.com.payment.v2.web.model.AssetAccount;
import cn.com.payment.v2.web.service.AssetAccountService;


/**
 * AssetAccountServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class AssetAccountServiceImpl extends BaseServiceImpl<AssetAccount, Long> 
	implements AssetAccountService{
	@Autowired
	private AssetAccountMapper assetAccountMapper;
	@Override
	public BaseMapper<AssetAccount, Long> getBaseMapper() {
		return this.assetAccountMapper;
	}
}