package ch.opibus.opibus.webAPI.controller;

import ch.opibus.opibus.security.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class WebAPILoginController {

    private final SecurityService securityService;


    @RequestMapping("/login")
    private String initializeLoginPage(Model model, Principal principal) {

        model.addAttribute("appUser", securityService.getAppUser(principal));

        return "login_page";
    }

    @RequestMapping("/logout")
    private String initializeLoginPage() {

        return "redirect:login";
    }

    @RequestMapping("/loginSuccess")
    private String redirectLogin(Principal principal){

        try{

            String prefix = securityService.getPrefix(principal);

            return "redirect:" + prefix + "/main";

        } catch (Exception e) {

            return "redirect:login";

        }

    }

}
