package ch.opibus.opibus.security.service;

import ch.opibus.opibus.Interfaces.service.EmailService;
import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.UserHead;
import ch.opibus.opibus.security.crud.ValidationTokenRep;
import ch.opibus.opibus.security.dao.ValidationToken;
import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ValidationTokenService {

    private final EmailService emailService;

    private final ValidationTokenRep dB;

    public void initalize(AppUser appUser, UserHead userHead) {

        try {

            save(appUser);
            emailService.sendValidation(userHead);

        } catch (Error error) {

        }

    }

    private void save(AppUser appUser) throws Error {

        try{

            ValidationToken token = new ValidationToken();

            token.setValidTo(LocalDateTime.now().plusMinutes(15));
            token.setAppUserId(appUser.getId());
            token.setValidationKey(getValidationKey());

            dB.save(token);

        } catch (Exception e) {

            new Error("Validation error");

        }
    }

    private String getValidationKey() {
        /* TODO: set validation key */

        return "X1";
    }
}
