package framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截校验用户登录状态
 * 
 * @author XiangZhuRui
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info(request.getMethod() + ":" + request.getRequestURL());
		// 获取url地址
		String reqUrl = request.getRequestURI().replace(request.getContextPath(), "");
		// 当url地址为登录的url的时候跳过拦截器
		if (reqUrl.contains("/login.html")) {
			return true;
		} else {
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("user");
			if (obj == null || "".equals(obj.toString())) {
				response.sendRedirect("/error");
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
