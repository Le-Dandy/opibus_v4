package ch.opibus.opibus.webAPI.controller;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.service.AppUserService;
import ch.opibus.opibus.webAPI.service.WebAPIMainService;
import ch.opibus.opibus.webAPI.service.template.WebTemplatePageService;
import ch.opibus.opibus.webAPI.service.template.objects.WtNavBarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class WebAPIMainController {

    private final WtNavBarService navBar;
    private final WebTemplatePageService builder;
    private final WebAPIMainService mainWindow;

    private final AppUserService user;

    private static String activeTab ="main";





    @RequestMapping("/admin/main")
    private String initializeAdminMainPage(Model model, Principal principal) {



        try{

            AppUser thisUser = user.get(principal.getName());

            model.addAttribute("navBar", navBar.get(thisUser, activeTab));

            model.addAttribute("mainWindow", mainWindow.getAdmin(thisUser));

            return "main_page";

        } catch (DBError error) {

            model.addAttribute("error", error);

            return "error_page";

        }


    }

    @RequestMapping("/user/main")
    private String initializeUserMainPage(Model model, Principal principal) {

        try{

            AppUser thisUser = user.get(principal.getName());

            model.addAttribute("navBar", navBar.get(thisUser,"main" , "EN");

            model.addAttribute("mainWindow", mainWindow.getUser(thisUser));

            return "main_page";

        } catch (DBError error) {

            model.addAttribute("error", error);

            return "error_page";

        }
    }

}
