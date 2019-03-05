package cn.com.payment.v2.web.controller.sys;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.payment.v2.web.annotation.SystemControllerLog;
import cn.com.payment.v2.web.common.RespCodeEnum;
import cn.com.payment.v2.web.contansts.Constants;
import cn.com.payment.v2.web.controller.context.PasswordHelper;
import cn.com.payment.v2.web.dto.IResponse;
import cn.com.payment.v2.web.dto.PageEasyUi;
import cn.com.payment.v2.web.dto.ResponseBase;
import cn.com.payment.v2.web.exceptions.BaseException;
import cn.com.payment.v2.web.model.Admin;
import cn.com.payment.v2.web.model.Role;
import cn.com.payment.v2.web.service.AdminRoleService;
import cn.com.payment.v2.web.utils.CommonUtils;
import cn.com.payment.v2.web.utils.PageUtils;

@Controller
@RequestMapping("admin")
public class AdminController {
	private static final String PREFIX = "sys/";
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class.getName());
	@Autowired
	private AdminRoleService adminService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("toAdminPage")
	@RequiresPermissions("admin:view")
	public String toAdminPage(Model model) {
		Role role = new Role();
		List<Role> roleList = null;
		try {
			roleList = adminService.searchRolePage(role);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (BaseException e) {
			e.printStackTrace();
		}
		model.addAttribute("roleList", roleList);
		return PREFIX + "admin";
	}

	/**
	 * 获取分页数据
	 * 
	 * @param page
	 * @return
	 */
	@SystemControllerLog(description="获取用户",type="0")
	@RequestMapping("getAdminPage")
	@RequiresPermissions("admin:view")
	public @ResponseBody ResponseBase getAdminPage(Integer page, Integer rows, Admin entity) {
		PageEasyUi<Admin> result = null;
		try {
			PageUtils.initPages(page, rows);
			List<Admin> list = this.adminService.searchByProperty(entity);
			result = new PageEasyUi<Admin>(list);
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
	 * @param admin
	 * @return
	 */
	@SystemControllerLog(description = "添加用户", type = "2")
	@RequestMapping("addAdmin")
	@RequiresPermissions("admin:add")
	public @ResponseBody ResponseBase addAdmin(Admin admin, String password, String repassword, String roleId) {
		if (CommonUtils.isEmpty(admin))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");
		if(CommonUtils.isEmpty(admin.getUserName()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求参数userName为空");
		if (CommonUtils.isEmpty(password))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "新密码为空");
		if (CommonUtils.isEmpty(repassword))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "确认密码为空");
		if (!password.equals(repassword))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "两次输入的密码不一致");
		if(CommonUtils.isEmpty(roleId))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求参数roleId为空");
		if(CommonUtils.isEmpty(admin.getCellPhone()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求参数cellPhone为空");
		try {
			admin.setPswd(password);
			new PasswordHelper().encryptPasswor(admin);
			this.adminService.createAdmin(admin);
			// 添加角色
			// 用户id
			Long[] uids = new Long[1];
			uids[0] = admin.getId();
			// 角色id
			String[] rparams = roleId.split(",");
			Long[] rids = new Long[rparams.length];
			for (int i = 0, len = rparams.length; i < len; i++) {
				rids[i] = Long.valueOf(rparams[i]);
			}
			this.adminService.createAdminRole(uids, rids);
			return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), "创建用户成功");
		} catch (BaseException e) {
			e.printStackTrace();
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
	@SystemControllerLog(description = "修改密码", type = "2")
	@RequestMapping("setPassWord")
	@RequiresPermissions("admin:setPassWord")
	public @ResponseBody ResponseBase setPassWord(String password, String repassword) {
		if (CommonUtils.isEmpty(password))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "新密码为空");
		if (CommonUtils.isEmpty(repassword))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "确认密码为空");
		if (!password.equals(repassword))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "两次输入的密码不一致");
		// session 获取用户身份
		Long adminId = (Long) SecurityUtils.getSubject().getSession().getAttribute("adminId");
		if (CommonUtils.isEmpty(adminId))
			return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD.getCode(), "非法请求");
		try {
			Admin data = this.adminService.searchById(adminId);
			if (CommonUtils.isNotEmpty(data)) {
				data.setPswd(password);
				new PasswordHelper().encryptPasswor(data);
				this.adminService.modify(data);
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
	 * 更新数据
	 * 
	 * @param entity
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description = "修改用户", type = "2")
	@RequestMapping("updateAdmin")
	public @ResponseBody ResponseBase updateAdmin(Admin admin) {
		if (null == admin || CommonUtils.isEmpty(admin.getId()))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");
		try {
			Admin data = this.adminService.searchById(admin.getId());
			if (CommonUtils.isNotEmpty(data)) {
				if (CommonUtils.isNotEmpty(admin.getName()))
					data.setName(admin.getName());
				if (CommonUtils.isNotEmpty(admin.getMateName()))
					data.setMateName(admin.getMateName());
				if (CommonUtils.isNotEmpty(admin.getGender()))
					data.setGender(admin.getGender());
				if (CommonUtils.isNotEmpty(admin.getCellPhone()))
					data.setCellPhone(admin.getCellPhone());
				if (CommonUtils.isNotEmpty(admin.getPhoto()))
					data.setPhoto(admin.getPhoto());
				if (CommonUtils.isNotEmpty(admin.getEmail()))
					data.setEmail(admin.getEmail());
				if (CommonUtils.isNotEmpty(admin.getState()))
					data.setState(admin.getState());
				if (CommonUtils.isNotEmpty(admin.getDuty()))
					data.setDuty(admin.getDuty());
				if (CommonUtils.isNotEmpty(admin.getLocked()))
					data.setLocked(admin.getLocked());
				if (CommonUtils.isNotEmpty(admin.getAccNo()))
					data.setAccNo(admin.getAccNo());
				if (CommonUtils.isNotEmpty(admin.getEmail()))
					data.setEmail(admin.getEmail());
				if (CommonUtils.isNotEmpty(admin.getAccounts()))
					data.setAccounts(admin.getAccounts());
				if (CommonUtils.isNotEmpty(admin.getRemark()))
					data.setRemark(admin.getRemark());
				if (CommonUtils.isNotEmpty(admin.getPswd())) {
					data.setPswd(admin.getPswd());
					new PasswordHelper().encryptPasswor(data);
				}
				if (CommonUtils.isNotEmpty(admin.getMerId())) {
					data.setMerId(admin.getMerId());
				}
				if (CommonUtils.isNotEmpty(admin.getMerAgentId())) {
					data.setMerAgentId(admin.getMerAgentId());
				}
				this.adminService.modify(data);
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
	@SystemControllerLog(description = "删除用户", type = "2")
	@RequestMapping("deleteAdmin")
	@RequiresPermissions("admin:delete")
	public @ResponseBody ResponseBase deleteAdmin(String param) {
		if (CommonUtils.isEmpty(param))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		Long[] ids = null;
		try {
			String[] params = param.split(",");
			ids = new Long[params.length];
			for (int i = 0, len = params.length; i < len; i++) {
				if (!"1".equals(params[i])) {// 不允許刪除超級管理員admin
					ids[i] = Long.valueOf(params[i]);
				}
			}
		} catch (NumberFormatException e1) {
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求参数格式错误");
		}

		try {
			this.adminService.remove(ids);
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
	@RequestMapping("getAdminById")
	@RequiresPermissions("admin:view")
	public @ResponseBody ResponseBase getAdminById(Long id) {
		if (CommonUtils.isEmpty(id))
			return new ResponseBase(RespCodeEnum.INVALID_REQUEST.getCode(), "请求数据为空");

		try {
			Admin admin = this.adminService.searchById(id);
			if (null != admin)
				return new ResponseBase(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg(), admin);
			else
				return new ResponseBase(RespCodeEnum.DATA_NOT_FONUD);
		} catch (BaseException e) {
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseBase(RespCodeEnum.SYSTEM_ERR.getCode(), "系统内部错误");
		}
	}

	// ******************************************* 分割线
	// ******************************************************
	/**
	 * 检测用户名是否存在
	 * 
	 * @param userName
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("checkAdminUserExists")
	@RequiresPermissions("admin:view")
	public @ResponseBody ResponseEntity checkAdminUserExists(String userName) {
		if (CommonUtils.isEmpty(userName))
			return new ResponseEntity<String>(Constants.EX_PARAM, HttpStatus.BAD_REQUEST);

		try {
			List<Admin> list = this.adminService.searchByProperty(new Admin(userName, false));
			if (null != list && list.size() > 0)
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 添加用户角色关联
	 * 
	 * @param users
	 * @param roles
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("addAdminRole")
	@RequiresPermissions("admin:addAdminRole")
	public @ResponseBody ResponseEntity addAdminRole(String users, String roles) {
		if (CommonUtils.isEmpty(users, roles))
			return new ResponseEntity<String>(Constants.EX_PARAM, HttpStatus.BAD_REQUEST);

		try {
			// 用户id
			String[] uparams = users.split(",");
			Long[] uids = new Long[uparams.length];
			for (int i = 0, len = uparams.length; i < len; i++) {
				uids[i] = Long.valueOf(uparams[i]);
			}
			// 角色id
			String[] rparams = roles.split(",");
			Long[] rids = new Long[rparams.length];
			for (int i = 0, len = rparams.length; i < len; i++) {
				rids[i] = Long.valueOf(rparams[i]);
			}
			this.adminService.createAdminRole(uids, rids);
			return new ResponseEntity<String>(Constants.PASS_OK, HttpStatus.OK);
		} catch (BaseException e) {
			return new ResponseEntity<String>(e.getCode(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<String>(Constants.EX_APP, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userNames
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("getAdmin")
//	@RequiresPermissions("admin:view")
	public @ResponseBody ResponseEntity getAdmin(final String userName) {
		if (CommonUtils.isEmpty(userName))
			return new ResponseEntity<String>(Constants.EX_PARAM, HttpStatus.BAD_REQUEST);

		try {
			List<Admin> list = this.adminService.searchAdminAndRole(new Admin(userName, false));
			if (null != list && list.size() > 0) {
				Admin admin = list.get(0);
				admin.setPswd(null);
				admin.setSalt(null);
				return new ResponseEntity<Admin>(list.get(0), HttpStatus.OK);
			} else
				return new ResponseEntity<String>(IResponse.DATA_NOT_FONUD, HttpStatus.NO_CONTENT);
		} catch (BaseException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<String>(Constants.EX_APP, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}