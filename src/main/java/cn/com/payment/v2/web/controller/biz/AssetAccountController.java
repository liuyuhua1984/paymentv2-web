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
import cn.com.payment.v2.web.model.AssetAccount;
import cn.com.payment.v2.web.service.AssetAccountService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("assetAccount")
public class AssetAccountController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(AssetAccountController.class.getName());
	@Autowired
	private AssetAccountService assetAccountService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toAssetAccountPage")
	@RequiresPermissions("assetAccount:view")
	public String toAssetAccountPage() {
		return PREFIX + "assetAccount";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getAssetAccountPage")
	@RequiresPermissions("assetAccount:view")
	public @ResponseBody ResponseBase getAssetAccountPage(PageEasyUi<AssetAccount> page, AssetAccount entity) {
		PageEasyUi<AssetAccount> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<AssetAccount> list = this.assetAccountService.searchByProperty(entity);
			result = new PageEasyUi<AssetAccount>(list);
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
	 * @param assetAccount
	 * @return
	 */
	@RequestMapping("addAssetAccount")
	@RequiresPermissions("assetAccount:add")
	public @ResponseBody ResponseBase addAssetAccount(AssetAccount assetAccount) {
		if (CommonUtils.isEmpty(assetAccount))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.assetAccountService.create(assetAccount);
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
	@RequestMapping("updateAssetAccount")
	@RequiresPermissions("assetAccount:update")
	public @ResponseBody ResponseBase updateAssetAccount(AssetAccount assetAccount) {
		if (CommonUtils.isEmpty(assetAccount) || CommonUtils.isEmpty(assetAccount.getAccountId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			AssetAccount data = this.assetAccountService.searchById(assetAccount.getAccountId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(assetAccount.getAccountId()))
					data.setAccountId(assetAccount.getAccountId());
				if (CommonUtils.isNotEmpty(assetAccount.getAccType()))
					data.setAccType(assetAccount.getAccType());
				if (CommonUtils.isNotEmpty(assetAccount.getBalance()))
					data.setBalance(assetAccount.getBalance());
				if (CommonUtils.isNotEmpty(assetAccount.getBalanceUnaccounted()))
					data.setBalanceUnaccounted(assetAccount.getBalanceUnaccounted());
				if (CommonUtils.isNotEmpty(assetAccount.getBalanceFreeze()))
					data.setBalanceFreeze(assetAccount.getBalanceFreeze());
				if (CommonUtils.isNotEmpty(assetAccount.getBalanceValid()))
					data.setBalanceValid(assetAccount.getBalanceValid());
				if (CommonUtils.isNotEmpty(assetAccount.getCreateTime()))
					data.setCreateTime(assetAccount.getCreateTime());
				if (CommonUtils.isNotEmpty(assetAccount.getUpdateTime()))
					data.setUpdateTime(assetAccount.getUpdateTime());
				if (CommonUtils.isNotEmpty(assetAccount.getRemark()))
					data.setRemark(assetAccount.getRemark());
				if (CommonUtils.isNotEmpty(assetAccount.getIsDel()))
					data.setIsDel(assetAccount.getIsDel());

				this.assetAccountService.modify(data);
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
	@RequestMapping("deleteAssetAccount")
	@RequiresPermissions("assetAccount:delete")
	public @ResponseBody ResponseBase deleteAssetAccount(String param) {
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
			this.assetAccountService.remove(ids);
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
	@RequestMapping("getAssetAccountById")
	@RequiresPermissions("assetAccount:view")
	public @ResponseBody ResponseBase getAssetAccountById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			AssetAccount assetAccount = this.assetAccountService.searchById(id);
			if (null != assetAccount)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), assetAccount);
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