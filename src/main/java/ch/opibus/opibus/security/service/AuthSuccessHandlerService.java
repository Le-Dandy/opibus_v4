package ch.opibus.opibus.security.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@AllArgsConstructor
public class AuthSuccessHandlerService extends SimpleUrlAuthenticationSuccessHandler {

    private final AppUserService appUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        appUserService.resetFailedAttempt(request.getParameter("email"));

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
