package ch.opibus.opibus.webAPI.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.service.AppUserService;
import ch.opibus.opibus.partner.service.PartnerService;
import ch.opibus.opibus.security.service.ValidationTokenService;
import ch.opibus.opibus.webAPI.model.WebPageDefault;
import ch.opibus.opibus.webAPI.model.WebPageRegister;
import ch.opibus.opibus.webAPI.model.WebPageValidation;
import ch.opibus.opibus.webAPI.model.template.WebTemplateNavigationBar;
import ch.opibus.opibus.partner.model.PartnerWebTemplateInput;
import ch.opibus.opibus.webAPI.service.template.WebTemplatePartnerInputService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebAPIRegisterService {

    private final WebTemplatePartnerInputService partnerInput;
    private final PartnerService partnerService;
    private final AppUserService appUserService;
    private final WebAPIErrorService errorService;

    private final ValidationTokenService validationTokenService;

    public WebPageRegister getWebPageRegister(AppUser appUser, Partner partner, String language) throws TranslationError {

        try{

            return new WebPageRegister(
                    new WebTemplateNavigationBar(),
                    partnerInput.get(appUser, partner, language)
            );


        } catch (TranslationError error) {

            throw error;

        }


    }

    public void save(WebPageRegister partner) throws DBError {

        try{

            partnerService.create(partner.getPartner().getPartner(), partner.getPartner().getAppUser());

        } catch (DBError error) {

            throw error;

        }
    }

    public WebPageDefault translateRegistrationInput(AppUser appUser, Partner partner, String language) {

        try{

            return new WebPageDefault(
                    "registration_page",
                    getWebPageRegister(appUser, partner, language),
                    null,
                    null,
                    null,
                    null
            );

        } catch (TranslationError e){

            return errorService.getTranslationError(e);

        }

    }

    public WebPageDefault checkSubmitRegistration(PartnerWebTemplateInput partner, String language) {

        if(partner != null){

            return saveRegistration(partner.getAppUser(), partner.getPartner(), language);

        } else {

            return incompletionForm(partner.getAppUser(), partner.getPartner(), language, 0);

        }
    }
    public WebPageDefault saveRegistration(AppUser appUser, Partner partner, String language) {

        if(emailExists(appUser, partner)){

            return incompletionForm(appUser, partner, language, 1);

        } else {

            return validateEmail(appUser, partner , language);


        }
    }

    private WebPageDefault validateEmail(AppUser appUser, Partner partner, String language) {

        validationTokenService.initalize(appUser, partner.getUserHead());

        return new WebPageDefault(
                "/register/validation",
                getWebPageValidation(appUser, partner , language),
                null,
                null,
                null,
                null
        );

    }

    private WebPageValidation getWebPageValidation(AppUser appUser, Partner partner, String language) {

        return new WebPageValidation(
                );

    }

    private WebPageDefault incompletionForm(AppUser appUser, Partner partner, String language, int errorCase) {

        WebPageDefault page = translateRegistrationInput(appUser, partner, language);

        switch (errorCase) {
            case 1: page.setObject2("Email already exists");
            default : page.setObject1("Incomplete data");
        }

        return page;
    }

    private boolean emailExists(AppUser appUser, Partner partner) {

        try{

            appUserService.getByEmail(appUser.getEmail());

            return true;

        } catch (DBError appUserError) {

            try{

                partnerService.getByEmail(partner.getUserHead().getEmail());

                return true;

            } catch (DBError partnerError) {

                return false;

            }

        }
    }

    public WebPageDefault checkForRegistrationInput(PartnerWebTemplateInput partner, String language) {

        if(partner == null){

            return translateRegistrationInput(
                    new AppUser(),
                    new Partner(),
                    language);

        } else {

            return translateRegistrationInput(
                    partner.getAppUser(),
                    partner.getPartner(),
                    language);

        }
    }


}
