package ch.opibus.opibus.webAPI.service.template.objects;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.partner.service.PartnerService;
import ch.opibus.opibus.security.model.SecurityRole;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.webAPI.model.template.fields.WtDropDown;
import ch.opibus.opibus.webAPI.model.template.fields.WtLink;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavBarTab;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavigationBar;
import ch.opibus.opibus.webAPI.model.template.objects.WtPartnerSettings;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.webAPI.service.template.fields.WtDropDownService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WtNavBarService extends Translation {

    private final PartnerService partner;

    private final WtDropDownService dropDown;
/*
    public WtNavigationBar get(AppUser appUser, String activeTab) {

        return get(appUser.getSecurityRole(), activeTab);
    }

    */


    public WtNavigationBar get(AppUser appUser, String activeTab, String language) throws DBError {

        try{

            return buildNavBar(
                    partner.get(appUser),
                    activeTab,
                    appUser.getSecurityRole(),
                    language
            );

        } catch (DBError error) {

            throw error;
        }


    }

    private WtNavigationBar buildNavBar(Partner partner, String activeTab,SecurityRole securityRole, String language) {



        WtNavigationBar navBar = new WtNavigationBar();

        navBar.setNavbarBrand(getBrand(securityRole));
/*
        navBar = buildMainNavBar(navBar, securityRole, activeTab, language);


        navBar.setTitle1(buildTitle(securityRole, language));

        navBar.setTitle2(getTitle2(securityRole));
        navBar.setDropDown2(getDropDown2(securityRole));



         */

        return navBar;
    }

    /*
    private WtNavigationBar buildMainNavBar(WtNavigationBar navBar, SecurityRole securityRole, String activeTab, String language) {

        switch (securityRole) {

            case ADMIN_BASIC -> return adminNavBar(navBar, activeTab, language);

            case ADMIN_FULL -> return adminNavBar(navBar, activeTab, language);

            case USER_BASIC -> return userNavBar(navBar, activeTab, language);

            case USER_FULL -> return userNavBar(navBar, activeTab, language);

            default -> return userNavBar(navBar, activeTab, language);

        }
    }


    private WtNavigationBar adminNavBar(WtNavigationBar navBar, String activeTab, String language) {

        navBar.setTitle1();

        return navBar;
    }


    public WtNavigationBar get(String language, String activeTab, Partner partner) {
    }


     */

    public WtNavigationBar get(SecurityRole securityRole, String activeTab) {

        WtNavigationBar navBar = new WtNavigationBar();

        Partner partner = new Partner();
/*
        navBar = setActive(activeTab);


 */
        navBar.setNavbarBrand(getBrand(securityRole));

        /*
        navBar = setTabs(navBar, securityRole, activeTab);


        navBar.getTab1(getTab1(securityRole));
        navBar.getTab2(getTab2(securityRole));



         */

        /*
        navBar.setUser(getUser(partner));

         */

        navBar.setLogoff(getLogoff());

        return navBar;
    }
/*
    private WtNavigationBar setTabs(WtNavigationBar navBar, SecurityRole securityRole, String activeTab) {
        
        navBar.setTab1(getTab1(securityRole, activeTab));

    }

    private WtNavBarTab getTab1(SecurityRole securityRole, String activeTab) {

        WtNavBarTab tab = new WtNavBarTab();

        if(activeTab == "main"){

            tab.setActive(true);

        } else {

            tab.setActive(false);

        }

        tab.setTitle(getTitle1(securityRole));
/*
        tab.setDropDown(dropDown.get());


    }

 */

    
/*
    private WtPartnerSettings getUser(Partner partner) {
        return new WtPartnerSettings(
             
                partner.getUserHead().getFirstName() + " " + partner.getUserHead().getLastName()

          

                "Hans Werner"
        );
    }


    private WtNavigationBar setActive(String activeTab) {

        WtNavigationBar actNavBar = new WtNavigationBar();

        switch (activeTab) {

            case "main": actNavBar.setActive1(true);

            default: actNavBar.setActive1(true);
        }

        return actNavBar;
    }
    
    
 */

    private List<WtLink> getDropDown2(SecurityRole securityRole) {

        List<WtLink> linkList = new ArrayList<>();

        linkList.add(new WtLink(securityRole.getUrlPrefix() + "/linkList1","linkList1",null));
        linkList.add(new WtLink(securityRole.getUrlPrefix() + "/linkList2","linkList2",null));

        return linkList;
    }

    private WtLink getTitle1(SecurityRole securityRole) {

        return new WtLink(securityRole.getUrlPrefix() + "/link1","Title1",null);
    }

    private WtLink getTitle2(SecurityRole securityRole) {

        return new WtLink(securityRole.getUrlPrefix() + "/link2","Title2",null);
    }

    private WtLink getBrand(SecurityRole securityRole) {

        return new WtLink(securityRole.getUrlPrefix() + "/main","brand",null);
    }

    private WtLink getLogoff() {

        return new WtLink("/logout","logout",null);
    }



}
