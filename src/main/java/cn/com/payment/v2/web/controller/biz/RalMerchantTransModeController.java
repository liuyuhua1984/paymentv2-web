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
import cn.com.payment.v2.web.model.RalMerchantTransMode;
import cn.com.payment.v2.web.service.RalMerchantTransModeService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("ralMerchantTransMode")
public class RalMerchantTransModeController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(RalMerchantTransModeController.class.getName());
	@Autowired
	private RalMerchantTransModeService ralMerchantTransModeService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toRalMerchantTransModePage")
	@RequiresPermissions("ralMerchantTransMode:view")
	public String toRalMerchantTransModePage() {
		return PREFIX + "ralMerchantTransMode";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getRalMerchantTransModePage")
	@RequiresPermissions("ralMerchantTransMode:view")
	public @ResponseBody ResponseBase getRalMerchantTransModePage(PageEasyUi<RalMerchantTransMode> page,
			RalMerchantTransMode entity) {
		PageEasyUi<RalMerchantTransMode> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<RalMerchantTransMode> list = this.ralMerchantTransModeService.searchByProperty(entity);
			result = new PageEasyUi<RalMerchantTransMode>(list);
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
	 * @param ralMerchantTransMode
	 * @return
	 */
	@RequestMapping("addRalMerchantTransMode")
	@RequiresPermissions("ralMerchantTransMode:add")
	public @ResponseBody ResponseBase addRalMerchantTransMode(RalMerchantTransMode ralMerchantTransMode) {
		if (CommonUtils.isEmpty(ralMerchantTransMode))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.ralMerchantTransModeService.create(ralMerchantTransMode);
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
	@RequestMapping("updateRalMerchantTransMode")
	@RequiresPermissions("ralMerchantTransMode:update")
	public @ResponseBody ResponseBase updateRalMerchantTransMode(RalMerchantTransMode ralMerchantTransMode) {
		if (CommonUtils.isEmpty(ralMerchantTransMode)
				|| CommonUtils.isEmpty(ralMerchantTransMode.getRalMerTransModeId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			RalMerchantTransMode data = this.ralMerchantTransModeService
					.searchById(ralMerchantTransMode.getRalMerTransModeId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getRalMerTransModeId()))
					data.setRalMerTransModeId(ralMerchantTransMode.getRalMerTransModeId());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getMchId()))
					data.setMchId(ralMerchantTransMode.getMchId());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getTransModeId()))
					data.setTransModeId(ralMerchantTransMode.getTransModeId());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getFeeRate()))
					data.setFeeRate(ralMerchantTransMode.getFeeRate());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getTotleAmtLimit()))
					data.setTotleAmtLimit(ralMerchantTransMode.getTotleAmtLimit());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getMinAmt()))
					data.setMinAmt(ralMerchantTransMode.getMinAmt());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getMaxAmt()))
					data.setMaxAmt(ralMerchantTransMode.getMaxAmt());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getBeginTime()))
					data.setBeginTime(ralMerchantTransMode.getBeginTime());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getEndTime()))
					data.setEndTime(ralMerchantTransMode.getEndTime());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getCreateTime()))
					data.setCreateTime(ralMerchantTransMode.getCreateTime());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getUpdateTime()))
					data.setUpdateTime(ralMerchantTransMode.getUpdateTime());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getState()))
					data.setState(ralMerchantTransMode.getState());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getIsDel()))
					data.setIsDel(ralMerchantTransMode.getIsDel());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getRestrictState()))
					data.setRestrictState(ralMerchantTransMode.getRestrictState());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getValidIP()))
					data.setValidIP(ralMerchantTransMode.getValidIP());
				if (CommonUtils.isNotEmpty(ralMerchantTransMode.getSettleType()))
					data.setSettleType(ralMerchantTransMode.getSettleType());

				this.ralMerchantTransModeService.modify(data);
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
	@RequestMapping("deleteRalMerchantTransMode")
	@RequiresPermissions("ralMerchantTransMode:delete")
	public @ResponseBody ResponseBase deleteRalMerchantTransMode(String param) {
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
			this.ralMerchantTransModeService.remove(ids);
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
	@RequestMapping("getRalMerchantTransModeById")
	@RequiresPermissions("ralMerchantTransMode:view")
	public @ResponseBody ResponseBase getRalMerchantTransModeById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			RalMerchantTransMode ralMerchantTransMode = this.ralMerchantTransModeService.searchById(id);
			if (null != ralMerchantTransMode)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),
						ralMerchantTransMode);
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