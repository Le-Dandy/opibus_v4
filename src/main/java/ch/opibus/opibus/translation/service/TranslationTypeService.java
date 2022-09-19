package ch.opibus.opibus.translation.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.translation.crud.TranslationTypeRep;
import ch.opibus.opibus.translation.dao.TranslationType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationTypeService {

    private final TranslationTypeRep dB;

    public TranslationType get(String typeName) throws DBError {

        try {

            return dB.findByTypeName(typeName).get();

        } catch (Exception e) {

            throw new DBError(typeName);

        }


    }

    public TranslationType save(String typeName) throws DBError {

        TranslationType translationType = new TranslationType();

        try{

            translationType = get(typeName);

        } catch (DBError e){

            try{

                translationType.setTypeName(typeName);

                save(translationType);

            } catch (DBError error){

                throw error;

            }

        }

        return translationType;

    }

    private void save(TranslationType translationType) throws DBError {

        try{

            dB.save(translationType);

        } catch (Exception e){

            throw new DBError(translationType.getClass().getSimpleName());

        }
    }


}
