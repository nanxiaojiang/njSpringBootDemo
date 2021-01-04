package com.nj.springcloud.controller;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南江
 * @Description: 自定义全局 error 错误页面第二种方式实现ErrorController
 * @date 2021/1/4 23:26
 */
@RestController
public class ErrorHandlerController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Object error(){
        RequestContext context = RequestContext.getCurrentContext();
        ZuulException exception = (ZuulException)context.getThrowable();
        return exception.nStatusCode + "===" + exception.getMessage();
    }
}
