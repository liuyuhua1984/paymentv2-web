package cn.com.payment.v2.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.payment.v2.web.mapper.BaseMapper;
import cn.com.payment.v2.web.mapper.TransModeMapper;
import cn.com.payment.v2.web.model.TransMode;
import cn.com.payment.v2.web.service.TransModeService;


/**
 * TransModeServiceImpl
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
@Service
public class TransModeServiceImpl extends BaseServiceImpl<TransMode, Long> 
	implements TransModeService{
	@Autowired
	private TransModeMapper transModeMapper;
	@Override
	public BaseMapper<TransMode, Long> getBaseMapper() {
		return this.transModeMapper;
	}
}