package com.nj.cloudalibaba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/13 23:46
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod("*");    //是什么请求方法，比如 GET POST PUT DELETE .....
        corsConfiguration.addAllowedOrigin("*");    //来自哪个域名的请求，*号表示所有
        corsConfiguration.addAllowedHeader("*");    //是什么请求头

        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);

    }
}
