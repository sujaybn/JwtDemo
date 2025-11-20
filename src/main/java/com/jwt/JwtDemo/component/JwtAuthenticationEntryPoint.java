package com.jwt.JwtDemo.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), authException.getMessage());
        pd.setProperty("description", "Invalid or missing token");

        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write(pd.toString());
    }
}