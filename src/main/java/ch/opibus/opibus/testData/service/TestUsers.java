package ch.opibus.opibus.testData.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.partner.dao.*;
import ch.opibus.opibus.partner.service.PartnerService;
import ch.opibus.opibus.security.model.SecurityRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Set;

import static ch.opibus.opibus.partner.dao.ColorSettings.DARK;
import static ch.opibus.opibus.partner.dao.ColorSettings.LIGHT;
import static ch.opibus.opibus.security.model.SecurityRole.ADMIN_FULL;
import static ch.opibus.opibus.security.model.SecurityRole.USER_FULL;

@Service
@AllArgsConstructor
public class TestUsers {

    private final PartnerService partnerService;

    public void create() {

            createUser(1, ADMIN_FULL, DARK, "Admin", "King", "Admin","admin@test.ch", "test123");
            createUser(2, USER_FULL, LIGHT, "User", "Worker", "User","user@test.ch", "test123");


    }

    private void createUser(long id, SecurityRole securityRole, ColorSettings color, String firstName, String lastName, String userName, String email, String password) {

        AppUser appUser = setAppUser(id, securityRole, userName, email, password);
        Partner partner = setPartner(id, firstName, lastName, color);

        try{

            partnerService.create(partner, appUser);

        } catch (DBError error ) {

        }

    }

    private Partner setPartner(long id, String firstName, String lastName, ColorSettings color) {

        return new Partner(
                id,
                id,
                getUserHead(id, firstName, lastName),
                getSettings(id, color)
        );
    }

    private UserHead getUserHead(long id, String firstName, String lastName) {

        return new UserHead(
                id,
                id,
                firstName,
                null,
                lastName,
                null,
                "",
                null
        );

    }

    private PartnerSettings getSettings(long id, ColorSettings color) {

        return new PartnerSettings(
                id,
                color ,
                null
        );
    }

    private AppUser setAppUser(long id, SecurityRole securityRole, String userName, String email, String password) {

        return new AppUser(
                id,
                email,
                userName,
                password,
                securityRole,
                false,
                false,
                null,
                false,
                true,
                0,
                securityRole.getGrantedAuthorities()
        );
    }
}
