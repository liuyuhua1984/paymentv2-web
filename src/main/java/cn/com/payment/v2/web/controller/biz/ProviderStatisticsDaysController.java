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
import cn.com.payment.v2.web.model.ProviderStatisticsDays;
import cn.com.payment.v2.web.service.ProviderStatisticsDaysService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("providerStatisticsDays")
public class ProviderStatisticsDaysController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(ProviderStatisticsDaysController.class.getName());
	@Autowired
	private ProviderStatisticsDaysService providerStatisticsDaysService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toProviderStatisticsDaysPage")
	@RequiresPermissions("providerStatisticsDays:view")
	public String toProviderStatisticsDaysPage() {
		return PREFIX + "providerStatisticsDays";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getProviderStatisticsDaysPage")
	@RequiresPermissions("providerStatisticsDays:view")
	public @ResponseBody ResponseBase getProviderStatisticsDaysPage(PageEasyUi<ProviderStatisticsDays> page,
			ProviderStatisticsDays entity) {
		PageEasyUi<ProviderStatisticsDays> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<ProviderStatisticsDays> list = this.providerStatisticsDaysService.searchByProperty(entity);
			result = new PageEasyUi<ProviderStatisticsDays>(list);
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
	 * @param providerStatisticsDays
	 * @return
	 */
	@RequestMapping("addProviderStatisticsDays")
	@RequiresPermissions("providerStatisticsDays:add")
	public @ResponseBody ResponseBase addProviderStatisticsDays(ProviderStatisticsDays providerStatisticsDays) {
		if (CommonUtils.isEmpty(providerStatisticsDays))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.providerStatisticsDaysService.create(providerStatisticsDays);
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
	@RequestMapping("updateProviderStatisticsDays")
	@RequiresPermissions("providerStatisticsDays:update")
	public @ResponseBody ResponseBase updateProviderStatisticsDays(ProviderStatisticsDays providerStatisticsDays) {
		if (CommonUtils.isEmpty(providerStatisticsDays) || CommonUtils.isEmpty(providerStatisticsDays.getSdId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			ProviderStatisticsDays data = this.providerStatisticsDaysService
					.searchById(providerStatisticsDays.getSdId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getSdId()))
					data.setSdId(providerStatisticsDays.getSdId());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getTransModeId()))
					data.setTransModeId(providerStatisticsDays.getTransModeId());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getProductId()))
					data.setProductId(providerStatisticsDays.getProductId());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getProviderAccId()))
					data.setProviderAccId(providerStatisticsDays.getProviderAccId());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getSdYear()))
					data.setSdYear(providerStatisticsDays.getSdYear());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getSdMouth()))
					data.setSdMouth(providerStatisticsDays.getSdMouth());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getSdDay()))
					data.setSdDay(providerStatisticsDays.getSdDay());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getPayCountTotal()))
					data.setPayCountTotal(providerStatisticsDays.getPayCountTotal());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getPayAmountTotal()))
					data.setPayAmountTotal(providerStatisticsDays.getPayAmountTotal());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getPayCountSucc()))
					data.setPayCountSucc(providerStatisticsDays.getPayCountSucc());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getPayAmountSucc()))
					data.setPayAmountSucc(providerStatisticsDays.getPayAmountSucc());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getRealAmount()))
					data.setRealAmount(providerStatisticsDays.getRealAmount());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getTotleFee()))
					data.setTotleFee(providerStatisticsDays.getTotleFee());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getRefundCount()))
					data.setRefundCount(providerStatisticsDays.getRefundCount());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getRefundAmount()))
					data.setRefundAmount(providerStatisticsDays.getRefundAmount());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getState()))
					data.setState(providerStatisticsDays.getState());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getCreateTime()))
					data.setCreateTime(providerStatisticsDays.getCreateTime());
				if (CommonUtils.isNotEmpty(providerStatisticsDays.getUpdateTime()))
					data.setUpdateTime(providerStatisticsDays.getUpdateTime());

				this.providerStatisticsDaysService.modify(data);
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
	@RequestMapping("deleteProviderStatisticsDays")
	@RequiresPermissions("providerStatisticsDays:delete")
	public @ResponseBody ResponseBase deleteProviderStatisticsDays(String param) {
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
			this.providerStatisticsDaysService.remove(ids);
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
	@RequestMapping("getProviderStatisticsDaysById")
	@RequiresPermissions("providerStatisticsDays:view")
	public @ResponseBody ResponseBase getProviderStatisticsDaysById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			ProviderStatisticsDays providerStatisticsDays = this.providerStatisticsDaysService.searchById(id);
			if (null != providerStatisticsDays)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),
						providerStatisticsDays);
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