package ch.opibus.opibus.webAPI.model.template.objects;

import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.dao.PartnerSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WtPartnerSettings {

    private String userName;

    public WtPartnerSettings get(String language, Partner partner) {

        return new WtPartnerSettings(getUser(partner));
    }

    private WtPartnerSettings getUser(Partner partner) {
        return new WtPartnerSettings(
                /*
                partner.getUserHead().getFirstName() + " " + partner.getUserHead().getLastName()

                 */

                "Hans Werner"
        );
    }
}
