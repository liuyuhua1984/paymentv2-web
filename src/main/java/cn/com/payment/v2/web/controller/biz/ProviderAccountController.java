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
import cn.com.payment.v2.web.model.ProviderAccount;
import cn.com.payment.v2.web.service.ProviderAccountService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("providerAccount")
public class ProviderAccountController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(ProviderAccountController.class.getName());
	@Autowired
	private ProviderAccountService providerAccountService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toProviderAccountPage")
	@RequiresPermissions("providerAccount:view")
	public String toProviderAccountPage() {
		return PREFIX + "providerAccount";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getProviderAccountPage")
	@RequiresPermissions("providerAccount:view")
	public @ResponseBody ResponseBase getProviderAccountPage(PageEasyUi<ProviderAccount> page, ProviderAccount entity) {
		PageEasyUi<ProviderAccount> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<ProviderAccount> list = this.providerAccountService.searchByProperty(entity);
			result = new PageEasyUi<ProviderAccount>(list);
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
	 * @param providerAccount
	 * @return
	 */
	@RequestMapping("addProviderAccount")
	@RequiresPermissions("providerAccount:add")
	public @ResponseBody ResponseBase addProviderAccount(ProviderAccount providerAccount) {
		if (CommonUtils.isEmpty(providerAccount))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.providerAccountService.create(providerAccount);
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
	@RequestMapping("updateProviderAccount")
	@RequiresPermissions("providerAccount:update")
	public @ResponseBody ResponseBase updateProviderAccount(ProviderAccount providerAccount) {
		if (CommonUtils.isEmpty(providerAccount) || CommonUtils.isEmpty(providerAccount.getProviderAccId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			ProviderAccount data = this.providerAccountService.searchById(providerAccount.getProviderAccId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(providerAccount.getProviderAccId()))
					data.setProviderAccId(providerAccount.getProviderAccId());
				if (CommonUtils.isNotEmpty(providerAccount.getProductId()))
					data.setProductId(providerAccount.getProductId());
				if (CommonUtils.isNotEmpty(providerAccount.getAccName()))
					data.setAccName(providerAccount.getAccName());
				if (CommonUtils.isNotEmpty(providerAccount.getProviderMchNo()))
					data.setProviderMchNo(providerAccount.getProviderMchNo());
				if (CommonUtils.isNotEmpty(providerAccount.getProviderMchKey()))
					data.setProviderMchKey(providerAccount.getProviderMchKey());
				if (CommonUtils.isNotEmpty(providerAccount.getProviderAPPId()))
					data.setProviderAPPId(providerAccount.getProviderAPPId());
				if (CommonUtils.isNotEmpty(providerAccount.getProviderRSAKey()))
					data.setProviderRSAKey(providerAccount.getProviderRSAKey());
				if (CommonUtils.isNotEmpty(providerAccount.getState()))
					data.setState(providerAccount.getState());
				if (CommonUtils.isNotEmpty(providerAccount.getTotleAmtLimit()))
					data.setTotleAmtLimit(providerAccount.getTotleAmtLimit());
				if (CommonUtils.isNotEmpty(providerAccount.getMinAmt()))
					data.setMinAmt(providerAccount.getMinAmt());
				if (CommonUtils.isNotEmpty(providerAccount.getMaxAmt()))
					data.setMaxAmt(providerAccount.getMaxAmt());
				if (CommonUtils.isNotEmpty(providerAccount.getBeginTime()))
					data.setBeginTime(providerAccount.getBeginTime());
				if (CommonUtils.isNotEmpty(providerAccount.getEndTime()))
					data.setEndTime(providerAccount.getEndTime());
				if (CommonUtils.isNotEmpty(providerAccount.getRestrictState()))
					data.setRestrictState(providerAccount.getRestrictState());
				if (CommonUtils.isNotEmpty(providerAccount.getPercentage()))
					data.setPercentage(providerAccount.getPercentage());
				if (CommonUtils.isNotEmpty(providerAccount.getFeeRate()))
					data.setFeeRate(providerAccount.getFeeRate());
				if (CommonUtils.isNotEmpty(providerAccount.getCreateTime()))
					data.setCreateTime(providerAccount.getCreateTime());
				if (CommonUtils.isNotEmpty(providerAccount.getUpdateTime()))
					data.setUpdateTime(providerAccount.getUpdateTime());
				if (CommonUtils.isNotEmpty(providerAccount.getIsDel()))
					data.setIsDel(providerAccount.getIsDel());

				this.providerAccountService.modify(data);
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
	@RequestMapping("deleteProviderAccount")
	@RequiresPermissions("providerAccount:delete")
	public @ResponseBody ResponseBase deleteProviderAccount(String param) {
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
			this.providerAccountService.remove(ids);
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
	@RequestMapping("getProviderAccountById")
	@RequiresPermissions("providerAccount:view")
	public @ResponseBody ResponseBase getProviderAccountById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			ProviderAccount providerAccount = this.providerAccountService.searchById(id);
			if (null != providerAccount)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), providerAccount);
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