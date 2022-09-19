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
                    input.get(appUser, appUser.getUserName(true) , language),
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




}
