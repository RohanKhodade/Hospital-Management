package com.example.hospitalManagement.filters;

import com.example.hospitalManagement.service.implementations.UserServiceImpl;
import com.example.hospitalManagement.util.JwtUtility;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
    private final UserServiceImpl userServiceImpl;
    public JwtAuthFilter(JwtUtility jwtUtility,
                         UserServiceImpl userServiceImpl){
        this.jwtUtility=jwtUtility;
        this.userServiceImpl=userServiceImpl;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        //1. get authentication header
        String authHeader=httpServletRequest.getHeader("Authorization");
        String token=null;
        String username=null;

        //2. check if header has a bearer token
        if (authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            try{
                username=jwtUtility.extractUsername(token);
            }catch(ExpiredJwtException ex){
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "Token expired please login again");
                return;
            }catch (Exception ex){
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "Invalid Token");
                return;
            }
        }

        //3. if username is found and security context holder is not set then set it
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userServiceImpl.loadUserByUsername(username);
            // set authentication in security context holder
            UsernamePasswordAuthenticationToken authToken=
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        // continue the filter chain
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}