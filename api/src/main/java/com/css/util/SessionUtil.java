package com.css.util;



import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class SessionUtil {

    private final static String USER = "user";

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取用户基本信息
     * 统一返回 SessionUser
     * @return
     */
    public Object getUser() {
        HttpSession session = getRequest().getSession();
        return session.getAttribute(USER);
    }


    /**
     * 存取用户基本信息
     *
     * @param user
     */
    public void setUser(Object user) {
        HttpSession session = getRequest().getSession();
        if (user != null) {
            //log.info("access session userinfo:{}" , JSON.toJSONString(user));
            session.setAttribute(USER, user);
            //session过期时间设置，以秒为单位，即在没有活动1小时后，session将失效
        }
    }


    /**
     * 获取sessionId
     */
    public String getSessionId() {
        return getRequest().getSession().getId();
    }


    public void setAuthorize(String value) {
        HttpSession session = getRequest().getSession();
        session.setAttribute("wps", value);
        session.setMaxInactiveInterval(3 * 60 * 60);
    }

    public String getAuthorize() {
        HttpSession session = getRequest().getSession();
        return (String) session.getAttribute("wps");
    }

    /**
     * 清除当前session
     */
    public void logout() {
        HttpSession session = getRequest().getSession();
        session.invalidate();
    }

}
