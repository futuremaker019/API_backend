package com.stock.analysis.config.jwt;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {

    public Cookie createCookie(String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest req, String cookieName) {
        final Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }

    public Cookie deleteCookie(HttpServletRequest request, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }

    public void deleteCookie(HttpServletResponse response, Cookie cookie) {
        cookie.setMaxAge(0);
        cookie.setPath("/api/v1/user");
        cookie.setValue("");
        response.addCookie(cookie);
    }
}
