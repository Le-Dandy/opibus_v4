package ch.opibus.opibus.webAPI.service.template.fields;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.translation.service.TranslationService;
import ch.opibus.opibus.webAPI.model.template.fields.WebTemplateInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
@AllArgsConstructor
public class WebTemplateInputService {

    private final TranslationService translationService;
    private final String type = WebTemplateInput.class.getSimpleName();

    public WebTemplateInput get(Object object, String field, String language) throws TranslationError {

        try {

            Translation translation = translationService.get(
                    object.getClass().getSimpleName(),
                    field,
                    type,
                    language);

            return new WebTemplateInput(
                    translation.getText(),
                    translation.getPlaceholder(),
                    translation.getDescription()

            );

        } catch (TranslationError error) {

            throw error;

        }

    }

    public void save(Object object, String field, String language, String text, String description, String placeholder) throws DBError {

        try{

            translationService.save(object, field, type, language, text, description, placeholder);

        } catch (DBError error){

            throw error;

        }
    }


}
