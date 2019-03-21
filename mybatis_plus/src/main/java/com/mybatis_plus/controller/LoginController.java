package com.mybatis_plus.controller;


import com.mybatis_plus.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("login")
public class LoginController {

    @RequestMapping("check")
    public String login(String name, String password) {

        System.out.println("----------login-------------");

        Subject subject = SecurityUtils.getSubject();
        //MD5Util.getPwd(password)
        //AuthenticationToken token = new UsernamePasswordToken(name, MD5Util.getPwd(password),true);
        AuthenticationToken token = new UsernamePasswordToken(name, password, true);

        try {

            subject.login(token);
            Session session = subject.getSession();
            session.setTimeout(-1000L);
            //登录成功
            return "success";

        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }
        //登录失败
        return "fail";

    }
}
