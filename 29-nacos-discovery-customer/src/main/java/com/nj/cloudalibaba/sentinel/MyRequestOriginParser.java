package com.nj.cloudalibaba.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {

        String origin = request.getParameter("origin");

        /*if (StringUtils.isBlank(origin)) {
            throw new IllegalArgumentException("origin参数未指定");
        }*/

        return origin;
    }
}
