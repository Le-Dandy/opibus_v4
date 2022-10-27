package ch.opibus.opibus.webAPI.service.template.fields;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.translation.service.TranslationService;
import ch.opibus.opibus.webAPI.model.template.fields.WtText;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WtTextService {

    private final TranslationService translationService;
    private static String type = WtText.class.getSimpleName();

    public WtText get(Object object, String field, String language) throws TranslationError {

        try {

            Translation translation = translationService.get(
                    object.getClass().getSimpleName(),
                    field,
                    type,
                    language);

            return new WtText(
                    translation.getText(),
                    translation.getDescription()
            );

        } catch (TranslationError error) {

            throw error;

        }

    }

    public void save(Object object, String field, String language, String text, String description) throws DBError {

        try {

            translationService.save(object, field, type, language, text, description, null);

        } catch (DBError error) {

            throw error;

        }
    }
}
