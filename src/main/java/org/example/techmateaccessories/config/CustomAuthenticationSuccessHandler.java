package org.example.techmateaccessories.config;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import org.example.techmateaccessories.domain.User;
import org.example.techmateaccessories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService  ;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/";

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (role.equals("ROLE_ADMIN")) {
                redirectUrl = "/admin/user";
                break;
            } else if (role.equals("ROLE_USER")) {
                redirectUrl = "/";
                break;
            }
        }
        HttpSession session = request.getSession();
        String email =   (String) authentication.getName();
        User user = this.userService.findByEmail(email);
        if(user != null) {
            session.setAttribute("fullname", user.getFullName());
            session.setAttribute("avatar", user.getAvatar());
            session.setAttribute("id", user.getID());
            session.setAttribute("email", user.getEmail());
            int sum = 0  ;
            if(user.getCart() == null )sum =  0  ;
            else sum  =  user.getCart().getSum();
            session.setAttribute("sum" , sum);
        }
        response.sendRedirect(redirectUrl);
    }
}
