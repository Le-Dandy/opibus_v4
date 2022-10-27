package ch.opibus.opibus.security.service;

import ch.opibus.opibus.partner.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@AllArgsConstructor
public class AuthFailureHandlerService extends SimpleUrlAuthenticationFailureHandler {

    private final AppUserService appUserService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        appUserService.setFailedLoginAttempt(request.getParameter("email"));


        super.onAuthenticationFailure(request, response, exception);
    }
}
