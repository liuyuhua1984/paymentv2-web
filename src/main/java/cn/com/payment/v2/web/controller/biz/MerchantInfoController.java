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
import cn.com.payment.v2.web.model.MerchantInfo;
import cn.com.payment.v2.web.service.MerchantInfoService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("merchantInfo")
public class MerchantInfoController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(MerchantInfoController.class.getName());
	@Autowired
	private MerchantInfoService merchantInfoService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toMerchantInfoPage")
	@RequiresPermissions("merchantInfo:view")
	public String toMerchantInfoPage() {
		return PREFIX + "merchantInfo";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getMerchantInfoPage")
	@RequiresPermissions("merchantInfo:view")
	public @ResponseBody ResponseBase getMerchantInfoPage(PageEasyUi<MerchantInfo> page, MerchantInfo entity) {
		PageEasyUi<MerchantInfo> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<MerchantInfo> list = this.merchantInfoService.searchByProperty(entity);
			result = new PageEasyUi<MerchantInfo>(list);
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
	 * @param merchantInfo
	 * @return
	 */
	@RequestMapping("addMerchantInfo")
	@RequiresPermissions("merchantInfo:add")
	public @ResponseBody ResponseBase addMerchantInfo(MerchantInfo merchantInfo) {
		if (CommonUtils.isEmpty(merchantInfo))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.merchantInfoService.create(merchantInfo);
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
	@RequestMapping("updateMerchantInfo")
	@RequiresPermissions("merchantInfo:update")
	public @ResponseBody ResponseBase updateMerchantInfo(MerchantInfo merchantInfo) {
		if (CommonUtils.isEmpty(merchantInfo) || CommonUtils.isEmpty(merchantInfo.getMchId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			MerchantInfo data = this.merchantInfoService.searchById(merchantInfo.getMchId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(merchantInfo.getMchId()))
					data.setMchId(merchantInfo.getMchId());
				if (CommonUtils.isNotEmpty(merchantInfo.getOrgParentId()))
					data.setOrgParentId(merchantInfo.getOrgParentId());
				if (CommonUtils.isNotEmpty(merchantInfo.getChainParentId()))
					data.setChainParentId(merchantInfo.getChainParentId());
				if (CommonUtils.isNotEmpty(merchantInfo.getAccNo()))
					data.setAccNo(merchantInfo.getAccNo());
				if (CommonUtils.isNotEmpty(merchantInfo.getMchName()))
					data.setMchName(merchantInfo.getMchName());
				if (CommonUtils.isNotEmpty(merchantInfo.getMchDesc()))
					data.setMchDesc(merchantInfo.getMchDesc());
				if (CommonUtils.isNotEmpty(merchantInfo.getMchType()))
					data.setMchType(merchantInfo.getMchType());
				if (CommonUtils.isNotEmpty(merchantInfo.getContacts()))
					data.setContacts(merchantInfo.getContacts());
				if (CommonUtils.isNotEmpty(merchantInfo.getContactsCell()))
					data.setContactsCell(merchantInfo.getContactsCell());
				if (CommonUtils.isNotEmpty(merchantInfo.getEmail()))
					data.setEmail(merchantInfo.getEmail());
				if (CommonUtils.isNotEmpty(merchantInfo.getMchNo()))
					data.setMchNo(merchantInfo.getMchNo());
				if (CommonUtils.isNotEmpty(merchantInfo.getMchKey()))
					data.setMchKey(merchantInfo.getMchKey());
				if (CommonUtils.isNotEmpty(merchantInfo.getMchRSAKey()))
					data.setMchRSAKey(merchantInfo.getMchRSAKey());
				if (CommonUtils.isNotEmpty(merchantInfo.getMchAPPId()))
					data.setMchAPPId(merchantInfo.getMchAPPId());
				if (CommonUtils.isNotEmpty(merchantInfo.getMchPayPassWord()))
					data.setMchPayPassWord(merchantInfo.getMchPayPassWord());
				if (CommonUtils.isNotEmpty(merchantInfo.getGoogleAuthUrl()))
					data.setGoogleAuthUrl(merchantInfo.getGoogleAuthUrl());
				if (CommonUtils.isNotEmpty(merchantInfo.getGoogleAuthKey()))
					data.setGoogleAuthKey(merchantInfo.getGoogleAuthKey());
				if (CommonUtils.isNotEmpty(merchantInfo.getGoogleAuthFlag()))
					data.setGoogleAuthFlag(merchantInfo.getGoogleAuthFlag());
				if (CommonUtils.isNotEmpty(merchantInfo.getIsDel()))
					data.setIsDel(merchantInfo.getIsDel());
				if (CommonUtils.isNotEmpty(merchantInfo.getState()))
					data.setState(merchantInfo.getState());
				if (CommonUtils.isNotEmpty(merchantInfo.getAuthState()))
					data.setAuthState(merchantInfo.getAuthState());
				if (CommonUtils.isNotEmpty(merchantInfo.getValidIP()))
					data.setValidIP(merchantInfo.getValidIP());
				if (CommonUtils.isNotEmpty(merchantInfo.getCreateTime()))
					data.setCreateTime(merchantInfo.getCreateTime());
				if (CommonUtils.isNotEmpty(merchantInfo.getUpdateTime()))
					data.setUpdateTime(merchantInfo.getUpdateTime());

				this.merchantInfoService.modify(data);
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
	@RequestMapping("deleteMerchantInfo")
	@RequiresPermissions("merchantInfo:delete")
	public @ResponseBody ResponseBase deleteMerchantInfo(String param) {
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
			this.merchantInfoService.remove(ids);
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
	@RequestMapping("getMerchantInfoById")
	@RequiresPermissions("merchantInfo:view")
	public @ResponseBody ResponseBase getMerchantInfoById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			MerchantInfo merchantInfo = this.merchantInfoService.searchById(id);
			if (null != merchantInfo)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), merchantInfo);
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