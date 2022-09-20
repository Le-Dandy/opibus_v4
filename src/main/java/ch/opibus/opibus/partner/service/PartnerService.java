package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.partner.crud.PartnerRep;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.dao.UserHead;
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

        if(!appUserService.checkIfExists(appUser)){

            appUser = appUserService.create(appUser);

        }

        if(!initialPartnerCheck(partner, appUser)){

            partner = completeUser(appUser, partner);

            try {

                create(appUser.getId(), partner);

            } catch(DBError error){

                throw new DBError(partner, partner.getId());

            }


        } else {

            partner = changePartner(appUser, partner);

        }

    }

    private Partner changePartner(AppUser appUser, Partner partner) throws DBError {

        if(appUser.getEmail() != partner.getUserHead().getEmail()){

            appUser.setEmail(partner.getUserHead().getEmail());

        }

        try {

            appUserService.save(appUser);
            dB.save(partner);

        } catch(DBError error){

            throw new DBError(partner, partner.getId());

        }

        return partner;
    }

    private void create(long id, Partner partner) throws DBError {

        partner.setAppUserId(id);

        try{

            partnerSettingsService.create(partner.getSettings());

            userHeadService.create(partner.getUserHead());

            try{

                save(partner);

            } catch(DBError error){

                throw error;

            }

        } catch(DBError error) {

            if(error == null){

                error = new DBError(partner, partner.getId());

            }

            throw error;

        }
    }

    public void save(Partner partner) throws DBError{

        try{
            dB.save(partner);
            partnerSettingsService.save(partner.getSettings());
            userHeadService.save(partner.getUserHead());

        } catch(DBError error){

            if(error == null) {

                throw new DBError(partner, partner.getId());

            } else {

                throw error;

            }

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

    private boolean initialPartnerCheck(Partner partner, AppUser appUser) {

        boolean exists = false;

        /*
        if(appUserService.checkIfExists(appUser)) {

            exists = true;

        } else if(userHeadService.checkIfExists(partner.getUserHead())){

            exists = true;

        }

         */

        return exists;

    }

    public Partner get(AppUser appUser) throws DBError {

        try{

            return dB.findByAppUserId(appUser.getId()).get();

        } catch (Exception e) {

            throw new DBError(appUser, appUser.getId());
        }
    }
}
