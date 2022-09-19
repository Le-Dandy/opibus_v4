package ch.opibus.opibus.testData.service;

import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.security.model.SecurityRole;
import ch.opibus.opibus.partner.dao.ColorSettings;
import ch.opibus.opibus.partner.dao.PartnerSettings;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.dao.UserHead;
import ch.opibus.opibus.partner.service.PartnerService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static ch.opibus.opibus.partner.dao.ColorSettings.*;
import static ch.opibus.opibus.security.model.SecurityRole.*;

@Service
@AllArgsConstructor
public class testDataService {

    private final PartnerService partnerService;
    private final TestPageTranslation pageTranslation;

    @EventListener(ApplicationReadyEvent.class)
    public void insertAppUser() {

        createInitUsers();
        pageTranslation.create();

    }

    private void createInitUsers(){

        createUser(1, ADMIN_FULL, DARK, "Admin", "King", "Admin","admin@test.ch", "test123");
        createUser(2, USER_FULL, LIGHT, "User", "Worker", "User","user@test.ch", "test123");

    }

    private void createUser(long id, SecurityRole securityRole, ColorSettings color, String firstName, String lastName, String userName, String email, String password) {

        AppUser appUser = getAppUser(id, securityRole, userName, email, password);

        Partner partner = new Partner(
                id,
                id,
                getUserHead(id, firstName, lastName),
                getSettings(id, color));

        try{

            partnerService.create(partner, appUser);

        } catch (Error error ) {

        }
    }



    private AppUser getAppUser(long id, SecurityRole securityRole, String userName, String email, String password) {
        return new AppUser(
                id,
                email,
                userName,
                password,
                securityRole,
                false,
                false,
                false,
                true,
                securityRole.getGrantedAuthorities());
    }

    private PartnerSettings getSettings(long id, ColorSettings color) {
        return new PartnerSettings(
                id,
                color ,
                null);
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
                null);

    }

}
