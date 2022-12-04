package com.lizza.order.interceptor;

import com.lizza.base.common.tracer.Tracer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lizza.base.common.util.Constants.SERVER_TYPE;

public class CustomInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // to-do 从请求头中获取并设置值
        String serverType = request.getParameter(SERVER_TYPE);
        Tracer.setServerType(serverType);
        return super.preHandle(request, response, handler);
    }
}
