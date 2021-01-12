package com.nj.cloudalibaba.predicate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/12 23:30
 */
@Slf4j
@Component
public class AccessTimeRoutePredicateFactory extends AbstractRoutePredicateFactory<AccessTimeConfig> {
    public AccessTimeRoutePredicateFactory() {
        super(AccessTimeConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(AccessTimeConfig config) {

        LocalTime start = config.getStart();
        LocalTime end = config.getEnd();

        return (serverWebExchange) -> {
            log.info("AccessTime -> " + LocalTime.now().toString());

            LocalTime now = LocalTime.now();

            return now.isAfter(start) && now.isBefore(end);
            //return true;
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("start", "end");
    }
}
