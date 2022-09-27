package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.partner.crud.PartnerSettingsRep;
import ch.opibus.opibus.partner.dao.PartnerSettings;
import ch.opibus.opibus.partner.dao.Partner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static ch.opibus.opibus.partner.dao.ColorSettings.DARK;

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

    public void delete(PartnerSettings settings) throws DBError {

        if(settings.getId() < 0){

            try {

                dB.delete(settings);

            } catch(Exception e) {

                throw new DBError(settings, settings.getId());

            }

        }
    }

    public PartnerSettings create() {

        PartnerSettings partnerSettings = new PartnerSettings();
        partnerSettings.setColor(DARK);

        return partnerSettings;
    }
}
