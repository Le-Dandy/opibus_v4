package ch.opibus.opibus.webAPI.service;

import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.webAPI.model.WebPageDefault;
import ch.opibus.opibus.webAPI.model.WebPageError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebAPIErrorService {

    public WebPageDefault getTranslationError(TranslationError e) {

        return new WebPageDefault(
                "redirect:/APIerror",
                new WebPageError("Translation Error","Not entry " + e.getMessage()),
                null,
                null,
                null,
                null
        );
    }
}
