package ch.opibus.opibus.testData.service;


import ch.opibus.opibus.webAPI.model.template.fields.WtDropDown;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavBarTab;
import ch.opibus.opibus.webAPI.service.template.fields.WtLinkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestNavBar {

    private final WtLinkService link;

    private final String admin = "admin";
    private final String user = "user";


    public void create() {

        createTab1();
        createTab2();

    }

    private void createTab1() {



        String urlSuffix = "main";

        setupTab(admin, urlSuffix );
        setupTab(user, urlSuffix );

    }

    private void createTab2() {



        String urlSuffix = "tab2";

        setupTab(admin, urlSuffix );
        setupTab(user, urlSuffix );

    }


    private void setupTab(String prefix, String urlSuffix) {

        WtNavBarTab tab = new WtNavBarTab();

        setupLink(
                tab,
                prefix + "_" + tab.getTitle(true),
                "Title1",
                null,
                null,
                "/" + prefix + "/" + urlSuffix);

        setupDropDown1(
                tab,
                prefix + "_" + tab.getDropDown(true),
                prefix
        );

        setupDropDown2(
                tab,
                prefix + "_" + tab.getDropDown(true),
                prefix
        );
    }

    private void setupDropDown1(WtNavBarTab tab, String fieldNamePrefix, String urlPrefix) {

        String tabNo = "_1_";

        setupDropDown(tab,fieldNamePrefix, tabNo, "1", urlPrefix);
        setupDropDown(tab,fieldNamePrefix, tabNo, "2", urlPrefix);
        setupDropDown(tab,fieldNamePrefix, tabNo, "3", urlPrefix);


    }
    private void setupDropDown2(WtNavBarTab tab, String fieldNamePrefix, String urlPrefix) {

        String tabNo = "_2_";

        setupDropDown(tab,fieldNamePrefix, tabNo, "1", urlPrefix);
        setupDropDown(tab,fieldNamePrefix, tabNo, "2", urlPrefix);
        setupDropDown(tab,fieldNamePrefix, tabNo, "3", urlPrefix);


    }

    private void setupDropDown(WtNavBarTab tab, String fieldNamePrefix, String tabNo, String row, String urlPrefix) {

        setupLink(
                tab,
                fieldNamePrefix + tabNo + row,
                "Link " +row,
                null,
                null,
                "/" + urlPrefix + "/main/link" + row);
    }

    private void setupLink(Object object, String method, String fieldText, String description, String placeholder, String url) {

        link.createTestdata(
                object,
                method,
                WtDropDown.class.getSimpleName(),
                fieldText,
                description,
                url
        );

    }


}
