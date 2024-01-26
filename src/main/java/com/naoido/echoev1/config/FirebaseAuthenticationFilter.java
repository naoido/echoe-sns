package com.naoido.echoev1.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.naoido.echoev1.entity.FirebaseAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FirebaseAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if (token == null) {
            response.getWriter().print("token is empty.");
            response.setStatus(403);
            return;
        }
        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token);
            SecurityContextHolder.getContext().setAuthentication(new FirebaseAuthenticationToken(firebaseToken, null));

            filterChain.doFilter(request, response);
        } catch (FirebaseAuthException e) {
            response.getWriter().print("token is invalid.");
            response.setStatus(403);
        }
    }


    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring("Bearer ".length());
    }
}
