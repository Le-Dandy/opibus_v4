package ch.opibus.opibus.translation.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.translation.crud.TranslationFieldRep;
import ch.opibus.opibus.translation.dao.TranslationField;
import ch.opibus.opibus.translation.dao.TranslationObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationFieldService {

    private final TranslationFieldRep dB;

    public TranslationField get(TranslationObject translationObject, String fieldName) throws DBError {

        try {

            return dB.findByObjectAndFieldName(translationObject, fieldName).get();

        } catch (Exception e){

            throw new DBError(fieldName);

        }

    }

    public TranslationField save(TranslationObject translationObject, String fieldName) throws DBError {

        TranslationField translationField = new TranslationField();

        try{

            translationField = get(translationObject, fieldName);

        } catch (DBError e){

            try{

                translationField.setObject(translationObject);
                translationField.setFieldName(fieldName);

                save(translationField);

            } catch (DBError error){

                throw error;

            }

        }

        return translationField;
    }

    public void save(TranslationField translationField) throws DBError {

        try{

            dB.save(translationField);

        } catch (Exception e){

            throw new DBError(translationField.getClass().getName());

        }
    }

}
