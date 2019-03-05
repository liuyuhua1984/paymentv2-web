package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.AccountStatementMapper;
import cn.com.payment.v2.web.model.AccountStatement;
import cn.com.payment.v2.web.service.AccountStatementService;


/**
 * AccountStatementServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class AccountStatementServiceImpl extends BaseServiceImpl<AccountStatement, Long> 
	implements AccountStatementService{
	@Autowired
	private AccountStatementMapper accountStatementMapper;
	@Override
	public BaseMapper<AccountStatement, Long> getBaseMapper() {
		return this.accountStatementMapper;
	}
}