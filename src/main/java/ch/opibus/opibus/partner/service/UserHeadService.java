package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.partner.crud.UserHeadRep;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.UserHead;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserHeadService {

    private final UserHeadRep dB;

    public boolean checkIfExists(UserHead userHead) {

        boolean exists = false;

        if(dB.findById(userHead.getId()).isPresent()){

            exists = true;

        } else if(dB.findByEmail(userHead.getEmail()).isPresent()){

            exists = true;

        }

        return exists;
    }

    public UserHead create(UserHead userHead) throws DBError {

        try {

            save(userHead);

        } catch (Exception e) {

            throw new DBError(userHead, userHead.getId());

        }

        return userHead;

    }

    public void delete(UserHead userHead) throws DBError {

        if(userHead.getId() < 0){

            try {

                dB.delete(userHead);

            } catch (Exception e) {

                throw new DBError(userHead, userHead.getId());

            }

        }



    }

    public void save(UserHead userHead) throws DBError{

        try {

            dB.save(userHead);

        } catch (Exception e) {

            throw new DBError(userHead, userHead.getId());

        }

    }

    public UserHead getByEmail(String email) throws DBError{

        try {

            return dB.findByEmail(email).get();

        } catch (Exception error) {

            throw new DBError(new AppUser());

        }
    }
}
