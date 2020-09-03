package com.chenpt.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: chenpt
 * @Description:
 * @Date: Created in 2020-08-18 17:33
 * @Modified By:
 */
@Component
public class RequestOriginParserDefinition implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {

        String serviceName = request.getParameter("serviceName");

        return serviceName;
    }
}
