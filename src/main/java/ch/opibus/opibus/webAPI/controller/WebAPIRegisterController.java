package ch.opibus.opibus.webAPI.controller;

import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.webAPI.model.WebPageRegister;
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
    private String initializeRegisterPage(Model model, Partner partner, AppUser appUser) {

        try{

            model.addAttribute("page", webService.get(appUser, partner, "EN"));
            return "registration_page";

        } catch (TranslationError error) {

            return "redirect:/error";

        }




    }

    @PostMapping("/register/submit")
    private String saveRegistration(Model model, Partner partner) {


        return "registration_page";
    }

}
