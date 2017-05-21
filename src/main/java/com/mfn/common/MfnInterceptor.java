package com.mfn.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by LPF on 2017/4/22.
 */
public class MfnInterceptor implements HandlerInterceptor{
    private static final Logger LOG = LoggerFactory.getLogger(MfnInterceptor.class);

    @Autowired
    @Qualifier("com.mfn.common.SeqUtils")
    private SeqUtils seqUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean checkMfnToken = checkMfnToken(request);
        if (!checkMfnToken) {
            LOG.error("MfnInterceptor.preHandle checkMfnToken is false");
            response.sendRedirect(request.getContextPath()+"/index.html");
            return false;
        }
        if (seqUtils == null) {
            LOG.debug("seqUtils is null");
            BeanFactory beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            seqUtils = (SeqUtils)beanFactory.getBean("com.mfn.common.SeqUtils");
        }
        MfnContextUtils.setSeq(seqUtils.getNextSeq(new Date()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean checkMfnToken(HttpServletRequest request) throws Exception{
        String url = request.getServletPath();
        if ("/login".equals(url) ||
            "/usermanager/register".equals(url) ||
            "/error".equalsIgnoreCase(url)) {
            return true;
        }else {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                return false;
            }
            String userId = null;
            String mfnToken = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                }
                if (cookie.getName().equals("mfnToken")) {
                    mfnToken = cookie.getValue();
                }
            }
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(mfnToken)) {
                return false;
            }

            if (mfnToken.equals("mfnToken"+userId)) {
                return true;
            }else {
                return false;
            }
        }
    }
}
