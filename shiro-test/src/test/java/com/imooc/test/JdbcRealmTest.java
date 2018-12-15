package com.imooc.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 1639489689@qq.com
 * @date 2018/7/25 0025 下午 3:26
 */
public class JdbcRealmTest {
    private Logger logger=LoggerFactory.getLogger(AuthenticationTest.class);
    DruidDataSource dataSource=new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
    }
    @Test
    public void testAuthentication(){
        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        logger.info("使用jdbcRealm权限时，要打开权限开关，默认是false");
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql="select password from test_user where user_name=?";
        jdbcRealm.setAuthenticationQuery(sql);

        String roleSql="select role_name from test_user_role where username = ?";
        jdbcRealm.setUserRolesQuery(roleSql);

        //1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("小明","654321");
        subject.login(token);
        logger.info("isAuthenticated:"+subject.isAuthenticated());
        //subject.checkRole("user");
//        subject.checkRoles("admin","user");
//        subject.checkPermission("user:select");

        subject.checkRole("user");


    }
}
