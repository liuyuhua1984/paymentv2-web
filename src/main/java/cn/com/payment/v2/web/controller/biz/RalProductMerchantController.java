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
import cn.com.payment.v2.web.model.RalProductMerchant;
import cn.com.payment.v2.web.service.RalProductMerchantService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("ralProductMerchant")
public class RalProductMerchantController {
	private static final String PREFIX = "bas/";
	private static final Logger logger = LoggerFactory.getLogger(RalProductMerchantController.class.getName());
	@Autowired
	private RalProductMerchantService ralProductMerchantService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toRalProductMerchantPage")
	@RequiresPermissions("ralProductMerchant:view")
	public String toRalProductMerchantPage() {
		return PREFIX + "ralProductMerchant";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getRalProductMerchantPage")
	@RequiresPermissions("ralProductMerchant:view")
	public @ResponseBody ResponseBase getRalProductMerchantPage(PageEasyUi<RalProductMerchant> page,
			RalProductMerchant entity) {
		PageEasyUi<RalProductMerchant> result = null;
		try {
			PageUtils.initPages(page.getPageNumber(), page.getPageSize());
			List<RalProductMerchant> list = this.ralProductMerchantService.searchByProperty(entity);
			result = new PageEasyUi<RalProductMerchant>(list);
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
	 * @param ralProductMerchant
	 * @return
	 */
	@RequestMapping("addRalProductMerchant")
	@RequiresPermissions("ralProductMerchant:add")
	public @ResponseBody ResponseBase addRalProductMerchant(RalProductMerchant ralProductMerchant) {
		if (CommonUtils.isEmpty(ralProductMerchant))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.ralProductMerchantService.create(ralProductMerchant);
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
	@RequestMapping("updateRalProductMerchant")
	@RequiresPermissions("ralProductMerchant:update")
	public @ResponseBody ResponseBase updateRalProductMerchant(RalProductMerchant ralProductMerchant) {
		if (CommonUtils.isEmpty(ralProductMerchant) || CommonUtils.isEmpty(ralProductMerchant.getRalMerProductId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			RalProductMerchant data = this.ralProductMerchantService
					.searchById(ralProductMerchant.getRalMerProductId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(ralProductMerchant.getRalMerProductId()))
					data.setRalMerProductId(ralProductMerchant.getRalMerProductId());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getMchId()))
					data.setMchId(ralProductMerchant.getMchId());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getProductId()))
					data.setProductId(ralProductMerchant.getProductId());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getFeeRate()))
					data.setFeeRate(ralProductMerchant.getFeeRate());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getTotleAmtLimit()))
					data.setTotleAmtLimit(ralProductMerchant.getTotleAmtLimit());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getMinAmt()))
					data.setMinAmt(ralProductMerchant.getMinAmt());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getMaxAmt()))
					data.setMaxAmt(ralProductMerchant.getMaxAmt());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getBeginTime()))
					data.setBeginTime(ralProductMerchant.getBeginTime());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getEndTime()))
					data.setEndTime(ralProductMerchant.getEndTime());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getPercentage()))
					data.setPercentage(ralProductMerchant.getPercentage());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getCreateTime()))
					data.setCreateTime(ralProductMerchant.getCreateTime());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getUpdateTime()))
					data.setUpdateTime(ralProductMerchant.getUpdateTime());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getState()))
					data.setState(ralProductMerchant.getState());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getIsDel()))
					data.setIsDel(ralProductMerchant.getIsDel());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getValidIP()))
					data.setValidIP(ralProductMerchant.getValidIP());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getRestrictState()))
					data.setRestrictState(ralProductMerchant.getRestrictState());
				if (CommonUtils.isNotEmpty(ralProductMerchant.getSettleType()))
					data.setSettleType(ralProductMerchant.getSettleType());

				this.ralProductMerchantService.modify(data);
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
	@RequestMapping("deleteRalProductMerchant")
	@RequiresPermissions("ralProductMerchant:delete")
	public @ResponseBody ResponseBase deleteRalProductMerchant(String param) {
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
			this.ralProductMerchantService.remove(ids);
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
	@RequestMapping("getRalProductMerchantById")
	@RequiresPermissions("ralProductMerchant:view")
	public @ResponseBody ResponseBase getRalProductMerchantById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			RalProductMerchant ralProductMerchant = this.ralProductMerchantService.searchById(id);
			if (null != ralProductMerchant)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),
						ralProductMerchant);
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