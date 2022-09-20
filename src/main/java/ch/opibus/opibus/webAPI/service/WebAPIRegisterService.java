package ch.opibus.opibus.webAPI.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.service.PartnerService;
import ch.opibus.opibus.webAPI.model.WebPageRegister;
import ch.opibus.opibus.webAPI.model.template.WebTemplateNavigationBar;
import ch.opibus.opibus.partner.model.PartnerWebTemplateInput;
import ch.opibus.opibus.webAPI.service.template.WebTemplatePartnerInputService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebAPIRegisterService {

    private final WebTemplatePartnerInputService partnerInputService;
    private final PartnerService partnerService;

    public WebPageRegister get(AppUser appUser, Partner partner, String language) throws TranslationError {

        try{
            WebPageRegister page = get(language);

            page.setPartner(mapPartner(page.getPartner(), appUser,  partner));

            return page;

        } catch (TranslationError error) {
            throw error;
        }


    }

    private PartnerWebTemplateInput mapPartner(PartnerWebTemplateInput pagePartner, AppUser appUser, Partner partner) {

        pagePartner.setAppUser(appUser);
        pagePartner.setPartner(partner);

        return pagePartner;
    }


    public WebPageRegister get(String language)  throws TranslationError{

        try{

            PartnerWebTemplateInput partnerInput =partnerInputService.get(language);
            return new WebPageRegister(
                    new WebTemplateNavigationBar(),
                    partnerInput

            );

        }catch (TranslationError error){
            throw error;
        }



    }

    public void save(Partner partner) throws DBError {

        try{

            partnerService.save(partner);

        } catch (DBError error) {
            throw error;
        }
    }
}
