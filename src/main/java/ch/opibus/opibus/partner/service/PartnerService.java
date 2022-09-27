package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.partner.crud.PartnerRep;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.dao.PartnerSettings;
import ch.opibus.opibus.partner.dao.UserHead;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PartnerService {

    private final AppUserService appUserService;
    private final UserHeadService userHeadService;
    private final PartnerSettingsService partnerSettingsService;
    private final PartnerRep dB;

    public void create(Partner partner, AppUser appUser) throws DBError {

            if(partner.getSettings() == null){

                partner.setSettings(partnerSettingsService.create());

            }

            try {

                appUser.setId(appUserService.get(appUser).getId());
                partner.setId(get(partner).getId());

            } catch (DBError error) {

                completeUser(appUser, partner);

            }

            appUser.setEmail(setEmail(partner, appUser));
            partner.getUserHead().setEmail(setEmail(partner, appUser));

            try {

                save(partner, appUser);

            } catch (DBError error) {

                delete(partner);

                throw error;
            }

    }

    private Partner completeUser(AppUser appUser, Partner partner) {

        UserHead userHead = partner.getUserHead();

        if(userHead == null){

            userHead.setAppUserId(appUser.getId());
            userHead.setEmail(appUser.getEmail());

            partner.setUserHead(userHead);

        } else {

            if(userHead.getEmail() == null){

                userHead.setFirstName(appUser.getUsername());

            }

            if(userHead.getAppUserId() < 1){

                userHead.setAppUserId(appUser.getId());

            }

        }

        return partner;
    }

    private void delete(Partner partner) throws DBError {

        if(partner.getId() < 0){

            try {
                appUserService.delete(partner.getAppUserId());
                dB.delete(partner);
                partnerSettingsService.delete(partner.getSettings());
                userHeadService.delete(partner.getUserHead());

            } catch(DBError error) {

                throw setError(error, partner);

            }

        }


    }

    private Partner get(Partner partner) throws DBError {

        try{

            return dB.findById(partner.getId()).get();

        } catch (Exception e){

            throw new DBError(partner, partner.getId());

        }
    }

    public Partner get (AppUser appUser) throws DBError {

            try {

                return dB.findByAppUserId(appUser.getId()).get();

            } catch (Exception e) {

                throw new DBError(appUser, appUser.getId());
            }
        }

    public void save(Partner partner, AppUser appUser) throws DBError {

        try{

            appUserService.save(appUser);
            partner.setAppUserId(appUser.getId());

            save(partner);

        } catch (DBError error){

            try{

                delete(partner);

            } catch (DBError deleteError){

                throw  deleteError;

            }

            throw error;


        }
    }

    public void save(Partner partner) throws DBError{

        try{

            partner = save(partner, partner.getSettings());
            partner = save(partner, partner.getUserHead());

            dB.save(partner);

        } catch(DBError saveError){

            try{

                delete(partner);

            } catch (DBError deleteError) {

                throw setError(deleteError, partner);

            }

            throw setError(saveError, partner);

        }

    }

    private Partner save(Partner partner, PartnerSettings partnerSettings) throws DBError {

        try{

            partnerSettingsService.save(partnerSettings);
            partner.setSettings(partnerSettings);

            return partner;

        } catch (DBError error) {
            throw error;
        }

    }

    private Partner save(Partner partner, UserHead userHead) throws DBError {

        try{

            userHeadService.save(userHead);
            partner.setUserHead(userHead);

            return partner;

        } catch (DBError error) {

            throw error;

        }

    }

    private String setEmail(Partner partner, AppUser appUser) {

        if(partner.getUserHead().getEmail() == null){

            return appUser.getEmail();

        } else {

            return partner.getUserHead().getEmail();

        }
    }

    private DBError setError(DBError error, Partner partner) {

        if(error == null) {

            return new DBError(partner, partner.getId());

        } else {

            return error;

        }
    }

    public Partner getByEmail(String email) throws DBError {

        try{

            return getByUserHead(userHeadService.getByEmail(email));

        } catch (DBError error){

            throw error;

        }
    }

    private Partner getByUserHead(UserHead userHead) throws DBError {

        try{

            return dB.findByUserHead(userHead).get();

        } catch (Exception e){

            throw new DBError(userHead, userHead.getId());

        }
    }
}
