package cn.com.payment.v2.web.controller.biz;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.payment.v2.web.common.RespCodeEnum;
import cn.com.payment.v2.web.dto.PageEasyUi;
import cn.com.payment.v2.web.dto.ResponseBase;
import cn.com.payment.v2.web.exceptions.BaseException;
import cn.com.payment.v2.web.model.TransRecordScan;
import cn.com.payment.v2.web.service.TransRecordScanService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("transRecordScan")
public class TransRecordScanController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(TransRecordScanController.class.getName());
	@Autowired
	private TransRecordScanService transRecordScanService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toTransRecordScanPage")
	@RequiresPermissions("transRecordScan:view")
	public String toTransRecordScanPage() {
		return PREFIX + "transRecordScan";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getTransRecordScanPage")
	@RequiresPermissions("transRecordScan:view")
	public @ResponseBody ResponseBase getTransRecordScanPage(PageEasyUi<TransRecordScan> page, TransRecordScan entity) {
		PageEasyUi<TransRecordScan> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<TransRecordScan> list = this.transRecordScanService.searchByProperty(entity);
			result = new PageEasyUi<TransRecordScan>(list);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), result);
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	/**
	 * 新建数据
	 * 
	 * @param transRecordScan
	 * @return
	 */
	@RequestMapping("addTransRecordScan")
	@RequiresPermissions("transRecordScan:add")
	public @ResponseBody ResponseBase addTransRecordScan(TransRecordScan transRecordScan) {
		if (CommonUtils.isEmpty(transRecordScan))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.transRecordScanService.create(transRecordScan);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg());
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @param id
	 * @return
	 */
	@RequestMapping("updateTransRecordScan")
	@RequiresPermissions("transRecordScan:update")
	public @ResponseBody ResponseBase updateTransRecordScan(TransRecordScan transRecordScan) {
		if (CommonUtils.isEmpty(transRecordScan) || CommonUtils.isEmpty(transRecordScan.getId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			TransRecordScan data = this.transRecordScanService.searchById(transRecordScan.getId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(transRecordScan.getId()))
					data.setId(transRecordScan.getId());
				if (CommonUtils.isNotEmpty(transRecordScan.getTransModeId()))
					data.setTransModeId(transRecordScan.getTransModeId());
				if (CommonUtils.isNotEmpty(transRecordScan.getTransModeName()))
					data.setTransModeName(transRecordScan.getTransModeName());
				if (CommonUtils.isNotEmpty(transRecordScan.getMerParentId()))
					data.setMerParentId(transRecordScan.getMerParentId());
				if (CommonUtils.isNotEmpty(transRecordScan.getMerId()))
					data.setMerId(transRecordScan.getMerId());
				if (CommonUtils.isNotEmpty(transRecordScan.getProviderAccId()))
					data.setProviderAccId(transRecordScan.getProviderAccId());
				if (CommonUtils.isNotEmpty(transRecordScan.getProviderMchNo()))
					data.setProviderMchNo(transRecordScan.getProviderMchNo());
				if (CommonUtils.isNotEmpty(transRecordScan.getProductId()))
					data.setProductId(transRecordScan.getProductId());
				if (CommonUtils.isNotEmpty(transRecordScan.getProductName()))
					data.setProductName(transRecordScan.getProductName());
				if (CommonUtils.isNotEmpty(transRecordScan.getRalAccProductId()))
					data.setRalAccProductId(transRecordScan.getRalAccProductId());
				if (CommonUtils.isNotEmpty(transRecordScan.getOutTradeNo()))
					data.setOutTradeNo(transRecordScan.getOutTradeNo());
				if (CommonUtils.isNotEmpty(transRecordScan.getAmount()))
					data.setAmount(transRecordScan.getAmount());
				if (CommonUtils.isNotEmpty(transRecordScan.getActulAmt()))
					data.setActulAmt(transRecordScan.getActulAmt());
				if (CommonUtils.isNotEmpty(transRecordScan.getState()))
					data.setState(transRecordScan.getState());
				if (CommonUtils.isNotEmpty(transRecordScan.getStateMsg()))
					data.setStateMsg(transRecordScan.getStateMsg());
				if (CommonUtils.isNotEmpty(transRecordScan.getStateRemark()))
					data.setStateRemark(transRecordScan.getStateRemark());
				if (CommonUtils.isNotEmpty(transRecordScan.getType()))
					data.setType(transRecordScan.getType());
				if (CommonUtils.isNotEmpty(transRecordScan.getSrcOutTradeNo()))
					data.setSrcOutTradeNo(transRecordScan.getSrcOutTradeNo());
				if (CommonUtils.isNotEmpty(transRecordScan.getOutTransactionId()))
					data.setOutTransactionId(transRecordScan.getOutTransactionId());
				if (CommonUtils.isNotEmpty(transRecordScan.getTerminalIP()))
					data.setTerminalIP(transRecordScan.getTerminalIP());
				if (CommonUtils.isNotEmpty(transRecordScan.getGoodsName()))
					data.setGoodsName(transRecordScan.getGoodsName());
				if (CommonUtils.isNotEmpty(transRecordScan.getGoodsDesc()))
					data.setGoodsDesc(transRecordScan.getGoodsDesc());
				if (CommonUtils.isNotEmpty(transRecordScan.getBizOrderNo()))
					data.setBizOrderNo(transRecordScan.getBizOrderNo());
				if (CommonUtils.isNotEmpty(transRecordScan.getNotifyUrl()))
					data.setNotifyUrl(transRecordScan.getNotifyUrl());
				if (CommonUtils.isNotEmpty(transRecordScan.getResultUrl()))
					data.setResultUrl(transRecordScan.getResultUrl());
				if (CommonUtils.isNotEmpty(transRecordScan.getPayWayUserId()))
					data.setPayWayUserId(transRecordScan.getPayWayUserId());
				if (CommonUtils.isNotEmpty(transRecordScan.getSpTransactionId()))
					data.setSpTransactionId(transRecordScan.getSpTransactionId());
				if (CommonUtils.isNotEmpty(transRecordScan.getSpTransTime()))
					data.setSpTransTime(transRecordScan.getSpTransTime());
				if (CommonUtils.isNotEmpty(transRecordScan.getSpTransDate()))
					data.setSpTransDate(transRecordScan.getSpTransDate());
				if (CommonUtils.isNotEmpty(transRecordScan.getSpRespCode()))
					data.setSpRespCode(transRecordScan.getSpRespCode());
				if (CommonUtils.isNotEmpty(transRecordScan.getSpRespMsg()))
					data.setSpRespMsg(transRecordScan.getSpRespMsg());
				if (CommonUtils.isNotEmpty(transRecordScan.getProviderFee()))
					data.setProviderFee(transRecordScan.getProviderFee());
				if (CommonUtils.isNotEmpty(transRecordScan.getProviderFeeRate()))
					data.setProviderFeeRate(transRecordScan.getProviderFeeRate());
				if (CommonUtils.isNotEmpty(transRecordScan.getMerFee()))
					data.setMerFee(transRecordScan.getMerFee());
				if (CommonUtils.isNotEmpty(transRecordScan.getMerfeeRate()))
					data.setMerfeeRate(transRecordScan.getMerfeeRate());
				if (CommonUtils.isNotEmpty(transRecordScan.getNoticeState()))
					data.setNoticeState(transRecordScan.getNoticeState());
				if (CommonUtils.isNotEmpty(transRecordScan.getSettleState()))
					data.setSettleState(transRecordScan.getSettleState());
				if (CommonUtils.isNotEmpty(transRecordScan.getCreateTime()))
					data.setCreateTime(transRecordScan.getCreateTime());
				if (CommonUtils.isNotEmpty(transRecordScan.getUpdateTime()))
					data.setUpdateTime(transRecordScan.getUpdateTime());

				this.transRecordScanService.modify(data);
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg());
			} else
				return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "数据不存在");
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("deleteTransRecordScan")
	@RequiresPermissions("transRecordScan:delete")
	public @ResponseBody ResponseBase deleteTransRecordScan(String param) {
		if (CommonUtils.isEmpty(param))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		Long[] ids = null;
		try {
			String[] params = param.split(",");
			ids = new Long[params.length];
			for (int i = 0, len = params.length; i < len; i++) {
				ids[i] = Long.valueOf(params[i]);
			}
		} catch (NumberFormatException e1) {
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), "参数格式错误！");
		}

		try {
			this.transRecordScanService.remove(ids);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg());
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	/**
	 * 根据id获取信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("getTransRecordScanById")
	@RequiresPermissions("transRecordScan:view")
	public @ResponseBody ResponseBase getTransRecordScanById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			TransRecordScan transRecordScan = this.transRecordScanService.searchById(id);
			if (null != transRecordScan)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), transRecordScan);
			else
				return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD);
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}
}