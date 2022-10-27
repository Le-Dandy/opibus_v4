package ch.opibus.opibus.webAPI.model;

import ch.opibus.opibus.partner.dao.PartnerSettings;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavigationBar;
import ch.opibus.opibus.webAPI.model.template.objects.WtPartnerSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebPage {

    private WtNavigationBar navBar;

    private Object mainWindow;

    private Object Window2;

    private Object Window3;

    private Object Window4;


    private Object popUp;


    private String string1;

    private String string2;

    private String string3;

    private String string4;

    private String string5;

    private boolean error;
}
