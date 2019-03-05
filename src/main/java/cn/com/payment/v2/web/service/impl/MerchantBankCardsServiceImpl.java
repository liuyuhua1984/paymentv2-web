package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.MerchantBankCardsMapper;
import cn.com.payment.v2.web.model.MerchantBankCards;
import cn.com.payment.v2.web.service.MerchantBankCardsService;


/**
 * MerchantBankCardsServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class MerchantBankCardsServiceImpl extends BaseServiceImpl<MerchantBankCards, Long> 
	implements MerchantBankCardsService{
	@Autowired
	private MerchantBankCardsMapper merchantBankCardsMapper;
	@Override
	public BaseMapper<MerchantBankCards, Long> getBaseMapper() {
		return this.merchantBankCardsMapper;
	}
}