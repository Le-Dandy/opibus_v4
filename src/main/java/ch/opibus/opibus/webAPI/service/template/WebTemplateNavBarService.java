package ch.opibus.opibus.webAPI.service.template;

import ch.opibus.opibus.security.model.SecurityRole;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.webAPI.model.template.fields.WebTemplateLink;
import ch.opibus.opibus.webAPI.model.template.WebTemplateNavigationBar;
import ch.opibus.opibus.webAPI.model.template.WebTemplatePartnerSettings;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WebTemplateNavBarService extends Translation {


    public WebTemplateNavigationBar get(SecurityRole securityRole, String activeTab) {

        WebTemplateNavigationBar navBar = new WebTemplateNavigationBar();

        Partner partner = new Partner();

        navBar = getActive(activeTab);

        navBar.setNavbarBrand(getBrand(securityRole));

        navBar.setTitle1(getTitle1(securityRole));

        navBar.setTitle2(getTitle2(securityRole));
        navBar.setDropDown2(getDropDown2(securityRole));

        navBar.setUser(getUser(partner));

        navBar.setLogoff(getLogoff());

        return navBar;
    }

    private WebTemplatePartnerSettings getUser(Partner partner) {
        return new WebTemplatePartnerSettings(
                /*
                partner.getUserHead().getFirstName() + " " + partner.getUserHead().getLastName()

                 */

                "Hans Werner"
        );
    }


    private WebTemplateNavigationBar getActive(String activeTab) {

        WebTemplateNavigationBar actNavBar = new WebTemplateNavigationBar();

        switch (activeTab) {

            case "main": actNavBar.setActive1(true);

            default: actNavBar.setActive1(true);
        }

        return actNavBar;
    }

    private List<WebTemplateLink> getDropDown2(SecurityRole securityRole) {

        List<WebTemplateLink> linkList = new ArrayList<>();

        linkList.add(new WebTemplateLink(securityRole.getUrlPrefix() + "/linkList1","linkList1",null));
        linkList.add(new WebTemplateLink(securityRole.getUrlPrefix() + "/linkList2","linkList2",null));

        return linkList;
    }

    private WebTemplateLink getTitle1(SecurityRole securityRole) {

        return new WebTemplateLink(securityRole.getUrlPrefix() + "/link1","Title1",null);
    }

    private WebTemplateLink getTitle2(SecurityRole securityRole) {

        return new WebTemplateLink(securityRole.getUrlPrefix() + "/link2","Title2",null);
    }

    private WebTemplateLink getBrand(SecurityRole securityRole) {

        return new WebTemplateLink(securityRole.getUrlPrefix() + "/main","brand",null);
    }

    public WebTemplateNavigationBar get(AppUser appUser, String activeTab) {

        return get(appUser.getSecurityRole(), activeTab);
    }

    private WebTemplateLink getLogoff() {

        return new WebTemplateLink("/logout","logout",null);
    }
}
