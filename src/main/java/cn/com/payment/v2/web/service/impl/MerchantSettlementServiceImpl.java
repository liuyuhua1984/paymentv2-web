package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.MerchantSettlementMapper;
import cn.com.payment.v2.web.model.MerchantSettlement;
import cn.com.payment.v2.web.service.MerchantSettlementService;


/**
 * MerchantSettlementServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class MerchantSettlementServiceImpl extends BaseServiceImpl<MerchantSettlement, Long> 
	implements MerchantSettlementService{
	@Autowired
	private MerchantSettlementMapper merchantSettlementMapper;
	@Override
	public BaseMapper<MerchantSettlement, Long> getBaseMapper() {
		return this.merchantSettlementMapper;
	}
}