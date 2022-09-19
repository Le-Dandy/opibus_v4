package ch.opibus.opibus.webAPI.model.template;

import ch.opibus.opibus.partner.dao.PartnerSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebTemplatePage {

    private WebTemplateNavigationBar navBar;

    private PartnerSettings settings;


}
