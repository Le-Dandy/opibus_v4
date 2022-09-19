package ch.opibus.opibus.webAPI.model.template;

import ch.opibus.opibus.webAPI.model.template.fields.WebTemplateLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebTemplateNavigationBar {

    private boolean activeMain;
    private WebTemplateLink navbarBrand;

    private boolean active1;
    private WebTemplateLink title1;
    private List<WebTemplateLink> dropDown1;

    private boolean active2;
    private WebTemplateLink title2;
    private List<WebTemplateLink> dropDown2;

    private boolean active3;
    private WebTemplateLink title3;
    private List<WebTemplateLink> dropDown3;

    private boolean active4;
    private WebTemplateLink title4;
    private List<WebTemplateLink> dropDown4;

    private boolean active5;
    private WebTemplateLink title5;
    private List<WebTemplateLink> dropDown5;

    private boolean active6;
    private WebTemplateLink title6;
    private List<WebTemplateLink> dropDown6;

    private boolean active7;
    private WebTemplateLink title7;
    private List<WebTemplateLink> dropDown7;

    private boolean active8;
    private WebTemplateLink title8;
    private List<WebTemplateLink> dropDown8;

    private boolean active9;
    private WebTemplateLink title9;
    private List<WebTemplateLink> dropDown9;

    private boolean active10;
    private WebTemplateLink title10;
    private List<WebTemplateLink> dropDown10;

    private String search;

    private WebTemplatePartnerSettings user;

    private WebTemplateLink logoff;
}
