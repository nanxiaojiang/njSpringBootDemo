package com.nj.cloudalibaba.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.nj.cloudalibaba.model.RestObject;
import com.nj.cloudalibaba.sentinel.MyBlockHandlerClass;
import com.nj.cloudalibaba.sentinel.MyFallbackClass;
import com.nj.cloudalibaba.service.EchoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/10 12:52
 */
@RestController
public class TestController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    //feign 的声明式调用
    @Autowired
    private EchoFeignService echoFeignService;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * blockHandler = "block", blockHandlerClass = MyBlockHandlerClass.class 处理限流和降级
     * fallback = "fallback", fallbackClass = MyFallbackClass.class 处理限流和降级
     *
     * @param a
     * @param b
     * @return
     */
    @GetMapping("/app") // 埋点：加入sentinel的监控
//    @SentinelResource(value = "app", fallback = "fallback", fallbackClass = MyFallbackClass.class)
    @SentinelResource(value = "app", blockHandler = "block", blockHandlerClass = MyBlockHandlerClass.class)
    public String app(@RequestParam(value = "a", required = false) String a,
                      @RequestParam(value = "b", required = false) String b) {
        System.out.println("/app/--> " + a + "--" + b);
        return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
    }

    /**
     *
     * @return
     */
    @GetMapping("/test/{app}")
    public String test3(@PathVariable(name = "app") String app){
        System.out.println("/test3/{app} --> " + app);

        ContextUtil.enter("test3");
        Entry entry = null;
        try {
            entry = SphU.entry("test3");
            //受sentinel保护的代码 start
            int a = 10 / 0;
            return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
            //受sentinel保护的代码 end
        }catch (BlockException e){
            e.printStackTrace();
            //手动写上服务降级的代码
            if (e instanceof FlowException) {
                return "接口限流了。。。。。。";
            } else if (e instanceof DegradeException) {
                return "服务降级了。。。。。。";
            } else if (e instanceof ParamFlowException) {
                return "热点参数限流了。。。。。。";
            } else if (e instanceof SystemBlockException) {
                return "触发系统保护规则。。。。。。";
            } else if (e instanceof AuthorityException) {
                return "授权规则不通过。。。。。。";
            }
            return "熔断了。。。。。。";
        }catch (ArithmeticException e){
            //对 int a = 10 / 0; 异常的监控
            Tracer.trace(e);
            return "除数不能为0";
        }finally {
            if (entry != null){
                entry.exit();
            }
            ContextUtil.exit();
        }
    }


    @RequestMapping("/test2")
    public String test2(){
        return restTemplate.getForObject("http://29-nacos-discovery-provider/test",String.class);
    }

    @GetMapping("/notFound-feign")
    public String notFound(){
        return echoFeignService.notFound();
    }

}
