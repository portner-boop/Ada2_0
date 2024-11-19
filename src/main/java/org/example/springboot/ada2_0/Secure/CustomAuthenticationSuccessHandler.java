package org.example.springboot.ada2_0.Secure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails instanceof CustomUserDetails) {
            int groupId = ((CustomUserDetails) userDetails).getGroupId();
            response.sendRedirect(request.getContextPath() + "/home/" + groupId);
        } else {

            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}



