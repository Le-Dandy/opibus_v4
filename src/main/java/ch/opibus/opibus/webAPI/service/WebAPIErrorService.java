package ch.opibus.opibus.webAPI.service;

import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.webAPI.model.WebPage;
import ch.opibus.opibus.webAPI.model.template.window.WwError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebAPIErrorService {

    public WebPage getTranslationError(TranslationError e) {

        return new WebPage(
                "redirect:/APIerror",
                new WwError("Translation Error","Not entry " + e.getMessage()),
                null,
                null,
                null,
                null
        );
    }
}
