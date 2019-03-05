package cn.com.payment.v2.web.config;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.payment.v2.web.common.RespCodeEnum;
import cn.com.payment.v2.web.dto.ResponseBase;
import cn.com.payment.v2.web.utils.JsonUtils;

public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger logger = LoggerFactory.getLogger(ShiroFormAuthenticationFilter.class.getName());

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		if (isLoginRequest(request, response)) {//
		String ajaxHeader = httpServletRequest.getHeader("client");
		if (!"app".equals(ajaxHeader)) {
//		if (!isAjax(httpServletRequest)) {
			if (isLoginSubmission(request, response)) {
				if (logger.isTraceEnabled()) {
					logger.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(request, response);
			} else {
				if (logger.isTraceEnabled()) {
					logger.trace("Login page view.");
				}
				// allow them to see the login page ;)
//				return true;
				return executeLogin(request, response);
			}
		} else {
			if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
				httpServletResponse.setStatus(HttpStatus.OK.value());
				return true;
			}
			if (logger.isTraceEnabled()) {
				logger.trace("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}
			// 前端Ajax请求时requestHeader里面带一些参数，用于判断是否是前端的请求
			// 前端Ajax请求，则不会重定向
			httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpServletResponse.setContentType("application/json; charset=utf-8");
			httpServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = httpServletResponse.getWriter();
			out.println(JsonUtils.to(new ResponseBase(RespCodeEnum.ERROR_EXPIRED)));
			out.flush();
			out.close();
			return false;
		}
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		issueSuccessRedirect(request, response);
		return false;
	}

	/**
	 * 判断ajax请求
	 * 
	 * @param request
	 * @return
	 */
	boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
}