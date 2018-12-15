package com.imooc.controller;

import com.imooc.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 1639489689@qq.com
 * @date 2018/7/26 0026 上午 10:16
 */
@Controller
public class UserController {
    @RequestMapping(value = "/subLogin",method=RequestMethod.POST,
    produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user){
        System.out.println("====="+user.getUsername());
        System.out.println(user.getPassword());
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            token.setRememberMe(user.isRememberMe());
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        if(subject.hasRole("admin")){
            System.out.println("有admin权限");
        }

        try {
            subject.checkPermission("user:delete");
            return "有user:delete权限";
        } catch (AuthorizationException e) {
            return e.getMessage();
        }
    }

//    @RequiresRoles("admin")
//    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
//    @ResponseBody
//    public String testRole(){
//        return "testRole success";
//    }
//
//    @RequiresRoles("admin,admin1")
//    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
//    @ResponseBody
//    public String testRole1(){
//        return "testRole1 success";
//    }

    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    @ResponseBody
    public String testRole(){
        return "testRole success";
    }

    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    @ResponseBody
    public String testRole1(){
        return "testRole1 success";
    }

    @RequestMapping(value = "/testPerms",method = RequestMethod.GET)
    @ResponseBody
    public String testPerms(){
        return "testPerms success";
    }

    @RequestMapping(value = "/testPerms1",method = RequestMethod.GET)
    @ResponseBody
    public String testPerms1(){
        return "testPerms1 success";
    }
}
