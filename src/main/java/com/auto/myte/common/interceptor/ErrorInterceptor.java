package com.auto.myte.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 */
public class ErrorInterceptor implements HandlerInterceptor {

    /**
     * Controller処理前呼び出す
     * preHandle
     * true 次の処理を行う、falseキャンセル
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }

    /**
     * Controller実行あと処理を行う
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
//        if (response.getStatus() == 500) {
//            modelAndView.setViewName("/errorpage/500");
//        } else if (response.getStatus() == 404) {
//            modelAndView.setViewName("errorpage/404");
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
