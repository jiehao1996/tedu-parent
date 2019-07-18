package com.gdglc.tedu.rest.modular.shiro;

import com.gdglc.tedu.code.util.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomRealm extends AuthorizingRealm {

    private final UserMapper userMapper;

    @Autowired
    public CustomRealm(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    /**
     * 必须重写此方法,不然会报错
     * */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /*
    * 获取授权信息
    * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole, checkPermission之类的
    *
    * @Param principalCollection
    * @return
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("——————权限认证——————");
        String username = JWTUtil.getUsername(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo ();
        // 获得该用户角色
        String role = userMapper.getRole(username);
        // 每个角色y拥有默认的权限
        String rolePermission = userMapper.getRolePermission(username);
        // 每个用户可以设置新的权限
        String permission = userMapper.getPermission(username);
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        // 需要将role, permission封装到Set作为info.setRoles(), info.setStringPermissions()的参数
        roleSet.add(role);
        permissionSet.add(rolePermission);
        permissionSet.add(permission);
        // 设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("——————身份认证方法——————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        String password = userMapper.getPassword(username);
        if (null == password){
            throw new AccountException("用户名不正确");
        }
        int ban = userMapper.checkUserBanStatus(username);
        if (ban == 1){
            throw new AuthenticationException("该用户已封号！");
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }
}
