package com.gdglc.tedu.rest.modular;

import com.alibaba.dubbo.config.annotation.Service;
import com.gdglc.tedu.api.user.UserAPI;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UserAPI.class)
public class UserStartImpl implements UserAPI{
    @Override
    public String sendMessage(String message) {
        return "这是tedu-gateway的UserStartImpl的方法";
    }
}
