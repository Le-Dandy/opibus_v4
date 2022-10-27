package ch.opibus.opibus.webAPI.service;

import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.webAPI.model.WebPage;
import ch.opibus.opibus.webAPI.model.template.objects.WtPartnerSettings;
import ch.opibus.opibus.webAPI.service.template.objects.WtNavBarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class WebPageService {

    private final WtNavBarService navBarService;

    private final WtPartnerSettings partnerSettingService;


    public WebPage get(String language,  String activeTab, Partner partner) {

        WebPage page = new WebPage();

        page.setNavBar(navBarService.get(language, activeTab, partner));

        return page;



    }

    public WebPage get(String language, String activeTab,  Partner partner, Object mainWindow) {

        WebPage page = get(language, activeTab, partner);

        page.setMainWindow(mainWindow);

        return page;

    }



}
