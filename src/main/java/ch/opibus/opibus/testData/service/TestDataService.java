package ch.opibus.opibus.testData.service;

import ch.opibus.opibus.error.model.DBError;
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
public class TestDataService {
    private final TestUsers users;
    private final TestPageTranslation pageTranslation;

    private final TestNavBar navBar;

    @EventListener(ApplicationReadyEvent.class)
    public void insertAppUser() {

        users.create();

        pageTranslation.create();

        navBar.create();



    }


}
