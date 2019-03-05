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
import cn.com.payment.v2.web.model.RalProviderTransMode;
import cn.com.payment.v2.web.service.RalProviderTransModeService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("ralProviderTransMode")
public class RalProviderTransModeController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(RalProviderTransModeController.class.getName());
	@Autowired
	private RalProviderTransModeService ralProviderTransModeService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toRalProviderTransModePage")
	@RequiresPermissions("ralProviderTransMode:view")
	public String toRalProviderTransModePage() {
		return PREFIX + "ralProviderTransMode";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getRalProviderTransModePage")
	@RequiresPermissions("ralProviderTransMode:view")
	public @ResponseBody ResponseBase getRalProviderTransModePage(PageEasyUi<RalProviderTransMode> page,
			RalProviderTransMode entity) {
		PageEasyUi<RalProviderTransMode> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<RalProviderTransMode> list = this.ralProviderTransModeService.searchByProperty(entity);
			result = new PageEasyUi<RalProviderTransMode>(list);
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
	 * @param ralProviderTransMode
	 * @return
	 */
	@RequestMapping("addRalProviderTransMode")
	@RequiresPermissions("ralProviderTransMode:add")
	public @ResponseBody ResponseBase addRalProviderTransMode(RalProviderTransMode ralProviderTransMode) {
		if (CommonUtils.isEmpty(ralProviderTransMode))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.ralProviderTransModeService.create(ralProviderTransMode);
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
	@RequestMapping("updateRalProviderTransMode")
	@RequiresPermissions("ralProviderTransMode:update")
	public @ResponseBody ResponseBase updateRalProviderTransMode(RalProviderTransMode ralProviderTransMode) {
		if (CommonUtils.isEmpty(ralProviderTransMode) || CommonUtils.isEmpty(ralProviderTransMode.getProductId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			RalProviderTransMode data = this.ralProviderTransModeService
					.searchById(ralProviderTransMode.getProductId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getProductId()))
					data.setProductId(ralProviderTransMode.getProductId());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getParentId()))
					data.setParentId(ralProviderTransMode.getParentId());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getProType()))
					data.setProType(ralProviderTransMode.getProType());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getProviderName()))
					data.setProviderName(ralProviderTransMode.getProviderName());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getProductName()))
					data.setProductName(ralProviderTransMode.getProductName());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getTransModeId()))
					data.setTransModeId(ralProviderTransMode.getTransModeId());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getTransRecordPrefix()))
					data.setTransRecordPrefix(ralProviderTransMode.getTransRecordPrefix());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getServiceName()))
					data.setServiceName(ralProviderTransMode.getServiceName());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getMethodName()))
					data.setMethodName(ralProviderTransMode.getMethodName());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getParamClass()))
					data.setParamClass(ralProviderTransMode.getParamClass());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getDesc()))
					data.setDesc(ralProviderTransMode.getDesc());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getSubmitParamJson()))
					data.setSubmitParamJson(ralProviderTransMode.getSubmitParamJson());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getFeeRate()))
					data.setFeeRate(ralProviderTransMode.getFeeRate());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getState()))
					data.setState(ralProviderTransMode.getState());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getCreateTime()))
					data.setCreateTime(ralProviderTransMode.getCreateTime());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getUpdateTime()))
					data.setUpdateTime(ralProviderTransMode.getUpdateTime());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getTotleAmtLimit()))
					data.setTotleAmtLimit(ralProviderTransMode.getTotleAmtLimit());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getMinAmt()))
					data.setMinAmt(ralProviderTransMode.getMinAmt());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getMaxAmt()))
					data.setMaxAmt(ralProviderTransMode.getMaxAmt());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getRestrictState()))
					data.setRestrictState(ralProviderTransMode.getRestrictState());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getBeginTime()))
					data.setBeginTime(ralProviderTransMode.getBeginTime());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getEndTime()))
					data.setEndTime(ralProviderTransMode.getEndTime());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getValidIP()))
					data.setValidIP(ralProviderTransMode.getValidIP());
				if (CommonUtils.isNotEmpty(ralProviderTransMode.getIsDel()))
					data.setIsDel(ralProviderTransMode.getIsDel());

				this.ralProviderTransModeService.modify(data);
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
	@RequestMapping("deleteRalProviderTransMode")
	@RequiresPermissions("ralProviderTransMode:delete")
	public @ResponseBody ResponseBase deleteRalProviderTransMode(String param) {
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
			this.ralProviderTransModeService.remove(ids);
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
	@RequestMapping("getRalProviderTransModeById")
	@RequiresPermissions("ralProviderTransMode:view")
	public @ResponseBody ResponseBase getRalProviderTransModeById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			RalProviderTransMode ralProviderTransMode = this.ralProviderTransModeService.searchById(id);
			if (null != ralProviderTransMode)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),
						ralProviderTransMode);
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