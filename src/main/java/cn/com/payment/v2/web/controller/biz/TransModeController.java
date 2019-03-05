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
import cn.com.payment.v2.web.model.TransMode;
import cn.com.payment.v2.web.service.TransModeService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("transMode")
public class TransModeController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(TransModeController.class.getName());
	@Autowired
	private TransModeService transModeService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toTransModePage")
	@RequiresPermissions("transMode:view")
	public String toTransModePage() {
		return PREFIX + "transMode";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getTransModePage")
	@RequiresPermissions("transMode:view")
	public @ResponseBody ResponseBase getTransModePage(PageEasyUi<TransMode> page, TransMode entity) {
		PageEasyUi<TransMode> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<TransMode> list = this.transModeService.searchByProperty(entity);
			result = new PageEasyUi<TransMode>(list);
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
	 * @param transMode
	 * @return
	 */
	@RequestMapping("addTransMode")
	@RequiresPermissions("transMode:add")
	public @ResponseBody ResponseBase addTransMode(TransMode transMode) {
		if (CommonUtils.isEmpty(transMode))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.transModeService.create(transMode);
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
	@RequestMapping("updateTransMode")
	@RequiresPermissions("transMode:update")
	public @ResponseBody ResponseBase updateTransMode(TransMode transMode) {
		if (CommonUtils.isEmpty(transMode) || CommonUtils.isEmpty(transMode.getTransModeId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			TransMode data = this.transModeService.searchById(transMode.getTransModeId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(transMode.getTransModeId()))
					data.setTransModeId(transMode.getTransModeId());
				if (CommonUtils.isNotEmpty(transMode.getTransModeCode()))
					data.setTransModeCode(transMode.getTransModeCode());
				if (CommonUtils.isNotEmpty(transMode.getTransModeName()))
					data.setTransModeName(transMode.getTransModeName());
				if (CommonUtils.isNotEmpty(transMode.getState()))
					data.setState(transMode.getState());
				if (CommonUtils.isNotEmpty(transMode.getRemark()))
					data.setRemark(transMode.getRemark());
				if (CommonUtils.isNotEmpty(transMode.getCreateTime()))
					data.setCreateTime(transMode.getCreateTime());
				if (CommonUtils.isNotEmpty(transMode.getUpdateTime()))
					data.setUpdateTime(transMode.getUpdateTime());

				this.transModeService.modify(data);
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
	@RequestMapping("deleteTransMode")
	@RequiresPermissions("transMode:delete")
	public @ResponseBody ResponseBase deleteTransMode(String param) {
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
			this.transModeService.remove(ids);
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
	@RequestMapping("getTransModeById")
	@RequiresPermissions("transMode:view")
	public @ResponseBody ResponseBase getTransModeById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			TransMode transMode = this.transModeService.searchById(id);
			if (null != transMode)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), transMode);
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