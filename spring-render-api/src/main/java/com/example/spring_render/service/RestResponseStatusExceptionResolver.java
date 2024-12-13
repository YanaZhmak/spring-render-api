package com.example.spring_render.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@Service
public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) {
        logger.error("Error during api call", ex);

        response.setStatus(400);
        response.addHeader("error", ex.getMessage());

        return new ModelAndView();
    }
}
