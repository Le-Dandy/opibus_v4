package ch.opibus.opibus.security.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class SecurityService {

    private final AppUserService appUserService;

    public AppUser getAppUser(Principal principal) {

        AppUser user = new AppUser();

        if(principal != null) {
            user.setUsername(principal.getName());
        }

        return user;

    }

    public String getPrefix(Principal principal) throws DBError {

        try{

            return appUserService.getURLPrefixFromUser(principal.getName());

        } catch (DBError error) {

            throw error;

        }

    }
}
