package com.nj.springcloud.common;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/26 12:50
 */
public class MyHystrixCommand extends HystrixCommand {

    private RestTemplate restTemplate;

    public MyHystrixCommand(Setter setter,RestTemplate restTemplate){
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        //调用远程方法
        String body = restTemplate.getForEntity("http://01SPRINGCLOUDPROVIDER/getProviderSpringCloud", String.class).getBody();
        return body;
    }

    /**
     * 当远程服务超市、异常、不可用的情况时会触发熔断
     * @return
     */
    @Override
    protected String getFallback() {
        Throwable throwable = super.getExecutionException();
        System.out.println(throwable.getMessage());
        return "自定义ERROR!!!";
    }
}
