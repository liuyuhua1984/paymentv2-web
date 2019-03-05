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
import cn.com.payment.v2.web.model.TransRecordDpay;
import cn.com.payment.v2.web.service.TransRecordDpayService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("transRecordDpay")
public class TransRecordDpayController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(TransRecordDpayController.class.getName());
	@Autowired
	private TransRecordDpayService transRecordDpayService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toTransRecordDpayPage")
	@RequiresPermissions("transRecordDpay:view")
	public String toTransRecordDpayPage() {
		return PREFIX + "transRecordDpay";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getTransRecordDpayPage")
	@RequiresPermissions("transRecordDpay:view")
	public @ResponseBody ResponseBase getTransRecordDpayPage(PageEasyUi<TransRecordDpay> page, TransRecordDpay entity) {
		PageEasyUi<TransRecordDpay> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<TransRecordDpay> list = this.transRecordDpayService.searchByProperty(entity);
			result = new PageEasyUi<TransRecordDpay>(list);
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
	 * @param transRecordDpay
	 * @return
	 */
	@RequestMapping("addTransRecordDpay")
	@RequiresPermissions("transRecordDpay:add")
	public @ResponseBody ResponseBase addTransRecordDpay(TransRecordDpay transRecordDpay) {
		if (CommonUtils.isEmpty(transRecordDpay))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.transRecordDpayService.create(transRecordDpay);
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
	@RequestMapping("updateTransRecordDpay")
	@RequiresPermissions("transRecordDpay:update")
	public @ResponseBody ResponseBase updateTransRecordDpay(TransRecordDpay transRecordDpay) {
		if (CommonUtils.isEmpty(transRecordDpay) || CommonUtils.isEmpty(transRecordDpay.getTransId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			TransRecordDpay data = this.transRecordDpayService.searchById(transRecordDpay.getTransId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(transRecordDpay.getTransId()))
					data.setTransId(transRecordDpay.getTransId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getTransModeId()))
					data.setTransModeId(transRecordDpay.getTransModeId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getTransModeName()))
					data.setTransModeName(transRecordDpay.getTransModeName());
				if (CommonUtils.isNotEmpty(transRecordDpay.getMerParentId()))
					data.setMerParentId(transRecordDpay.getMerParentId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getMerId()))
					data.setMerId(transRecordDpay.getMerId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getProviderAccId()))
					data.setProviderAccId(transRecordDpay.getProviderAccId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getProviderMchNo()))
					data.setProviderMchNo(transRecordDpay.getProviderMchNo());
				if (CommonUtils.isNotEmpty(transRecordDpay.getProductId()))
					data.setProductId(transRecordDpay.getProductId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getProductName()))
					data.setProductName(transRecordDpay.getProductName());
				if (CommonUtils.isNotEmpty(transRecordDpay.getBankCode()))
					data.setBankCode(transRecordDpay.getBankCode());
				if (CommonUtils.isNotEmpty(transRecordDpay.getBankName()))
					data.setBankName(transRecordDpay.getBankName());
				if (CommonUtils.isNotEmpty(transRecordDpay.getAccountType()))
					data.setAccountType(transRecordDpay.getAccountType());
				if (CommonUtils.isNotEmpty(transRecordDpay.getBankCardType()))
					data.setBankCardType(transRecordDpay.getBankCardType());
				if (CommonUtils.isNotEmpty(transRecordDpay.getBankId()))
					data.setBankId(transRecordDpay.getBankId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getBankCardNo()))
					data.setBankCardNo(transRecordDpay.getBankCardNo());
				if (CommonUtils.isNotEmpty(transRecordDpay.getRealName()))
					data.setRealName(transRecordDpay.getRealName());
				if (CommonUtils.isNotEmpty(transRecordDpay.getAmount()))
					data.setAmount(transRecordDpay.getAmount());
				if (CommonUtils.isNotEmpty(transRecordDpay.getOutTradeNo()))
					data.setOutTradeNo(transRecordDpay.getOutTradeNo());
				if (CommonUtils.isNotEmpty(transRecordDpay.getState()))
					data.setState(transRecordDpay.getState());
				if (CommonUtils.isNotEmpty(transRecordDpay.getStateMsg()))
					data.setStateMsg(transRecordDpay.getStateMsg());
				if (CommonUtils.isNotEmpty(transRecordDpay.getStateRemark()))
					data.setStateRemark(transRecordDpay.getStateRemark());
				if (CommonUtils.isNotEmpty(transRecordDpay.getType()))
					data.setType(transRecordDpay.getType());
				if (CommonUtils.isNotEmpty(transRecordDpay.getSrcOutTradeNo()))
					data.setSrcOutTradeNo(transRecordDpay.getSrcOutTradeNo());
				if (CommonUtils.isNotEmpty(transRecordDpay.getOutTransactionId()))
					data.setOutTransactionId(transRecordDpay.getOutTransactionId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getTerminalIP()))
					data.setTerminalIP(transRecordDpay.getTerminalIP());
				if (CommonUtils.isNotEmpty(transRecordDpay.getGoodsName()))
					data.setGoodsName(transRecordDpay.getGoodsName());
				if (CommonUtils.isNotEmpty(transRecordDpay.getGoodsDesc()))
					data.setGoodsDesc(transRecordDpay.getGoodsDesc());
				if (CommonUtils.isNotEmpty(transRecordDpay.getBizOrderNo()))
					data.setBizOrderNo(transRecordDpay.getBizOrderNo());
				if (CommonUtils.isNotEmpty(transRecordDpay.getNotifyUrl()))
					data.setNotifyUrl(transRecordDpay.getNotifyUrl());
				if (CommonUtils.isNotEmpty(transRecordDpay.getResultUrl()))
					data.setResultUrl(transRecordDpay.getResultUrl());
				if (CommonUtils.isNotEmpty(transRecordDpay.getPayWayUserId()))
					data.setPayWayUserId(transRecordDpay.getPayWayUserId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getSpTransactionId()))
					data.setSpTransactionId(transRecordDpay.getSpTransactionId());
				if (CommonUtils.isNotEmpty(transRecordDpay.getSpTransTime()))
					data.setSpTransTime(transRecordDpay.getSpTransTime());
				if (CommonUtils.isNotEmpty(transRecordDpay.getSpTransDate()))
					data.setSpTransDate(transRecordDpay.getSpTransDate());
				if (CommonUtils.isNotEmpty(transRecordDpay.getSpRespCode()))
					data.setSpRespCode(transRecordDpay.getSpRespCode());
				if (CommonUtils.isNotEmpty(transRecordDpay.getSpRespMsg()))
					data.setSpRespMsg(transRecordDpay.getSpRespMsg());
				if (CommonUtils.isNotEmpty(transRecordDpay.getFee()))
					data.setFee(transRecordDpay.getFee());
				if (CommonUtils.isNotEmpty(transRecordDpay.getNoticetate()))
					data.setNoticetate(transRecordDpay.getNoticetate());
				if (CommonUtils.isNotEmpty(transRecordDpay.getCreateTime()))
					data.setCreateTime(transRecordDpay.getCreateTime());
				if (CommonUtils.isNotEmpty(transRecordDpay.getUpdateTime()))
					data.setUpdateTime(transRecordDpay.getUpdateTime());
				if (CommonUtils.isNotEmpty(transRecordDpay.getRalAccProductId()))
					data.setRalAccProductId(transRecordDpay.getRalAccProductId());

				this.transRecordDpayService.modify(data);
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
	@RequestMapping("deleteTransRecordDpay")
	@RequiresPermissions("transRecordDpay:delete")
	public @ResponseBody ResponseBase deleteTransRecordDpay(String param) {
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
			this.transRecordDpayService.remove(ids);
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
	@RequestMapping("getTransRecordDpayById")
	@RequiresPermissions("transRecordDpay:view")
	public @ResponseBody ResponseBase getTransRecordDpayById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			TransRecordDpay transRecordDpay = this.transRecordDpayService.searchById(id);
			if (null != transRecordDpay)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), transRecordDpay);
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