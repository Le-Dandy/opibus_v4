package ch.opibus.opibus.webAPI.service.template.objects;

import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.webAPI.model.template.objects.WtPartnerInput;
import ch.opibus.opibus.webAPI.service.template.fields.WtInputService;
import ch.opibus.opibus.webAPI.service.template.fields.WtTextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WtPartnerInputService {

    private final WtInputService input;
    private final WtTextService text;

    public WtPartnerInput get(String language) throws TranslationError{
        Partner partner = new Partner();
        AppUser appUser = new AppUser();

        try{

            return new WtPartnerInput(
                    partner,
                    appUser,
                    text.get(appUser, "AppUserInputTitle", language),
                    input.get(appUser, appUser.getUsername(true) , language),
                    input.get(appUser, appUser.getPassword(true) , language),
                    text.get(partner, "PartnerInputTitle", language),
                    input.get(partner, partner.getFirstName(true) , language),
                    input.get(partner, partner.getMiddleName(true) , language),
                    input.get(partner, partner.getLastName(true) , language),
                    input.get(partner, partner.getEmail(true) , language)


            );

        } catch (TranslationError error) {
            throw error;
        }

    }
    public WtPartnerInput get(AppUser appUser, Partner partner, String language) throws TranslationError{

        try {

            WtPartnerInput partnerInput = get(language);

            if(appUser.getEmail() != null){

                partnerInput = mapAppUserValues(partnerInput, appUser);

            }

            if(partner.getUserHead() != null){


                partnerInput = mapPartnerValues(partnerInput, partner);

            }

            return partnerInput;

        } catch (TranslationError error) {

            throw error;

        }
    }

    private WtPartnerInput mapAppUserValues(WtPartnerInput partnerInput, AppUser appUser) {

        partnerInput.setAppUser(appUser);

        partnerInput.setUserName(input.setValue(partnerInput.getUserName(), appUser.getUsername()));
        partnerInput.setPassword(input.setValue(partnerInput.getPassword(), appUser.getPassword()));

        return partnerInput;
    }

    private WtPartnerInput mapPartnerValues(WtPartnerInput partnerInput, Partner partner) {

        partnerInput.setPartner(partner);

        partnerInput.setFirstName(input.setValue(partnerInput.getFirstName(), partner.getUserHead().getFirstName()));
        partnerInput.setMiddleName(input.setValue(partnerInput.getMiddleName(), partner.getUserHead().getMiddleName()));
        partnerInput.setLastName(input.setValue(partnerInput.getLastName(), partner.getUserHead().getLastName()));
        partnerInput.setEmail(input.setValue(partnerInput.getEmail(), partner.getUserHead().getEmail()));

        return partnerInput;

    }









}
