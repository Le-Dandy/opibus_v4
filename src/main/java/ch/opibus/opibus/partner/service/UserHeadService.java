package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.partner.crud.UserHeadRep;
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

    public UserHead create(UserHead userHead) throws Error{

        try {

            save(userHead);

        } catch (Exception e) {

            throw new Error(userHead, userHead.getId());

        }

        return userHead;

    }

    public void delete(UserHead userHead) throws Error {

        try {

            dB.delete(userHead);

        } catch (Exception e) {

            throw new Error(userHead, userHead.getId());

        }

    }

    public void save(UserHead userHead) throws Error{

        try {

            dB.save(userHead);

        } catch (Exception e) {

            throw new Error(userHead, userHead.getId());

        }

    }
}
