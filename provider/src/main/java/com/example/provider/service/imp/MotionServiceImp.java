package com.example.provider.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.css.api.MotionService;

/**
 * @author : lin
 * @date : 17:25 2022/1/11
 */
@Service
public class MotionServiceImp implements MotionService {
    @Override
    public String getCurrenUserMotion() {
        String userInfo = RpcContext.getContext().getAttachment("userInfo");
        return userInfo+"'s motion";

    }
}
