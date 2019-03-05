package cn.com.payment.v2.web.controller.sys;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.payment.v2.web.common.RespCodeEnum;
import cn.com.payment.v2.web.controller.context.BaseController;
import cn.com.payment.v2.web.dto.ResponseBase;
import cn.com.payment.v2.web.dto.TreeBean;
import cn.com.payment.v2.web.exceptions.BaseException;
import cn.com.payment.v2.web.model.Menu;
import cn.com.payment.v2.web.service.RoleAuthService;
import cn.com.payment.v2.web.utils.CommonUtils;

@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {
	private static final String PREFIX = "sys/";
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class.getName());
	@Autowired
	private RoleAuthService menuService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toMenuPage")
	@RequiresPermissions("menu:view")
	public String toMenuPage() {
		return PREFIX + "menu";
	}

	/**
	 * 获取树形数据(兼容easyui)
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/getAsyncTree")
	@RequiresPermissions("menu:view")
	public @ResponseBody ResponseBase getTreeMenu(Long parentId) {
		if (null == parentId)
			parentId = 0L;

		try {
			List<Menu> list = this.menuService.searchByProperty(new Menu(parentId));
			if (null != list && list.size() > 0) {
				for (Menu m : list) {
					if (null != m) {
						m.setState(null != m.getMenuType() && m.getMenuType() == 0 ? "closed" : "open");
					}
				}
				TreeBean<Menu> zt = new TreeBean<Menu>();
				zt.setTotal(list.size());
				zt.setRows(list);
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), zt);
			}
			return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "数据不存在");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	/**
	 * 获取属性列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getTreeList")
	@RequiresPermissions("menu:view")
	public @ResponseBody ResponseBase getTreeList(Long id) {
		if (null == id)
			id = 0L;

		try {
			List<Menu> list = this.menuService.searchByProperty(new Menu(id));
			if (null != list && list.size() > 0) {
				for (Menu m : list) {
					if (null != m) {
						m.setState(null != m.getMenuType() && m.getMenuType() == 0 ? "closed" : "open");
					}
				}
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), list);
			}
			return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "数据不存在");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	/**
	 * 获取树形菜单(childrens)
	 * 
	 * @return
	 */
	@RequestMapping("/getTreeMenu")
	@RequiresPermissions("menu:view")
	public @ResponseBody ResponseBase getTreeMenu() {
		try {
			List<Menu> list = this.menuService.searchTreeMenu(new Menu(0L));
			if (null != list && list.size() > 0) {
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), list);
			}
			return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "数据不存在");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	/**
	 * 新建数据
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping("addMenu")
	@RequiresPermissions("menu:add")
	public @ResponseBody ResponseBase addMenu(Menu menu) {
		if (CommonUtils.isEmpty(menu))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			this.menuService.create(menu);
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
	@RequestMapping("updateMenu")
	@RequiresPermissions("menu:update")
	public @ResponseBody ResponseBase updateMenu(Menu menu) {
		if (null == menu || CommonUtils.isEmpty(menu.getId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			Menu data = this.menuService.searchById(menu.getId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(menu.getName()))
					data.setName(menu.getName());
				// if(CommonUtils.isNotEmpty(menu.getMenuType()))data.setMenuType(menu.getMenuType());
				if (CommonUtils.isNotEmpty(menu.getUrl()))
					data.setUrl(menu.getUrl());
				if (CommonUtils.isNotEmpty(menu.getPermission()))
					data.setPermission(menu.getPermission());
				if (CommonUtils.isNotEmpty(menu.getSort()))
					data.setSort(menu.getSort());
				if (CommonUtils.isNotEmpty(menu.getTarget()))
					data.setTarget(menu.getTarget());
				if (CommonUtils.isNotEmpty(menu.getDesc()))
					data.setDesc(menu.getDesc());

				this.menuService.modify(data);
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
	@RequestMapping(value = "deleteMenu", produces = "application/json")
	@RequiresPermissions("menu:delete")
	public @ResponseBody ResponseBase deleteMenu(String param) {
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
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "参数格式错误");
		}

		try {
			this.menuService.remove(ids);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), "成功");
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
	@RequestMapping("getMenuById")
	@RequiresPermissions("menu:view")
	public @ResponseBody ResponseBase getMenuById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			Menu menu = this.menuService.searchById(id);
			if (null != menu)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), menu);
			else
				return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "数据不存在");
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	// ************************************************************************************
	/**
	 * 获取角色菜单列表
	 * 
	 * @param roleId
	 * @param menuType
	 * @param parentId
	 * @return
	 */
	@RequestMapping("getRoleMenu")
	/* @RequiresPermissions("menu:view") */
	public @ResponseBody ResponseBase getRoleMenu(Long roleId, Integer menuType, Long parentId) {
		try {
			Menu menu = new Menu();
			if (CommonUtils.isNotEmpty(roleId))
				menu.setRoleId(roleId);
			else {
				menu.setRoleIds(getLoginUserRoleIds());
			}
			if (null != menuType)
				menu.setMenuType(menuType);
			if (null != parentId)
				menu.setParentId(parentId);

			List<Menu> list = this.menuService.searchRoleMenu(menu);
			if (null != list && list.size() > 0)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), list);
			else
				return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "数据不存在");
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	/**
	 * 获取角色属性菜单
	 * 
	 * @param roleId
	 * @param menuType
	 * @param parentId
	 * @return
	 */
	@RequestMapping("getRoleMenuTree")
	/* @RequiresPermissions("menu:view") */
	public @ResponseBody ResponseBase getRoleMenuTree(Long roleId, Integer menuType, Long parentId) {
		try {
			Menu menu = new Menu();
			if (CommonUtils.isNotEmpty(roleId))
				menu.setRoleId(roleId);
			else {
				menu.setRoleIds(getLoginUserRoleIds());
			}
			if (null != menuType)
				menu.setMenuType(menuType);
			if (null != parentId)
				menu.setParentId(parentId);

			List<Menu> list = this.menuService.searchRoleMenuTree(menu);
			if (null != list && list.size() > 0)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), list);
			else
				return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "数据不存在");
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}
}