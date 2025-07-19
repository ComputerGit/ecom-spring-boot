//package com.at.t.eCommerce.security;
//
//import io.jsonwebtoken.Claims;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
////Extract the JWT from the Authorization header.
////Validate the JWT using JWTUtil.
////Set authentication in SecurityContext if the token is valid
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JWTUtil jwtUtil;
//
//    public JwtAuthenticationFilter(JWTUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//
//            try {
//                if (jwtUtil.validateToken(token)) {
//                    Claims claims = jwtUtil.parseToken(token);
//
//                    String username = claims.getSubject();
//
//                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", new ArrayList<>());
//                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>()));
//                }
//            } catch (Exception e) {
//               System.out.println(e.getMessage());
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
