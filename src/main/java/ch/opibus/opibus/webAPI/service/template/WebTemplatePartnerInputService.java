package ch.opibus.opibus.webAPI.service.template;

import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.model.PartnerWebTemplateInput;
import ch.opibus.opibus.webAPI.service.template.fields.WebTemplateInputService;
import ch.opibus.opibus.webAPI.service.template.fields.WebTemplateTextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebTemplatePartnerInputService {

    private final WebTemplateInputService input;
    private final WebTemplateTextService text;

    public PartnerWebTemplateInput get(String language) throws TranslationError{
        Partner partner = new Partner();
        AppUser appUser = new AppUser();

        try{

            return new PartnerWebTemplateInput(
                    partner,
                    appUser,
                    text.get(appUser, "AppUserInputTitle", language),
                    input.get(appUser, appUser.getUsername(true) , language),
                    input.get(appUser, appUser.getPassword(true) , language),
                    text.get(partner, "PartnerInputTitle", language),
                    input.get(partner, partner.getFirstName(true) , language),
                    input.get(partner, partner.getMiddleName(true) , language),
                    input.get(partner, partner.getLastName(true) , language),
                    input.get(partner, partner.getEmail(true) , language)


            );

        } catch (TranslationError error) {
            throw error;
        }

    }
    public PartnerWebTemplateInput get(AppUser appUser, Partner partner, String language) throws TranslationError{

        try {

            PartnerWebTemplateInput partnerInput = get(language);

            if(appUser.getEmail() != null){

                partnerInput = mapAppUserValues(partnerInput, appUser);

            }

            if(partner.getUserHead() != null){


                partnerInput = mapPartnerValues(partnerInput, partner);

            }

            return partnerInput;

        } catch (TranslationError error) {

            throw error;

        }
    }

    private PartnerWebTemplateInput mapAppUserValues(PartnerWebTemplateInput partnerInput, AppUser appUser) {

        partnerInput.setAppUser(appUser);

        partnerInput.setUserName(input.setValue(partnerInput.getUserName(), appUser.getUsername()));
        partnerInput.setPassword(input.setValue(partnerInput.getPassword(), appUser.getPassword()));

        return partnerInput;
    }

    private PartnerWebTemplateInput mapPartnerValues(PartnerWebTemplateInput partnerInput, Partner partner) {

        partnerInput.setPartner(partner);

        partnerInput.setFirstName(input.setValue(partnerInput.getFirstName(), partner.getUserHead().getFirstName()));
        partnerInput.setMiddleName(input.setValue(partnerInput.getMiddleName(), partner.getUserHead().getMiddleName()));
        partnerInput.setLastName(input.setValue(partnerInput.getLastName(), partner.getUserHead().getLastName()));
        partnerInput.setEmail(input.setValue(partnerInput.getEmail(), partner.getUserHead().getEmail()));

        return partnerInput;

    }









}
