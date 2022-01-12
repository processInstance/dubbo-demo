package com.css.consumer.filter;

import com.alibaba.dubbo.rpc.*;
import com.css.util.SessionUtil;

/**
 * @author : lin
 * @date : 16:41 2022/1/11
 */
public class UserDubboFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //判断是消费者  还是 服务提供者
        SessionUtil sessionUtil = new SessionUtil();
        if (RpcContext.getContext().isConsumerSide()) {
            //消费者 将userID set至上下文中
            String user= (String) sessionUtil.getUser();
            RpcContext.getContext().setAttachment("userInfo", user);
        }
        return invoker.invoke(invocation);
    }
}
