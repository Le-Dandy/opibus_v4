package ch.opibus.opibus.webAPI.service.template.fields;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.translation.service.TranslationFieldService;
import ch.opibus.opibus.translation.service.TranslationService;
import ch.opibus.opibus.webAPI.model.template.fields.WtLink;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WtLinkService {

    private final TranslationService translationService;

    private final TranslationFieldService fieldService;
    private final String thisType = WtLink.class.getSimpleName();

    public WtLink get(Object object, String field, String language) throws TranslationError {

        try {

            return get(object, field, thisType, language);

        } catch (TranslationError error) {

            throw error;

        }

    }

    public WtLink get(Object object, String field, String type, String language) throws TranslationError {

        try {

            Translation translation = translationService.get(
                    object.getClass().getSimpleName(),
                    field,
                    type,
                    language);

            return new WtLink(
                    getURL(object, field),
                    translation.getText(),
                    translation.getDescription()
            );

        } catch (TranslationError error) {

            throw error;

        }

    }

    private String getURL(Object object, String field) throws TranslationError {

        try {

            return fieldService.get(object.getClass().getSimpleName(), field).getUrl();

        } catch (DBError error) {

            throw new TranslationError(object.getClass().getSimpleName(),"URL not defined","EN");

        }

    }

    public void save(Object object, String field, String language, String text, String description) throws DBError {

        try{

            save(object, field, language, text, thisType, description, null);

        } catch (DBError error){

            throw error;

        }
    }

    public void save(Object object, String field, String type, String language, String text, String description, String url) throws DBError {

        try{

            translationService.save(object, field, type, language, text, description, null, url);

        } catch (DBError error){

            throw error;

        }
    }

    public void createTestdata(Object object, String field, String type, String fieldText, String description, String url) {

        try {

            save(object, field, type, "EN", fieldText, description, url);
            save(object, field, type, "DE", fieldText, description, url);

        } catch (DBError e) {

        }
    }
}
