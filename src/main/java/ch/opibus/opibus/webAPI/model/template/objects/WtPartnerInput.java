package ch.opibus.opibus.webAPI.model.template.objects;

import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.webAPI.model.template.fields.WtInput;
import ch.opibus.opibus.webAPI.model.template.fields.WtText;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WtPartnerInput {

    private Partner partner;

    private AppUser appUser;

    private WtText appUserTitle;

    private WtInput userName;

    private WtInput password;

    private WtText partnerHeaderTitle;

    private WtInput firstName;

    private WtInput middleName;

    private WtInput lastName;

    private WtInput email;

}
