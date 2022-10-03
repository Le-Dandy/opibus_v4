package ch.opibus.opibus.Interfaces.service;

import ch.opibus.opibus.Interfaces.model.Email;
import ch.opibus.opibus.partner.dao.UserHead;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class EmailService {

    public void sendValidation(UserHead userHead, String validationKey) {

        List<String> reciever = setEmailAddress(null, userHead.getEmail());

        Email email = new Email(
                reciever,
                null,
                null,
                getValidationEmailHeader(),
                getValidationEmailBody(userHead, validationKey)
        );



        /* TODO: send validation email */

    }

    private String getValidationEmailBody(UserHead userHead, String validationKey) {

        /*TODO: translate / set up email validation Body*/

        return "Dear " + userHead.getFirstName() + " " + userHead.getLastName() + "// pleas confirm your email with tho following confirmation token: " +
                validationKey + ". Regards your Opibus team";
    }

    private String getValidationEmailHeader() {

        /*TODO: translate validation header*/

        return "validate your email address";
    }

    private List<String> setEmailAddress(List<String> emailAddresses, String emailAddress) throws Error {

        if(validateEmailAddress(emailAddress) == true){

            if(emailAddresses == null) {

                emailAddresses = new ArrayList<>();

            }

            emailAddresses.add(emailAddress);

            return emailAddresses;

        } else {

            throw new Error("wrong email address " + emailAddress);

        }
    }

    private boolean validateEmailAddress(String emailAddress) {

        /*TODO: setup email check */

        return true;
    }
}
