package ch.opibus.opibus.translation.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.translation.crud.TranslationRep;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.translation.dao.TranslationField;
import ch.opibus.opibus.translation.dao.TranslationObject;
import ch.opibus.opibus.translation.dao.TranslationType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationService {

    private final TranslationObjectService objectService;
    private final TranslationFieldService fieldService;
    private final TranslationTypeService typeService;
    private final TranslationRep dB;

    public Translation get(String object, String field,  String type, String language) throws TranslationError {

        if(dB.findByObject_ObjectNameAndField_FieldNameAndType_TypeNameAndLanguageKey(object, field, type, language).isPresent()){
            return dB.findByObject_ObjectNameAndField_FieldNameAndType_TypeNameAndLanguageKey(object, field, type, language).get();
        } else {
            throw new TranslationError(object, type, language);
        }

    }

    public void save(Object object, String field, String type, String language, String text, String description, String placeholder) throws DBError {

        try {

            TranslationObject translationObject = objectService.save(object.getClass().getSimpleName());
            TranslationField translationField = fieldService.save(translationObject, field);
            TranslationType translationType = typeService.save(type);

            Translation translation = new Translation();

            try {

                translation = get(
                        translationObject.getClass().getSimpleName(),
                        field,
                        translationType.getClass().getSimpleName(),
                        language);

            } catch (TranslationError getError) {

                translation = new Translation(
                        translationObject,
                        translationField,
                        translationType,
                        language,
                        text,
                        description,
                        placeholder

                );

            }

            save(translation);

        } catch (DBError error){

            throw error;

        }

    }

    private void save(Translation translation) throws DBError {

        try{

            dB.save(translation);

        } catch (Exception e){

            throw new DBError(translation.getClass().getName());

        }
    }

}
