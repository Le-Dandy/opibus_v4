package ch.opibus.opibus.webAPI.service.template.objects;

import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.security.model.SecurityRole;
import ch.opibus.opibus.webAPI.model.template.fields.WtDropDown;
import ch.opibus.opibus.webAPI.model.template.fields.WtLink;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavBarTab;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavigationBar;
import ch.opibus.opibus.webAPI.service.template.fields.WtDropDownService;
import ch.opibus.opibus.webAPI.service.template.fields.WtLinkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WtNavBarTabService {

    private final WtLinkService link;
    private final WtDropDownService dropDown;

    public WtNavBarTab get(String thisTab, String activeTab, int tabNo, String language, SecurityRole role){

        WtNavBarTab navBarTab = new WtNavBarTab();

        return new WtNavBarTab(
                setActiveTab(thisTab, activeTab),
                setTitle(navBarTab, role, tabNo, language),
                setDropDown(navBarTab, role, tabNo, language)
        );
    }

    private WtLink setTitle(Object object, SecurityRole role, int tabNo, String language) throws TranslationError {

        try{

            return link.get(
                    object,
                     role.getUrlPrefix() + "_title" + tabNo,
                    language
            );

        } catch (TranslationError e){

            throw e;

        }

    }

    private WtDropDown setDropDown(Object object, SecurityRole role, int tabNo, String language) {

        return dropDown.get(
                getUrlList(role),
                object,
                role,
                tabNo,
                language
        );

    }


    private List<WtLink> getUrlList(SecurityRole role, int tabNo) {
        
        switch (role.getUrlPrefix()) {
            
            case "admin" : return getAdminUrlList(tabNo);

            case "user" : return getUserUrlList(tabNo);
            
            default : return getUserUrlList(tabNo);
        }
    }



    private List<WtLink> getAdminUrlList(int tabNo) {

        List<WtLink> list = new ArrayList<>();

        list.add( new WtLink("/admin/linkList1_1", null, null));
        list.add( new WtLink("/admin/linkList1_2", null, null));

        return list;
    }

    private List<WtLink> getUserUrlList() {


        List<WtLink> list = new ArrayList<>();

        list.add( new WtLink("/user/linkList1_1", null, null));
        list.add( new WtLink("/user/linkList1_2", null, null));

        return list;

    }

    private boolean setActiveTab(String thisTab, String activeTab) {

        if(thisTab == activeTab) {

            return true;

        } else {

            return false;

        }
    }
}
