package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.partner.crud.PartnerSettingsRep;
import ch.opibus.opibus.partner.dao.PartnerSettings;
import ch.opibus.opibus.partner.dao.Partner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PartnerSettingsService {

    private final PartnerSettingsRep dB;

    public PartnerSettings get(Partner partner) {

        try{

            return dB.findByPartner(partner).get();

        } catch (Exception e) {

        }

        return new PartnerSettings();

    }

    public void create(PartnerSettings partnerSettings) throws Error {

        try{

            save(partnerSettings);

        } catch (Exception e) {

            throw new Error(partnerSettings, partnerSettings.getId());

        }


    }

    public void save(PartnerSettings partnerSettings) throws Error  {

        try{

            dB.save(partnerSettings);

        } catch (Exception e) {

            throw new Error(partnerSettings, partnerSettings.getId());

        }
    }
}
