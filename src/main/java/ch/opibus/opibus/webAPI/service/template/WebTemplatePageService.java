package ch.opibus.opibus.webAPI.service.template;

import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.webAPI.model.template.WebTemplateNavigationBar;
import ch.opibus.opibus.partner.dao.PartnerSettings;
import ch.opibus.opibus.partner.service.PartnerSettingsService;
import ch.opibus.opibus.webAPI.model.template.WebTemplatePage;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.service.AppUserService;
import ch.opibus.opibus.partner.service.PartnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class WebTemplatePageService {


    private final WebTemplateNavBarService webTemplateNavBarService;
    private final PartnerSettingsService partnerSettingsService;
    private final PartnerService partnerService;
    private final AppUserService appUserService;
    
    public WebTemplatePage get(Principal principal, String activeTab){

        AppUser appUser = getAppUser(principal);
        Partner partner = getPartner(appUser);

        try{
            appUser = appUserService.getByUsername(principal.getName());

        } catch (Error error){

        }


        try{

            partner = partnerService.get(appUser);

        } catch (Error error){

        }

        return new WebTemplatePage(
                getNavbar(appUser, activeTab),
                partner.getSettings()
        );

    }

    private Partner getPartner(AppUser appUser) {

        try{

            return partnerService.get(appUser);

        } catch (Error error){

            return new Partner();

        }

    }

    private AppUser getAppUser(Principal principal) {

        try{

            return appUserService.getByUsername(principal.getName());

        } catch (Error error){

            return new AppUser();
            
        }

    }

    public WebTemplateNavigationBar getNavbar(AppUser appUser, String activeTab){

        return webTemplateNavBarService.get(appUser, activeTab);
    }

    public PartnerSettings getSettings(Partner partner){

        return partnerSettingsService.get(partner);
    }

}
