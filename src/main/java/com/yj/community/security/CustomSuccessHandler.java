package com.yj.community.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Security가 요청을 가로챈 경우 사용자가 원래 요청했던 URI 정보를 저장한 객체
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        /* 로그인 버튼 눌러 접속했을 경우의 데이터 get */
        String prevPage = (String) request.getSession().getAttribute("prevPage");

        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }

        String uri = "/";

        // 있을 경우 URI 등 정보를 가져와서 사용
        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();

            // 세션에 저장된 객체를 다 사용한 뒤에는 지워줘서 메모리 누수 방지
            requestCache.removeRequest(request, response);

        } else if (prevPage != null && !prevPage.equals("")) {
            uri = prevPage;

        }
        // 세션 Attribute 확인
        /*Enumeration<String> list = request.getSession().getAttributeNames();
        while (list.hasMoreElements()) {
            System.out.println("세션 Attribute 확인 : " + list.nextElement());
        }*/

        //
        if (uri.equals("http://localhost:9091/members/login")) {
            uri = "/";
        }


        System.out.println("login success uri : " + uri);
        response.sendRedirect(uri);
    }
}
