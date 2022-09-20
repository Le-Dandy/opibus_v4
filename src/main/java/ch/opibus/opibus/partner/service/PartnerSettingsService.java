package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.DBError;
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

    public PartnerSettings get(Partner partner) throws DBError {

        try{

            return dB.findByPartner(partner).get();

        } catch (Exception e) {

            throw  new DBError(partner, partner.getId());

        }

    }

    public void create(PartnerSettings partnerSettings) throws DBError {

        try{

            save(partnerSettings);

        } catch (Exception e) {

            throw new DBError(partnerSettings, partnerSettings.getId());

        }


    }

    public void save(PartnerSettings partnerSettings) throws DBError  {

        try{

            dB.save(partnerSettings);

        } catch (Exception e) {

                throw new DBError(partnerSettings, partnerSettings.getId());


        }
    }
}
