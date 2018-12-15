package com.imooc.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @author 1639489689@qq.com
 * @date 2018/7/28 0028 下午 5:51
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId=getSessionId(sessionKey);
        ServletRequest request=null;
        if(sessionKey instanceof WebSessionKey){
            request=((WebSessionKey)sessionKey).getServletRequest();
        }
        if(request!=null && sessionId!=null){
            Session session= (Session) request.getAttribute(sessionId.toString());
            if(session !=null){
                return session;
            }
        }
        Session session= super.retrieveSession(sessionKey);
        if(request!=null && sessionId!=null){
            request.setAttribute(sessionId.toString(),session);
        }
        return session;
    }
}
