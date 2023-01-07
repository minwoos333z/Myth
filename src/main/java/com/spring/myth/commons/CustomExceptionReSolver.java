package com.spring.myth.commons;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionReSolver  extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");

        if (handler != null) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            modelAndView.addObject("errorMethod", handlerMethod.getMethod().getName());
        }
        modelAndView.addObject("errorCause", ex.getCause());
        modelAndView.addObject("errorClass", ex.getClass().getSimpleName());
        modelAndView.addObject("errorMessage", ex.getMessage());

        return modelAndView;
    }
}
