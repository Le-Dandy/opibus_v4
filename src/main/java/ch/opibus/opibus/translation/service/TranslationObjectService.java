package ch.opibus.opibus.translation.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.translation.crud.TranslationObjectRep;
import ch.opibus.opibus.translation.dao.TranslationObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationObjectService {

    private final TranslationObjectRep dB;

    public TranslationObject get(String objectName) throws DBError {

        try {

            return dB.findByObjectName(objectName).get();

        } catch (Exception e){

            throw new DBError(objectName);

        }
    }

    public TranslationObject save(String objectName) throws DBError {

        TranslationObject translationObject = new TranslationObject();

        try{

            translationObject = get(objectName);

        } catch (DBError e){

            try{


                translationObject.setObjectName(objectName);

                save(translationObject);

            } catch (DBError error){

                throw error;

            }

        }

        return translationObject;
    }

    public void save(TranslationObject translationObject) throws DBError {

        try{

            dB.save(translationObject);

        } catch (Exception e){

            throw new DBError(translationObject.getClass().getName());

        }
    }

}
