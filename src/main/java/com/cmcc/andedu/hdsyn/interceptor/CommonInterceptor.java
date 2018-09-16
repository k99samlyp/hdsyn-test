package com.cmcc.andedu.hdsyn.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * Created by LY on 2015/11/19.
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    private long preTime = 0l;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        preTime = System.currentTimeMillis();
        request.setAttribute("preTime", preTime);
        logger.info("contextPATH:{},servletPath:{},URI:{},URL:{},sessionID{}",new String[]{request.getContextPath(),request.getServletPath(),request.getRequestURI(),request.getRequestURL().toString(),request.getSession().getId()});
        String requestPath=new UrlPathHelper().getLookupPathForRequest(request);
        if(handler.getClass().equals(HandlerMethod.class)){
//            String invokeControllerClassName=((HandlerMethod)handler).getBeanType().toString();
//            String invokeControllerMethodName=((HandlerMethod)handler).getMethod().getName();
//            String invokeControllerFullMethodSign=handler.toString();
            logger.info("************请求路径************："+requestPath);
            Map<String,String[]> map = request.getParameterMap();
            for(String name:map.keySet()){
                String[] values = map.get(name);
                logger.info("************请求参数："+name+"="+ Arrays.toString(values));
            }
        }
        return true;
    }
}