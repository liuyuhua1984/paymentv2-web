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
import cn.com.payment.v2.web.model.MerchantStatisticsDay;
import cn.com.payment.v2.web.service.MerchantStatisticsDayService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("merchantStatisticsDay")
public class MerchantStatisticsDayController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(MerchantStatisticsDayController.class.getName());
	@Autowired
	private MerchantStatisticsDayService merchantStatisticsDayService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toMerchantStatisticsDayPage")
	@RequiresPermissions("merchantStatisticsDay:view")
	public String toMerchantStatisticsDayPage() {
		return PREFIX + "merchantStatisticsDay";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getMerchantStatisticsDayPage")
	@RequiresPermissions("merchantStatisticsDay:view")
	public @ResponseBody ResponseBase getMerchantStatisticsDayPage(PageEasyUi<MerchantStatisticsDay> page,
			MerchantStatisticsDay entity) {
		PageEasyUi<MerchantStatisticsDay> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<MerchantStatisticsDay> list = this.merchantStatisticsDayService.searchByProperty(entity);
			result = new PageEasyUi<MerchantStatisticsDay>(list);
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
	 * @param merchantStatisticsDay
	 * @return
	 */
	@RequestMapping("addMerchantStatisticsDay")
	@RequiresPermissions("merchantStatisticsDay:add")
	public @ResponseBody ResponseBase addMerchantStatisticsDay(MerchantStatisticsDay merchantStatisticsDay) {
		if (CommonUtils.isEmpty(merchantStatisticsDay))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.merchantStatisticsDayService.create(merchantStatisticsDay);
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
	@RequestMapping("updateMerchantStatisticsDay")
	@RequiresPermissions("merchantStatisticsDay:update")
	public @ResponseBody ResponseBase updateMerchantStatisticsDay(MerchantStatisticsDay merchantStatisticsDay) {
		if (CommonUtils.isEmpty(merchantStatisticsDay) || CommonUtils.isEmpty(merchantStatisticsDay.getSdId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			MerchantStatisticsDay data = this.merchantStatisticsDayService.searchById(merchantStatisticsDay.getSdId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getSdId()))
					data.setSdId(merchantStatisticsDay.getSdId());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getMchId()))
					data.setMchId(merchantStatisticsDay.getMchId());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getTransModeId()))
					data.setTransModeId(merchantStatisticsDay.getTransModeId());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getSdYear()))
					data.setSdYear(merchantStatisticsDay.getSdYear());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getSdMouth()))
					data.setSdMouth(merchantStatisticsDay.getSdMouth());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getSdDay()))
					data.setSdDay(merchantStatisticsDay.getSdDay());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getPayCountTotal()))
					data.setPayCountTotal(merchantStatisticsDay.getPayCountTotal());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getPayAmountTotal()))
					data.setPayAmountTotal(merchantStatisticsDay.getPayAmountTotal());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getPayCountSucc()))
					data.setPayCountSucc(merchantStatisticsDay.getPayCountSucc());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getPayAmountSucc()))
					data.setPayAmountSucc(merchantStatisticsDay.getPayAmountSucc());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getRealAmount()))
					data.setRealAmount(merchantStatisticsDay.getRealAmount());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getTotleFee()))
					data.setTotleFee(merchantStatisticsDay.getTotleFee());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getRefundCount()))
					data.setRefundCount(merchantStatisticsDay.getRefundCount());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getRefundAmount()))
					data.setRefundAmount(merchantStatisticsDay.getRefundAmount());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getState()))
					data.setState(merchantStatisticsDay.getState());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getCreateTime()))
					data.setCreateTime(merchantStatisticsDay.getCreateTime());
				if (CommonUtils.isNotEmpty(merchantStatisticsDay.getUpdateTime()))
					data.setUpdateTime(merchantStatisticsDay.getUpdateTime());

				this.merchantStatisticsDayService.modify(data);
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
	@RequestMapping("deleteMerchantStatisticsDay")
	@RequiresPermissions("merchantStatisticsDay:delete")
	public @ResponseBody ResponseBase deleteMerchantStatisticsDay(String param) {
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
			this.merchantStatisticsDayService.remove(ids);
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
	@RequestMapping("getMerchantStatisticsDayById")
	@RequiresPermissions("merchantStatisticsDay:view")
	public @ResponseBody ResponseBase getMerchantStatisticsDayById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			MerchantStatisticsDay merchantStatisticsDay = this.merchantStatisticsDayService.searchById(id);
			if (null != merchantStatisticsDay)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),
						merchantStatisticsDay);
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