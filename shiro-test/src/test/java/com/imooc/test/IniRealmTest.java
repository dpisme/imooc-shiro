package com.imooc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 1639489689@qq.com
 * @date 2018/7/25 0025 下午 2:09
 */
public class IniRealmTest {
    private Logger logger=LoggerFactory.getLogger(AuthenticationTest.class);
    @Test
    public void testAuthentication(){
        IniRealm iniRealm=new IniRealm("classpath:user.ini");

        //1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("Mark","123456");
        subject.login(token);
        logger.info("isAuthenticated:"+subject.isAuthenticated());
        subject.checkRole("admin");

        subject.checkPermission("user:delete");

    }
}
