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
import cn.com.payment.v2.web.model.AccountStatement;
import cn.com.payment.v2.web.service.AccountStatementService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("accountStatement")
public class AccountStatementController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(AccountStatementController.class.getName());
	@Autowired
	private AccountStatementService accountStatementService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toAccountStatementPage")
	@RequiresPermissions("accountStatement:view")
	public String toAccountStatementPage() {
		return PREFIX + "accountStatement";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getAccountStatementPage")
	@RequiresPermissions("accountStatement:view")
	public @ResponseBody ResponseBase getAccountStatementPage(PageEasyUi<AccountStatement> page,
			AccountStatement entity) {
		PageEasyUi<AccountStatement> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<AccountStatement> list = this.accountStatementService.searchByProperty(entity);
			result = new PageEasyUi<AccountStatement>(list);
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
	 * @param accountStatement
	 * @return
	 */
	@RequestMapping("addAccountStatement")
	@RequiresPermissions("accountStatement:add")
	public @ResponseBody ResponseBase addAccountStatement(AccountStatement accountStatement) {
		if (CommonUtils.isEmpty(accountStatement))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.accountStatementService.create(accountStatement);
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
	@RequestMapping("updateAccountStatement")
	@RequiresPermissions("accountStatement:update")
	public @ResponseBody ResponseBase updateAccountStatement(AccountStatement accountStatement) {
		if (CommonUtils.isEmpty(accountStatement) || CommonUtils.isEmpty(accountStatement.getAdjustId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			AccountStatement data = this.accountStatementService.searchById(accountStatement.getAdjustId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(accountStatement.getAdjustId()))
					data.setAdjustId(accountStatement.getAdjustId());
				if (CommonUtils.isNotEmpty(accountStatement.getOperatorId()))
					data.setOperatorId(accountStatement.getOperatorId());
				if (CommonUtils.isNotEmpty(accountStatement.getAccountId()))
					data.setAccountId(accountStatement.getAccountId());
				if (CommonUtils.isNotEmpty(accountStatement.getSubAccType()))
					data.setSubAccType(accountStatement.getSubAccType());
				if (CommonUtils.isNotEmpty(accountStatement.getFinancialType()))
					data.setFinancialType(accountStatement.getFinancialType());
				if (CommonUtils.isNotEmpty(accountStatement.getAdjustType()))
					data.setAdjustType(accountStatement.getAdjustType());
				if (CommonUtils.isNotEmpty(accountStatement.getBeforBalance()))
					data.setBeforBalance(accountStatement.getBeforBalance());
				if (CommonUtils.isNotEmpty(accountStatement.getAfterBalance()))
					data.setAfterBalance(accountStatement.getAfterBalance());
				if (CommonUtils.isNotEmpty(accountStatement.getAdjustAmount()))
					data.setAdjustAmount(accountStatement.getAdjustAmount());
				if (CommonUtils.isNotEmpty(accountStatement.getState()))
					data.setState(accountStatement.getState());
				if (CommonUtils.isNotEmpty(accountStatement.getRemark()))
					data.setRemark(accountStatement.getRemark());
				if (CommonUtils.isNotEmpty(accountStatement.getCreateTime()))
					data.setCreateTime(accountStatement.getCreateTime());
				if (CommonUtils.isNotEmpty(accountStatement.getUpdateTime()))
					data.setUpdateTime(accountStatement.getUpdateTime());

				this.accountStatementService.modify(data);
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
	@RequestMapping("deleteAccountStatement")
	@RequiresPermissions("accountStatement:delete")
	public @ResponseBody ResponseBase deleteAccountStatement(String param) {
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
			this.accountStatementService.remove(ids);
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
	@RequestMapping("getAccountStatementById")
	@RequiresPermissions("accountStatement:view")
	public @ResponseBody ResponseBase getAccountStatementById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			AccountStatement accountStatement = this.accountStatementService.searchById(id);
			if (null != accountStatement)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),
						accountStatement);
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