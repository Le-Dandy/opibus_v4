package ch.opibus.opibus.webAPI.controller;

import ch.opibus.opibus.webAPI.model.WebPageError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class WebAPIErrorController {

    @RequestMapping("/APIerror")
    private String initializeErrorPage(Model model, WebPageError error) {

        model.addAttribute("page", error);

        return "error_page";
    }
}
