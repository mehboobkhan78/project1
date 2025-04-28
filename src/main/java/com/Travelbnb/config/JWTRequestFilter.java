package com.Travelbnb.config;

import com.Travelbnb.entity.Appuser;
import com.Travelbnb.repository.AppuserRepository;
import com.Travelbnb.service.jwtservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    private jwtservice tokenservice;
    private AppuserRepository appuserRepository;

    public JWTRequestFilter(jwtservice tokenservice, AppuserRepository appuserRepository) {
        this.tokenservice = tokenservice;
        this.appuserRepository = appuserRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String tokenHeader = request.getHeader("Authorization");
 if(tokenHeader!=null && tokenHeader.startsWith("Bearer "))
 {
           String Token = tokenHeader.substring(8, tokenHeader.length() - 1);
           System.out.println(Token);
           String Username = tokenservice.getUsername(Token);
            Optional<Appuser> entity =appuserRepository.findByUsername(Username);
            if(entity.isPresent())
            {
                Appuser user = entity.get();
                UsernamePasswordAuthenticationToken uspt=
                        new UsernamePasswordAuthenticationToken
                                (user,null, Collections.singleton
                                        (new SimpleGrantedAuthority(user.getRole())));
                uspt.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(uspt);
            }
        }
        filterChain.doFilter(request,response);
    }

}

