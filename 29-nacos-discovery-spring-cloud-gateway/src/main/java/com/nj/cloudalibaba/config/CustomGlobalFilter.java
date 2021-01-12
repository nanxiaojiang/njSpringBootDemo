package com.nj.cloudalibaba.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 南江
 * @Description: 自定义全局Filter
 * @date 2021/1/13 0:16
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("自定义全局Filter......");

        MultiValueMap<String, String> valueMap = exchange.getRequest().getQueryParams();

        valueMap.forEach((k,v) -> {
            log.info("全局filter参数： ,{}",k);
            v.forEach( s -> {
                log.info("全局filter参数值：,{}",s);
            });
        });
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
