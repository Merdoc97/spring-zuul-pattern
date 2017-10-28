package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

/**
 */
@Component
public class CheckSystemBeforeRoute extends ZuulFilter {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${zuul.routes.system1.url}")
    private String firstSystemUrl;

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
        restTemplate.exchange(firstSystemUrl+"/check?value=" + RequestContext.getCurrentContext().getRequest().getParameter("value"),
                HttpMethod.GET,
                null,
                Object.class);
        return true;
    }
}
