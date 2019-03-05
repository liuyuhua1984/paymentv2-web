package cn.com.payment.v2.web.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.com.payment.v2.web.common.RespCodeEnum;
import cn.com.payment.v2.web.controller.sys.AdminController;
import cn.com.payment.v2.web.dto.ResponseBase;
import cn.com.payment.v2.web.utils.JsonUtils;

/**
 * Created by Administrator on 2017/12/11. 全局异常处理
 */
public class MyExceptionHandler implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class.getName());

	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception ex) {
		ModelAndView mv = new ModelAndView();
		httpServletResponse.setStatus(HttpStatus.OK.value()); // 设置状态码
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
		httpServletResponse.setCharacterEncoding("UTF-8"); // 避免乱码
		httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			httpServletResponse.getWriter().write(JsonUtils.to(new ResponseBase(RespCodeEnum.ERROR_EXPIRED)));
		} catch (IOException e) {
			logger.error("与客户端通讯异常:" + e.getMessage(), e);
		}
//        FastJsonJsonView view = new FastJsonJsonView();
//        Map<String, Object> attributes = new HashMap<String, Object>();
//        if (ex instanceof UnauthenticatedException) {
//            attributes.put("code", "1000001");
//            attributes.put("msg", "token错误");
//        } else if (ex instanceof UnauthorizedException) {
//            attributes.put("code", "1000002");
//            attributes.put("msg", "用户无权限");
//        } else {
//            attributes.put("code", "1000003");
//            attributes.put("msg", ex.getMessage());
//        }
// 
//        view.setAttributesMap(attributes);
//        mv.setView(view);
		return mv;
	}
}