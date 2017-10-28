package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

/**
 */
@Component
public class CheckSystemBeforeRoute extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(CheckSystemBeforeRoute.class);
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private ProxyRequestHelper requestHelper;

    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getRequest().getRequestURI().contains("/second/answer");
    }

    @Override
    public Object run() {
        ResponseEntity response = restTemplate.exchange("http://127.0.0.1:8090/check?value=" + RequestContext.getCurrentContext().getRequest().getParameter("value"),
                HttpMethod.GET,
                null,
                Object.class);
        return true;
    }
}
