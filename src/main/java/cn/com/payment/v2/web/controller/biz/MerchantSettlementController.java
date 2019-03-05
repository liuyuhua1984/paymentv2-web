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
import cn.com.payment.v2.web.model.MerchantSettlement;
import cn.com.payment.v2.web.service.MerchantSettlementService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("merchantSettlement")
public class MerchantSettlementController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(MerchantSettlementController.class.getName());
	@Autowired
	private MerchantSettlementService merchantSettlementService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toMerchantSettlementPage")
	@RequiresPermissions("merchantSettlement:view")
	public String toMerchantSettlementPage() {
		return PREFIX + "merchantSettlement";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getMerchantSettlementPage")
	@RequiresPermissions("merchantSettlement:view")
	public @ResponseBody ResponseBase getMerchantSettlementPage(PageEasyUi<MerchantSettlement> page,
			MerchantSettlement entity) {
		PageEasyUi<MerchantSettlement> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<MerchantSettlement> list = this.merchantSettlementService.searchByProperty(entity);
			result = new PageEasyUi<MerchantSettlement>(list);
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
	 * @param merchantSettlement
	 * @return
	 */
	@RequestMapping("addMerchantSettlement")
	@RequiresPermissions("merchantSettlement:add")
	public @ResponseBody ResponseBase addMerchantSettlement(MerchantSettlement merchantSettlement) {
		if (CommonUtils.isEmpty(merchantSettlement))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.merchantSettlementService.create(merchantSettlement);
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
	@RequestMapping("updateMerchantSettlement")
	@RequiresPermissions("merchantSettlement:update")
	public @ResponseBody ResponseBase updateMerchantSettlement(MerchantSettlement merchantSettlement) {
		if (CommonUtils.isEmpty(merchantSettlement) || CommonUtils.isEmpty(merchantSettlement.getSettleId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			MerchantSettlement data = this.merchantSettlementService.searchById(merchantSettlement.getSettleId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(merchantSettlement.getSettleId()))
					data.setSettleId(merchantSettlement.getSettleId());
				if (CommonUtils.isNotEmpty(merchantSettlement.getMchId()))
					data.setMchId(merchantSettlement.getMchId());
				if (CommonUtils.isNotEmpty(merchantSettlement.getSettleDate()))
					data.setSettleDate(merchantSettlement.getSettleDate());
				if (CommonUtils.isNotEmpty(merchantSettlement.getPayDate()))
					data.setPayDate(merchantSettlement.getPayDate());
				if (CommonUtils.isNotEmpty(merchantSettlement.getSettleType()))
					data.setSettleType(merchantSettlement.getSettleType());
				if (CommonUtils.isNotEmpty(merchantSettlement.getSettleAmount()))
					data.setSettleAmount(merchantSettlement.getSettleAmount());
				if (CommonUtils.isNotEmpty(merchantSettlement.getState()))
					data.setState(merchantSettlement.getState());
				if (CommonUtils.isNotEmpty(merchantSettlement.getCreateTime()))
					data.setCreateTime(merchantSettlement.getCreateTime());
				if (CommonUtils.isNotEmpty(merchantSettlement.getUpdateTime()))
					data.setUpdateTime(merchantSettlement.getUpdateTime());

				this.merchantSettlementService.modify(data);
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
	@RequestMapping("deleteMerchantSettlement")
	@RequiresPermissions("merchantSettlement:delete")
	public @ResponseBody ResponseBase deleteMerchantSettlement(String param) {
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
			this.merchantSettlementService.remove(ids);
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
	@RequestMapping("getMerchantSettlementById")
	@RequiresPermissions("merchantSettlement:view")
	public @ResponseBody ResponseBase getMerchantSettlementById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			MerchantSettlement merchantSettlement = this.merchantSettlementService.searchById(id);
			if (null != merchantSettlement)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),
						merchantSettlement);
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