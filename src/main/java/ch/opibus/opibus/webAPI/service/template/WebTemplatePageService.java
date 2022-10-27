package ch.opibus.opibus.webAPI.service.template;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavigationBar;
import ch.opibus.opibus.partner.dao.PartnerSettings;
import ch.opibus.opibus.partner.service.PartnerSettingsService;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.service.AppUserService;
import ch.opibus.opibus.partner.service.PartnerService;
import ch.opibus.opibus.webAPI.service.template.objects.WtNavBarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class WebTemplatePageService {


    private final WtNavBarService wtNavBarService;
    private final PartnerSettingsService partnerSettingsService;
    private final PartnerService partnerService;
    private final AppUserService appUserService;
    
    public WebTemplatePage get(Principal principal, String activeTab) throws DBError{

        try{
            AppUser appUser = appUserService.get(principal.getName());
            Partner partner = partnerService.get(appUser);

            return new WebTemplatePage(
                    getNavbar(appUser, activeTab),
                    partner.getSettings()
            );

        } catch (DBError error){

            throw error;

        }

    }

    /*
    private Partner getPartner(AppUser appUser) {

        try{

            return partnerService.get(appUser);

        } catch (DBError error){

            return new Partner();

        }

    }

    private AppUser getAppUser(Principal principal) {

        try{

            return appUserService.get(principal.getName());

        } catch (DBError error){

            return new AppUser();
            
        }

    }

     */

    public WtNavigationBar getNavbar(AppUser appUser, String activeTab){

        return wtNavBarService.get(appUser, activeTab);
    }

    public PartnerSettings getSettings(Partner partner){

        try{

            return partnerSettingsService.get(partner);

        } catch (DBError error){

            return new PartnerSettings();

        }


    }

}
