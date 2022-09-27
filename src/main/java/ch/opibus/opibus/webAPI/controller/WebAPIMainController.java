package ch.opibus.opibus.webAPI.controller;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.webAPI.service.template.WebTemplatePageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class WebAPIMainController {

    private final WebTemplatePageService builder;
    private static String activeTab ="main";



    @RequestMapping("/admin/main")
    private String initializeAdminMainPage(Model model, Principal principal) {

        try{

            model.addAttribute("PageDefaultBuilder", builder.get(principal, activeTab));

            return "main_page";

        } catch (DBError error) {

            model.addAttribute("error", error);

            return "error_page";

        }


    }

    @RequestMapping("/user/main")
    private String initializeUserMainPage(Model model, Principal principal) {

        try{

            model.addAttribute("PageDefaultBuilder", builder.get(principal, activeTab));

            return "main_page";

        } catch (DBError error) {

            model.addAttribute("error", error);

            return "error_page";

        }
    }

}
