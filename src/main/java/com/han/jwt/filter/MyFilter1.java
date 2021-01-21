package com.han.jwt.filter;

import org.apache.tomcat.util.http.parser.Authorization;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        req.setCharacterEncoding("UTF-8");

        //토큰 : han 을 만듦. id/pw가 정상적으로 들어와 로그인이 완료되면 토큰을 만들어주고 개인키로 묶고 그걸 응답해준다.
        // 요청할때마다 header Authorization에 value값으로 토큰을 가져오게된다.
        // 그때 토큰이 넘어오면 내가만든 토큰인지 퍼블릭키로 풀어 검증하기만 하면 된다.(RSA, HS256)
        if(req.getMethod().equals("POST")){
            String headerAuth = req.getHeader("Authorization");
            System.out.println("headerAuth -- "+headerAuth);

            if(headerAuth.equals("han")){
                System.out.println("필터1");
                filterChain.doFilter(req,res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("인증안됨");
            }
        }
    }
}
