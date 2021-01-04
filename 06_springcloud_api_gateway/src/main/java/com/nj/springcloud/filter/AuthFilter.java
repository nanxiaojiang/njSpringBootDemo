package com.nj.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/4 21:50
 */
@Component
public class AuthFilter extends ZuulFilter {
    /**
     * filterType 方法的返回值为过滤器的类型，过滤器的类型决定了过滤器在哪个生命周期执行，pre 表示在路由之前执行过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder 方法表示过滤器的执行顺序，当过滤器很多时，我们可以通过
     * 该方法的返回值来指定过滤器的执行顺序。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter 方法用来判断过滤器是否执行，true 表示执行，false 表示不执行。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     *  run 方法则表示过滤的具体逻辑
     *  本次测试：如果请求地址中携带了 token 参数的话，则认为是合法请求，否则为非法请求，如果是非法请求的话
     *  首先设置ctx.setSendZuulResponse(false); 表示不对该请求进行路由
     *  然后设置响应码和响应值
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //制造运行时异常信息
        int a = 10/0;
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
        if (token == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            ctx.setResponseBody("非法访问！！！");
        }
        return null;
    }
}
