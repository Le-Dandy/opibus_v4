package ch.opibus.opibus.webAPI.model;

import ch.opibus.opibus.webAPI.model.template.WebTemplateNavigationBar;
import ch.opibus.opibus.partner.model.PartnerWebTemplateInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebPageRegister {

    private WebTemplateNavigationBar navBar;

    private PartnerWebTemplateInput partner;
}
