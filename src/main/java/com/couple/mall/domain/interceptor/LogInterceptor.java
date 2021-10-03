package com.couple.mall.domain.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request -> WAS -> Filter -> Servlet -> Spring Intercepter -> Controller
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String UUID = java.util.UUID.randomUUID().toString();
        request.setAttribute("logId", UUID);    // 싱글톤으로 만들어지기 때문에 servletRequest에 담아둬야한다.

        /**
         * @RequestMapping: HandlerMethod
         * 정적 리소스: ResourceHttpRequestHandler
         */
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
        }
        log.info("REQUEST [{}][{}][{}][{}]", UUID, requestURI, request.getDispatcherType() ,handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandler [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String UUID = (String) request.getAttribute("logId");
        log.info("RESPONSE [{}][{}]", UUID, requestURI);
        if(ex != null){
            log.error("afterCompletion ERROR", ex);
        }
    }
}
