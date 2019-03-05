package cn.com.payment.v2.web.controller.sys;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.payment.v2.web.annotation.SystemControllerLog;
import cn.com.payment.v2.web.common.RespCodeEnum;
import cn.com.payment.v2.web.dto.ResponseBase;

@Controller
public class Snippet {
	/**
	 * 登录方法
	 * 
	 * @param userInfo
	 * @return
	 */
	@SystemControllerLog(description="用户登录",type="0")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBase ajaxLogin(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		ResponseBase resp = new ResponseBase(RespCodeEnum.SUCCESS);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			subject.login(token);
			map.put("token", subject.getSession().getId());
			resp.setMsg("登录成功");
			resp.setData(map);
		} catch (IncorrectCredentialsException e) {
			resp.setCode(RespCodeEnum.ERROR_AUTH.getCode());
			resp.setMsg("密码错误");
		} catch (LockedAccountException e) {
			resp.setCode(RespCodeEnum.ERROR_AUTH.getCode());
			resp.setMsg("登录失败，该用户已被冻结");
		} catch (AuthenticationException e) {
			resp.setCode(RespCodeEnum.DATA_NOT_FONUD.getCode());
			resp.setMsg("该用户不存在");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
	 * 
	 * @return
	 */
	@SystemControllerLog(description="用户未登录",type="0")
	@RequestMapping(value = "/unauth")
	@ResponseBody
	public ResponseBase unauth() {
		ResponseBase resp = new ResponseBase(RespCodeEnum.ERROR_EXPIRED);
		return resp;
	}
}
