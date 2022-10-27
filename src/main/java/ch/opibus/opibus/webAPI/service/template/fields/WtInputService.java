package ch.opibus.opibus.webAPI.service.template.fields;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.translation.service.TranslationService;
import ch.opibus.opibus.webAPI.model.template.fields.WtInput;
import ch.opibus.opibus.webAPI.model.template.objects.WtNavBarTab;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WtInputService {

    private final TranslationService translationService;
    private final String type = WtInput.class.getSimpleName();

    public WtInput get(Object object, String field, String language) throws TranslationError {

        try {

            Translation translation = translationService.get(
                    object.getClass().getSimpleName(),
                    field,
                    type,
                    language);

            return new WtInput(
                    translation.getText(),
                    null,
                    translation.getPlaceholder(),
                    translation.getDescription()

            );

        } catch (TranslationError error) {

            throw error;

        }

    }

    public void save(Object object, String field, String language, String text, String description, String placeholder) throws DBError {

        try{

            translationService.save(object, field, type, language, text, description, placeholder, null);

        } catch (DBError error){

            throw error;

        }
    }


    public WtInput setValue(WtInput webInput, String value) {

        webInput.setValue(value);

        return webInput;
    }

    public void createTestdata(Object object, String method, String fieldText, String description, String placeholder) {

        try{

            save(object, method, "EN", fieldText, description, placeholder);
            save(object, method, "DE", fieldText, description, placeholder);

        } catch(DBError e){

        }

    }
}
