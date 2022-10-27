package ch.opibus.opibus.testData.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.webAPI.service.template.fields.WtInputService;
import ch.opibus.opibus.webAPI.service.template.fields.WtTextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestPageTranslation {

    private final WtInputService input;
    private final WtTextService text;

    private final String language1 = "EN";
    private final String language2 = "DE";

    public void create() {

        createPartnerInput();
    }

    private void createPartnerInput() {

        Partner partner = new Partner();
        AppUser appUser = new AppUser();

        setupText(appUser, "AppUserInputTitle", "User data", null);
        setupInput(appUser, appUser.getUsername(true), "User name", null, "enter username");
        setupInput(appUser, appUser.getPassword(true), "Password", null, "enter password");

        setupText(partner, "PartnerInputTitle", "Personal data", null);
        setupInput(partner, partner.getFirstName(true), "First name", null, "enter first name");
        setupInput(partner, partner.getMiddleName(true), "Middle name", null, "enter middle name");
        setupInput(partner, partner.getLastName(true), "Last name", null, "enter last name");
        setupInput(partner, partner.getEmail(true), "email", null, "enter email");

    }

    private void setupText(Object object, String field, String fieldText, String description) {

        try{

            text.save(object, field, language1, fieldText, description);
            text.save(object, field, language2, fieldText, description);

        } catch(DBError e){

        }
    }

    private void setupInput(Object object, String method, String fieldText, String description, String placeholder) {

        input.createTestdata(object, method, fieldText, description, placeholder, null);

    }
}
