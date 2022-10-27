package ch.opibus.opibus.webAPI.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.service.AppUserService;
import ch.opibus.opibus.partner.service.PartnerService;
import ch.opibus.opibus.security.service.ValidationTokenService;
import ch.opibus.opibus.webAPI.model.WebPage;
import ch.opibus.opibus.webAPI.model.template.window.WwRegister;
import ch.opibus.opibus.webAPI.model.template.window.WwValidation;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavigationBar;
import ch.opibus.opibus.webAPI.model.template.objects.WtPartnerInput;
import ch.opibus.opibus.webAPI.service.template.objects.WtPartnerInputService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebAPIRegisterService {

    private final WtPartnerInputService partnerInput;
    private final PartnerService partnerService;
    private final AppUserService appUserService;
    private final WebAPIErrorService errorService;

    private final ValidationTokenService validationTokenService;

    public WwRegister getWebPageRegister(AppUser appUser, Partner partner, String language) throws TranslationError {

        try{

            return new WwRegister(
                    new WtNavigationBar(),
                    partnerInput.get(appUser, partner, language)
            );


        } catch (TranslationError error) {

            throw error;

        }


    }

    public void save(WwRegister partner) throws DBError {

        try{

            partnerService.create(partner.getPartner().getPartner(), partner.getPartner().getAppUser());

        } catch (DBError error) {

            throw error;

        }
    }

    public WebPage translateRegistrationInput(AppUser appUser, Partner partner, String language) {

        try{

            return new WebPage(
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

    public WebPage checkSubmitRegistration(WtPartnerInput partner, String language) {

        if(partner != null){

            return saveRegistration(partner.getAppUser(), partner.getPartner(), language);

        } else {

            return incompletionForm(partner.getAppUser(), partner.getPartner(), language, 0);

        }
    }
    public WebPage saveRegistration(AppUser appUser, Partner partner, String language) {

        if(emailExists(appUser, partner)){

            return incompletionForm(appUser, partner, language, 1);

        } else {

            return validateEmail(appUser, partner , language);


        }
    }

    private WebPage validateEmail(AppUser appUser, Partner partner, String language) {

        validationTokenService.initalize(appUser, partner.getUserHead());

        return new WebPage(
                "/register/validation",
                getWebPageValidation(appUser, partner , language),
                null,
                null,
                null,
                null
        );

    }

    private WwValidation getWebPageValidation(AppUser appUser, Partner partner, String language) {

        return new WwValidation(
                );

    }

    private WebPage incompletionForm(AppUser appUser, Partner partner, String language, int errorCase) {

        WebPage page = translateRegistrationInput(appUser, partner, language);

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

    public WebPage checkForRegistrationInput(WtPartnerInput partner, String language) {

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
