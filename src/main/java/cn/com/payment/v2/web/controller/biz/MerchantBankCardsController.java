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
import cn.com.payment.v2.web.model.MerchantBankCards;
import cn.com.payment.v2.web.service.MerchantBankCardsService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("merchantBankCards")
public class MerchantBankCardsController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(MerchantBankCardsController.class.getName());
	@Autowired
	private MerchantBankCardsService merchantBankCardsService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toMerchantBankCardsPage")
	@RequiresPermissions("merchantBankCards:view")
	public String toMerchantBankCardsPage() {
		return PREFIX + "merchantBankCards";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getMerchantBankCardsPage")
	@RequiresPermissions("merchantBankCards:view")
	public @ResponseBody ResponseBase getMerchantBankCardsPage(PageEasyUi<MerchantBankCards> page,
			MerchantBankCards entity) {
		PageEasyUi<MerchantBankCards> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<MerchantBankCards> list = this.merchantBankCardsService.searchByProperty(entity);
			result = new PageEasyUi<MerchantBankCards>(list);
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
	 * @param merchantBankCards
	 * @return
	 */
	@RequestMapping("addMerchantBankCards")
	@RequiresPermissions("merchantBankCards:add")
	public @ResponseBody ResponseBase addMerchantBankCards(MerchantBankCards merchantBankCards) {
		if (CommonUtils.isEmpty(merchantBankCards))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.merchantBankCardsService.create(merchantBankCards);
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
	@RequestMapping("updateMerchantBankCards")
	@RequiresPermissions("merchantBankCards:update")
	public @ResponseBody ResponseBase updateMerchantBankCards(MerchantBankCards merchantBankCards) {
		if (CommonUtils.isEmpty(merchantBankCards) || CommonUtils.isEmpty(merchantBankCards.getBankCardId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			MerchantBankCards data = this.merchantBankCardsService.searchById(merchantBankCards.getBankCardId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankCardId()))
					data.setBankCardId(merchantBankCards.getBankCardId());
				if (CommonUtils.isNotEmpty(merchantBankCards.getMchId()))
					data.setMchId(merchantBankCards.getMchId());
				if (CommonUtils.isNotEmpty(merchantBankCards.getUseType()))
					data.setUseType(merchantBankCards.getUseType());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankCode()))
					data.setBankCode(merchantBankCards.getBankCode());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankName()))
					data.setBankName(merchantBankCards.getBankName());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankAccType()))
					data.setBankAccType(merchantBankCards.getBankAccType());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankCardType()))
					data.setBankCardType(merchantBankCards.getBankCardType());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankReservedPhone()))
					data.setBankReservedPhone(merchantBankCards.getBankReservedPhone());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankCardNo()))
					data.setBankCardNo(merchantBankCards.getBankCardNo());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankProvince()))
					data.setBankProvince(merchantBankCards.getBankProvince());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankCity()))
					data.setBankCity(merchantBankCards.getBankCity());
				if (CommonUtils.isNotEmpty(merchantBankCards.getBankLineNo()))
					data.setBankLineNo(merchantBankCards.getBankLineNo());
				if (CommonUtils.isNotEmpty(merchantBankCards.getSubBankName()))
					data.setSubBankName(merchantBankCards.getSubBankName());
				if (CommonUtils.isNotEmpty(merchantBankCards.getIdCardNo()))
					data.setIdCardNo(merchantBankCards.getIdCardNo());
				if (CommonUtils.isNotEmpty(merchantBankCards.getRealName()))
					data.setRealName(merchantBankCards.getRealName());
				if (CommonUtils.isNotEmpty(merchantBankCards.getState()))
					data.setState(merchantBankCards.getState());
				if (CommonUtils.isNotEmpty(merchantBankCards.getCreateTime()))
					data.setCreateTime(merchantBankCards.getCreateTime());
				if (CommonUtils.isNotEmpty(merchantBankCards.getUpdateTime()))
					data.setUpdateTime(merchantBankCards.getUpdateTime());
				if (CommonUtils.isNotEmpty(merchantBankCards.getIsDel()))
					data.setIsDel(merchantBankCards.getIsDel());

				this.merchantBankCardsService.modify(data);
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
	@RequestMapping("deleteMerchantBankCards")
	@RequiresPermissions("merchantBankCards:delete")
	public @ResponseBody ResponseBase deleteMerchantBankCards(String param) {
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
			this.merchantBankCardsService.remove(ids);
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
	@RequestMapping("getMerchantBankCardsById")
	@RequiresPermissions("merchantBankCards:view")
	public @ResponseBody ResponseBase getMerchantBankCardsById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			MerchantBankCards merchantBankCards = this.merchantBankCardsService.searchById(id);
			if (null != merchantBankCards)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),
						merchantBankCards);
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