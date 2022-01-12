package com.example.provider.service.imp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.css.api.MotionService;
import com.css.api.UserService;

/**
 * @author : lin
 * @date : 15:22 2022/1/11
 */
@Service
public class UserServiceImp implements UserService {
    @Reference
    MotionService motionService;

    @Override
    public String login(String userName) {
        return userName;
    }

    @Override
    public String getUserMotion() {
        String userInfo = RpcContext.getContext().getAttachment("userInfo");
        String motions = motionService.getCurrenUserMotion();
        String afterFilterUserInfo = RpcContext.getContext().getAttachment("userInfo");
        System.out.println(motions);
        return "user:"+userInfo+"motion:"+motions;
    }
}
