package cn.com.payment.v2.web.controller.sys;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.payment.v2.web.annotation.SystemControllerLog;
import cn.com.payment.v2.web.common.RespCodeEnum;
import cn.com.payment.v2.web.dto.PageEasyUi;
import cn.com.payment.v2.web.dto.ResponseBase;
import cn.com.payment.v2.web.exceptions.BaseException;
import cn.com.payment.v2.web.model.Role;
import cn.com.payment.v2.web.service.AdminRoleService;
import cn.com.payment.v2.web.service.RoleAuthService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("role")
public class RoleController {
	private static final String PREFIX = "sys/";
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class.getName());
	@Autowired
	private AdminRoleService roleService;
	@Autowired
	private RoleAuthService roleAuthService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toRolePage")
	@RequiresPermissions("role:view")
	public String toRolePage() {
		return PREFIX + "role";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("getRolePage")
	@RequiresPermissions("role:view")
	public @ResponseBody ResponseBase getRolePage(Integer page ,Integer rows, Role entity) {
		PageEasyUi<Role> result = null;
		try {
			PageUtils.initPages(page, rows);
			List<Role> list = this.roleService.searchRolePage(entity);
			result = new PageEasyUi<Role>(list);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), "成功", result);
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
	 * @param role
	 * @return
	 */
	@SystemControllerLog(description="添加角色",type="2")
	@RequestMapping("addRole")
	@RequiresPermissions("role:add")
	public @ResponseBody ResponseBase addRole(Role role) {
		if (CommonUtils.isEmpty(role))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.roleService.createRole(role);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), "创建用户成功");
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
	@SystemControllerLog(description="更新角色",type="2")
	@RequestMapping("updateRole")
	@RequiresPermissions("role:update")
	public @ResponseBody ResponseBase updateRole(Role role) {
		if (null == role || CommonUtils.isEmpty(role.getId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.roleService.updateRole(role);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), "创建用户成功");
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
	@SystemControllerLog(description="删除角色",type="2")
	@RequestMapping("deleteRole")
	@RequiresPermissions("role:delete")
	public @ResponseBody ResponseBase deleteRole(String param) {
		if (CommonUtils.isEmpty(param))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		Long[] ids = null;
		try {
			String[] params = param.split(",");
			ids = new Long[params.length];
			for (int i = 0, len = params.length; i < len; i++) {
				if (!"1".equals(params[i])&&!"28".equals(params[i])&&!"29".equals(params[i])) {// 不允許刪除超級管理員,机构、部门管理员
					ids[i] = Long.valueOf(params[i]);
				}
			}
		} catch (NumberFormatException e1) {
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "参数格式错误");
		}
		try {
			this.roleService.deleteCascadeRole(ids);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), "创建用户成功");
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
	@RequestMapping("getRoleById")
	@RequiresPermissions("role:view")
	public @ResponseBody ResponseBase getRoleById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			Role role = this.roleService.searchRoleById(id);
			if (null != role)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(),role);
			else
				return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "数据不存在");
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}
	@SystemControllerLog(description="分配菜单",type="2")
	@RequestMapping("/addRoleAuth")
	@RequiresPermissions("role:addRoleAuth")
	public @ResponseBody ResponseBase addRoleAuth(Long roleId, String roles) {
		if (CommonUtils.isEmpty(roleId, roles))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			String[] params = roles.split(",");
			Long[] ids = new Long[params.length];
			for (int i = 0, len = params.length; i < len; i++) {
				ids[i] = Long.valueOf(params[i]);
			}

			this.roleAuthService.createRoleAuth(roleId, ids);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg());
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}
}