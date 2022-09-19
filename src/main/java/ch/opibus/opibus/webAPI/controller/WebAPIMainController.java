package ch.opibus.opibus.webAPI.controller;

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

        model.addAttribute("PageDefaultBuilder", builder.get(principal, activeTab));

        return "main_page";
    }

    @RequestMapping("/user/main")
    private String initializeUserMainPage(Model model, Principal principal) {

        model.addAttribute("PageDefaultBuilder", builder.get(principal, activeTab));

        return "main_page";
    }

}
