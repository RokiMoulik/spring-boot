package com.ezybytes.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CsrfCookieFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("CsrfCookieFilter -> doFilterInternal");

        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if(csrfToken.getHeaderName() != null){
            System.out.println("csrfToken.getHeaderName(): "+csrfToken.getHeaderName());

            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }
        else{
            System.out.println("csrfToken.getHeaderName() is null");
        }
        filterChain.doFilter(request, response);
    }
}
