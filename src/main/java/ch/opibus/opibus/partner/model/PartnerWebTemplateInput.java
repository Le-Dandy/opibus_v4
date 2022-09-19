package ch.opibus.opibus.partner.model;

import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.webAPI.model.template.fields.WebTemplateInput;
import ch.opibus.opibus.webAPI.model.template.fields.WebTemplateText;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerWebTemplateInput {

    private Partner partner;

    private AppUser appUser;

    private WebTemplateText appUserTitle;

    private WebTemplateInput userName;

    private WebTemplateInput password;

    private WebTemplateText partnerHeaderTitle;

    private WebTemplateInput firstName;

    private WebTemplateInput middleName;

    private WebTemplateInput lastName;

    private WebTemplateInput email;

}
