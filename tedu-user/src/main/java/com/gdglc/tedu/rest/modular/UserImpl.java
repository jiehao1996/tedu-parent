package com.gdglc.tedu.rest.modular;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gdglc.tedu.api.user.UserAPI;
import org.springframework.stereotype.Component;

@Component
public class UserImpl {

    @Reference(interfaceClass = UserAPI.class)
    UserAPI userAPI;

    public void sendMessage(String message){
        System.out.println("这是tedu-user的UserImpl方法: "+userAPI.sendMessage(message));
    }
}
