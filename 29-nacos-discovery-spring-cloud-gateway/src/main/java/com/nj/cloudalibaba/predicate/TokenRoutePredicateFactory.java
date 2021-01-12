package com.nj.cloudalibaba.predicate;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;


/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/12 23:11
 */
@Slf4j
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenConfig> {

    public TokenRoutePredicateFactory() {
        super(TokenConfig.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("token");
    }

    @Override
    public Predicate<ServerWebExchange> apply(TokenConfig tokenConfig) {
        // (T t) -> true
        return exchange -> {
            MultiValueMap<String, String> valueMap = exchange.getRequest().getQueryParams();

            boolean flag = false;

            List<String> list = new ArrayList<>();

            valueMap.forEach((k, v) -> {
                list.addAll(v);
            });

            for (String s : list) {
                log.info("Token -> {} ", s);
                if (StringUtils.equalsIgnoreCase(s, tokenConfig.getToken())) {
                    flag = true;
                    break;
                }
            }
            return flag;
        };
    }
}
