package ch.opibus.opibus.webAPI.controller;

import ch.opibus.opibus.webAPI.model.WebPage;
import ch.opibus.opibus.webAPI.model.template.window.WwRegister;
import ch.opibus.opibus.webAPI.service.WebAPIRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
public class WebAPIRegisterController {

    private final WebAPIRegisterService webService;
    private static String activeTab ="register";



    @RequestMapping("/register")
    private String initializeRegisterPage(Model model, WwRegister partner) {

        WebPage pageDefault = webService.checkForRegistrationInput(partner.getPartner(), "EN");

        model.addAttribute("page", pageDefault.getPage());
        model.addAttribute("emailError", pageDefault.getObject1());

        return pageDefault.getReturnString();

        /*
        try{

            model.addAttribute("page", webService.get(appUser, partner, "EN"));
            return "registration_page";

        } catch (TranslationError error) {

            return "redirect:/error";

        }

         */




    }

    @PostMapping("/register/submit")
    private String saveRegistration(Model model, WwRegister partner) {

        WebPage pageDefault = webService.checkSubmitRegistration(partner.getPartner(), "EN");

        model.addAttribute("page", pageDefault.getPage());
        model.addAttribute("emailError", pageDefault.getObject1());
        model.addAttribute("inputError", pageDefault.getObject2());

        return pageDefault.getReturnString();

        /*
        if (webService.emailCheck() == false) {

            try{
                webService.save(partner);

                return "validation_page";

            } catch (DBError error) {

                if(error.getType() == 2 ){

                }
                    model.addAttribute("page", webService.get(partner.getPartner().getAppUser(), partner.getPartner().getPartner(), "EN"));
                    return "redirect:/register";

                } else {

                    return "error";

                }

        } else {

            model.addAttribute("page", webService.get(partner.getPartner().getAppUser(), partner.getPartner().getPartner(), "EN"));
            return "redirect:/register";

        }

         */


    }

}
